package com.example.techstock;

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
            Connection connection = DriverManager.getConnection("jdbc:mysql://mysqluv2023.ddns.net:3306/techstock?useSSL=false", "techstock", "Ikmujn19283");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from centro_computo");
            resultSet.next();
            message = resultSet.getString(2);
        } catch (Exception e){
            System.out.println(e.toString());
        }

        welcomeText.setText(message);
    }
}