package com.example.techstock;

import com.example.techstock.domain.CentroComputo;
import com.example.techstock.domain.Software;

public class DataSingleton {
    private static final DataSingleton instance = new DataSingleton();
    private Integer idSoftware;
    private String usuario;
    private String numeroSerie;
    private Integer idCentroComputo;
    private CentroComputo centroComputo;
    private Software software;

    public Software getSoftware() {
        return software;
    }

    public void setSoftware(Software software) {
        this.software = software;
    }

    public CentroComputo getCentroComputo() {
        return centroComputo;
    }

    public void setCentroComputo(CentroComputo centroComputo) {
        this.centroComputo = centroComputo;
    }

    public DataSingleton() {
    }

    public static DataSingleton getInstance(){
        return instance;
    }

    public Integer getIdSoftware(){
        return idSoftware;
    }

    public void setIdSoftware(Integer idSoftware){
        this.idSoftware = idSoftware;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
}
