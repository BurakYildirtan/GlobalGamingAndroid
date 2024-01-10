package com.example.globalgaming.common.helper;

import android.annotation.SuppressLint;

import com.example.globalgaming.common.Constants;
import com.example.globalgaming.domain.model.HardwareModel;
import com.example.globalgaming.domain.model.ProductModel;
import com.example.globalgaming.domain.model.SoftwareModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModelHelpers {

    public static Boolean isSoftware(JSONObject data) {
        return data.has("genre");
    }

    public static List<ProductModel> createProductList(JSONArray result) throws JSONException {
        ArrayList<ProductModel> resultList = new ArrayList<>();

        for(int i = 0; i < result.length(); i++) {
            JSONObject productJson = (JSONObject) result.get(i);

            int productId = productJson.getInt("productId");
            String designation = productJson.getString("designation");
            Double price = productJson.getDouble("price");
            Double saleInPercent = productJson.getDouble("saleInPercent");
            Double rating = productJson.getDouble("rating");
            String picPath = productJson.getString("picPath");
            String releaseDate = productJson.getString("releaseDate");

            int productType;
            ProductModel product;
            if (ModelHelpers.isSoftware(productJson)) {
                productType = Constants.PRODUCT_TYPE_SOFTWARE;
                String genre = productJson.getString("genre");
                int fsk = productJson.getInt("fsk");
                product = new SoftwareModel(i, productType, productId, designation, price, saleInPercent, genre, rating, picPath, releaseDate, fsk);
            } else {
                productType = Constants.PRODUCT_TYPE_HARDWARE;
                String type = productJson.getString("type");
                String manufacturer = productJson.getString("manufacturer");
                product = new HardwareModel(i, productType, productId, designation, price, saleInPercent, rating, picPath, releaseDate, type, manufacturer);
            }
            resultList.add(product);
        }
        return resultList;
    }
}
