package com.example.techstock.logic.Usuarios;

import com.example.techstock.dao.UsuarioDAO;
import com.example.techstock.domain.Usuario;
import com.example.techstock.logic.Log.LogWriting;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;

public class UsuarioLogic {
    public static Boolean autorizar(String nombreUsuarioIntroducido, String contrasenaIntroducida) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = null;
        Boolean logged = null;

        String contrasenaCifrada = DigestUtils.sha256Hex(contrasenaIntroducida);

        try {
            usuario = usuarioDAO.read(nombreUsuarioIntroducido);

            if (usuario.getContrasena().equals(contrasenaCifrada))
                logged = true;
            else
                logged = false;

        } catch (SQLException exception) {
            LogWriting.writeLog(exception.getMessage());
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        } finally {
            return logged;
        }
    }

    public static Integer cambiarContrasena(String nombreUsuario, String actualContrasena, String nuevaContrasena, String confirmacionNuevaContrasena) throws SQLException{
        Integer resultado = 0;

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.read(nombreUsuario);

        String contrasenaIngresadaActualCifrada = DigestUtils.sha256Hex(actualContrasena);

        if (usuario.getContrasena().equals(contrasenaIngresadaActualCifrada)){
            if (confirmacionNuevaContrasena.equals(nuevaContrasena)){
                String contrasenaNuevaCifrada = DigestUtils.sha256Hex(nuevaContrasena);
                usuario.setContrasena(contrasenaNuevaCifrada);

                if (usuarioDAO.update(nombreUsuario, usuario)){
                    resultado = 1;
                }
            } else {
                resultado = 2;
            }
        } else {
            resultado = 3;
        }
        return resultado;
    }

    public static Integer actualizarUsuario(String nombreUsuarioActual, String nombreUsuarioNuevo, String nombreCompletoNuevo) throws SQLException {
        Integer resultado = 0;

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.read(nombreUsuarioActual);

        usuario.setNombreUsuario(nombreUsuarioNuevo);
        usuario.setNombreCompleto(nombreCompletoNuevo);

        if (usuarioDAO.update(nombreUsuarioActual, usuario)){
            resultado = 1;
        }
        return resultado;
    }



}
