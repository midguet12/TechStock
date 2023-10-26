package com.example.techstock.views.software;

import com.example.techstock.DataSingleton;
import com.example.techstock.dao.SoftwareDAO;
import com.example.techstock.domain.Software;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ActualizarSoftwareController implements Initializable {
    public TextField nombreTextField;
    public TextField versionTextField;
    public Label successMessage;
    DataSingleton data = DataSingleton.getInstance();

    Integer idSoftware = data.getIdSoftware();
    Software software;
    SoftwareDAO softwareDAO = new SoftwareDAO();


    public void actualizarSoftware(ActionEvent actionEvent) {
        software.setNombre(nombreTextField.getText());
        software.setVersion(versionTextField.getText());

        if (softwareDAO.update(software)){
            successMessage.setText("Se ha actualizado el software");
        };
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        software = softwareDAO.read(data.getIdSoftware());

        nombreTextField.setText(software.getNombre());
        versionTextField.setText(software.getVersion());


    }
}
