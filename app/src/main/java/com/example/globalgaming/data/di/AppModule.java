package com.example.globalgaming.data.di;

import android.content.Context;

import com.example.globalgaming.domain.repository.UserRepository;

public abstract class AppModule {

    protected Context context;
    protected UserRepository userRepository;

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
