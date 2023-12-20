package com.example.globalgaming;

import android.annotation.SuppressLint;
import android.app.Application;

import com.example.globalgaming.data.di.AppModule;
import com.example.globalgaming.data.di.AppModuleImpl;
import com.example.globalgaming.data.di.AppModuleImplTest;

public class TheApp extends Application {

    //TODO mal gucken was wird, was wird
    @SuppressLint("StaticFieldLeak")
    public static AppModule appModule;


    @Override
    public void onCreate() {
        super.onCreate();
//        appModule = new AppModuleImpl(this);
        //TODO im Test
        appModule = new AppModuleImpl(this);
    }
}
