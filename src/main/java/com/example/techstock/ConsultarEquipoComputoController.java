package com.example.techstock;

import com.example.techstock.dao.CentroComputoDAO;
import com.example.techstock.dao.EquipoComputoDAO;
import com.example.techstock.domain.CentroComputo;
import com.example.techstock.domain.EquipoComputo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ConsultarEquipoComputoController implements Initializable {

    @FXML
    private TableView<EquipoComputo> tablaEquipo;
    @FXML
    private TableColumn<EquipoComputo, Integer> idCentroComputo = new TableColumn<>();
    @FXML
    private TableColumn<EquipoComputo, String>  noSerie = new TableColumn<>();
    @FXML
    private TableColumn<EquipoComputo, String>  marca = new TableColumn<>();
    @FXML
    private TableColumn<EquipoComputo, String>  almacenamiento = new TableColumn<>();
    @FXML
    private TableColumn<EquipoComputo, String>  memoria = new TableColumn<>();
    @FXML
    private TableColumn<EquipoComputo, String>  procesador = new TableColumn<>();


    // Al momento de llenar la tabla debe de decir el NOMBRE del centro de COmputo no el ID

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        llenarTabla();
    }

    public void llenarTabla(){
        idCentroComputo.setCellValueFactory(new PropertyValueFactory<EquipoComputo, Integer>("idCentroComputo"));
        noSerie.setCellValueFactory(new PropertyValueFactory<EquipoComputo, String>("numeroSerie"));
        marca.setCellValueFactory(new PropertyValueFactory<EquipoComputo, String>("marca"));
        almacenamiento.setCellValueFactory(new PropertyValueFactory<EquipoComputo, String>("almacenamiento"));
        memoria.setCellValueFactory(new PropertyValueFactory<EquipoComputo, String>("memoria"));
        procesador.setCellValueFactory(new PropertyValueFactory<EquipoComputo, String>("procesador"));

        EquipoComputoDAO equipoComputoDAO = new EquipoComputoDAO();
        List<EquipoComputo> equipoComputos = equipoComputoDAO.readAll();
        ObservableList<EquipoComputo> listaObservableEquipo = FXCollections.observableList(equipoComputos);

        tablaEquipo.setItems(listaObservableEquipo);
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

    public void btnAgregar(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ModuloInventarioHardware/AgregarEquipoComputo.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Agregar Equipo");
        stage.setScene(scene);
        stage.initModality(Modality.NONE);
        stage.show();
    }

    public void btnEliminar(ActionEvent actionEvent) {
        EquipoComputo equipoComputoSeleccionado = tablaEquipo.getSelectionModel().getSelectedItem();
        EquipoComputoDAO equipoComputoDAO = new EquipoComputoDAO();

        if(equipoComputoSeleccionado != null){
            boolean confirmacion = mostrarConfirmacion(equipoComputoSeleccionado.getNumeroSerie());
            String equipoCentro = equipoComputoSeleccionado.getNumeroSerie();

            if(confirmacion){
                boolean eliminado = equipoComputoDAO.delete(equipoCentro);
                if (eliminado) {
                    tablaEquipo.getItems().remove(equipoComputoSeleccionado);
                } else {
                    mostrarAlerta("Error","No se pudo eliminar el registro.");
                }
            }
        }else {
            mostrarAlerta("Advertencia","Por favor, selecciona un Centro de Computo para eliminar.");
        }
    }

    public void btnActualizar(ActionEvent actionEvent) {
    }

    public void btnCancelar(ActionEvent actionEvent) {
    }
}
