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
    public void loginUser(JSONObject typedUserData, ResultCallback<UserModel> resultCallback) {
        OkHttpClient client = Connection.getOkHttpClient();
        String url = Constants.BASE_URL + Constants.USER_ALL;

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
                    //TODO Test
                    JSONObject user1 = (JSONObject) result.get(1);
                    UserModel userModel = new UserModel(user1);


                    if (userModel.getEmail().equals(typedUserData.getString(Constants.USER_MODEL_EMAIL)) && userModel.getPassword().equals(typedUserData.getString(Constants.USER_MODEL_PASSWORD))) {
                        resultCallback.onSuccess(Result.success(userModel));
                    }
                    else {
                        resultCallback.onError(Result.error(new Exception()));
                    }
                    //TODO test ende
                    Log.d("usersAll", "Success " + response);

                } catch (JSONException e) {
//                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void loginUserNew(JSONObject typedUserData, ResultCallback<UserModel> responseCallback) {
        String url = Constants.BASE_URL + Constants.USER_LOGIN;



        OkHttpClient client = Connection.getOkHttpClient();
        Request request = new Request.Builder().url(url).build();

//        client.newCall(request).enqueue(new Callback() {

//        Connection.ResponseCallback rCB = new Connection.ResponseCallback() {
//            @Override
//            public void onSuccess(JSONArray response) {
////                try {
////                    String responseBody = response.body().string();
////                    JSONArray result = new JSONArray(responseBody);
////                } catch (JSONException e) {
//                //                    throw new RuntimeException(e);
//                }
//            }
//
//            @Override
//            public void onError(Exception e) {
//                Log.d("Succ", "Hat geklappt" + e.toString());
//            }
//        };

//        Connection.performPostRequest("http://141.87.68.204:4567/userLogin", rCB, "email", "rovcanar@hs-albsig.", "password", "Test1234");
    }



    @Override
    public void registerUser(JSONObject typedUserData, ResultCallback<UserModel> responseCallback) {

    }

    @Override
    public void getUser(int id, ResultCallback<UserModel> resultCallback) {

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
