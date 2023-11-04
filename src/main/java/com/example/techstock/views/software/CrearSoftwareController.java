package com.example.techstock.views.software;
import com.example.techstock.dao.SoftwareDAO;
import com.example.techstock.domain.Software;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }


    public void agregarSoftware(ActionEvent actionEvent) {
        Software software = new Software();
        String nombre = nombreTextField.getText().toString();
        String version = versionTextField.getText().toString();

        software.setNombre(nombre);
        software.setVersion(version);

        SoftwareDAO softwareDAO = new SoftwareDAO();

        try{
            if (softwareDAO.create(software)){
                successMessage.setText( nombre + " " + version +  " se ha registrado.");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
