package com.example.techstock.domain;

public class CentroComputo {

    private String nombre;

    public CentroComputo() {

    }
    public CentroComputo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
