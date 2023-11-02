package com.example.techstock;

import com.example.techstock.dao.CentroComputoDAO;
import com.example.techstock.domain.CentroComputo;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ConsultarCentroComputoController implements Initializable {
    @FXML
    private TableView<CentroComputo> tablaCentro;
    @FXML
    private TableColumn<CentroComputo, Integer> idCentroComputo = new TableColumn<>();
    @FXML
    private TableColumn<CentroComputo, String> nombre = new TableColumn<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCentroComputo.setCellValueFactory(new PropertyValueFactory<CentroComputo, Integer>("idCentroComputo"));
        nombre.setCellValueFactory(new PropertyValueFactory<CentroComputo, String>("nombre"));

        CentroComputoDAO centroComputoDAO = new CentroComputoDAO();
        List<CentroComputo> centros = centroComputoDAO.showTable();
        ObservableList<CentroComputo> listaObservableCentro = FXCollections.observableList(centros);


        tablaCentro.setItems(listaObservableCentro);
    }

    public void btnAgregar(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ModuloInventarioHardware/AgregarCentroComputo.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Agregar Software");
        stage.setScene(scene);
        stage.initModality(Modality.NONE);
        stage.show();
    }

    public void btnEliminar(ActionEvent actionEvent) {
        CentroComputo centroSeleccionado = tablaCentro.getSelectionModel().getSelectedItem();
        CentroComputoDAO centroDAO = new CentroComputoDAO();

        if (centroSeleccionado != null) {

            boolean confirmacion = mostrarConfirmacion(centroSeleccionado.getNombre());
            String nombreCentro = centroSeleccionado.getNombre();

            if(confirmacion){
                boolean eliminado = centroDAO.delete(nombreCentro);
                if (eliminado) {
                    tablaCentro.getItems().remove(centroSeleccionado);
                } else {
                    mostrarAlerta("Error","No se pudo eliminar el registro.");
                }
            }
        } else {
            mostrarAlerta("Advertencia","Por favor, selecciona un Centro de Computo para eliminar.");
        }

    }

    private void mostrarAlerta(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private boolean mostrarConfirmacion(String nombreCentro) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Seguro que quieres eliminar " + nombreCentro + "?");
        Optional<ButtonType> resultado = alert.showAndWait();

        if(resultado.get() == ButtonType.OK){
           return true;
        }
        return false;
    }


    public void btnActualizar(ActionEvent actionEvent) {
    }
}
