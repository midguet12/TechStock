package com.example.techstock.dao;

import com.example.techstock.domain.Software;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class SoftwareDAO {
    Connection connection;
    PreparedStatement preparedStatement;

    private Connection getConnection(){
        try {
            connection = DBConnection.getConnection();
            return connection;
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    public boolean create(Software software) throws SQLException{
        String query = "INSERT INTO software(nombre, version) values(?,?)";

        String nombre = software.getNombre();
        String version = software.getVersion();

        boolean success = false;

        try{
            connection = getConnection();
            if (connection != null){
                preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, nombre);
                preparedStatement.setString(2, version);

                int rows = preparedStatement.executeUpdate();
                if (rows>0){
                    System.out.println("Se registro software");
                    success = true;
                }
            }
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }finally {
            connection.close();
            return success;
        }

    }

    public Software read(Integer idSoftware) throws SQLException{
        String query = "SELECT * FROM software WHERE idSoftware = ?";
        Software software = null;

        try{
            connection = getConnection();
            if (connection != null){
                preparedStatement = connection.prepareStatement(query);

                preparedStatement.setInt(1, idSoftware);

                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();

                software = new Software();
                software.setIdSoftware(resultSet.getInt("idSoftware"));
                software.setNombre(resultSet.getString("nombre"));
                software.setVersion(resultSet.getString("version"));
            }
        } catch (Exception exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        } finally {
            connection.close();
            return software;
        }
    }

    public List<Software> readAll() throws SQLException{
        String query = "SELECT * FROM software";
        List<Software> softwareList = null;
        try {
            softwareList = new ArrayList<Software>();
            connection = getConnection();

            if (connection != null){
                preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    Software software = new Software();
                    software.setIdSoftware(resultSet.getInt("idSoftware"));
                    software.setNombre(resultSet.getString("nombre"));
                    software.setVersion(resultSet.getString("version"));

                    softwareList.add(software);
                }

                System.out.println("Se obtuvieron " + softwareList.size() + " elementos.");
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();

        } finally {
            connection.close();
            return softwareList;
        }
    }

    public boolean update(Software software) throws SQLException{
        String query = "UPDATE software SET nombre = ?, version = ? WHERE idSoftware = ?";

        String nombre = software.getNombre();
        String version = software.getVersion();

        Integer idSoftware = software.getIdSoftware();

        boolean success = false;

        try{
            connection = getConnection();
            if (connection != null) {
                preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, nombre);
                preparedStatement.setString(2, version);

                preparedStatement.setInt(3, idSoftware);

                int rows = preparedStatement.executeUpdate();
                if (rows > 0){
                    System.out.println("Se actualizaron " +  rows + " lineas.");
                    success = true;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            connection.close();
            return success;
        }
    }

    public boolean delete(Integer idSoftware) throws SQLException{
        String query = "DELETE FROM software WHERE idSoftware = ?";
        boolean success = false;
        try{
            connection = getConnection();
            if (connection != null){
                preparedStatement = connection.prepareStatement(query);

                preparedStatement.setInt(1, idSoftware);

                int rows = preparedStatement.executeUpdate();

                if (rows > 0){
                    System.out.println("Se elimino el registro");
                    success = true;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            connection.close();
            return success;
        }
    }
}
