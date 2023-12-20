package com.example.globalgaming.domain.model;


import java.io.Serializable;

public class ProductModel<T> implements Serializable {
    private final int id;

    private  T modelData;

    public ProductModel(int id, HardwareModel hardwareModel) {
        this.id = id;
        this.modelData = (T) hardwareModel;
    }

    public ProductModel(int id, SoftwareModel softwareModel) {
        this.id = id;
        this.modelData = (T) softwareModel;
    }

    public int getId() {
        return id;
    }

    public T getModelData() {
        return modelData;
    }
}
