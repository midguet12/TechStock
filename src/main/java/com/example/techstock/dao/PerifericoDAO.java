package com.example.techstock.dao;
import com.example.techstock.domain.EquipoComputo;
import com.example.techstock.domain.Periferico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PerifericoDAO {
    String url = DBConnection.getUrl();
    String user = DBConnection.getUser();
    String password = DBConnection.getPassword();
    Connection connection;
    PreparedStatement preparedStatement;

    public PerifericoDAO() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public boolean create(Periferico periferico){
        String query = "INSERT INTO periferico(numeroSerie, idCentroComputo, marca) " +
                "values (?,?,?)";
        String numeroSerie = periferico.getNumeroSerie();
        Integer idCentroComputo = periferico.getIdCentroComputo();
        String marca = periferico.getMarca();

        try{
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, numeroSerie);
            preparedStatement.setInt(2, idCentroComputo);
            preparedStatement.setString(3, marca);

            int rows = preparedStatement.executeUpdate();
            if (rows > 0){
                System.out.println("Se registraron " +  rows + " lineas.");
                return true;
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }
}
