package com.example.techstock.controller.ModuloInventarioHardware;

import com.example.techstock.dao.CentroComputoDAO;
import com.example.techstock.dao.EquipoComputoDAO;
import com.example.techstock.domain.CentroComputo;
import com.example.techstock.domain.EquipoComputo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;

public class AgregarEquipoComputoController {
    @FXML
    private TextField noDeSerieTextField;
    @FXML
    private TextField marcaTextField;
    @FXML
    private TextField capacidadAlmaTextField;
    @FXML
    private TextField memoriaRamTextField;
    @FXML
    private TextField cpuTextField;
    @FXML
    private ComboBox<CentroComputo> centroCompuComboBox;

    private ObservableList<CentroComputo> listaComputo = FXCollections.observableArrayList();


    public void btnAceptar(ActionEvent actionEvent) {
        EquipoComputo equipo = new EquipoComputo(centroCompuComboBox, noDeSerieTextField.toString(), marcaTextField.toString(), capacidadAlmaTextField.toString(), memoriaRamTextField.toString(), cpuTextField.toString());
        EquipoComputoDAO centroDao = new EquipoComputoDAO();
        boolean exito = false;
        try {
            exito = centroDao.create(equipo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (exito) {
            System.out.println("Equipo de cómputo agregado exitosamente.");
        } else {
            System.err.println("Error al agregar el equipo de cómputo.");
        }
    }

    public void cargarCentroComputo(){
        ArrayList<CentroComputoDAO> resultadoConsulta = CentroComputoDAO.readAll;
        if (resultadoConsulta != null) {
            if (!resultadoConsulta.isEmpty()) {
                listaComputo.clear();
                listaComputo.addAll(resultadoConsulta);
                centroCompuComboBox.setItems(listaComputo);
            } else {
                System.out.println("Cargo Info");
            }
        } else {
            System.out.println("No cargo");
        }
    }

    public void btnCancelar(ActionEvent actionEvent) {
    }
}
