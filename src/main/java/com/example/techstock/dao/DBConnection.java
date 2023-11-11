package com.example.techstock.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;
    private static final String url = "jdbc:mysql://mysqluv2023.ddns.net:3306/techstock?useSSL=false";
    private static final String user = "techstock";
    private static final String password = "Ikmujn19283";



    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(url,user,password);
        return connection;
    }
}
