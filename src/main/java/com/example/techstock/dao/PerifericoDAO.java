package com.example.techstock.dao;

import com.example.techstock.domain.Periferico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, periferico.getNumeroSerie());
            preparedStatement.setInt(2, periferico.getIdCentroComputo());
            preparedStatement.setString(3, periferico.getMarca());

            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("Se registraron " + rows + " líneas.");
                result = true;
            }
        } catch (SQLException e) {
            throw new SQLException("Error al crear periférico: " + e.getMessage(), e);
        } finally {
            connection.close();
            return result;
        }
    }
}
