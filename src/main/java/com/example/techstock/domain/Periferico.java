package com.example.techstock.domain;

public class Periferico {
    private String numeroSerie;
    private Integer idCentroComputo;
    private String marca;

    public Periferico() {
    }

    public Periferico(String numeroSerie, Integer idCentroComputo, String marca) {
        this.numeroSerie = numeroSerie;
        this.idCentroComputo = idCentroComputo;
        this.marca = marca;
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

    @Override
    public String toString() {
        return "Periferico{" +
                "numeroSerie='" + numeroSerie + '\'' +
                ", idCentroComputo=" + idCentroComputo +
                ", marca='" + marca + '\'' +
                '}';
    }
}
