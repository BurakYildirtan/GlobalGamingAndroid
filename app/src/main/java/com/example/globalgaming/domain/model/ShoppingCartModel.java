package com.example.globalgaming.domain.model;


import java.io.Serializable;

public class ShoppingCartModel implements Serializable {


    private final int id;
    private int quantity;
    private final ProductModel product;

    public ShoppingCartModel(int id, int quantity, ProductModel product) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
    }

    public int getId() {return id;}

    public ProductModel getProduct() {return product;}

    public int getQuantity() {
        return quantity;
    }

    public void addOne() {
        quantity += 1;
    }

    public void removeOne() {
        quantity -= 1;
    }
}
