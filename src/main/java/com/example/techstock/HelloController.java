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
}