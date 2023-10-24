package com.example.techstock;

import com.example.techstock.dao.EquipoComputoDAO;
import com.example.techstock.dao.PerifericoDAO;
import com.example.techstock.domain.EquipoComputo;
import com.example.techstock.domain.Periferico;
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
            PerifericoDAO perifericoDAO = new PerifericoDAO();
            Periferico periferico = perifericoDAO.read("QWOIE");

            message = periferico.getIdCentroComputo().toString();

        } catch (Exception e){
            System.out.println(e.toString());
        }

        welcomeText.setText(message);
    }
}