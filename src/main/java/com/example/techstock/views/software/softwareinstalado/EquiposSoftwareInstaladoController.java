package com.example.techstock.views.software.softwareinstalado;

import com.example.techstock.DataSingleton;
import com.example.techstock.dao.CentroComputoDAO;
import com.example.techstock.dao.SoftwareEquipoDAO;
import com.example.techstock.domain.CentroComputo;
import com.example.techstock.domain.EquipoComputo;
import com.example.techstock.logic.Log.LogWriting;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EquiposSoftwareInstaladoController implements Initializable {
    @FXML
    private TableView<EquipoComputo> tablaEquipo;
    @FXML
    private TableColumn<EquipoComputo, String> nombreCentroComputo = new TableColumn<>();
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
    @FXML
    public ComboBox<CentroComputo> centroComputoComboBox;
    @FXML
    private Button listaSoftwareButton;
    @FXML
    private Label softwareNombreLabel;


    DataSingleton data = DataSingleton.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        softwareNombreLabel.setText("Equipos con " + data.getSoftware().getNombre() + " instalado.");
        CentroComputo centroComputo = null;
        llenarCentroComputoComboBox();

        if (centroComputo == null){
            centroComputo = new CentroComputo();
            centroComputo.setIdCentroComputo(null);
            centroComputo.setNombre("Todos");
        }

        centroComputoComboBox.getSelectionModel().select(centroComputo);

        try {
            llenarTabla(data.getSoftware().getIdSoftware(), centroComputoComboBox.getValue().getIdCentroComputo());
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            LogWriting.writeLog(exception.getMessage());
        }

    }
    public void instalarAction(ActionEvent actionEvent) {
    }

    public void desinstalarAction(ActionEvent actionEvent) {
    }

    public void listasoftwareAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) listaSoftwareButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/src/main/resources/com/example/techstock/ModuloInventarioSoftware/Software/LeerSoftware.fxml"));
        stage.setTitle("Software");
        stage.setScene(new Scene(root));
    }

    public void buscarAction(ActionEvent actionEvent) {
        Integer idCentroComputo = centroComputoComboBox.getValue().getIdCentroComputo();
        try {
            llenarTabla(data.getSoftware().getIdSoftware(), idCentroComputo);
        } catch (Exception exception){
            System.out.println(exception.getMessage());
            LogWriting.writeLog(exception.getMessage());
        }
    }

    public void llenarCentroComputoComboBox(){
        CentroComputoDAO centroComputoDAO = new CentroComputoDAO();

        //Se declara de esta manera para poder mostrar una opcion llamada TODOS
        CentroComputo centroComputo = new CentroComputo();
        centroComputo.setIdCentroComputo(null);
        centroComputo.setNombre("Todos");

        try{
            ObservableList<CentroComputo> listaObservableCentroComputo = FXCollections.observableList(centroComputoDAO.readAll());
            listaObservableCentroComputo.addFirst(centroComputo);
            centroComputoComboBox.setItems(listaObservableCentroComputo);

        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }

    }

    public void llenarTabla(Integer idSoftware, Integer idCentroComputo) throws Exception{
        nombreCentroComputo.setCellValueFactory(new PropertyValueFactory<EquipoComputo, String>("nombreCentroComputo"));
        noSerie.setCellValueFactory(new PropertyValueFactory<EquipoComputo, String>("numeroSerie"));
        marca.setCellValueFactory(new PropertyValueFactory<EquipoComputo, String>("marca"));
        almacenamiento.setCellValueFactory(new PropertyValueFactory<EquipoComputo, String>("almacenamiento"));
        memoria.setCellValueFactory(new PropertyValueFactory<EquipoComputo, String>("memoria"));
        procesador.setCellValueFactory(new PropertyValueFactory<EquipoComputo, String>("procesador"));
        tablaEquipo.getItems().clear();

        List<EquipoComputo> equiposComputo = null;
        SoftwareEquipoDAO softwareEquipoDAO = new SoftwareEquipoDAO();
        equiposComputo = softwareEquipoDAO.readFromSoftware(idSoftware);

        if (idCentroComputo == null){
            equiposComputo = softwareEquipoDAO.readFromSoftware(idSoftware);
        } else if (idCentroComputo!=0){
            equiposComputo = softwareEquipoDAO.readFromSoftwareWithCentroComputo(idSoftware, idCentroComputo);
        }

        ObservableList<EquipoComputo> listaObservableEquipoComputo = FXCollections.observableList(equiposComputo);
        tablaEquipo.setItems(listaObservableEquipoComputo);

    }


}
