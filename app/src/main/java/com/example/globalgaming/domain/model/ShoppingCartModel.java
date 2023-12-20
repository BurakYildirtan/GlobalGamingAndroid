package com.example.globalgaming.domain.model;


import java.io.Serializable;

public class ShoppingCartModel<T> implements Serializable {
    private  T modelData;

    private int quantity;

    public ShoppingCartModel(T product, int quantity) {
        this.modelData = product;
        this.quantity = quantity;
    }

    public T getModelData() {
        return modelData;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addOne() {
        quantity += 1;
    }
}
