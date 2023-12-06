package com.example.globalgaming.ui.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.globalgaming.common.mapper.Result;
import com.example.globalgaming.common.callbacks.ResultCallback;
import com.example.globalgaming.domain.model.UserModel;
import com.example.globalgaming.domain.repository.UserRepository;

import org.json.JSONObject;

public class UserViewModel extends ViewModel {

    private final UserRepository userRepository;
    private MutableLiveData<Result<UserModel>> userModelResult = new MutableLiveData<>();

    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LiveData<Result<UserModel>> getUserModelResult() {
        return userModelResult;
    }

    public void loginUser(JSONObject userData) {
        userRepository.loginUser(userData, new ResultCallback<UserModel>() {
            @Override
            public void onSuccess(Result<UserModel> response) {
                Log.d("s","Hat funkioniert "+ response.getValue().getEmail());
                userModelResult.postValue(response);
            }

            @Override
            public void onError(Result<UserModel> error) {
                userModelResult.postValue(error);
            }
        });
    }

    public void registerUser(JSONObject userData) {

    }

}
