package com.example.globalgaming.domain.model;

import androidx.annotation.Nullable;

public class Product {
    private int id;
    private String title;
    private Double price;

    private Double saleInPercent;

    public Product(int id, String title, Double price, Double saleInPercent) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.saleInPercent = saleInPercent;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public Double getSaleInPercent() {
        return saleInPercent;
    }
}
