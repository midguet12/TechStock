package com.example.techstock.dao;

import com.example.techstock.domain.CentroComputo;
import com.example.techstock.domain.EquipoComputo;
import com.example.techstock.dao.DBConnection;

import java.sql.*;

public class CentroComputoDAO {
    String url = DBConnection.getUrl();
    String user = DBConnection.getUser();
    String password = DBConnection.getPassword();

    /*public boolean create(CentroComputo centroComputo){
        Boolean success = false;
        String nombre = centroComputo.getNombre();
        Integer equipoDeComputo = centroComputo.getEquipoDeComputo();
        Integer perifericos = centroComputo.getPerifericos();

        try{
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("INSERT INTO equipo_computo(nombre, equipoDeComputo,perifericos ) values (?,?,?)");

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return success;
    }*/

    public boolean create(CentroComputo centroComputo) throws SQLException {
        String sql = "INSERT INTO centro_computo (nombre) VALUES (?)";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, centroComputo.getNombre());

            int filasInsertadas = statement.executeUpdate();

            return filasInsertadas > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
