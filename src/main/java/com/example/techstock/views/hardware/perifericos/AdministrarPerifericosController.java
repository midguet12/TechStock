package com.example.techstock.views.hardware.perifericos;

import com.example.techstock.domain.CentroComputo;
import com.example.techstock.domain.Periferico;
import com.example.techstock.logic.Perifericos.AdministrarPerifericosLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.util.List;

public class AdministrarPerifericosController {

    @FXML
    private TextField agregarNumeroSerieField;
    @FXML
    private ComboBox<CentroComputo> agregarCentroComputoComboBox;
    @FXML
    private TextField agregarMarcaField;
    @FXML
    private TextField agregarDescripcionField;
    @FXML
    private TableView<Periferico> perifericosTableView;
    @FXML
    private TableColumn<Periferico, String> numeroSerieColumn;

    @FXML
    private TableColumn<Periferico, Integer> centroComputoColumn;

    @FXML
    private TableColumn<Periferico, String> marcaColumn;
    @FXML
    private TableColumn<Periferico, String> descripcionColumn;
    @FXML
    private ComboBox<CentroComputo> consultarCentroComputoComboBox;
    @FXML
    private ComboBox<CentroComputo> editarNuevoCentroComputoComboBox;
    @FXML
    private TextField editarNuevoNumeroSerieField;
    @FXML
    private TextField editarNuevaMarcaField;
    @FXML
    private TextField editarNuevaDescripcionField;

    @FXML
    private Button editarBtn;
    @FXML
    private Button showEdit;
    @FXML
    private ComboBox<String> editarPerifericoComboBox;
    @FXML
    private ComboBox<String> eliminarPerifericoComboBox;
    @FXML
    private Button eliminar;
    @FXML
    private HBox editBox;


    @FXML
    public void initialize() {

        llenarComboBoxCentrosComputo();
        numeroSerieColumn.setCellValueFactory(new PropertyValueFactory<>("numeroSerie"));
        centroComputoColumn.setCellValueFactory(new PropertyValueFactory<>("idCentroComputo"));
        marcaColumn.setCellValueFactory(new PropertyValueFactory<>("marca"));
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

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
        editarNuevoCentroComputoComboBox.setValue(null);
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
        String descripcion = agregarDescripcionField.getText();
        Integer idCentroComputo = agregarCentroComputoComboBox.getSelectionModel().getSelectedItem().getIdCentroComputo();



        AdministrarPerifericosLogic perifericosLogic = new AdministrarPerifericosLogic();
        Periferico periferico = new Periferico(numeroSerie, idCentroComputo,marca, descripcion);
        if(!(marca.isEmpty() || numeroSerie.isEmpty() || descripcion.isEmpty())) {
            if (perifericosLogic.agregarPeriferico(periferico)) {
                actualizarTablaPerifericos();
                limpiarCamposAgregar();
            }
        }else {mostrarAlerta("Error", "Agrega datos válidos");}

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
    private void showEditMenu(){
        Periferico perifericoSeleccionado = perifericosTableView.getSelectionModel().getSelectedItem();

        if (perifericoSeleccionado != null) {
            editarNuevaMarcaField.setText(perifericoSeleccionado.getMarca());
            editarNuevaDescripcionField.setText(perifericoSeleccionado.getDescripcion());
            int val =0;
            int temp =0;
            for (CentroComputo item : editarNuevoCentroComputoComboBox.getItems()){
                if(item.getIdCentroComputo()==perifericoSeleccionado.getIdCentroComputo()){
                    val=temp;
                }else {
                    temp = temp +1;
                }
            }
            editarNuevoCentroComputoComboBox.setValue(editarNuevoCentroComputoComboBox.getItems().get(val));
            editBox.setVisible(true);
        }



    }

    @FXML
    private void editarPerifericoAction(ActionEvent event) {
        Periferico periferic = perifericosTableView.getSelectionModel().getSelectedItem();
        String numeroSerie = periferic.getNumeroSerie();
        int nuevoCentroComputo = editarNuevoCentroComputoComboBox.getValue().getIdCentroComputo();
        String nuevaMarca = editarNuevaMarcaField.getText();
        String nuevaDescripcion = editarNuevaDescripcionField.getText();
        Periferico periferico = new Periferico(numeroSerie, nuevoCentroComputo, nuevaMarca, nuevaDescripcion);

        AdministrarPerifericosLogic perifericosLogic = new AdministrarPerifericosLogic();
        if(editarNuevoCentroComputoComboBox.getValue() != null && !(nuevaMarca.isEmpty() || nuevaDescripcion.isEmpty() ) ){
            boolean actualizacionExitosa = perifericosLogic.editarPeriferico(periferico);

            if (actualizacionExitosa) {
                limpiarCamposEditar();
            }
        }else {
            mostrarAlerta("Error", "Agrega datos válidos");
        }

        editBox.setVisible(false);
    }
    private void limpiarCamposEditar() {
        editarNuevoCentroComputoComboBox.getSelectionModel().clearSelection();
        editarNuevaMarcaField.clear();
        editarNuevaDescripcionField.clear();
    }


    @FXML
    private void eliminarPerifericoAction() {
        Periferico periferico = perifericosTableView.getSelectionModel().getSelectedItem();

        if (periferico != null) {
            String numeroSerie = periferico.getNumeroSerie();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Eliminación");
            alert.setHeaderText(null);
            alert.setContentText("¿Estás seguro de que deseas eliminar el periférico con número de serie " + numeroSerie + "?");

            ButtonType buttonTypeConfirmar = new ButtonType("Confirmar");
            ButtonType buttonTypeCancelar = new ButtonType("Cancelar", ButtonType.CANCEL.getButtonData());

            alert.getButtonTypes().setAll(buttonTypeConfirmar, buttonTypeCancelar);

            alert.showAndWait().ifPresent(buttonType -> {
                if (buttonType == buttonTypeConfirmar) {
                    AdministrarPerifericosLogic perifericosLogic = new AdministrarPerifericosLogic();
                    boolean success = perifericosLogic.eliminarPeriferico(numeroSerie);
                    if (success) {

                        perifericosTableView.getItems().remove(periferico);
                        eliminarPerifericoComboBox.getItems().remove(numeroSerie);
                    }
                }
            });
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
        agregarDescripcionField.clear();
        agregarCentroComputoComboBox.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
