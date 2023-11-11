package com.example.techstock.controller.ModuloInventarioHardware;

import com.example.techstock.DataSingleton;
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

public class ActualizarCentroComputoController implements Initializable {
    @FXML
    private TextField nombreTextField;

    DataSingleton data = DataSingleton.getInstance();
    Integer idCentroComputo = data.getIdCentroComputo();
    CentroComputoDAO centroComputoDAO = new CentroComputoDAO();
    CentroComputo centroComputo;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            centroComputo = centroComputoDAO.read(data.getIdCentroComputo());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        nombreTextField.setText(centroComputo.getNombre());
    }

    public void btnAceptar(ActionEvent actionEvent) {
        String nombre = nombreTextField.getText();
        CentroComputoDAO centroDao = new CentroComputoDAO();
        boolean exito = false;

        if (verificarExistencia() || nombreTextField.getText().isEmpty()) {
            mostrarAlerta("Error", "El campo ya existe o esta vacio");
        } else {
            try {
                CentroComputo centro = new CentroComputo(nombre);
                exito = centroDao.update(centro);

                if (exito) {
                    System.out.println("Equipo de cómputo moodifico exitosamente.");
                    nombreTextField.clear();
                } else {
                    System.err.println("Error al modificar el equipo de cómputo.");
                    nombreTextField.clear();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void btnCancelar(ActionEvent actionEvent) {
    }

    public boolean verificarExistencia() {
        CentroComputoDAO centroDao = new CentroComputoDAO();
        try{
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
}
