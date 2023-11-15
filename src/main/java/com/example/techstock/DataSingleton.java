package com.example.techstock;

public class DataSingleton {
    private static final DataSingleton instance = new DataSingleton();
    private Integer idSoftware;
    private String usuario;

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
}
