package com.example.techstock.dao;

import com.example.techstock.domain.Periferico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.techstock.dao.DBConnection.getConnection;

public class PerifericoDAO {
    /*String url = DBConnection.getUrl();
    String user = DBConnection.getUser();
    String password = DBConnection.getPassword();*/
    Connection connection;
    PreparedStatement preparedStatement;

    public PerifericoDAO() {
        try {
            //connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean create(Periferico periferico) throws SQLException {
        String query = "INSERT INTO periferico(numeroSerie, idCentroComputo, marca) VALUES (?,?,?)";
        boolean result = false;

        try {
            connection = getConnection();
            if (connection != null) {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, periferico.getNumeroSerie());
                preparedStatement.setInt(2, periferico.getIdCentroComputo());
                preparedStatement.setString(3, periferico.getMarca());
                int rows = preparedStatement.executeUpdate();
                if (rows > 0) {
                    System.out.println("Se registraron " + rows + " líneas.");
                    result = true;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al crear periférico: " + e.getMessage(), e);
        } finally {
            if (connection != null)
                connection.close();
            return result;
        }
    }

    public List<Periferico> readByCentroComputo(int idCentroComputo) throws SQLException {
        String query = "SELECT * FROM periferico WHERE idCentroComputo = ?";
        List<Periferico> listaPerifericos = null;
        try {
            listaPerifericos = new ArrayList<>();
            connection = getConnection();
            if (connection != null) {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, idCentroComputo);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Periferico periferico = new Periferico();
                    periferico.setNumeroSerie(resultSet.getString("numeroSerie"));
                    periferico.setIdCentroComputo(resultSet.getInt("idCentroComputo"));
                    periferico.setMarca(resultSet.getString("marca"));
                    listaPerifericos.add(periferico);
                }
                System.out.println("Se obtuvieron " + listaPerifericos.size() + " periféricos.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
            return listaPerifericos;
        }
    }



    public boolean delete(String numeroSerie) throws SQLException {
        String query = "DELETE FROM periferico WHERE numeroSerie = ?";
        try {
            connection = getConnection();
            if (connection != null) {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, numeroSerie);
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            connection.close();
        }
        return false;
    }

}
