package com.example.techstock.dao;

import com.example.techstock.domain.Usuario;
import com.example.techstock.dao.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {

    final String INSERT = "INSERT INTO usuario(nombreUsuario, contrasena) VALUES (?,?)";
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
}
