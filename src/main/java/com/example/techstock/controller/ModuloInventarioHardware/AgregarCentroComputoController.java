package com.example.techstock.controller.ModuloInventarioHardware;

import com.example.techstock.dao.CentroComputoDAO;
import com.example.techstock.domain.CentroComputo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AgregarCentroComputoController {
    @FXML
    private TextField nombreTextField;
    @FXML
    public void btnAceptar(ActionEvent actionEvent) {
        CentroComputo centro = new CentroComputo(nombreTextField.toString());
        CentroComputoDAO centroDao = new CentroComputoDAO();
        boolean exito = false;

        if (nombreTextField.getText().isEmpty() || centroDao.nombreExiste(nombreTextField.toString())) {
            System.out.println("El campo nombre no puede ser ingresado");
        } else {
            try {
                exito = centroDao.create(centro);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (exito) {
                System.out.println("Equipo de cómputo agregado exitosamente.");
                nombreTextField.setText("");
            } else {
                System.err.println("Error al agregar el equipo de cómputo.");
                nombreTextField.setText("");
            }
        }
    }
    @FXML
    public void btnCancelar(ActionEvent actionEvent) {
        //Me regresa al Menu principal
    }


}
