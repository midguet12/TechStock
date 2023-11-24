package com.example.techstock.views.usuarios;

import com.example.techstock.DataSingleton;
import com.example.techstock.dao.UsuarioDAO;
import com.example.techstock.domain.Usuario;
import com.example.techstock.logic.LogWriting;
import com.example.techstock.logic.UsuarioLogic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class EditarPerfilController implements Initializable {

    DataSingleton dataSingleton = DataSingleton.getInstance();

    @FXML
    TextField nombreUsuarioTextField;
    @FXML
    TextField nombreCompletoTextField;
    @FXML
    Label administradorLabel;
    @FXML
    PasswordField currentContrasenaPasswordField;
    @FXML
    PasswordField newContrasenaPasswordField;
    @FXML
    PasswordField confirmationContrasenaPasswordField;
    @FXML
    Button regresarButton;
    @FXML
    Label messageLabel;

    Usuario usuario;

    String nombreUsuarioActual;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usuario = new Usuario();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            usuario = usuarioDAO.read(dataSingleton.getUsuario());
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }

        nombreUsuarioActual = usuario.getNombreUsuario();


        nombreUsuarioTextField.setText(usuario.getNombreUsuario());
        nombreCompletoTextField.setText(usuario.getNombreCompleto());

        if (usuario.getAdministrador()){
            administradorLabel.setText(administradorLabel.getText() + "\t Sí");
        } else {
            administradorLabel.setText(administradorLabel.getText() + " \t No");
        }
    }


    public void guardarAction(ActionEvent actionEvent) {

        String nombreUsuarioNuevo = nombreUsuarioTextField.getText();
        String nombreCompletoNuevo = nombreCompletoTextField.getText();

        try {
            Integer resultado = UsuarioLogic.actualizarUsuario(nombreUsuarioActual, nombreUsuarioNuevo, nombreCompletoNuevo);

            if (resultado == 1){
                messageLabel.setTextFill(Color.BLACK);;
                messageLabel.setText("Los datos se ha actualizado correctamente");

                dataSingleton.setUsuario(nombreUsuarioNuevo);
            }else if (resultado == 0){
                messageLabel.setTextFill(Color.RED);;
                messageLabel.setText("La datos no se han actualizado");
            }

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            LogWriting.writeLog(exception.getMessage());
        } catch (Exception exception){
            System.out.println(exception.getMessage());
            LogWriting.writeLog(exception.getMessage());
        }


    }

    public void regresarAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) regresarButton.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/src/main/resources/com/example/techstock/MenuPrincipal.fxml")));
        stage.setTitle("Menu Principal");
        stage.setScene(new Scene(root));
    }

    public void actualizarContrasenaAction(ActionEvent actionEvent) {

        String actualContrasena = currentContrasenaPasswordField.getText();
        String nuevaContrasena = newContrasenaPasswordField.getText();
        String confirmacionNuevaContrasena = confirmationContrasenaPasswordField.getText();


        if (actualContrasena.isEmpty() || nuevaContrasena.isEmpty() || confirmacionNuevaContrasena.isEmpty()){
            messageLabel.setText("Alguno de los campos se encuentra vacio");
            messageLabel.setTextFill(Color.RED);;
        }else{
            try {

                Integer resultado = UsuarioLogic.cambiarContrasena(dataSingleton.getUsuario(), actualContrasena, nuevaContrasena, confirmacionNuevaContrasena);

                if (resultado == 1){
                    messageLabel.setTextFill(Color.BLACK);;
                    messageLabel.setText("Las contraseñas se ha actualizado correctamente");
                }else if (resultado == 2){
                    messageLabel.setTextFill(Color.RED);;
                    messageLabel.setText("La contraseña de confirmacion no coincide");
                }else if (resultado == 3){
                    messageLabel.setTextFill(Color.RED);;
                    messageLabel.setText("La contraseña actual no es la ingresada");
                }

            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
                LogWriting.writeLog(exception.getMessage());
                messageLabel.setText("Ha ocurrido un error de conexion");
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                LogWriting.writeLog(exception.getMessage());
                messageLabel.setText("Ha ocurrido un error revisar log.txt");
            }
        }

    }


}
