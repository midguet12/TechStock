package com.example.techstock.domain;

public class Usuario {
    private String nombre;
    private String contrasena;

    // Constructor
    public Usuario(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
    }
    public Usuario() {
    }

    // Getter para el nombre del usuario
    public String getNombreUsuario() {
        return nombre;
    }

    // Setter para el nombre del usuario
    public void setNombreUsuario(String nombre) {
        this.nombre = nombre;
    }

    // Getter para la contraseña del usuario
    public String getContrasena() {
        return contrasena;
    }

    // Setter para la contraseña del usuario
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}

