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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ConsultarCentroComputoController implements Initializable {
    @FXML
    private TableView tablaCentro;
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
    }

    public void btnActualizar(ActionEvent actionEvent) {
    }
}
