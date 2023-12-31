package com.example.techstock.views.hardware.centrocomputo;

import com.example.techstock.dao.CentroComputoDAO;
import com.example.techstock.domain.CentroComputo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AgregarCentroComputoController implements Initializable {
    @FXML
    private TextField nombreTextField;

    public void btnAceptar(ActionEvent actionEvent) {
        String nombre = nombreTextField.getText();
        CentroComputoDAO centroDao = new CentroComputoDAO();
        boolean exito = false;

        if (verificarExistencia() || nombreTextField.getText().isEmpty()) {
            mostrarAlerta("Error", "El campo ya existe o esta vacio");
        } else {
            try {
                CentroComputo centro = new CentroComputo(nombre);
                exito = centroDao.create(centro);

                if (exito) {
                    System.out.println("Equipo de cómputo agregado exitosamente.");
                    nombreTextField.clear();
                } else {
                    System.err.println("Error al agregar el equipo de cómputo.");
                    nombreTextField.clear();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean verificarExistencia() {
        CentroComputoDAO centroDao = new CentroComputoDAO();
        try {
            boolean existe = centroDao.nombreExiste(nombreTextField.getText());
            if (existe) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    private void mostrarAlerta(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }


    @FXML
    public void btnCancelar(ActionEvent actionEvent) {
        //Me regresa al Menu principal
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
