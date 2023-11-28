package com.example.techstock;

import com.example.techstock.logic.Log.LogWriting;
import com.example.techstock.logic.Usuarios.UsuarioLogic;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class IniciarSesionController implements Initializable {
    @FXML
    public TextField nombreUsuarioTextField;
    @FXML
    public PasswordField contrasenaPasswordField;
    public Label mensajeLabel;

    @FXML
    public Button iniciarSesionButton;

    String nombreUsuario = "";
    String contrasena = "";

    DataSingleton dataSingleton = DataSingleton.getInstance();

    private void iniciarSesion() throws Exception{
        nombreUsuario = nombreUsuarioTextField.getText();
        contrasena = contrasenaPasswordField.getText();


        Boolean autorizacion = UsuarioLogic.autorizar(nombreUsuario, contrasena);

        if(autorizacion == null){
            mensajeLabel.setTextFill(Color.RED);
            mensajeLabel.setText("Error de conexion");
        } else if (autorizacion){
            mensajeLabel.setTextFill(Color.GREEN);
            mensajeLabel.setText("Iniciando sesion...");

            dataSingleton.setUsuario(nombreUsuario);

            //Cambio de vista sin abrir nueva ventana
            Stage stage = (Stage) iniciarSesionButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
            stage.setTitle("Menu Principal");
            stage.setScene(new Scene(root));

        } else if (autorizacion == false){
            mensajeLabel.setTextFill(Color.RED);
            mensajeLabel.setText("Contraseña incorrecta");
        }
    };

    @FXML
    public void keyPressed(KeyEvent keyEvent){
        if (keyEvent.getCode().toString().equals("ENTER")){
            try {
                iniciarSesion();
            } catch (Exception exception){
                LogWriting.writeLog(exception.getMessage());
                System.out.println(exception.getMessage());
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }


    public void iniciarSesionAction(ActionEvent actionEvent) throws IOException {
        try {
            iniciarSesion();
        }
        catch (SQLException exception){
            LogWriting.writeLog(exception.getMessage());
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
        catch (Exception exception){
            LogWriting.writeLog(exception.getMessage());
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }

    }
}
