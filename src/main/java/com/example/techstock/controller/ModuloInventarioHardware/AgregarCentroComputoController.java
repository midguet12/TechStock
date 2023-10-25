package com.example.techstock.controller.ModuloInventarioHardware;

import com.example.techstock.dao.CentroComputoDAO;
import com.example.techstock.domain.CentroComputo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AgregarCentroComputoController {
    @FXML
    private TextField nombreTextField;

    public void btnAceptar(ActionEvent actionEvent) {
        CentroComputo centro = new CentroComputo(nombreTextField.toString());
        CentroComputoDAO centroDao = new CentroComputoDAO();
        boolean exito = false;
        try {
            exito = centroDao.create(centro);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (exito) {
            System.out.println("Equipo de cómputo agregado exitosamente.");
        } else {
            System.err.println("Error al agregar el equipo de cómputo.");
        }
    }
    public void btnCancelar(ActionEvent actionEvent) {
    }
}
