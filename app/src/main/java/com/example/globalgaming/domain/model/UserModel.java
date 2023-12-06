package com.example.globalgaming.domain.model;

import com.example.globalgaming.common.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class UserModel {
    private int id;
    private String userName;
    private String password;
    private String role;

    private Date date;

    private String street;

    private String postalCode;

    private String city;



    public UserModel(int id,
                     String userName,
                     String password,
                     Date date,
                     String street,
                     String postalCode,
                     String city,
                     String role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.date = date;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.role = role;
    }

    public UserModel(JSONObject userData) {
        try {
            this.id = userData.getInt(Constants.USER_MODEL_ID);
            this.userName = userData.getString(Constants.USER_MODEL_USER_NAME);
            this.password = userData.getString(Constants.USER_MODEL_PASSWORD);
            this.role = userData.getString(Constants.USER_MODEL_ROLE);
        } catch (JSONException e) {
            throw new RuntimeException();
        }
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Date getDate() {return date;}

    public String getStreet() {return street;}

    public String getPostalCode() {return postalCode;}

    public String getCity() {return city;}

    public String getRole() {return role; }
}
