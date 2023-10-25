package com.example.techstock;


import com.example.techstock.dao.UsuarioDAO;
import com.example.techstock.domain.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UsuariosController{
        @FXML
        private Label welcomeText;

        @FXML
        protected void onPruebaButtonClick() {
            String message = "";
            try{
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuario = new Usuario("Quacko", "elpato");

                usuarioDAO.insertarUsuario(usuario);

            } catch (Exception e){
                System.out.println(e.toString());
            }

            welcomeText.setText(message);
        }
    }
