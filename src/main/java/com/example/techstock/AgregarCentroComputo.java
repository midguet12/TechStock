package com.example.techstock;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

public class AgregarCentroComputo extends Application {
    @Override
    public void start(Stage stage) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(AgregarCentroComputo.class.getResource("ModuloInventarioHardware/AgregarCentroComputo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Agregar Centro de Computo");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args){
        launch();
    }
}
