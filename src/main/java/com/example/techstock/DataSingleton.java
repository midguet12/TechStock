package com.example.techstock;

public class DataSingleton {
    private static final DataSingleton instance = new DataSingleton();
    private Integer idCentroComputo;
    private String numeroSerie;
    private String marca;
    private String almacenamiento;
    private String memoria;
    private String procesador;

    public DataSingleton() {
    }

    public static DataSingleton getInstance(){
        return instance;
    }

    public Integer getIdCentroComputo() {
        return idCentroComputo;
    }

    public void setIdCentroComputo(Integer idCentroComputo) {
        this.idCentroComputo = idCentroComputo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
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
