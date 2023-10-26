package com.example.techstock.controller.ModuloInventarioHardware;

import com.example.techstock.dao.CentroComputoDAO;
import com.example.techstock.dao.EquipoComputoDAO;
import com.example.techstock.domain.CentroComputo;
import com.example.techstock.domain.EquipoComputo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AgregarEquipoComputoController implements Initializable {
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
    private ComboBox centroCompuComboBox;

    private ObservableList<CentroComputo> listaComputo = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarCentroComputo();
    }

    public void btnAceptar(ActionEvent actionEvent) {
        int centroComputo = centroCompuComboBox.getSelectionModel().getSelectedIndex();
        EquipoComputo equipo = new EquipoComputo(centroComputo, noDeSerieTextField.toString(), marcaTextField.toString(), capacidadAlmaTextField.toString(), memoriaRamTextField.toString(), cpuTextField.toString());
        EquipoComputoDAO equipoDao = new EquipoComputoDAO();

        boolean exito = false;
        if (validarCampos() || equipoDao.noSerieExiste(noDeSerieTextField.toString())) {
            System.out.println("El campo nombre no puede ser ingresado");
        } else {
            try {
                exito = equipoDao.create(equipo);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (exito) {
                System.out.println("Equipo de cómputo agregado exitosamente.");
                limpiarCampos();
            } else {
                System.err.println("Error al agregar el equipo de cómputo.");
            }
        }
    }

    public void cargarCentroComputo() {
        CentroComputoDAO centroComputoDAO = new CentroComputoDAO();
        List<CentroComputo> resultadoConsulta = centroComputoDAO.readAll();

        if (resultadoConsulta != null && !resultadoConsulta.isEmpty()) {
            listaComputo.clear();
            listaComputo.addAll(resultadoConsulta);
            centroCompuComboBox.setItems(listaComputo);
        } else {
            System.out.println("No se encontraron Centros de Computo.");
        }
    }

    public void btnCancelar(ActionEvent actionEvent) {
        //Mandarlo al Menú principal
    }

    private boolean validarCampos() {
        int centroComputo = centroCompuComboBox.getSelectionModel().getSelectedIndex();
        String noSerie = noDeSerieTextField.getText();
        String marca = marcaTextField.getText();
        String capacidadAlma = capacidadAlmaTextField.getText();
        String memoriaRam = memoriaRamTextField.getText();
        String cpu = cpuTextField.getText();

        if (centroComputo != 0 && !noSerie.isEmpty() && !marca.isEmpty() && !capacidadAlma.isEmpty() &&
                !memoriaRam.isEmpty() && !cpu.isEmpty()) {
            return true;
        } else {
            System.err.println("Por favor, complete todos los campos y seleccione un centro de cómputo válido antes de continuar.");
            return false;
        }
    }

    public void limpiarCampos() {
        noDeSerieTextField.clear();
        marcaTextField.clear();
        capacidadAlmaTextField.clear();
        memoriaRamTextField.clear();
        cpuTextField.clear();
        centroCompuComboBox.getSelectionModel().clearSelection();
    }
}
