package com.example.techstock.dao;
import com.example.techstock.domain.EquipoComputo;
import com.example.techstock.dao.DBConnection;

import java.sql.*;

public class EquipoComputoDAO {
    String url = DBConnection.getUrl();
    String user = DBConnection.getUser();
    String password = DBConnection.getPassword();

    public boolean create(EquipoComputo equipoComputo) {
        String query = "INSERT INTO equipo_computo(idCentroComputo, numeroSerie, marca, almacenamiento, memoria, procesador)" +
                "values (?,?,?,?,?,?)";

        Integer idCentroComputo = equipoComputo.getIdCentroComputo();
        String numeroSerie = equipoComputo.getNumeroSerie();
        String marca = equipoComputo.getMarca();
        String almacenamiento = equipoComputo.getAlmacenamiento();
        String memoria = equipoComputo.getMemoria();
        String procesador = equipoComputo.getProcesador();

        try{
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, idCentroComputo);
            preparedStatement.setString(2, numeroSerie);
            preparedStatement.setString(3, marca);
            preparedStatement.setString(4, almacenamiento);
            preparedStatement.setString(5, memoria);
            preparedStatement.setString(6, procesador);

            int resultSet = preparedStatement.executeUpdate();
            if (resultSet>0){
                return true;
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

        return false;
    }

}
