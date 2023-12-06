package com.example.globalgaming.data.di;

import android.content.Context;

import com.example.globalgaming.data.remote.UserRepositoryImpl;
import com.example.globalgaming.data.test.UserRepositoryImplTest;
import com.example.globalgaming.domain.repository.UserRepository;

public class AppModuleImplTest extends AppModule {

    public AppModuleImplTest(Context appContext) {
         this.context = appContext;
         this.userRepository = new UserRepositoryImplTest();
    }

    @Override
    public UserRepository getUserRepository() {
        return super.getUserRepository();
    }
}
