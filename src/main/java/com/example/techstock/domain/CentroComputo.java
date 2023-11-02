package com.example.techstock.domain;

public class CentroComputo {

     private String nombre;
     private Integer idCentroComputo;

    public CentroComputo() {

    }

    public CentroComputo(String nombre, Integer idCentroComputo) {
        this.nombre = nombre;
        this.idCentroComputo = idCentroComputo;
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

    public Integer getIdCentroComputo() {
        return idCentroComputo;
    }

    public void setIdCentroComputo(Integer idCentroComputo) {
        this.idCentroComputo = idCentroComputo;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
