package com.example.techstock;

import com.example.techstock.dao.EquipoComputoDAO;
import com.example.techstock.domain.EquipoComputo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        String message = "";
        try{
            EquipoComputo equipoComputo;
            equipoComputo = new EquipoComputo(2, "hs2oF6wZ", "Biostar", "480GB", "4GB", "Celeron G4900" );

            //EquipoComputoDAO equipoComputoDAO = new EquipoComputoDAO();
            //message = String.valueOf(equipoComputoDAO.create(equipoComputo));

            //EquipoComputoDAO equipoComputoDAO = new EquipoComputoDAO();
            //EquipoComputo equipoComputo = equipoComputoDAO.read("DySSd84E");
            //EquipoComputo equipoComputo = equipoComputoDAO.readAll().get(1);

            EquipoComputoDAO equipoComputoDAO = new EquipoComputoDAO();


            message = String.valueOf(equipoComputoDAO.delete(equipoComputo.getNumeroSerie()));

        } catch (Exception e){
            System.out.println(e.toString());
        }

        welcomeText.setText(message);
    }
}