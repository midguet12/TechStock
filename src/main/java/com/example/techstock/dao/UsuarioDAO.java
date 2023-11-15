package com.example.techstock.dao;

import com.example.techstock.domain.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    final String INSERT = "INSERT INTO usuario(nombreUsuario, contrasena) VALUES (?,?)";
    final String GETALL = "SELECT nombreUsuario, contrasena FROM usuario";
    final String GETONE = "SELECT * FROM usuario WHERE nombreUsuario = ?";
    final String DELETE = "DELETE FROM usuario WHERE nombreUsuario = ?";
    final String FULLUPDATE = "UPDATE usuario SET nombreUsuario = ?, contrasena = ? WHERE nombreUsuario = ?";
    final String UPDATE = "UPDATE usuario SET nombreUsuario = ? WHERE nombreUsuario = ?";

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
        String query = "INSERT INTO usuario(nombreUsuario, contrasena) VALUES (?,?)";
        String nombreUsuario = usuario.getNombreUsuario();
        String contrasena = usuario.getContrasena();

        boolean success = false;

        try {
            connection = getConnection();
            if (connection != null){
                preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, nombreUsuario);
                preparedStatement.setString(1, contrasena);

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

    public boolean update(Usuario usuario) throws SQLException{
        String query = "UPDATE usuario SET nombreUsuario = ?, contrasena = ? WHERE nombreUsuario = ?";
        String nombreUsuario = usuario.getNombreUsuario();
        String contrasena = usuario.getContrasena();

        boolean success = false;

        try {
            connection = getConnection();
            if (connection != null){
                preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, nombreUsuario);
                preparedStatement.setString(1, contrasena);

                preparedStatement.setString(3, nombreUsuario);

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
