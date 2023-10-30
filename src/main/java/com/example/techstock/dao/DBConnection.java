package com.example.techstock.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    private Connection connection;
    private static final String url = "jdbc:mysql://mysqluv2023.ddns.net:3306/techstock?useSSL=false";
    private static final String user = "techstock";
    private static final String password = "Ikmujn19283";

    public static String getUrl(){
        return url;
    }

    public static String getUser(){
        return user;
    }

    public static String getPassword(){
        return password;
    }

    public Connection getConnection() throws SQLException{
        connect();
        return connection;
    }

    private void connect() throws SQLException {
        connection= DriverManager.getConnection(url,user,password);
    }

    public void cerrarConexion(){
        if(connection!=null){
            try {
                if(!connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
