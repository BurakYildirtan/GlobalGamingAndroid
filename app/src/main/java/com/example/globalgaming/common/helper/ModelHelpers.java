package com.example.globalgaming.common.helper;

import android.annotation.SuppressLint;

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

    public static Boolean isHardware(JSONObject data) {
        return data.has("manufacturer");
    }

    public static List<ProductModel> createProductList(JSONArray result) throws JSONException {
        ArrayList<ProductModel> resultList = new ArrayList<>();

        for(int i = 0; i < result.length(); i++) {
            ProductModel productModel;
            JSONObject productJson = (JSONObject) result.get(i);
            if (ModelHelpers.isSoftware(productJson)) {
                SoftwareModel softwareModel = new SoftwareModel(productJson);
                productModel = new ProductModel(i,softwareModel);
            } else {
                HardwareModel hardwareModel = new HardwareModel(productJson);
                productModel = new ProductModel(i, hardwareModel);
            }
            resultList.add(productModel);
        }
        return resultList;
    }
}
