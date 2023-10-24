package com.example.techstock.dao;
import com.example.techstock.domain.EquipoComputo;
import com.example.techstock.domain.Periferico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
        return false;
    }

    public Periferico read (String numeroSerie){
        String query = "SELECT * FROM periferico WHERE numeroSerie = ?";

        try{
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, numeroSerie);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Periferico periferico = new Periferico();

            periferico.setNumeroSerie(resultSet.getString("numeroSerie"));
            periferico.setIdCentroComputo(resultSet.getInt("idCentroComputo"));
            periferico.setMarca(resultSet.getString("marca"));

            return periferico;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public List<Periferico> readAll(){
        String query = "SELECT * FROM periferico";

        try {
            List<Periferico> listaPerifericos = new ArrayList<Periferico>();
            preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Periferico periferico = new Periferico();

                periferico.setNumeroSerie(resultSet.getString("numeroSerie"));
                periferico.setIdCentroComputo(resultSet.getInt("idCentroComputo"));
                periferico.setMarca(resultSet.getString("marca"));

                listaPerifericos.add(periferico);
            }

            System.out.println("Se obtuvieron " +  listaPerifericos.size() + " elementos.");
            return listaPerifericos;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;

    }

    public boolean update(Periferico periferico){
        String query ="UPDATE periferico SET idCentroComputo = ?, marca = ? WHERE numeroSerie= ?";

        String numeroSerie = periferico.getNumeroSerie();
        Integer idCentroComputo = periferico.getIdCentroComputo();
        String marca = periferico.getMarca();

        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idCentroComputo);
            preparedStatement.setString(2, marca);

            preparedStatement.setString(3, numeroSerie);

            int rows = preparedStatement.executeUpdate();
            if(rows > 0){
                System.out.println("Se actualizo el registro");
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    public boolean delete(String numeroSerie){
        String query = "DELETE FROM periferico WHERE numeroSerie = ?";
        try{
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, numeroSerie);

            int rows = preparedStatement.executeUpdate();

            if (rows>0){
                System.out.println("Se elimino registro");
                return true;
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

}
