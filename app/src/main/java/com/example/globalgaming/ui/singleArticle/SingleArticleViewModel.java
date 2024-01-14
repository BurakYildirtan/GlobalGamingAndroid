package com.example.globalgaming.ui.singleArticle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.globalgaming.R;
import com.example.globalgaming.common.callbacks.ResultCallback;
import com.example.globalgaming.common.mapper.Result;
import com.example.globalgaming.domain.model.HardwareModel;
import com.example.globalgaming.domain.model.ProductModel;
import com.example.globalgaming.domain.repository.HardwareRepository;

import java.util.List;

public class SingleArticleViewModel extends ViewModel {
    private HardwareRepository hardwareRepository;

    public SingleArticleViewModel(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    public void getMinimumRequirements(String rating,ResultCallback<List<ProductModel>> listResultCallback) {
        hardwareRepository.getMinimumRequirements(rating, new ResultCallback<List<ProductModel>>() {
            @Override
            public void onSuccess(Result<List<ProductModel>> response) {
                listResultCallback.onSuccess(response);
            }

            @Override
            public void onError(Result<List<ProductModel>> error) {
                listResultCallback.onError(error);
            }
        });

    }

    public void getRecommendedRequirements(String rating,ResultCallback<List<ProductModel>> listResultCallback) {

        hardwareRepository.getRecommendedRequirements(rating, new ResultCallback<List<ProductModel>>() {
            @Override
            public void onSuccess(Result<List<ProductModel>> response) {
                listResultCallback.onSuccess(response);
            }

            @Override
            public void onError(Result<List<ProductModel>> error) {
                listResultCallback.onError(error);
            }
        });

    }

}