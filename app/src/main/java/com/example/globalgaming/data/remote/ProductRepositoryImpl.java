package com.example.globalgaming.data.remote;

import android.view.Display;

import androidx.annotation.NonNull;

import com.example.globalgaming.common.Connection;
import com.example.globalgaming.common.Constants;
import com.example.globalgaming.common.callbacks.ResultCallback;
import com.example.globalgaming.common.helper.ModelHelpers;
import com.example.globalgaming.common.mapper.Result;
import com.example.globalgaming.domain.model.ProductModel;
import com.example.globalgaming.domain.repository.ProductRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public void getProductAll(ResultCallback<List<ProductModel>> resultCallback) {
        OkHttpClient client = Connection.getOkHttpClient();

        String url = Constants.BASE_URL + Constants.PRODUCT_ALL;

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

    @Override
    public void addProduct(JSONObject typedProductData, ResultCallback<ProductModel> resultCallback) {
        String url = Constants.BASE_URL + Constants.PRODUCT_ADD;
            try {
                int productType = typedProductData.getInt(Constants.PRODUCT_MODEL_PRODUCT_TYPE);
                String imgPath = typedProductData.getString(Constants.PRODUCT_MODEL_PIC_PATH);
                String designation =  typedProductData.getString(Constants.PRODUCT_MODEL_DESIGNATION);
                String price =  typedProductData.getString(Constants.PRODUCT_MODEL_PRICE);
                String saleInPercent = typedProductData.getString(Constants.PRODUCT_MODEL_SALE_IN_PERCENT);
                String releaseDate =  typedProductData.getString(Constants.PRODUCT_MODEL_RELEASE_DATE);
                String rating =  typedProductData.getString(Constants.PRODUCT_MODEL_RATING);
                String spec1Key;
                String spec2Key;
                if (productType == Constants.PRODUCT_TYPE_SOFTWARE) {
                    spec1Key = Constants.SOFTWARE_MODEL_GENRE;
                    spec2Key = Constants.SOFTWARE_MODEL_FSK;
                } else {
                    spec1Key = Constants.HARDWARE_MODEL_MANUFACTURER;
                    spec2Key = Constants.HARDWARE_MODEL_TYPE;
                }
                String spec1 = typedProductData.getString(spec1Key);
                String spec2 = typedProductData.getString(spec2Key);

            Connection.performPostRequest(url, new Connection.ResponseCallback() {
                        @Override
                        public void onSuccess(JSONArray response) {
                            try {
                                if (response.length() > 0) {
                                    resultCallback.onSuccess(Result.success(ModelHelpers.createProductList(response).get(0)));
                                } else {
                                    resultCallback.onError(Result.error(new Exception()));
                                }
                            } catch (JSONException e) {
                                resultCallback.onError(Result.error(new Exception()));
                            }
                        }

                        @Override
                        public void onError(Exception e) {
                            resultCallback.onError(Result.error(new Exception()));
                        }
                    }, Constants.PRODUCT_MODEL_PIC_PATH,
                    imgPath,
                    Constants.PRODUCT_MODEL_DESIGNATION,
                    designation,
                    Constants.PRODUCT_MODEL_PRICE,
                    price,
                    Constants.PRODUCT_MODEL_SALE_IN_PERCENT,
                    saleInPercent,
                    Constants.PRODUCT_MODEL_RELEASE_DATE,
                    releaseDate,
                    Constants.PRODUCT_MODEL_RATING,
                    rating,
                    spec1Key,
                    spec1,
                    spec2Key,
                    spec2
            );

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateProduct(JSONObject typedProductData, ResultCallback<ProductModel> resultCallback) {
        String url = Constants.BASE_URL + Constants.PRODUCT_UPDATE;
        try {
            int productType = typedProductData.getInt(Constants.PRODUCT_MODEL_PRODUCT_TYPE);
            String imgPath = typedProductData.getString(Constants.PRODUCT_MODEL_PIC_PATH);
            String designation =  typedProductData.getString(Constants.PRODUCT_MODEL_DESIGNATION);
            String price =  typedProductData.getString(Constants.PRODUCT_MODEL_PRICE);
            String saleInPercent = typedProductData.getString(Constants.PRODUCT_MODEL_SALE_IN_PERCENT);
            String releaseDate =  typedProductData.getString(Constants.PRODUCT_MODEL_RELEASE_DATE);
            String rating =  typedProductData.getString(Constants.PRODUCT_MODEL_RATING);
            String productId = typedProductData.getString(Constants.PRODUCT_MODEL_PRODUCT_ID);
            String spec1Key;
            String spec2Key;
            if (productType == Constants.PRODUCT_TYPE_SOFTWARE) {
                spec1Key = Constants.SOFTWARE_MODEL_GENRE;
                spec2Key = Constants.SOFTWARE_MODEL_FSK;
            } else {
                spec1Key = Constants.HARDWARE_MODEL_MANUFACTURER;
                spec2Key = Constants.HARDWARE_MODEL_TYPE;
            }
            String spec1 = typedProductData.getString(spec1Key);
            String spec2 = typedProductData.getString(spec2Key);

            Connection.performPostRequest(url, new Connection.ResponseCallback() {
                        @Override
                        public void onSuccess(JSONArray response) {
                            try {
                                if (response.length() > 0) {
                                    resultCallback.onSuccess(Result.success(ModelHelpers.createProductList(response).get(0)));
                                } else {
                                    resultCallback.onError(Result.error(new Exception()));
                                }
                            } catch (JSONException e) {
                                resultCallback.onError(Result.error(new Exception()));
                            }
                        }

                        @Override
                        public void onError(Exception e) {
                            resultCallback.onError(Result.error(new Exception()));
                        }
                    }, Constants.PRODUCT_MODEL_PRODUCT_ID,
                    productId,
                    Constants.PRODUCT_MODEL_PIC_PATH,
                    imgPath,
                    Constants.PRODUCT_MODEL_DESIGNATION,
                    designation,
                    Constants.PRODUCT_MODEL_PRICE,
                    price,
                    Constants.PRODUCT_MODEL_SALE_IN_PERCENT,
                    saleInPercent,
                    Constants.PRODUCT_MODEL_RELEASE_DATE,
                    releaseDate,
                    Constants.PRODUCT_MODEL_RATING,
                    rating,
                    spec1Key,
                    spec1,
                    spec2Key,
                    spec2
            );

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
