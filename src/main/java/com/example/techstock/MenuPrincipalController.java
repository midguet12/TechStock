package com.example.techstock;

import com.example.techstock.dao.UsuarioDAO;
import com.example.techstock.domain.CentroComputo;
import com.example.techstock.domain.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuPrincipalController implements Initializable {

    DataSingleton dataSingleton = DataSingleton.getInstance();
    @FXML
    Label usuarioLabel;

    @FXML
    public Button cerrarSesionButton;

    @FXML
    public Button consultarSoftwareButton;
    @FXML
    public Button consultarCentrosComputoButton;
    @FXML
    public Button consultarEquiposComputoButton;
    @FXML
    public Button consultarPerifericosButton;

    @FXML
    public Button administrarUsuariosButton;
    @FXML
    public Button editarPerfilButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = null;
        try {
            usuario = usuarioDAO.read(dataSingleton.getUsuario());
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }

        usuarioLabel.setText(usuarioLabel.getText() + usuario.getNombreCompleto());

        if (!usuario.getAdministrador()){
            administrarUsuariosButton.setDisable(true);
        }

    }


    public void cerrarSesionAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) cerrarSesionButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("IniciarSesion.fxml"));
        stage.setTitle("Iniciar sesion");
        stage.setScene(new Scene(root));
    }

    public void consultarSoftwareAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) consultarSoftwareButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ModuloInventarioSoftware/Software/LeerSoftware.fxml"));
        stage.setTitle("Software");
        stage.setScene(new Scene(root));
    }

    public void consultarCentrosComputoAction(ActionEvent actionEvent) throws IOException{
        Stage stage = (Stage) consultarCentrosComputoButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ModuloInventarioHardware/CentroComputo/ConsultarCentroComputo.fxml"));
        stage.setTitle("Centro computo");
        stage.setScene(new Scene(root));
    }

    public void consultarEquiposComputoAction(ActionEvent actionEvent) throws IOException{
        CentroComputo centroComputo = null;
        dataSingleton.setCentroComputo(centroComputo);

        Stage stage = (Stage) consultarEquiposComputoButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ModuloInventarioHardware/EquipoComputo/ConsultarEquipoComputo.fxml"));
        stage.setTitle("Equipo computo");
        stage.setScene(new Scene(root));

    }

    public void consultarPerifericosAction(ActionEvent actionEvent) throws IOException{
        Stage stage = (Stage) consultarPerifericosButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ModuloInventarioHardware/Perifericos/AdministrarPerifericos.fxml"));
        stage.setTitle("Administrar perifericos");
        stage.setScene(new Scene(root));
    }

    public void administrarUsuariosAction(ActionEvent actionEvent) throws IOException{
        Stage stage = (Stage) administrarUsuariosButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ModuloUsuarios/Usuarios-Vista.fxml"));
        stage.setTitle("Administrar usuarios");
        stage.setScene(new Scene(root));
    }

    public void editarPerfilAction(ActionEvent actionEvent) throws IOException{

        dataSingleton.setUsuario(dataSingleton.getUsuario());

        Stage stage = (Stage) editarPerfilButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ModuloUsuarios/EditarPerfil.fxml"));
        stage.setTitle("Editar perfil");
        stage.setScene(new Scene(root));


    }
}
