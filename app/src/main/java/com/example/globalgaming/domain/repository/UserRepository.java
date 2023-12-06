package com.example.globalgaming.domain.repository;

import com.example.globalgaming.common.callbacks.ResultCallback;
import com.example.globalgaming.domain.model.UserModel;

import org.json.JSONObject;



public interface UserRepository {

    public void loginUser(JSONObject typedUserData, ResultCallback<UserModel> responseCallback);

}
