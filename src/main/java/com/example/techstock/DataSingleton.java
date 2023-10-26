package com.example.techstock;

public class DataSingleton {
    private static final DataSingleton instance = new DataSingleton();
    private Integer idSoftware;

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


}
