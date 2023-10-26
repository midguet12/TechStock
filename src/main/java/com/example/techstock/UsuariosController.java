package com.example.techstock;


import com.example.techstock.dao.UsuarioDAO;
import com.example.techstock.domain.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.apache.commons.codec.digest.DigestUtils;


public class UsuariosController{
        @FXML
        private Label welcomeText;

        @FXML
        protected void onPruebaButtonClick() {
            String message = "";
            try{
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuario = new Usuario("Quacker", DigestUtils.sha256Hex("elpato"));

                //PRUEBAS PARA INSERTAR USUARIO
                /*
                if (!usuarioDAO.UsuarioExiste(usuario)) {
                    usuarioDAO.insertarUsuario(usuario);
                }
                */
                //PRUEBAS PARA ELIMINAR USUARIO
                /*
                if (usuarioDAO.UsuarioExiste(usuario)) {
                    usuarioDAO.eliminarUsuario(usuario);
                }
                */

                //usuarioDAO.insertarUsuario(usuario);

                //PRUEBAS PARA CONSULTA DE USUARIO
                /*
                for (Usuario user : usuarioDAO.consultarUsuario()) {
                    System.out.println(user.toString());
                }
                */
            } catch (Exception e){
                System.out.println(e.toString());
            }

            welcomeText.setText(message);
        }
    }
