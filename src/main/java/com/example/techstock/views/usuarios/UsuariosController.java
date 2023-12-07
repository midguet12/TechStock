package com.example.techstock.views.usuarios;


import com.example.techstock.dao.UsuarioDAO;
import com.example.techstock.domain.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class UsuariosController implements Initializable {
        @FXML
        private Label welcomeText;
        @FXML
        private TextField nombreUsuarioField;

        @FXML
        private PasswordField contrasenaField;
        @FXML
        private TextField nuevoNombreUsuarioField;

        @FXML
        private TextField nuevaContrasenaField;
        @FXML
        private TableView<Usuario> tablaUsuarios;

        @FXML
        private TableColumn<Usuario, String> nombreUsuarioColumn;

        @FXML
        private TableColumn<Usuario, String> contrasenaColumn;
        @FXML
        private TableColumn<Usuario, String> nombreCompletoColumn;
        @FXML
        private TableColumn<Usuario, Boolean> administradorColumn;
        @FXML
        private HBox editarCampos;

        @FXML
        private Button registrarButton;

        @FXML
        private Button editarButton;

        @FXML
        private Button eliminarButton;
        @FXML
        private Button menuPrincipalButton;
        @FXML
        private TextField nombreCompletoTextField;
        @FXML
        private ComboBox<String> administradorComboBox;
        @FXML
        private ComboBox<String> nuevoAdministradorComboBox;
        @FXML
        private TextField nuevoNombreCompletoTextField;



    @FXML
    protected void registrarUsuario() throws Exception{
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        String nombreUsuario = nombreUsuarioField.getText();
        String contrasena = contrasenaField.getText();
        String nombreCompleto = nombreCompletoTextField.getText();
        Boolean administrador = null;

        String seleccionAdministrador = administradorComboBox.getSelectionModel().getSelectedItem().toString();

        if (seleccionAdministrador.equals("Si")){
            administrador = true;
        } else if (seleccionAdministrador.equals("No")){
            administrador = false;
        }

        if (nombreUsuario.isEmpty() || contrasena.isEmpty() || nombreCompleto.isEmpty() || administrador == null) {
            mostrarAlerta("Error", "Por favor, complete todos los campos.");
            return; // Salir del método si hay campos vacíos.
        }


        Usuario usuario = new Usuario(nombreUsuario, DigestUtils.sha256Hex(contrasena), nombreCompleto, administrador);

        if (true) {
            usuarioDAO.create(usuario);
            cargarUsuariosEnTabla();
            mostrarAlerta("Registro Exitoso", "El usuario se registró con éxito.");
        } else {
            mostrarAlerta("Registro Fallido", "El usuario ya existe en la base de datos.");
        }
    }

    @FXML
    protected void editarUsuario() {
        Usuario usuarioSeleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();

        if (usuarioSeleccionado != null) {

            editarCampos.setVisible(true);
            nuevoNombreUsuarioField.setText(usuarioSeleccionado.getNombreUsuario());
            nuevoNombreCompletoTextField.setText(usuarioSeleccionado.getNombreCompleto());

            if (usuarioSeleccionado.getAdministrador()){
                nuevoAdministradorComboBox.getSelectionModel().select("Si");
            } else {
                nuevoAdministradorComboBox.getSelectionModel().select("No");
            }


        }
    }

    @FXML
    protected void guardarCambios() throws Exception{

        Usuario usuarioSeleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
        Usuario nuevoUsuario = new Usuario();

        if (usuarioSeleccionado != null) {
            String nuevoNombreUsuario = nuevoNombreUsuarioField.getText();
            String nuevaContrasena = nuevaContrasenaField.getText();
            String nuevoNombreCompleto = nuevoNombreCompletoTextField.getText();
            Boolean nuevoAdministrador = null;


            if (nuevaContrasenaField.getText().isEmpty()){
                //nuevoUsuario.setContrasena(usuarioSeleccionado.getContrasena());
                //UsuarioDAO usuarioDAO = new UsuarioDAO();
                //nuevaContrasena = usuarioDAO.read(usuarioSeleccionado.getNombreUsuario()).getContrasena();
                nuevaContrasena = usuarioSeleccionado.getContrasena();
            } else{
                nuevaContrasena = DigestUtils.sha256Hex(nuevaContrasena);
            }

            String nuevaSeleccionAdministrador = nuevoAdministradorComboBox.getSelectionModel().getSelectedItem();

            if (nuevaSeleccionAdministrador.equals("Si")){
                nuevoAdministrador = true;
            } else if (nuevaSeleccionAdministrador.equals("No")){
                nuevoAdministrador = false;
            }

            nuevoUsuario.setNombreUsuario(nuevoNombreUsuario);
            nuevoUsuario.setContrasena(nuevaContrasena);
            nuevoUsuario.setNombreCompleto(nuevoNombreCompleto);
            nuevoUsuario.setAdministrador(nuevoAdministrador);

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            try{
                usuarioDAO.update(usuarioSeleccionado.getNombreUsuario(), nuevoUsuario);
            } catch (Exception exception){
                System.out.println(exception.getMessage());
            }


            // Actualizar la tabla
            cargarUsuariosEnTabla();

            // Ocultar los campos de edición
            editarCampos.setVisible(false);
            nuevaContrasenaField.setText("");
        }
    }


    @FXML
    protected void eliminarUsuario() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = tablaUsuarios.getSelectionModel().getSelectedItem();

        if (usuario != null) {
            // Crear una alerta de confirmación
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Eliminación");
            alert.setHeaderText("¿Estás seguro de que deseas eliminar a este usuario?");
            alert.setContentText("Esta acción no se puede deshacer.");


            ButtonType resultado = alert.showAndWait().orElse(ButtonType.CANCEL);

            if (resultado == ButtonType.OK) {
                try {
                    usuarioDAO.delete(usuario.getNombreUsuario());
                    mostrarAlerta("Usuario Eliminado", "El usuario ha sido eliminado exitosamente.");
                    cargarUsuariosEnTabla();
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }
        } else {
            mostrarAlerta("Error", "Selecciona un usuario para eliminar.");
        }
    }

    @FXML
        protected void onPruebaButtonClick() {
            String message = "";
            try{
                //UsuarioDAO usuarioDAO = new UsuarioDAO();
                //Usuario usuario = new Usuario("Quacker", DigestUtils.sha256Hex("elpato"));

                //PRUEBAS PARA INSERTAR USUARIO
                /*
                if (!usuarioDAO.UsuarioExiste(usuario)) {
                    usuarioDAO.insertarUsuario(usuario);
                }
                */
                //PRUEBAS PARA ELIMINAR USUARIO
                /*
                if (usuarioDAO.UsuarioExiste(usuario)) {
                    usuarioDAO.eliminarUsuario(usuario);
                }
                */

                //usuarioDAO.insertarUsuario(usuario);

                //PRUEBAS PARA CONSULTA DE USUARIO
                /*
                for (Usuario user : usuarioDAO.consultarUsuario()) {
                    System.out.println(user.toString());
                }
                */
            } catch (Exception e){
                System.out.println(e.toString());
            }

            welcomeText.setText(message);
        }

    public void cargarUsuariosEnTabla() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> usuarios = null;
        try{
            usuarios = usuarioDAO.readAll();
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }


        // Convierte la lista de usuarios en un ObservableList para usarlo en la tabla
        ObservableList<Usuario> observableUsuarios = FXCollections.observableArrayList(usuarios);

        // Asigna los datos de la lista a la tabla
        tablaUsuarios.setItems(observableUsuarios);
    }
    private void mostrarAlerta(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarUsuariosEnTabla();
        tablaUsuarios.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void menuPrincipalAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) menuPrincipalButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/src/main/resources/com/example/techstock/MenuPrincipal.fxml"));
        stage.setTitle("Menu Principal");
        stage.setScene(new Scene(root));
    }
}
