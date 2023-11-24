package com.example.techstock.views.hardware;

import com.example.techstock.DataSingleton;
import com.example.techstock.dao.CentroComputoDAO;
import com.example.techstock.dao.EquipoComputoDAO;
import com.example.techstock.domain.CentroComputo;
import com.example.techstock.domain.EquipoComputo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ActualizarEquipoComputoController implements Initializable {
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

    DataSingleton data = DataSingleton.getInstance();
    String idEquipoComputo = data.getNumeroSerie();
    EquipoComputoDAO equipoComputoDAO = new EquipoComputoDAO();
    EquipoComputo equipoComputo;


    public boolean verificarExistencia() {
        EquipoComputoDAO equipoComputoDAO = new EquipoComputoDAO();
        try{
            boolean existe = equipoComputoDAO.noSerieExiste(noDeSerieTextField.getText());
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

    private void cargarCentroComputo() {
        CentroComputoDAO centroComputoDAO = new CentroComputoDAO();

        try {
            List<CentroComputo> resultadoConsulta = centroComputoDAO.readAll();
            ObservableList<CentroComputo> listaCentros = FXCollections.observableArrayList(resultadoConsulta);

            if (!resultadoConsulta.isEmpty()) {
                centroCompuComboBox.setItems(listaCentros);
            } else {
                mostrarAlerta("Advertencia", "No se encontraron datos de centros de cómputo.");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public boolean validarCampos() {
        int centroComputo = centroCompuComboBox.getSelectionModel().getSelectedIndex() + 1;
        String noSerie = noDeSerieTextField.getText();
        String marca = marcaTextField.getText();
        String capacidadAlma = capacidadAlmaTextField.getText();
        String memoriaRam = memoriaRamTextField.getText();
        String cpu = cpuTextField.getText();

        if (centroComputo <= 0) {
            return true;
        }

        if (noSerie.length() > 30 || marca.length() > 50 || capacidadAlma.length() > 50 || memoriaRam.length() > 50 || cpu.length() > 50) {
            return true;
        }

        return false;
    }

    public void limpiarCampos() {
        noDeSerieTextField.clear();
        marcaTextField.clear();
        capacidadAlmaTextField.clear();
        memoriaRamTextField.clear();
        cpuTextField.clear();
        centroCompuComboBox.getSelectionModel().clearSelection();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            equipoComputo = equipoComputoDAO.read(data.getNumeroSerie());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        noDeSerieTextField.setText(equipoComputo.getNumeroSerie());
        marcaTextField.setText(equipoComputo.getMarca());
        capacidadAlmaTextField.setText(equipoComputo.getAlmacenamiento());
        memoriaRamTextField.setText(equipoComputo.getMemoria());
        cpuTextField.setText(equipoComputo.getProcesador());
        //centroCompuComboBox.setItems(equipoComputo.getIdCentroComputo());     ERROR AL CARGAR EN EL COMBOBOX !!!!

    }

    public void btnAceptar(ActionEvent actionEvent) {
        EquipoComputoDAO equipoDao = new EquipoComputoDAO();

        int centroComputo = centroCompuComboBox.getSelectionModel().getSelectedIndex() + 1;
        EquipoComputo equipo = new EquipoComputo(centroComputo, noDeSerieTextField.getText(), marcaTextField.getText(), capacidadAlmaTextField.getText(), memoriaRamTextField.getText(), cpuTextField.getText());


        if (verificarExistencia() || validarCampos()){
            mostrarAlerta("Error", "Por favor retifique los datos");
        } else {
            try {
                boolean exito = equipoDao.update(equipo);
                if (exito) {
                    mostrarAlerta("Información", "Se ha guardado correctamente");
                    limpiarCampos();
                } else {
                    mostrarAlerta("Error", "Hubo un fallo al guardar el equipo de cómputo.");
                }
            } catch (Exception e) {
                mostrarAlerta("Error", "Ocurrió un error inesperado.");
            }
        }
    }

    public void btnCancelar(ActionEvent actionEvent) {

    }
}
