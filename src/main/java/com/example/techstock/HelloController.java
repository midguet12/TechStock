package com.example.techstock;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;


public class HelloController {

    public void btnAgregarCentro(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("ModuloInventarioHardware/ConsultarCentroComputo.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Consultar Centro");
        stage.setScene(scene);
        stage.initModality(Modality.NONE);
        stage.show();

    }

    @FXML
    public void btnAgregarEquipo(ActionEvent actionEvent)  throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ModuloInventarioHardware/ConsultarEquipoComputo.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Agregar Equipo");
        stage.setScene(scene);
        stage.initModality(Modality.NONE);
        stage.show();
    }


}