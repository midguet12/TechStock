package com.example.techstock.views.usuarios;

import com.example.techstock.DataSingleton;
import com.example.techstock.dao.UsuarioDAO;
import com.example.techstock.domain.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditarPerfilController implements Initializable {

    DataSingleton dataSingleton = DataSingleton.getInstance();

    @FXML
    TextField nombreUsuarioTextField;
    @FXML
    TextField nombreCompletoTextField;
    @FXML
    Label administradorLabel;
    public Button cancelarButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Usuario usuario = new Usuario();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            usuario = usuarioDAO.read(dataSingleton.getUsuario());
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }

        nombreUsuarioTextField.setText(usuario.getNombreUsuario());
        nombreCompletoTextField.setText(usuario.getNombreCompleto());

        if (usuario.getAdministrador()){
            administradorLabel.setText(administradorLabel.getText() + "Sí");
        } else {
            administradorLabel.setText(administradorLabel.getText() +  "\t"+ " No");
        }
    }


    public void guardarAction(ActionEvent actionEvent) {

    }

    public void cancelarAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) cancelarButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
        stage.setTitle("Menu principal");
        stage.setScene(new Scene(root));
    }
}
