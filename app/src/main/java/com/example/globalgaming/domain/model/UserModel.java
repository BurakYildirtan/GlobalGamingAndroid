package com.example.globalgaming.domain.model;

import com.example.globalgaming.common.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class UserModel {
    private final int id;
    private final String email;
    private final String userName;
    private final String password;
    private final String role;

    private final String birthday;

    private final String street;

    private final String postalCode;

    private final String city;



    public UserModel(int id,
                     String email,
                     String userName,
                     String password,
                     String birthday,
                     String street,
                     String postalCode,
                     String city,
                     String role) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.birthday = birthday;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.role = role;
    }

    public UserModel(JSONObject userData) {
        try {
            this.id = userData.getInt(Constants.USER_MODEL_ID);
            this.email = userData.getString(Constants.USER_MODEL_EMAIL);
            this.userName = userData.getString(Constants.USER_MODEL_USER_NAME);
            this.password = userData.getString(Constants.USER_MODEL_PASSWORD);
            this.role = userData.getString(Constants.USER_MODEL_ROLE);
            this.birthday = userData.getString(Constants.USER_MODEL_BIRTHDAY);
            this.street = userData.getString(Constants.USER_MODEL_STREET);
            this.postalCode = userData.getString(Constants.USER_MODEL_POSTAL_CODE);
            this.city = userData.getString(Constants.USER_MODEL_CITY);
        } catch (JSONException e) {
            throw new RuntimeException();
        }
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthday() {return birthday;}

    public String getStreet() {return street;}

    public String getPostalCode() {return postalCode;}

    public String getCity() {return city;}

    public String getRole() {return role; }

    public String getUserName() {return userName; }
}
