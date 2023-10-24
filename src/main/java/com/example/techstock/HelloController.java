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
            equipoComputo = new EquipoComputo(1, "C02H9LLSQ05D", "Apple", "256GB", "8GB", "Apple M1" );

            EquipoComputoDAO equipoComputoDAO = new EquipoComputoDAO();
            equipoComputoDAO.create(equipoComputo);

        } catch (Exception e){
            System.out.println(e.toString());
        }

        welcomeText.setText(message);
    }
}