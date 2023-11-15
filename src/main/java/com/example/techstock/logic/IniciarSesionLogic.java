package com.example.techstock.logic;

import com.example.techstock.dao.UsuarioDAO;
import com.example.techstock.domain.Usuario;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;

public class IniciarSesionLogic {

    public IniciarSesionLogic() {

    }

    public boolean autorizar(String nombreUsuarioIntroducido, String contrasenaIntroducida){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = null;
        boolean logged = false;

        String contrasenaCifrada = DigestUtils.sha256Hex(contrasenaIntroducida);

        try {
            usuario = usuarioDAO.read(nombreUsuarioIntroducido);

            if (usuario.getContrasena().equals(contrasenaCifrada))
                logged = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return logged;
        }
    }

}
