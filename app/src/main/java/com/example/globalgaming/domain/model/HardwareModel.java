package com.example.globalgaming.domain.model;

import org.json.JSONException;
import org.json.JSONObject;

public class HardwareModel extends ProductModel {
    private final int productId;
    private final String type;
    private final String manufacturer;

    public HardwareModel(int id,
                         int productType,
                         int productId,
                         String designation,
                         Double price,
                         Double saleInPercent,
                         Double rating,
                         String picPath,
                         String releaseDate,
                         String type,
                         String manufacturer
                         ) {
        super(id, productType, designation, price, saleInPercent, picPath, releaseDate, rating);
        this.productId = productId;
        this.type = type;
        this.manufacturer = manufacturer;
    }

    public int getProductId() {
        return productId;
    }

    public String getType() {
        return type;
    }

    public String getManufacturer() {
        return manufacturer;
    }
}
