package com.example.globalgaming.domain.model;

import org.json.JSONException;
import org.json.JSONObject;

public class HardwareModel {
    private final int productId;
    private final String designation;
    private final Double price;
    private final Double saleInPercent;
    private final String type;
    private final Double rating;
    private final String picPath;
    private final String releaseDate;
    private final String manufacturer;

    public HardwareModel(int productId, String designation, Double price, Double saleInPercent, String type, Double rating, String picPath, String releaseDate, String manufacturer) {
        this.productId = productId;
        this.designation = designation;
        this.price = price;
        this.saleInPercent = saleInPercent;
        this.type = type;
        this.rating = rating;
        this.picPath = picPath;
        this.releaseDate = releaseDate;
        this.manufacturer = manufacturer;
    }

    public HardwareModel(JSONObject hardwareData) {
        try {
            this.productId = hardwareData.getInt("productId");
            this.designation = hardwareData.getString("designation");
            this.price = hardwareData.getDouble("price");
            this.saleInPercent = hardwareData.getDouble("saleInPercent");
            this.type = hardwareData.getString("type");
            this.rating = hardwareData.getDouble("rating");
            this.picPath = hardwareData.getString("picPath");
            this.releaseDate = hardwareData.getString("releaseDate");
            this.manufacturer = hardwareData.getString("manufacturer");
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

    public String getType() {
        return type;
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

    public String getManufacturer() {
        return manufacturer;
    }
}
