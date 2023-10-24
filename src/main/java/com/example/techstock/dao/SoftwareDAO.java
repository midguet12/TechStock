package com.example.techstock.dao;

import com.example.techstock.domain.EquipoComputo;
import com.example.techstock.domain.Software;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SoftwareDAO {
    String url = DBConnection.getUrl();
    String user = DBConnection.getUser();
    String password = DBConnection.getPassword();
    Connection connection;
    PreparedStatement preparedStatement;

    public SoftwareDAO() {
        try{
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public boolean create(Software software){
        String query = "INSERT INTO software(nombre, version)" +
                "values(?,?)";

        String nombre = software.getNombre();
        String version = software.getVersion();

        try{
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, version);

            int rows = preparedStatement.executeUpdate();
            if (rows>0){
                System.out.println("Se registro software");
                return true;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

        return false;
    }

    public Software read(Integer idSoftware){
        String query = "SELECT * FROM sofware WHERE idSoftware = ?";

        try{
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, idSoftware);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Software software = new Software();
            software.setIdSoftware(resultSet.getInt("idSoftware"));
            software.setNombre(resultSet.getString("nombre"));
            software.setVersion(resultSet.getString("version"));
            return software;

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Software> readAll(){
        String query = "SELECT * FROM software";

        try {
            List<Software> listaSoftware = new ArrayList<Software>();
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Software software = new Software();
                software.setIdSoftware(resultSet.getInt("idSoftware"));
                software.setNombre(resultSet.getString("nombre"));
                software.setVersion(resultSet.getString("version"));

                listaSoftware.add(software);
            }

            System.out.println("Se obtuvieron " + listaSoftware.size() + " elementos.");
            return listaSoftware;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public boolean update(Software software){
        String query = "UPDATE software SET nombre = ?, version = ? WHERE idSoftware = ?";

        String nombre = software.getNombre();
        String version = software.getVersion();

        Integer idSoftware = software.getIdSoftware();

        try{
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, version);

            preparedStatement.setInt(3, idSoftware);

            int rows = preparedStatement.executeUpdate();
            if (rows > 0){
                System.out.println("Se actualizaron " +  rows + " lineas.");
                return true;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

        return false;
    }

    public boolean delete(String idSoftware){
        String query = "DELETE FROM software WHERE idSoftware = ?";
        try{
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, idSoftware);

            int rows = preparedStatement.executeUpdate();

            if (rows > 0){
                System.out.println("Se elimino el registro");
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }
}
