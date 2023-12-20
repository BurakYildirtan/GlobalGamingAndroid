package com.example.globalgaming.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.globalgaming.common.Constants;
import com.example.globalgaming.common.callbacks.ResultCallback;
import com.example.globalgaming.common.mapper.Result;
import com.example.globalgaming.domain.model.ProductModel;
import com.example.globalgaming.domain.repository.ProductRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {


    private ProductRepository productRepository;

    private MutableLiveData<Result<List<ProductModel>>> productModelResult = new MutableLiveData<>();

    public HomeViewModel(ProductRepository productRepository) {
        this.productRepository = productRepository;
        getProductsAll();
    }


    public MutableLiveData<Result<List<ProductModel>>> getProductModelResult() {
        return productModelResult;
    }

    private void getProductsAll() {
        ResultCallback<List<ProductModel>> rb = getResultCallback();
        getProducts(rb);
    }

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

    private void getProducts(ResultCallback<List<ProductModel>> resultCallback) {
        productRepository.getProductAll(resultCallback);
    }
}