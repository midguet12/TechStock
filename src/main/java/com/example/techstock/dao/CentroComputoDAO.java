package com.example.techstock.dao;

import com.example.techstock.domain.CentroComputo;
import com.example.techstock.domain.EquipoComputo;
import com.example.techstock.dao.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CentroComputoDAO {
    Connection connection;
    PreparedStatement preparedStatement;

    private Connection getConnection(){
        try{
            connection = DBConnection.getConnection();
            return connection;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean create(CentroComputo centroComputo) throws SQLException {
        String query = "INSERT INTO centro_computo (nombre) VALUES (?)";
        boolean success = false;
        try{
            connection = getConnection();
            if (connection != null){
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, centroComputo.getNombre());
                int filasInsertadas = preparedStatement.executeUpdate();

                if(filasInsertadas >0){
                    success = true;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            connection.close();
            return success;
        }
    }

    public boolean update(CentroComputo centroComputo) throws SQLException {
        String query = "UPDATE centro_computo SET  nombre = ?  WHERE idCentroComputo = ?";
        String nombre = centroComputo.getNombre();
        boolean success = false;

        try{
            connection = getConnection();
            if(connection != null){
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, nombre);

                int rows = preparedStatement.executeUpdate();
                if (rows>0){
                    success = true;
                    System.out.println("Se actualizaron " + rows + " lineas.");
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            connection.close();
            return success;
        }
    }

    public boolean delete(Integer idCentroComputo) throws SQLException {
        String query = "DELETE from centro_computo WHERE idCentroComputo = ?";
        boolean success = false;
        try{
            connection = getConnection();
            if(connection != null){
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, idCentroComputo);

                int rows = preparedStatement.executeUpdate();
                if (rows>0){
                    success = true;
                    System.out.println("Se eliminaron " + rows + " filas");
                }

            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            connection.close();
            return success;
        }
    }

    public CentroComputo read(Integer idCentroComputo) throws SQLException {
        String query = "Select * from centro_computo Where idCentroComputo = ?";
        CentroComputo centroComputo = null;
        try {
            connection = getConnection();
            if(connection != null){
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1,idCentroComputo);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();

                centroComputo = new CentroComputo();
                centroComputo.setIdCentroComputo(resultSet.getInt("idCentroComputo"));
                centroComputo.setNombre(resultSet.getString("nombre"));

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            connection.close();
            return centroComputo;
        }
    }

    public List<CentroComputo> readAll() throws SQLException {
        String query = "SELECT * FROM centro_computo";
        List<CentroComputo> listaCentroComputo = null;
        try{
            listaCentroComputo = new ArrayList<CentroComputo>();
            connection = getConnection();
            if(connection != null){

                preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    CentroComputo centroComputo = new CentroComputo();
                    centroComputo.setIdCentroComputo(resultSet.getInt("idCentroComputo"));
                    centroComputo.setNombre(resultSet.getString("nombre"));
                    listaCentroComputo.add(centroComputo);

                }
                System.out.println("Se obtuvieron " + listaCentroComputo.size() + " elementos.");
            }

        } catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            connection.close();
            return listaCentroComputo;
        }
    }

    public boolean nombreExiste(String nombre) throws SQLException {
        String query = "SELECT COUNT(*) FROM centro_computo WHERE nombre = ?";
        boolean success = false;
        try{
            connection = getConnection();
            if(connection != null){
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, nombre);

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
