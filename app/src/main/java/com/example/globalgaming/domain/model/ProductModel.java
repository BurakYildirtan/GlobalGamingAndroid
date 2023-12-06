package com.example.globalgaming.domain.model;

public class ProductModel {
    private final int id;
    private final String title;
    private final Double price;

    private final Double saleInPercent;

    public ProductModel(int id, String title, Double price, Double saleInPercent) {
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
