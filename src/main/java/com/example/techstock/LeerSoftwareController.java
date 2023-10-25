package com.example.techstock;

import com.example.techstock.dao.SoftwareDAO;
import com.example.techstock.domain.Software;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LeerSoftwareController implements Initializable{

    @FXML
    public TableView tablaSoftware;
    @FXML
    private TableColumn<Software, Integer> idSoftware = new TableColumn<>();
    @FXML
    private TableColumn<Software, String> nombre = new TableColumn<>();
    @FXML
    private TableColumn<Software, String> version = new TableColumn<>();

    @FXML
    private Button createSoftware;
    @FXML
    private Button actualizarSoftware;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        idSoftware.setCellValueFactory(new PropertyValueFactory<Software, Integer>("idSoftware"));
        nombre.setCellValueFactory(new PropertyValueFactory<Software, String>("nombre"));
        version.setCellValueFactory(new PropertyValueFactory<Software, String>("version"));

        SoftwareDAO softwareDAO = new SoftwareDAO();
        List<Software> listaSoftware = softwareDAO.readAll();
        ObservableList<Software> listaObservableSoftware = FXCollections.observableList(listaSoftware);


        tablaSoftware.setItems(listaObservableSoftware);


    }



    public void agregarSoftware(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("ModuloInventarioSoftware/CrearSoftware.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Agregar Software");
        stage.setScene(scene);
        stage.initModality(Modality.NONE);
        stage.show();

    }


    public void actualizarSoftware(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("ModuloInventarioSoftware/ActualizarSoftware.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Actualizar Software");
        stage.setScene(scene);
        stage.initModality(Modality.NONE);
        stage.show();
    }
}
