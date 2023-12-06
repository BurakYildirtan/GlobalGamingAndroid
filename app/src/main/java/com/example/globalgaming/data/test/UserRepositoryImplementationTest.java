package com.example.globalgaming.data.test;

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

public class UserRepositoryImplementationTest implements UserRepository {

    @Override
    public void loginUser(JSONObject typedUserData, ResultCallback<UserModel> responseCallback) {
        try {
            String jString = "[{\"userId\":1,\"userName\":\"buraki\",\"role\":\"admin\",\"password\":\"Test123\"},{\"userId\":2,\"userName\":\"rovcanar\",\"role\":\"user\",\"password\":\"Test123\"}]";
            JSONArray result = new JSONArray(jString);
            //TODO Test
            JSONObject user1 = (JSONObject) result.get(0);
            UserModel userModel = new UserModel(user1);


            if (userModel.getUserName().equals(typedUserData.getString(Constants.USER_MODEL_USER_NAME)) && userModel.getPassword().equals(typedUserData.getString(Constants.USER_MODEL_PASSWORD))) {
                responseCallback.onSuccess(Result.success(userModel));
            }
            else {
                responseCallback.onError(Result.error(new Exception()));
            }
            Log.d("test userAll", "Success " + jString);

        } catch (JSONException e) {
            responseCallback.onError(Result.error(e));
        }
    }
}
