package com.example.techstock;

import com.example.techstock.dao.CentroComputoDAO;
import com.example.techstock.domain.CentroComputo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;



import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("TechStock!");
        stage.setScene(scene);
        stage.show();
        prueba();
    }

    public static String prueba(){
        String nombreTextField = "Si entre?";
        CentroComputo centro = new CentroComputo(nombreTextField);
        CentroComputoDAO centroDao = new CentroComputoDAO();
        boolean exito = false;
        try {
            exito = centroDao.create(centro);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (exito) {
            return("Equipo de cómputo agregado exitosamente.");
        } else {
            return("Error al agregar el equipo de cómputo.");
        }


    }

    public static void main(String[] args) {
        launch();
    }
}