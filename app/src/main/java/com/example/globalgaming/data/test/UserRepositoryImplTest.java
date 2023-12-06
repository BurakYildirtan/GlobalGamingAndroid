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
                    "  {\"userId\":1,\"userName\":\"buraki\",\"role\":\"admin\",\"password\":\"Test123\"},\n" +
                    "  {\"userId\":2,\"userName\":\"rovcanar\",\"role\":\"admin\",\"password\":\"Test123\"},\n" +
                    "  {\"userId\":3,\"userName\":\"user3@example.com\",\"role\":\"admin\",\"password\":\"passwort\",\"birthday\":\"2000-01-01\",\"street\":\"Street3\",\"postalCode\":\"12345\",\"city\":\"City3\"},\n" +
                    "  {\"userId\":4,\"userName\":\"user4@example.com\",\"role\":\"user\",\"password\":\"passwort\",\"birthday\":\"2000-01-01\",\"street\":\"Street4\",\"postalCode\":\"12345\",\"city\":\"City4\"},\n" +
                    "  {\"userId\":5,\"userName\":\"user5@example.com\",\"role\":\"user\",\"password\":\"passwort\",\"birthday\":\"2000-01-01\",\"street\":\"Street5\",\"postalCode\":\"12345\",\"city\":\"City5\"},\n" +
                    "  {\"userId\":6,\"userName\":\"user6@example.com\",\"role\":\"user\",\"password\":\"passwort\",\"birthday\":\"2000-01-01\",\"street\":\"Street6\",\"postalCode\":\"12345\",\"city\":\"City6\"},\n" +
                    "  {\"userId\":7,\"userName\":\"user7@example.com\",\"role\":\"user\",\"password\":\"passwort\",\"birthday\":\"2000-01-01\",\"street\":\"Street7\",\"postalCode\":\"12345\",\"city\":\"City7\"},\n" +
                    "  {\"userId\":8,\"userName\":\"user8@example.com\",\"role\":\"user\",\"password\":\"passwort\",\"birthday\":\"2000-01-01\",\"street\":\"Street8\",\"postalCode\":\"12345\",\"city\":\"City8\"},\n" +
                    "  {\"userId\":9,\"userName\":\"user9@example.com\",\"role\":\"user\",\"password\":\"passwort\",\"birthday\":\"2000-01-01\",\"street\":\"Street9\",\"postalCode\":\"12345\",\"city\":\"City9\"},\n" +
                    "  {\"userId\":10,\"userName\":\"user10@example.com\",\"role\":\"user\",\"password\":\"passwort\",\"birthday\":\"2000-01-01\",\"street\":\"Street10\",\"postalCode\":\"12345\",\"city\":\"City10\"}\n" +
                    "]\n";
            JSONArray result = new JSONArray(jString);
            //TODO Test
            JSONObject user1 = (JSONObject) result.get(2);
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
}
