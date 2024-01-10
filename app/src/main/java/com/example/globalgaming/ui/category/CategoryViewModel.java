package com.example.globalgaming.ui.category;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.globalgaming.common.Constants;
import com.example.globalgaming.common.callbacks.ResultCallback;
import com.example.globalgaming.common.helper.ModelHelpers;
import com.example.globalgaming.common.mapper.Result;
import com.example.globalgaming.domain.model.ProductModel;
import com.example.globalgaming.domain.repository.HardwareRepository;
import com.example.globalgaming.domain.repository.SaleRepository;
import com.example.globalgaming.domain.repository.SoftwareRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryViewModel extends ViewModel {

    private final SaleRepository saleRepository;
    private final SoftwareRepository softwareRepository;
    private final HardwareRepository hardwareRepository;

    private MutableLiveData<Result<List<ProductModel>>> productModelResult = new MutableLiveData<>();


    public CategoryViewModel(SaleRepository saleRepository,
                             SoftwareRepository softwareRepository,
                             HardwareRepository hardwareRepository) {
        this.saleRepository = saleRepository;
        this.softwareRepository = softwareRepository;
        this.hardwareRepository = hardwareRepository;
    }

    public MutableLiveData<Result<List<ProductModel>>> getProductModelResult() {
        return productModelResult;
    }

    public void getProductCategory(String titleKey) {

        ResultCallback<List<ProductModel>> rb = getResultCallback();
        if (titleKey.equals(Constants.TITLE_KEY_SALE)) {
            getSaleProducts(rb);

        } else if (titleKey.equals(Constants.TITLE_KEY_SOFTWARE)) {
            getSoftwareProducts(rb);

        } else { //Hardware
            getHardwareProducts(rb);

        }
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

    private void getSaleProducts(ResultCallback<List<ProductModel>> resultCallback) {
        saleRepository.getSalesAll(resultCallback);
    }

    private void getSoftwareProducts(ResultCallback<List<ProductModel>> resultCallback) {
        softwareRepository.getSoftwareAll(resultCallback);
    }

    private void getHardwareProducts(ResultCallback<List<ProductModel>> resultCallback) {
        hardwareRepository.getHardwareAll(resultCallback);
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
}