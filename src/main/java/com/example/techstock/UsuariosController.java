package com.example.techstock;


import com.example.techstock.dao.UsuarioDAO;
import com.example.techstock.domain.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import org.apache.commons.codec.digest.DigestUtils;

import java.net.URL;
import java.util.List;
import java.util.Objects;
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
        private HBox editarCampos;

        @FXML
        private Button registrarButton;

        @FXML
        private Button editarButton;

        @FXML
        private Button eliminarButton;


    @FXML
    protected void registrarUsuario() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String nombreUsuario = nombreUsuarioField.getText();
        String contrasena = contrasenaField.getText();

        if (nombreUsuario.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta("Error", "Por favor, complete todos los campos.");
            return; // Salir del método si hay campos vacíos.
        }

        Usuario usuario = new Usuario(nombreUsuario, DigestUtils.sha256Hex(contrasena));

        if (!usuarioDAO.UsuarioExiste(usuario)) {
            usuarioDAO.insertarUsuario(usuario);
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
            nuevaContrasenaField.setText("");
        }
    }

    @FXML
    protected void guardarCambios() {

        Usuario usuarioSeleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
        String nombre = usuarioSeleccionado.getNombreUsuario();

        if (usuarioSeleccionado != null) {
            String nuevoNombreUsuario = nuevoNombreUsuarioField.getText();
            if(Objects.equals(nuevaContrasenaField.getText(), "")){
                usuarioSeleccionado.setContrasena(nuevaContrasenaField.getText());
            }else{
                String nuevaContrasena = DigestUtils.sha256Hex(nuevaContrasenaField.getText());
                usuarioSeleccionado.setContrasena(nuevaContrasena);
            }


            usuarioSeleccionado.setNombreUsuario(nuevoNombreUsuario);


            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.actualizarUsuario(usuarioSeleccionado, nombre);

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
            usuarioDAO.eliminarUsuario(usuario);
            mostrarAlerta("Usuario Eliminado", "El usuario ha sido eliminado exitosamente.");
            cargarUsuariosEnTabla(); // Recargar la tabla después de eliminar
        } else {
            mostrarAlerta("Error", "Selecciona un usuario para eliminar.");
        }
    }

    @FXML
        protected void onPruebaButtonClick() {
            String message = "";
            try{
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuario = new Usuario("Quacker", DigestUtils.sha256Hex("elpato"));

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
        List<Usuario> usuarios = usuarioDAO.consultarUsuario();

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
}
