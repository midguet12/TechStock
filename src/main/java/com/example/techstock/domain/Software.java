package com.example.techstock.domain;

public class Software {
    private Integer idSoftware;
    private String nombre;
    private String version;

    public Software() {
    }

    public Software(Integer idSoftware, String nombre, String version) {
        this.idSoftware = idSoftware;
        this.nombre = nombre;
        this.version = version;
    }

    public Integer getIdSoftware() {
        return idSoftware;
    }

    public void setIdSoftware(Integer idSoftware) {
        this.idSoftware = idSoftware;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
