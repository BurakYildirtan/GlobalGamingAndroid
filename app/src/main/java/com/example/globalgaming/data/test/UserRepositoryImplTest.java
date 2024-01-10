package com.example.globalgaming.data.test;

import android.util.Log;
import com.example.globalgaming.common.Constants;
import com.example.globalgaming.common.mapper.Result;
import com.example.globalgaming.common.callbacks.ResultCallback;
import com.example.globalgaming.domain.model.UserModel;
import com.example.globalgaming.domain.repository.UserRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserRepositoryImplTest implements UserRepository {

    @Override
    public void loginUser(JSONObject typedUserData, ResultCallback<UserModel> responseCallback) {
        try {
            String jString = "[\n" +
                    "  {\n" +
                    "    \"userId\": 1,\n" +
                    "    \"userName\": \"rovcanar\",\n" +
                    "    \"role\": \"admin\",\n" +
                    "    \"password\": \"test\",\n" +
                    "    \"email\": \"rovcanar@hs-albsig.de\",\n" +
                    "    \"bday\": \"Dec 23, 2023, 12:00:00 AM\",\n" +
                    "    \"street\": \"musterstraße 20\",\n" +
                    "    \"pcode\": 72760,\n" +
                    "    \"city\": \"Reutlingen\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 2,\n" +
                    "    \"userName\": \"yildirbu\",\n" +
                    "    \"role\": \"user\",\n" +
                    "    \"password\": \"test\",\n" +
                    "    \"email\": \"yildirbu@hs-albsig.de\",\n" +
                    "    \"bday\": \"Dec 23, 2023, 12:00:00 AM\",\n" +
                    "    \"street\": \"musterstraße 20\",\n" +
                    "    \"pcode\": 72355,\n" +
                    "    \"city\": \"Schömberg\"\n" +
                    "  }\n" +
                    "]";

            JSONArray result = new JSONArray(jString);
            //TODO Test
            JSONObject user1 = (JSONObject) result.get(0);
            UserModel userModel = new UserModel(user1);


            if (userModel.getEmail().equals(typedUserData.getString(Constants.USER_MODEL_EMAIL)) && userModel.getPassword().equals(typedUserData.getString(Constants.USER_MODEL_PASSWORD))) {
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

    @Override
    public void registerUser(JSONObject typedUserData, ResultCallback<UserModel> responseCallback) {

    }

    @Override
    public void getUser(int id, ResultCallback<UserModel> resultCallback) {

    }

    @Override
    public void updateUser(JSONObject userData, ResultCallback<Boolean> resultCallback) {

    }
}
