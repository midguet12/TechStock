package com.example.techstock;

import com.example.techstock.dao.EquipoComputoDAO;
import com.example.techstock.domain.EquipoComputo;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    protected void onHelloButtonClick() {
        String message = "";
        try{
            EquipoComputo equipoComputo;
            equipoComputo = new EquipoComputo(2, "AKSLJD", "Acer", "2TB", "16GB", "Core i5-8300H" );

            //EquipoComputoDAO equipoComputoDAO = new EquipoComputoDAO();
            //message = String.valueOf(equipoComputoDAO.create(equipoComputo));

            //EquipoComputoDAO equipoComputoDAO = new EquipoComputoDAO();
            //EquipoComputo equipoComputo = equipoComputoDAO.read("DySSd84E");
            //EquipoComputo equipoComputo = equipoComputoDAO.readAll().get(1);

            EquipoComputoDAO equipoComputoDAO = new EquipoComputoDAO();


            message = String.valueOf(equipoComputoDAO.create(equipoComputo));

        } catch (Exception e){
            System.out.println(e.toString());
        }

        welcomeText.setText(message);
    }

    public void btnAgregarCentro(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("ModuloInventarioHardware/AgregarCentroComputo.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Agregar Software");
        stage.setScene(scene);
        stage.initModality(Modality.NONE);
        stage.show();

    }

    public void btnAgregarEquipo(ActionEvent actionEvent) {
    }

}