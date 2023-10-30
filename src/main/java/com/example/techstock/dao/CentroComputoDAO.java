package com.example.techstock.dao;

import com.example.techstock.domain.CentroComputo;
import com.example.techstock.domain.EquipoComputo;
import com.example.techstock.dao.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CentroComputoDAO {
    String url = DBConnection.getUrl();
    String user = DBConnection.getUser();
    String password = DBConnection.getPassword();

    public boolean create(CentroComputo centroComputo) throws SQLException {
        String query = "INSERT INTO centro_computo (nombre) VALUES (?)";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, centroComputo.getNombre());

            int filasInsertadas = preparedStatement.executeUpdate();

            return filasInsertadas > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean update(CentroComputo centroComputo) throws SQLException {
        String query = "UPDATE centro_computo SET  nombre = ?  WHERE numeroSerie = ?";
        String nombre = centroComputo.getNombre();

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, centroComputo.getNombre());

            int rows = preparedStatement.executeUpdate();
            if (rows>0){
                System.out.println("Se actualizaron " + rows + " lineas.");
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    public boolean delete(String nombre){
        String query = "DELETE from centro_computo WHERE nombre = ?";
        try{
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, nombre);

            int rows = preparedStatement.executeUpdate();
            if (rows>0){
                System.out.println("Se eliminaron " + rows + " filas");
                return true;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    public List<CentroComputo> readAll(){
        String query = "SELECT * FROM centro_computo";

        try{
            List<CentroComputo> listaCentroComputo = new ArrayList<CentroComputo>();

            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){

                CentroComputo centroComputo = new CentroComputo();
                centroComputo.setNombre(resultSet.getString("nombre"));
                listaCentroComputo.add(centroComputo);

            }

            System.out.println("Se obtuvieron " + listaCentroComputo.size() + " elementos.");

            return listaCentroComputo;

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<String> readAllString(){
        String query = "SELECT * FROM centro_computo";

        try{
            List<String> listaCentroComputo = new ArrayList<String>();

            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){

                CentroComputo centroComputo = new CentroComputo();
                centroComputo.setNombre(resultSet.getString("nombre"));
                listaCentroComputo.add(centroComputo.getNombre());

            }

            System.out.println("Se obtuvieron " + listaCentroComputo.size() + " elementos.");

            return listaCentroComputo;

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean nombreExiste(String nombre) {
        String query = "SELECT COUNT(*) FROM centro_computo WHERE nombre = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, nombre);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if(count > 0){
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
