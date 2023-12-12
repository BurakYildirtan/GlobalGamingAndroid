package com.example.globalgaming.domain.model;

import org.json.JSONException;
import org.json.JSONObject;

public class SoftwareModel {
    private final int productId;
    private final String designation;
    private final Double price;
    private final Double saleInPercent;
    private final String genre;
    private final Double rating;
    private final String picPath;
    private final String releaseDate;
    private final int fsk;

    public SoftwareModel(int productId, String designation, Double price, Double saleInPercent, String genre, Double rating, String picPath, String releaseDate, int fsk) {
        this.productId = productId;
        this.designation = designation;
        this.price = price;
        this.saleInPercent = saleInPercent;
        this.genre = genre;
        this.rating = rating;
        this.picPath = picPath;
        this.releaseDate = releaseDate;
        this.fsk = fsk;
    }

    public SoftwareModel(JSONObject hardwareData) {
        try {
            this.productId = hardwareData.getInt("productId");
            this.designation = hardwareData.getString("designation");
            this.price = hardwareData.getDouble("price");
            this.saleInPercent = hardwareData.getDouble("saleInPercent");
            this.genre = hardwareData.getString("genre");
            this.rating = hardwareData.getDouble("rating");
            this.picPath = hardwareData.getString("picPath");
            this.releaseDate = hardwareData.getString("releaseDate");
            this.fsk = hardwareData.getInt("fsk");
        } catch (JSONException e) {
            throw new RuntimeException();
        }
    }

    public int getProductId() {
        return productId;
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

    public String getGenre() {
        return genre;
    }

    public Double getRating() {
        return rating;
    }

    public String getPicPath() {
        return picPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getFsk() {
        return fsk;
    }
}
