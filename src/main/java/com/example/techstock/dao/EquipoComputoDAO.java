package com.example.techstock.dao;

import com.example.techstock.domain.EquipoComputo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipoComputoDAO {

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

    public boolean create(EquipoComputo equipoComputo) throws SQLException {
        String query = "INSERT INTO equipo_computo(idCentroComputo, numeroSerie, marca, almacenamiento, memoria, procesador)" +
                "values (?,?,?,?,?,?)";

        Integer idCentroComputo = equipoComputo.getIdCentroComputo();
        String numeroSerie = equipoComputo.getNumeroSerie();
        String marca = equipoComputo.getMarca();
        String almacenamiento = equipoComputo.getAlmacenamiento();
        String memoria = equipoComputo.getMemoria();
        String procesador = equipoComputo.getProcesador();

        boolean success = false;

        try{
            connection = getConnection();
            if (connection != getConnection()){
                preparedStatement = connection.prepareStatement(query);

                preparedStatement.setInt(1, idCentroComputo);
                preparedStatement.setString(2, numeroSerie);
                preparedStatement.setString(3, marca);
                preparedStatement.setString(4, almacenamiento);
                preparedStatement.setString(5, memoria);
                preparedStatement.setString(6, procesador);

                int rows = preparedStatement.executeUpdate();
                if (rows>0){
                    System.out.println("Se registraron " + rows + " lineas");
                    success = true;
                }
            }
        } catch (Exception exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        } finally {
            connection.close();
            return success;
        }
    }

    public EquipoComputo read(String numeroSerie) throws SQLException{
        String query = "SELECT * FROM equipo_computo WHERE numeroSerie = ?";
        EquipoComputo equipoComputo = null;

        try{
            connection = getConnection();
            if (connection != null){
                preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, numeroSerie);

                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();

                equipoComputo = new EquipoComputo();
                equipoComputo.setIdCentroComputo(resultSet.getInt("idCentroComputo"));
                equipoComputo.setNumeroSerie(resultSet.getString("numeroSerie"));
                equipoComputo.setMarca(resultSet.getString("marca"));
                equipoComputo.setAlmacenamiento(resultSet.getString("almacenamiento"));
                equipoComputo.setMemoria(resultSet.getString("memoria"));
                equipoComputo.setProcesador(resultSet.getString("procesador"));
            }
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        } finally {
            connection.close();
            return equipoComputo;
        }
    }

    public List<EquipoComputo> readAll() throws SQLException{
        String query = "SELECT * FROM equipo_computo";
        List<EquipoComputo> equipoComputoList = null;
        try{
            equipoComputoList = new ArrayList<EquipoComputo>();
            connection = getConnection();

            if (connection != null){
                preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    EquipoComputo equipoComputo = new EquipoComputo();
                    equipoComputo.setIdCentroComputo(resultSet.getInt("idCentroComputo"));
                    equipoComputo.setNumeroSerie(resultSet.getString("numeroSerie"));
                    equipoComputo.setMarca(resultSet.getString("marca"));
                    equipoComputo.setAlmacenamiento(resultSet.getString("almacenamiento"));
                    equipoComputo.setMemoria(resultSet.getString("memoria"));
                    equipoComputo.setProcesador(resultSet.getString("procesador"));

                    equipoComputoList.add(equipoComputo);
                }

                System.out.println("Se obtuvieron " + equipoComputoList.size() + " elementos.");
            }
        } catch(Exception exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        } finally {
            connection.close();
            return equipoComputoList;
        }
    }

    public boolean update(EquipoComputo equipoComputo) throws SQLException{
        String query = "UPDATE equipo_computo SET idCentroComputo = ?, marca = ?, almacenamiento = ?, memoria = ?, procesador = ? WHERE numeroSerie = ?";

        Integer idCentroComputo = equipoComputo.getIdCentroComputo();
        String marca = equipoComputo.getMarca();
        String almacenamiento = equipoComputo.getAlmacenamiento();
        String memoria = equipoComputo.getMemoria();
        String procesador = equipoComputo.getProcesador();

        String numeroSerie = equipoComputo.getNumeroSerie();

        boolean success = false;
        try{
            connection = getConnection();
            if (connection != null){
                preparedStatement = connection.prepareStatement(query);

                preparedStatement.setInt(1, idCentroComputo);
                preparedStatement.setString(2, marca);
                preparedStatement.setString(3, almacenamiento);
                preparedStatement.setString(4, memoria);
                preparedStatement.setString(5, procesador);

                preparedStatement.setString(6, numeroSerie);

                int rows = preparedStatement.executeUpdate();
                if (rows>0){
                    System.out.println("Se actualizaron " + rows + " lineas.");
                    success = true;
                }
            }
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            success = false;
        } finally {
            connection.close();
            return success;
        }
    }

    public boolean delete(String numeroSerie) throws SQLException{
        String query = "DELETE from equipo_computo WHERE numeroSerie = ?";
        boolean success = false;
        try{
            connection = getConnection();
            if (connection != null){
                preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, numeroSerie);

                int rows = preparedStatement.executeUpdate();
                if (rows>0){
                    System.out.println("Se eliminaron " + rows + " filas");
                    success = true;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            success = false;
        } finally {
            connection.close();
            return success;
        }
    }

    public boolean noSerieExiste(String numeroSerie) throws SQLException {
        String query = "SELECT COUNT(*) FROM equipo_computo WHERE numeroSerie = ?";
        boolean success = false;
        try{
            connection = getConnection();
            if(connection != null){
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, numeroSerie);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    if(count > 0){
                        success = true;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            connection.close();
            return success;
        }
    }
}