package com.example.techstock.views.hardware;

import com.example.techstock.domain.CentroComputo;
import com.example.techstock.domain.Periferico;
import com.example.techstock.logic.AdministrarPerifericosLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class AdministrarPerifericosController {

    @FXML
    private TextField agregarNumeroSerieField;
    @FXML
    private ComboBox<CentroComputo> agregarCentroComputoComboBox;
    @FXML
    private TextField agregarMarcaField;
    @FXML
    private TableView<Periferico> perifericosTableView;
    @FXML
    private TableColumn<Periferico, String> numeroSerieColumn;

    @FXML
    private TableColumn<Periferico, Integer> centroComputoColumn;

    @FXML
    private TableColumn<Periferico, String> marcaColumn;
    @FXML
    private ComboBox<CentroComputo> consultarCentroComputoComboBox;
    @FXML
    private ComboBox<CentroComputo> editarNuevoCentroComputoComboBox;
    @FXML
    private TextField editarNuevoNumeroSerieField;
    @FXML
    private TextField editarNuevaMarcaField;
    @FXML
    private Button editarBtn;
    @FXML
    private ComboBox<String> editarPerifericoComboBox;
    @FXML
    private ComboBox<String> eliminarPerifericoComboBox;
    @FXML
    private Button eliminar;


    @FXML
    public void initialize() {

        llenarComboBoxCentrosComputo();
        numeroSerieColumn.setCellValueFactory(new PropertyValueFactory<>("numeroSerie"));
        centroComputoColumn.setCellValueFactory(new PropertyValueFactory<>("idCentroComputo"));
        marcaColumn.setCellValueFactory(new PropertyValueFactory<>("marca"));
        perifericosTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    eliminarPerifericoComboBox.getItems().clear();
                    editarPerifericoComboBox.getItems().clear();
                    if (newValue != null) {
                        eliminarPerifericoComboBox.getItems().add(newValue.getNumeroSerie());
                        eliminar.setDisable(false);
                        editarPerifericoComboBox.getItems().add(newValue.getNumeroSerie());
                        editarBtn.setDisable(false);
                    }else {
                        eliminar.setDisable(true);
                        editarBtn.setDisable(true);
                    }
                }
        );
    }


    private void llenarComboBoxCentrosComputo() {
        AdministrarPerifericosLogic perifericosLogic = new AdministrarPerifericosLogic();
        List<CentroComputo> centrosComputo = perifericosLogic.obtenerCentrosComputo();

        if (centrosComputo != null) {
            ObservableList<CentroComputo> observableList = FXCollections.observableArrayList(centrosComputo);
            agregarCentroComputoComboBox.setItems(observableList);
            consultarCentroComputoComboBox.setItems(observableList);
            editarNuevoCentroComputoComboBox.setItems(observableList);
        } else {
            // Manejar el caso en el que no se puedan obtener los centros de cómputo
            System.out.println("Error al obtener centros de cómputo.");
        }
    }

    @FXML
    public void agregarPerifericoAction(ActionEvent actionEvent) {
        String numeroSerie = agregarNumeroSerieField.getText();
        String marca = agregarMarcaField.getText();
        Integer idCentroComputo = agregarCentroComputoComboBox.getSelectionModel().getSelectedItem().getIdCentroComputo();
        System.out.println(agregarCentroComputoComboBox.getSelectionModel().getSelectedItem().getIdCentroComputo());

        AdministrarPerifericosLogic perifericosLogic = new AdministrarPerifericosLogic();
        Periferico periferico = new Periferico(numeroSerie, idCentroComputo,marca);
        boolean success = perifericosLogic.agregarPeriferico(periferico);

        if (success) {
            actualizarTablaPerifericos();
            limpiarCamposAgregar();
        } else {
        }
    }

    public void consultarPerifericosAction(ActionEvent actionEvent) {
        CentroComputo centroSeleccionado = consultarCentroComputoComboBox.getValue();

        if (centroSeleccionado != null) {
            AdministrarPerifericosLogic perifericosLogic = new AdministrarPerifericosLogic();
            List<Periferico> perifericos = perifericosLogic.consultarPerifericosPorCentro(centroSeleccionado);
            llenarTablaPerifericos(perifericos);
        } else {
        }
    }



    @FXML
    private void editarPerifericoAction(ActionEvent event) {
        String numeroSerie = editarPerifericoComboBox.getValue();
        int nuevoCentroComputo = editarNuevoCentroComputoComboBox.getValue().getIdCentroComputo();
        String nuevaMarca = editarNuevaMarcaField.getText();
        Periferico periferico = new Periferico(numeroSerie, nuevoCentroComputo, nuevaMarca);

        AdministrarPerifericosLogic perifericosLogic = new AdministrarPerifericosLogic();
        boolean actualizacionExitosa = perifericosLogic.editarPeriferico(periferico);


        if (actualizacionExitosa) {
            limpiarCamposEditar();
        } else {

        }
    }
    private void limpiarCamposEditar() {
        editarNuevoCentroComputoComboBox.getSelectionModel().clearSelection();
        editarNuevaMarcaField.clear();
    }


    @FXML
    private void eliminarPerifericoAction() {
        String numeroSerie = eliminarPerifericoComboBox.getValue();
        if (numeroSerie != null) {
            AdministrarPerifericosLogic perifericosLogic = new AdministrarPerifericosLogic();
            boolean success = perifericosLogic.eliminarPeriferico(numeroSerie);
            if (success) {
                // Actualizar la tabla y el ComboBox
                perifericosTableView.getItems().removeIf(periferico -> periferico.getNumeroSerie().equals(numeroSerie));
                eliminarPerifericoComboBox.getItems().remove(numeroSerie);
            } else {
            }
        }
    }


    private void actualizarTablaPerifericos() {
        // Lógica para actualizar la tabla de periféricos
        // implementar esto según cómo esté estructurada tu tabla y datos
    }
    private void llenarTablaPerifericos(List<Periferico> perifericos) {

        perifericosTableView.getItems().clear();
        perifericosTableView.getItems().addAll(perifericos);
    }

    private void limpiarCamposAgregar() {
        agregarNumeroSerieField.clear();
        agregarMarcaField.clear();
        agregarCentroComputoComboBox.getSelectionModel().clearSelection();
    }
}
