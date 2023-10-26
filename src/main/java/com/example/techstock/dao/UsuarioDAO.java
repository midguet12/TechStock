package com.example.techstock.dao;

import com.example.techstock.domain.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {

    final String INSERT = "INSERT INTO usuario(nombreUsuario, contrasena) VALUES (?,?)";
    final String GETALL = "SELECT nombreUsuario, contrasena FROM usuario";
    final String GETONE = "SELECT * FROM usuario WHERE nombreUsuario = ?";
    final String DELETE = "DELETE FROM usuario WHERE nombreUsuario = ?";
    public void insertarUsuario(Usuario usuario) {
        DBConnection dataBaseConnection = new DBConnection();
        try(Connection connection=dataBaseConnection.getConnection()){
            PreparedStatement stat=connection.prepareStatement(INSERT);
            stat.setString(1, usuario.getNombreUsuario());
            stat.setString(2, usuario.getContrasena());

            if (stat.executeUpdate() == 0){
                throw new SQLException("No se pudo ingresar");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Usuario> consultarUsuario() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        DBConnection dataBaseConnection = new DBConnection();
        try(Connection connection=dataBaseConnection.getConnection()){
            PreparedStatement statement=connection.prepareStatement(GETALL);
            ResultSet resultSet=statement.executeQuery();
            if (resultSet.next()==false){
                throw new SQLException("no se encontraron usuarios");
            }else{
                String nombreUsuario="";
                String contrasena="";
                do {
                    nombreUsuario= resultSet.getString("nombreUsuario");
                    contrasena=resultSet.getString("contrasena");
                    Usuario usuario = new Usuario();
                    usuario.setNombreUsuario(nombreUsuario);
                    usuario.setContrasena(contrasena);
                    usuarios.add(usuario);
                }while (resultSet.next());
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }


    public boolean UsuarioExiste(Usuario usuario) {
        boolean resultado=false;
        DBConnection dataBaseConnection = new DBConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GETONE);
            statement.setString(1, usuario.getNombreUsuario());
            ResultSet resultSet = statement.executeQuery();
            resultado=resultSet.next();

        }catch (SQLException ex){
            java.util.logging.Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public void eliminarUsuario(Usuario usuario) {
        DBConnection dataBaseConnection = new DBConnection();

        try (Connection connection = dataBaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setString(1, usuario.getNombreUsuario());


            if (preparedStatement.executeUpdate() > 0) {
                System.out.println("Registro eliminado con éxito.");
            } else {
                System.out.println("No se encontró ningún registro con el nombre de usuario proporcionado.");
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
