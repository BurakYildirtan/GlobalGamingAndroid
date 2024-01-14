package com.example.globalgaming.data.remote;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.globalgaming.common.Connection;
import com.example.globalgaming.common.Constants;
import com.example.globalgaming.common.mapper.Result;
import com.example.globalgaming.common.callbacks.ResultCallback;
import com.example.globalgaming.domain.model.UserModel;
import com.example.globalgaming.domain.repository.UserRepository;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public void loginUser(JSONObject typedUserData, ResultCallback<UserModel> responseCallback) {
        String url = Constants.BASE_URL + Constants.USER_LOGIN;

        try {
            String email = typedUserData.getString(Constants.USER_MODEL_EMAIL);
            String password = typedUserData.getString(Constants.USER_MODEL_PASSWORD);

            Connection.performPostRequest(url, new Connection.ResponseCallback() {
                @Override
                public void onSuccess(JSONArray response) {
                    try {
                        if (response.length() > 0) {
                            UserModel userModel = new UserModel( (JSONObject) response.get(0));
                            responseCallback.onSuccess(Result.success(userModel));
                        } else {
                            responseCallback.onError(Result.error(new Exception()));
                        }
                    } catch (JSONException e) {
                        responseCallback.onError(Result.error(new Exception()));
                    }
                }

                @Override
                public void onError(Exception e) {
                    responseCallback.onError(Result.error(new Exception()));
                }
            }, "email", email, "password", password);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void registerUser(JSONObject typedUserData, ResultCallback<UserModel> responseCallback) {
        String url = Constants.BASE_URL + Constants.USER_REGISTRATION;

        try {
            String userName = typedUserData.getString(Constants.USER_MODEL_USER_NAME);
            String bDay = typedUserData.getString(Constants.USER_MODEL_BIRTHDAY);
            String email = typedUserData.getString(Constants.USER_MODEL_EMAIL);
            String street = typedUserData.getString(Constants.USER_MODEL_STREET);
            int postalCode = typedUserData.getInt(Constants.USER_MODEL_POSTAL_CODE);
            String city = typedUserData.getString(Constants.USER_MODEL_CITY);
            String password = typedUserData.getString(Constants.USER_MODEL_PASSWORD);

            Connection.performPostRequest(url, new Connection.ResponseCallback() {
                        @Override
                        public void onSuccess(JSONArray response) {
                            try {
                                if (response.length() > 0) {
                                    UserModel userModel = new UserModel( (JSONObject) response.get(0));
                                    responseCallback.onSuccess(Result.success(userModel));
                                } else {
                                    responseCallback.onError(Result.error(new Exception()));
                                }
                            } catch (JSONException e) {
                                responseCallback.onError(Result.error(new Exception()));
                            }
                        }

                        @Override
                        public void onError(Exception e) {
                            responseCallback.onError(Result.error(new Exception()));
                        }
                    }, Constants.USER_MODEL_EMAIL,
                    email,
                    Constants.USER_MODEL_PASSWORD,
                    password,
                    Constants.USER_MODEL_USER_NAME,
                    userName,
                    Constants.USER_MODEL_BIRTHDAY,
                    bDay,
                    Constants.USER_MODEL_STREET,
                    street,
                    Constants.USER_MODEL_POSTAL_CODE,
                    String.valueOf(postalCode),
                    Constants.USER_MODEL_CITY,
                    city
            );

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getUser(int id, ResultCallback<UserModel> resultCallback) {
        String url = Constants.BASE_URL + Constants.USER_GET + "/" + id;

        Connection.getResponse(url, new Connection.ResponseCallback() {
            @Override
            public void onSuccess(JSONArray response) {
                if (response.length() > 0) {
                    try {
                        UserModel userModel = new UserModel((JSONObject) response.get(0));
                        resultCallback.onSuccess(Result.success(userModel));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    resultCallback.onError(Result.error(new Exception()));
                }
            }

            @Override
            public void onError(Exception e) {
                resultCallback.onError(Result.error(e));
            }
        });



    }

    @Override
    public void updateUser(JSONObject userData, ResultCallback<Boolean> resultCallback) {
        String url = Constants.BASE_URL + Constants.USER_UPDATE;

        try {
            int uId = userData.getInt(Constants.USER_MODEL_ID);
            String userName = userData.getString(Constants.USER_MODEL_USER_NAME);
            String bDay = userData.getString(Constants.USER_MODEL_BIRTHDAY);
            String email = userData.getString(Constants.USER_MODEL_EMAIL);
            String street = userData.getString(Constants.USER_MODEL_STREET);
            int postalCode = userData.getInt(Constants.USER_MODEL_POSTAL_CODE);
            String city = userData.getString(Constants.USER_MODEL_CITY);
            String password = userData.getString(Constants.USER_MODEL_PASSWORD);
            String role = userData.getString(Constants.USER_MODEL_ROLE);

            Connection.performPostRequest(url, new Connection.ResponseCallback() {
                @Override
                public void onSuccess(JSONArray response) {
                    if (response.length() > 0) {
                        resultCallback.onSuccess(Result.success(true));
                    } else {
                        resultCallback.onError(Result.error(new Exception()));
                    }
                }

                @Override
                public void onError(Exception e) {
                    resultCallback.onError(Result.error(new Exception()));
                }
            }, Constants.USER_MODEL_EMAIL,
                    email,
                    Constants.USER_MODEL_PASSWORD,
                    password,
                    Constants.USER_MODEL_USER_NAME,
                    userName,
                    Constants.USER_MODEL_BIRTHDAY,
                    bDay,
                    Constants.USER_MODEL_ID,
                    String.valueOf(uId),
                    Constants.USER_MODEL_STREET,
                    street,
                    Constants.USER_MODEL_POSTAL_CODE,
                    String.valueOf(postalCode),
                    Constants.USER_MODEL_CITY,
                    city,
                    Constants.USER_MODEL_ROLE,
                    role
                    );

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
