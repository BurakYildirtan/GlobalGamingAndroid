package com.example.globalgaming.domain.repository;

import com.example.globalgaming.common.callbacks.ResultCallback;
import com.example.globalgaming.domain.model.ProductModel;
import com.example.globalgaming.domain.model.SoftwareModel;

import org.json.JSONObject;

import java.util.List;

public interface HardwareRepository {

    void getHardwareAll(ResultCallback<List<ProductModel>> resultCallback);

    void getRecommendedRequirements(String rating, ResultCallback<List<ProductModel>> resultCallback);

    void getMinimumRequirements(String rating, ResultCallback<List<ProductModel>> resultCallback);
}
