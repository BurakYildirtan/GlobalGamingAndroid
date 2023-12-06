package com.example.globalgaming.data.remote;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.globalgaming.common.Connection;
import com.example.globalgaming.common.Constants;
import com.example.globalgaming.common.Result;
import com.example.globalgaming.common.callbacks.ResultCallback;
import com.example.globalgaming.domain.model.UserModel;
import com.example.globalgaming.domain.repository.UserRepository;

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
        OkHttpClient client = Connection.getOkHttpClient();

        String url = Constants.BASE_URL + Constants.USER_LOGIN;

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                responseCallback.onError(Result.error(e));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                    String responseBody = response.body().string();
                    JSONArray result = new JSONArray(responseBody);
                    //TODO Test
                    JSONObject user1 = (JSONObject) result.get(0);
                    UserModel userModel = new UserModel(user1);


                    if (userModel.getUserName().equals(typedUserData.getString(Constants.USER_MODEL_USER_NAME)) && userModel.getPassword().equals(typedUserData.getString(Constants.USER_MODEL_PASSWORD))) {
                        responseCallback.onSuccess(Result.success(userModel));
                    }
                    else {
                        responseCallback.onError(Result.error(new Exception()));
                    }
                    //TODO test ende
                    Log.d("usersAll", "Success " + response);

                } catch (JSONException e) {
//                    throw new RuntimeException(e);
                }
            }
        });
    }
}



//        try {
//        JSONArray response = Connection.performRequestBaseUrl(Constants.USER_LOGIN);
//
//        //TODO Test
//        JSONObject user1 = (JSONObject) response.get(0);
//        UserModel userModel = new UserModel(user1);
//        //TODO test ende
//
//        Result<UserModel> userResult = Result.success(userModel);
//        Log.d("usersAll", "Success "+ response);
//        responseCallback.onSuccess(userResult);
//    } catch(Exception e) {
//        Result<UserModel> errorResult = Result.error(e);
//        Log.d("usersAll", "Error "+ e);
//        responseCallback.onError(errorResult);
//    }
