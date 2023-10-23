package com.example.techstock.dao;

public class DBConnection {
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
}
