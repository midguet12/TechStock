package com.example.techstock.dao;

import com.example.techstock.domain.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    Connection connection;
    PreparedStatement preparedStatement;

    private Connection getConnection(){
        try{
            connection = DBConnection.getConnection();
            return connection;
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }
    public boolean create(Usuario usuario) throws  SQLException{
        String query = "INSERT INTO usuario(nombreUsuario, contrasena, nombreCompleto, administrador) VALUES (?,?,?,?)";
        String nombreUsuario = usuario.getNombreUsuario();
        String contrasena = usuario.getContrasena();
        String nombreCompleto = usuario.getNombreCompleto();
        boolean administrador = usuario.getAdministrador();

        boolean success = false;

        try {
            connection = getConnection();
            if (connection != null){
                preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, nombreUsuario);
                preparedStatement.setString(2, contrasena);
                preparedStatement.setString(3, nombreCompleto);
                preparedStatement.setBoolean(4, administrador);

                int rows = preparedStatement.executeUpdate();
                if (rows > 0){
                    System.out.println("Se registro Usuario");
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

    public Usuario read(String nombreUsuario) throws SQLException{
        String query = "SELECT * FROM usuario WHERE nombreUsuario = ?";
        Usuario usuario = null;

        try{
            connection = getConnection();
            if (connection != null){
                preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, nombreUsuario);

                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();

                usuario = new Usuario();
                usuario.setNombreUsuario(resultSet.getString("nombreUsuario"));
                usuario.setContrasena(resultSet.getString("contrasena"));
                usuario.setNombreCompleto(resultSet.getString("nombreCompleto"));
                usuario.setAdministrador(resultSet.getBoolean("administrador"));


            }
        } catch (Exception exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        } finally {
            connection.close();
            return usuario;
        }
    }

    public List<Usuario> readAll() throws SQLException{
        String query = "SELECT * FROM usuario";
        List<Usuario> usuarioList = null;
        try{
            usuarioList = new ArrayList<Usuario>();
            connection = getConnection();
            if (connection!= null) {
                preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    Usuario usuario = new Usuario();
                    usuario.setNombreUsuario(resultSet.getString("nombreUsuario"));
                    usuario.setContrasena(resultSet.getString("contrasena"));
                    usuario.setNombreCompleto(resultSet.getString("nombreCompleto"));
                    usuario.setAdministrador(resultSet.getBoolean("administrador"));


                    usuarioList.add(usuario);
                }
                System.out.println("Se obtuvieron " + usuarioList.size() + " elementos.");
            }
        } catch (Exception exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        } finally {
            connection.close();
            return usuarioList;
        }
    }

    public boolean update(String nombreUsuarioActual, Usuario usuario) throws SQLException{
        String query = "UPDATE usuario SET nombreUsuario = ?, contrasena = ?, nombreCompleto = ?, administrador = ? WHERE nombreUsuario = ?";
        String nombreUsuario = usuario.getNombreUsuario();
        String contrasena = usuario.getContrasena();
        String nombreCompleto = usuario.getNombreCompleto();
        boolean administrador = usuario.getAdministrador();

        boolean success = false;

        try {
            connection = getConnection();
            if (connection != null){
                preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, nombreUsuario);
                preparedStatement.setString(2, contrasena);
                preparedStatement.setString(3, nombreCompleto);
                preparedStatement.setBoolean(4, administrador);

                preparedStatement.setString(5, nombreUsuarioActual);

                int rows = preparedStatement.executeUpdate();
                if (rows > 0){
                    System.out.println("Se actualizo un usuario");
                    success = true;
                }
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        } finally {
            connection.close();
            return success;
        }
    }

    public boolean delete(String nombreUsuario) throws SQLException{
        String query = "DELETE FROM usuario WHERE nombreUsuario = ?";
        boolean success = false;

        try{
            connection = getConnection();
            if (connection!=null){
                preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, nombreUsuario);

                int rows = preparedStatement.executeUpdate();

                if(rows > 0) {
                    System.out.println("Se elimino el registro");
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
}
