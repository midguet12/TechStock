package com.example.techstock.controller.ModuloInventarioHardware;

import com.example.techstock.dao.CentroComputoDAO;
import com.example.techstock.dao.EquipoComputoDAO;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarCentroComputo();
    }

    private void cargarCentroComputo() {
        CentroComputoDAO centroComputoDAO = new CentroComputoDAO();
        List<String> resultadoConsulta = centroComputoDAO.readAllString();
        ObservableList<String> listaComputo = FXCollections.observableArrayList(resultadoConsulta);

        if (!resultadoConsulta.isEmpty()) {
            try{
                centroCompuComboBox.setItems(listaComputo);
            }catch (Exception e){
                mostrarAlerta("Error", "Por favor retifique su conexión");
            }
        } else {
            mostrarAlerta("Advertencia", "No sé encontro datos.");
        }
    }

    private boolean validarCampos() {
        int centroComputo = centroCompuComboBox.getSelectionModel().getSelectedIndex();
        String noSerie = noDeSerieTextField.getText();
        String marca = marcaTextField.getText();
        String capacidadAlma = capacidadAlmaTextField.getText();
        String memoriaRam = memoriaRamTextField.getText();
        String cpu = cpuTextField.getText();
        int maxLength = 30;

        System.out.println("Centro de Cómputo: " + centroCompuComboBox.getSelectionModel().getSelectedItem());
        System.out.println("Número de Serie: " + noDeSerieTextField.toString());
        System.out.println("Marca: " +  marcaTextField.toString());
        System.out.println("Capacidad de Almacenamiento: " + capacidadAlmaTextField.toString());
        System.out.println("Memoria RAM: " + memoriaRamTextField.toString());
        System.out.println("CPU: " + cpuTextField.toString());

        if (marca.length() <= maxLength || capacidadAlma.length() <= maxLength || memoriaRam.length() <= maxLength || cpu.length() <= maxLength) {
            if(noSerie.length() <= 50 && centroComputo != 0){
                return true;
            }
            return false;
        } else {
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

    public void limpiarCampos() {
        noDeSerieTextField.clear();
        marcaTextField.clear();
        capacidadAlmaTextField.clear();
        memoriaRamTextField.clear();
        cpuTextField.clear();
        centroCompuComboBox.getSelectionModel().clearSelection();
    }

    public void btnAceptar(ActionEvent actionEvent) {
        EquipoComputoDAO equipoDao = new EquipoComputoDAO();
        int centroComputo = centroCompuComboBox.getSelectionModel().getSelectedIndex();
        EquipoComputo equipo = new EquipoComputo(centroComputo, noDeSerieTextField.toString(), marcaTextField.toString(), capacidadAlmaTextField.toString(), memoriaRamTextField.toString(), cpuTextField.toString());

        boolean exito = false;
        if (validarCampos() || equipoDao.noSerieExiste(noDeSerieTextField.toString())) {
            mostrarAlerta("Error", "Por favor retifique los datos");
        } else {
            try {
                exito = equipoDao.create(equipo);
                if (exito) {
                    mostrarAlerta("Información", "Se ha guardado correctamente");
                    limpiarCampos();
                } else {
                    mostrarAlerta("Error", "Hubo un fallo");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void btnCancelar(ActionEvent actionEvent){

    }

}
