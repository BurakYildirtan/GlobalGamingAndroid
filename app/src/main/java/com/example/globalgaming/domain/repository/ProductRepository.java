package com.example.globalgaming.domain.repository;

import com.example.globalgaming.common.callbacks.ResultCallback;
import com.example.globalgaming.domain.model.HardwareModel;
import com.example.globalgaming.domain.model.ProductModel;
import com.example.globalgaming.domain.model.UserModel;

import org.json.JSONObject;

import java.util.List;

public interface ProductRepository {
    void getProductAll(ResultCallback<List<ProductModel>> resultCallback);

    void addProduct(JSONObject typedUserData, ResultCallback<ProductModel> resultCallback);

    void updateProduct(JSONObject productData, ResultCallback<ProductModel> resultCallback);

    void deleteProduct(JSONObject productData, ResultCallback<String> resultCallback);
}
