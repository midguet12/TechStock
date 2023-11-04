package com.example.techstock.dao;

import com.example.techstock.domain.EquipoComputo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EquipoComputoDAO {
    /*String url = DBConnection.getUrl();
    String user = DBConnection.getUser();
    String password = DBConnection.getPassword();*/
    Connection connection;
    PreparedStatement preparedStatement;



    public EquipoComputoDAO() {
        try{
            //connection = DriverManager.getConnection(url, user, password);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

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
            //Connection connection = DriverManager.getConnection(url, user, password);
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
                return true;
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

        return false;
    }

    public EquipoComputo read(String numeroSerie){
        String query = "SELECT * FROM equipo_computo WHERE numeroSerie = ?";

        try{
            //Connection connection = DriverManager.getConnection(url, user, password);
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, numeroSerie);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            EquipoComputo equipoComputo = new EquipoComputo();
            equipoComputo.setIdCentroComputo(resultSet.getInt("idCentroComputo"));
            equipoComputo.setNumeroSerie(resultSet.getString("numeroSerie"));
            equipoComputo.setMarca(resultSet.getString("marca"));
            equipoComputo.setAlmacenamiento(resultSet.getString("almacenamiento"));
            equipoComputo.setMemoria(resultSet.getString("memoria"));
            equipoComputo.setProcesador(resultSet.getString("procesador"));
            return equipoComputo;

        }catch (Exception e){
            System.out.println(e.getMessage());

        }

        return null;

    }

    public List<EquipoComputo> readAll(){
        String query = "SELECT * FROM equipo_computo";

        try{
            List<EquipoComputo> listaEquipoComputo = new ArrayList<EquipoComputo>();

            //Connection connection = DriverManager.getConnection(url, user, password);
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

                listaEquipoComputo.add(equipoComputo);
            }

            System.out.println("Se obtuvieron " + listaEquipoComputo.size() + " elementos.");

            return listaEquipoComputo;

        } catch(Exception e){
            System.out.println(e.getMessage());
        }

        return null;

    }

    public boolean update(EquipoComputo equipoComputo){
        String query = "UPDATE equipo_computo SET idCentroComputo = ?, marca = ?, almacenamiento = ?, memoria = ?, procesador = ? WHERE numeroSerie = ?";

        Integer idCentroComputo = equipoComputo.getIdCentroComputo();
        String numeroSerie = equipoComputo.getNumeroSerie();
        String marca = equipoComputo.getMarca();
        String almacenamiento = equipoComputo.getAlmacenamiento();
        String memoria = equipoComputo.getMemoria();
        String procesador = equipoComputo.getProcesador();

        try{
            //Connection connection = DriverManager.getConnection(url, user, password);
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
                return true;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

        return false;
    }

    public boolean delete(String numeroSerie){
        String query = "DELETE from equipo_computo WHERE numeroSerie = ?";
        try{
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, numeroSerie);

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
}
