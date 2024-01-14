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

            int productId = productJson.getInt(Constants.PRODUCT_MODEL_PRODUCT_ID);
            String designation = productJson.getString(Constants.PRODUCT_MODEL_DESIGNATION);
            Double price = productJson.getDouble(Constants.PRODUCT_MODEL_PRICE);
            Double saleInPercent = productJson.getDouble(Constants.PRODUCT_MODEL_SALE_IN_PERCENT);
            Double rating = productJson.getDouble(Constants.PRODUCT_MODEL_RATING);
            String picPath = productJson.getString(Constants.PRODUCT_MODEL_PIC_PATH);
            String releaseDate = productJson.getString(Constants.PRODUCT_MODEL_RELEASE_DATE);

            int productType;
            ProductModel product;
            if (ModelHelpers.isSoftware(productJson)) {
                productType = Constants.PRODUCT_TYPE_SOFTWARE;
                String genre = productJson.getString(Constants.SOFTWARE_MODEL_GENRE);
                int fsk = productJson.getInt(Constants.SOFTWARE_MODEL_FSK);
                product = new SoftwareModel(i, productType, productId, designation, price, saleInPercent, genre, rating, picPath, releaseDate, fsk);
            } else {
                productType = Constants.PRODUCT_TYPE_HARDWARE;
                String type = productJson.getString(Constants.HARDWARE_MODEL_TYPE);
                String manufacturer = productJson.getString(Constants.HARDWARE_MODEL_MANUFACTURER);
                product = new HardwareModel(i, productType, productId, designation, price, saleInPercent, rating, picPath, releaseDate, type, manufacturer);
            }
            resultList.add(product);
        }
        return resultList;
    }

    public static JSONObject createJsonOfProduct(ProductModel product) throws JSONException {
        JSONObject resultJson = new JSONObject();
        resultJson.put(Constants.PRODUCT_MODEL_DESIGNATION, product.getDesignation());
        resultJson.put(Constants.PRODUCT_MODEL_PRICE, String.valueOf(product.getPrice()));
        resultJson.put(Constants.PRODUCT_MODEL_SALE_IN_PERCENT, String.valueOf(product.getSaleInPercent()));
        resultJson.put(Constants.PRODUCT_MODEL_RATING, String.valueOf(product.getRating()));
        resultJson.put(Constants.PRODUCT_MODEL_PIC_PATH, product.getPicPath());
        resultJson.put(Constants.PRODUCT_MODEL_RELEASE_DATE, product.getReleaseDate());

//        int productType;
//        if (ModelHelpers.isSoftware(productJson)) {
//            productType = Constants.PRODUCT_TYPE_SOFTWARE;
//            String genre = productJson.getString(Constants.SOFTWARE_MODEL_GENRE);
//            int fsk = productJson.getInt(Constants.SOFTWARE_MODEL_FSK);
//            product = new SoftwareModel(i, productType, productId, designation, price, saleInPercent, genre, rating, picPath, releaseDate, fsk);
//        } else {
//            productType = Constants.PRODUCT_TYPE_HARDWARE;
//            String type = productJson.getString(Constants.HARDWARE_MODEL_TYPE);
//            String manufacturer = productJson.getString(Constants.HARDWARE_MODEL_MANUFACTURER);
//            product = new HardwareModel(i, productType, productId, designation, price, saleInPercent, rating, picPath, releaseDate, type, manufacturer);
//        }
//        resultList.add(product);


        return null;
    }


}
