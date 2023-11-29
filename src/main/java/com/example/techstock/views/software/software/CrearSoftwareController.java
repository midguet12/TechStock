package com.example.techstock.views.software.software;
import com.example.techstock.dao.SoftwareDAO;
import com.example.techstock.domain.Software;
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
import java.sql.SQLException;
import java.util.ResourceBundle;
public class CrearSoftwareController implements Initializable {

    public Label successMessage;
    public Button agregarSoftwareButton;
    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField versionTextField;
    @FXML
    private Button regresarButton;
    @FXML

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }


    public void agregarSoftwareAction(ActionEvent actionEvent) throws IOException {
        Software software = new Software();
        String nombre = nombreTextField.getText();
        String version = versionTextField.getText();

        software.setNombre(nombre);
        software.setVersion(version);

        SoftwareDAO softwareDAO = new SoftwareDAO();

        try{
            if (softwareDAO.create(software)){
                successMessage.setText( nombre + " " + version +  " se ha registrado.");

                Stage stage = (Stage) regresarButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/src/main/resources/com/example/techstock/ModuloInventarioSoftware/LeerSoftware.fxml"));
                stage.setTitle("Menu Principal");
                stage.setScene(new Scene(root));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public void regresarAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) regresarButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/src/main/resources/com/example/techstock/ModuloInventarioSoftware/LeerSoftware.fxml"));
        stage.setTitle("Consultar software");
        stage.setScene(new Scene(root));
    }
}
