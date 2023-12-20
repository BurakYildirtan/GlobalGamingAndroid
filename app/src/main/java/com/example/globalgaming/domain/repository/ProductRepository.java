package com.example.globalgaming.domain.repository;

import com.example.globalgaming.common.callbacks.ResultCallback;
import com.example.globalgaming.domain.model.HardwareModel;
import com.example.globalgaming.domain.model.ProductModel;

import java.util.List;

public interface ProductRepository {
    void getProductAll(ResultCallback<List<ProductModel>> resultCallback);
}
