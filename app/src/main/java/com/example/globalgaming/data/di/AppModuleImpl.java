package com.example.globalgaming.data.di;

import android.content.Context;

import com.example.globalgaming.data.remote.UserRepositoryImpl;
import com.example.globalgaming.domain.repository.UserRepository;

public class AppModuleImpl extends AppModule {

    public AppModuleImpl(Context appContext) {
         this.context = appContext;
         this.userRepository = new UserRepositoryImpl();
    }

    @Override
    public UserRepository getUserRepository() {
        return super.getUserRepository();
    }
}
