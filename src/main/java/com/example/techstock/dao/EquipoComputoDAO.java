package com.example.techstock.dao;
import com.example.techstock.domain.EquipoComputo;
import com.example.techstock.dao.DBConnection;

import java.sql.*;

public class EquipoComputoDAO {
    String url = DBConnection.getUrl();
    String user = DBConnection.getUser();
    String password = DBConnection.getPassword();

    public boolean create(EquipoComputo equipoComputo){
        Boolean success = false;
        String numeroSerie = equipoComputo.getNumeroSerie();
        Integer idCentroComputo = equipoComputo.getIdCentroComputo();
        String marca = equipoComputo.getMarca();
        String almacenamiento = equipoComputo.getAlmacenamiento();
        String memoria = equipoComputo.getMemoria();
        String procesador = equipoComputo.getProcesador();

        try{
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("INSERT INTO equipo_computo(numeroSerie, idCentroComputo, marca, almacenamiento, memoria, procesador) values (%s, %i, %s, %s, %s, %s)");

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return success;
    }

}
