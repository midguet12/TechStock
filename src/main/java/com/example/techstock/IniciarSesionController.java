package com.example.techstock;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class IniciarSesionController implements Initializable {
    @FXML
    public TextField nombreUsuarioTextField;
    @FXML
    public PasswordField contrasenaPasswordField;

    String nombre = "";
    String contrasena = "";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void iniciarSesionAction(ActionEvent actionEvent) {
        nombre = nombreUsuarioTextField.getText();
        contrasena = contrasenaPasswordField.getText();

        System.out.println("Hola como estas, ya funciono " + nombre + " " + contrasena);

    }
}
