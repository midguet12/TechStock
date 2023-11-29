package com.example.techstock.dao;

import com.example.techstock.domain.EquipoComputo;
import com.example.techstock.logic.Log.LogWriting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SoftwareEquipoDAO {

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

    public SoftwareEquipoDAO() {
    }



    public List<EquipoComputo> readFromSoftware(Integer idSoftware) throws SQLException{
        String query = "select  equipo_computo.idCentroComputo, equipo_computo.numeroSerie, equipo_computo.marca, equipo_computo.almacenamiento, equipo_computo.memoria, equipo_computo.procesador from software_equipo\n" +
                "right join equipo_computo\n" +
                "on software_equipo.numeroSerie = equipo_computo.numeroSerie\n" +
                "where idSoftware=?;";

        List<EquipoComputo> equipoComputoList = null;
        try {
            equipoComputoList = new ArrayList<EquipoComputo>();
            connection = getConnection();

            if (connection!=null){
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, idSoftware);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    EquipoComputo equipoComputo = new EquipoComputo();
                    //equipoComputo.setNombreCentroComputo(resultSet.getString("nombre"));
                    equipoComputo.setIdCentroComputo(resultSet.getInt("idCentroComputo"));
                    equipoComputo.setNumeroSerie(resultSet.getString("numeroSerie"));
                    equipoComputo.setMarca(resultSet.getString("marca"));
                    equipoComputo.setAlmacenamiento(resultSet.getString("almacenamiento"));
                    equipoComputo.setMemoria(resultSet.getString("memoria"));
                    equipoComputo.setProcesador(resultSet.getString("procesador"));

                    equipoComputoList.add(equipoComputo);
                }
            }
        } catch (Exception exception){
            System.out.println(exception.getMessage());
            LogWriting.writeLog(exception.getMessage());
        } finally {
            connection.close();
            return equipoComputoList;
        }
    }

    public List<EquipoComputo> readFromSoftwareWithCentroComputo(Integer idSoftware, Integer idCentroComputo) throws SQLException{
        String query = "select  equipo_computo.idCentroComputo, equipo_computo.numeroSerie, equipo_computo.marca, equipo_computo.almacenamiento, equipo_computo.memoria, equipo_computo.procesador from software_equipo\n" +
                "right join equipo_computo\n" +
                "on software_equipo.numeroSerie = equipo_computo.numeroSerie\n" +
                "where idSoftware=? and idCentroComputo=?;";

        List<EquipoComputo> equipoComputoList = null;
        try {
            equipoComputoList = new ArrayList<EquipoComputo>();
            connection = getConnection();

            if (connection!=null){
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, idSoftware);
                preparedStatement.setInt(2, idCentroComputo);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    EquipoComputo equipoComputo = new EquipoComputo();
                    //equipoComputo.setNombreCentroComputo(resultSet.getString("nombre"));
                    equipoComputo.setIdCentroComputo(resultSet.getInt("idCentroComputo"));
                    equipoComputo.setNumeroSerie(resultSet.getString("numeroSerie"));
                    equipoComputo.setMarca(resultSet.getString("marca"));
                    equipoComputo.setAlmacenamiento(resultSet.getString("almacenamiento"));
                    equipoComputo.setMemoria(resultSet.getString("memoria"));
                    equipoComputo.setProcesador(resultSet.getString("procesador"));

                    equipoComputoList.add(equipoComputo);
                }
            }
        } catch (Exception exception){
            System.out.println(exception.getMessage());
            LogWriting.writeLog(exception.getMessage());
        } finally {
            connection.close();
            return equipoComputoList;
        }
    }


}
