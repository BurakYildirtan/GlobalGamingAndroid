package com.example.globalgaming.data.remote;

import androidx.annotation.NonNull;

import com.example.globalgaming.common.Connection;
import com.example.globalgaming.common.Constants;
import com.example.globalgaming.common.callbacks.ResultCallback;
import com.example.globalgaming.common.helper.ModelHelpers;
import com.example.globalgaming.common.mapper.Result;
import com.example.globalgaming.domain.model.ProductModel;
import com.example.globalgaming.domain.repository.HardwareRepository;
import com.example.globalgaming.domain.repository.SaleRepository;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HardwareRepositoryImpl implements HardwareRepository {

    @Override
    public void getHardwareAll(ResultCallback<List<ProductModel>> resultCallback) {
        OkHttpClient client = Connection.getOkHttpClient();

        String url = Constants.BASE_URL + Constants.HARDWARE_ALL;

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                resultCallback.onError(Result.error(e));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                    String responseBody = response.body().string();
                    JSONArray result = new JSONArray(responseBody);
                    List<ProductModel> resultList = ModelHelpers.createProductList(result);

                    resultCallback.onSuccess(Result.success(resultList));

                } catch (JSONException e) {
                    resultCallback.onError(Result.error(e));
                }
            }
        });
    }
}
