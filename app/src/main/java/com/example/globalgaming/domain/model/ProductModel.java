package com.example.globalgaming.domain.model;


import java.io.Serializable;

public class ProductModel implements Serializable {
    private final int id;
    private final int productType;
    private final String designation;
    private final Double price;
    private final Double saleInPercent;
    private final String picPath;
    private final String releaseDate;
    private final Double rating;

    public ProductModel(int id,
                        int productType,
                        String designation,
                        Double price,
                        Double saleInPercent,
                        String picPath,
                        String releaseDate,
                        Double rating) {
        this.id = id;
        this.productType = productType;
        this.designation = designation;
        this.price = price;
        this.saleInPercent = saleInPercent;
        this.picPath = picPath;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public int getProductType() {
        return productType;
    }

    public String getDesignation() {
        return designation;
    }

    public Double getPrice() {
        return price;
    }

    public Double getSaleInPercent() {
        return saleInPercent;
    }

    public String getPicPath() {
        return picPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Double getRating() {
        return rating;
    }

    public boolean isInSale() {
        return saleInPercent != 0.0;
    }
}
