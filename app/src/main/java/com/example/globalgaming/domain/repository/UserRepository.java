package com.example.globalgaming.domain.repository;

import com.example.globalgaming.common.callbacks.ResultCallback;
import com.example.globalgaming.domain.model.UserModel;

import org.json.JSONObject;



public interface UserRepository {

    void loginUser(JSONObject typedUserData, ResultCallback<UserModel> resultCallback);

    void registerUser(JSONObject typedUserData, ResultCallback<UserModel> resultCallback);

    void getUser(int id, ResultCallback<UserModel> resultCallback);

    void updateUser(JSONObject userData, ResultCallback<Boolean> resultCallback);

}
