package com.example.globalgaming.ui.adminPanel.editProduct;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.globalgaming.common.Constants;
import com.example.globalgaming.common.callbacks.ResultCallback;
import com.example.globalgaming.common.mapper.Result;
import com.example.globalgaming.domain.model.ProductModel;
import com.example.globalgaming.domain.repository.HardwareRepository;
import com.example.globalgaming.domain.repository.ProductRepository;
import com.example.globalgaming.domain.repository.SaleRepository;
import com.example.globalgaming.domain.repository.SoftwareRepository;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdminEditProductViewModel extends ViewModel {

    private final ProductRepository productRepository;
    private MutableLiveData<Result<List<ProductModel>>> productModelResult = new MutableLiveData<>();

    private ResultCallback<List<ProductModel>> getResultCallback() {
        ResultCallback<List<ProductModel>> resultCallback = new ResultCallback<List<ProductModel>>() {
            @Override
            public void onSuccess(Result<List<ProductModel>> response) {
                productModelResult.postValue(response);
            }

            @Override
            public void onError(Result<List<ProductModel>> error) {
                productModelResult.postValue(error);
            }
        };
        return resultCallback;
    }


    public AdminEditProductViewModel(ProductRepository productRepository) {
        this.productRepository = productRepository;
        getAllProducts();
    }

    public void getAllProducts() {
        productRepository.getProductAll(getResultCallback());
    }

    public MutableLiveData<Result<List<ProductModel>>> getProductModelResult() {
        return productModelResult;
    }

    public List<ProductModel> filterList(String s) {
        Result<List<ProductModel>> resultProductList = productModelResult.getValue();
        if (resultProductList != null) {
            if (resultProductList.isSuccess()) {
                return resultProductList.getValue().stream()
                        .filter(item -> item.getDesignation().toLowerCase().contains(s.toLowerCase()))
                        .collect(Collectors.toList());
            }
        }
        return new ArrayList<>();
    }

    public void deleteProduct(JSONObject productData, ResultCallback<String> resultCallback) {
        productRepository.deleteProduct(productData, new ResultCallback<String>() {
            @Override
            public void onSuccess(Result<String> response) {
                resultCallback.onSuccess(response);
            }

            @Override
            public void onError(Result<String> error) {
                resultCallback.onError(error);
            }
        });
    }
}