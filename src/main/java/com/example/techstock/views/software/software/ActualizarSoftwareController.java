package com.example.techstock.views.software.software;

import com.example.techstock.DataSingleton;
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

public class ActualizarSoftwareController implements Initializable {
    @FXML
    public Label successMessage;
    @FXML
    public TextField nombreTextField;
    @FXML
    public TextField versionTextField;
    @FXML
    private Button regresarButton;

    DataSingleton data = DataSingleton.getInstance();

    Integer idSoftware = data.getIdSoftware();
    Software software = new Software();
    SoftwareDAO softwareDAO = new SoftwareDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            software = softwareDAO.read(data.getIdSoftware());
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
        nombreTextField.setText(software.getNombre());
        versionTextField.setText(software.getVersion());
    }

    public void actualizarSoftwareAction(ActionEvent actionEvent) throws IOException {
        software.setNombre(nombreTextField.getText());
        software.setVersion(versionTextField.getText());

        try {
            if (softwareDAO.update(software)){
                successMessage.setText("Se ha actualizado el software");

                Stage stage = (Stage) regresarButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/techstock/ModuloInventarioSoftware/Software/LeerSoftware.fxml"));
                stage.setTitle("Menu Principal");
                stage.setScene(new Scene(root));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ;
    }


    public void regresarAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) regresarButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/techstock/ModuloInventarioSoftware/Software/LeerSoftware.fxml"));
        stage.setTitle("Consultar software");
        stage.setScene(new Scene(root));
    }
}
