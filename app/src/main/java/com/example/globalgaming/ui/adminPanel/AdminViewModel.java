package com.example.globalgaming.ui.adminPanel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.globalgaming.common.callbacks.ResultCallback;
import com.example.globalgaming.common.mapper.Result;
import com.example.globalgaming.domain.model.ProductModel;
import com.example.globalgaming.domain.repository.ProductRepository;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import javax.security.auth.callback.Callback;

public class AdminViewModel extends ViewModel {

    private final ProductRepository productRepository;

    public AdminViewModel( ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(JSONObject productData, ResultCallback<ProductModel> resultCallback) {
        productRepository.addProduct(productData, new ResultCallback<ProductModel>() {
            @Override
            public void onSuccess(Result<ProductModel> response) {
                if (response.isSuccess()) {
                    resultCallback.onSuccess(response);
                }
            }

            @Override
            public void onError(Result<ProductModel> error) {
                resultCallback.onError(Result.error(new Exception()));
            }
        });
    }
}
