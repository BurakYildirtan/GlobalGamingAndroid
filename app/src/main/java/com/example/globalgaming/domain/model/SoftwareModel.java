package com.example.globalgaming.domain.model;

import org.json.JSONException;
import org.json.JSONObject;

public class SoftwareModel extends ProductModel{
    private final int productId;
    private final String genre;
    private final int fsk;

    public SoftwareModel(int id,
                         int productType,
                         int productId,
                         String designation,
                         Double price,
                         Double saleInPercent,
                         String genre,
                         Double rating,
                         String picPath,
                         String releaseDate,
                         int fsk) {
        super(id, productType, designation, price, saleInPercent, picPath, releaseDate, rating);
        this.productId = productId;
        this.genre = genre;
        this.fsk = fsk;
    }

    public int getProductId() {
        return productId;
    }

    public String getGenre() {
        return genre;
    }

    public int getFsk() {
        return fsk;
    }
}

