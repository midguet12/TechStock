package com.example.techstock.domain;

public class EquipoComputo {
    private String numeroSerie;
    private Integer idCentroComputo;
    private String marca;
    private String almacenamiento;
    private String memoria;
    private String procesador;

    public EquipoComputo() {
    }

    public EquipoComputo(String numeroSerie, Integer idCentroComputo, String marca, String almacenamiento, String memoria, String procesador) {
        this.numeroSerie = numeroSerie;
        this.idCentroComputo = idCentroComputo;
        this.marca = marca;
        this.almacenamiento = almacenamiento;
        this.memoria = memoria;
        this.procesador = procesador;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Integer getIdCentroComputo() {
        return idCentroComputo;
    }

    public void setIdCentroComputo(Integer idCentroComputo) {
        this.idCentroComputo = idCentroComputo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(String almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    public String getMemoria() {
        return memoria;
    }

    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }
}
