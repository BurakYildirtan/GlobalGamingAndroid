package com.example.globalgaming.data.di;

import android.content.Context;

import com.example.globalgaming.data.remote.HardwareRepositoryImpl;
import com.example.globalgaming.data.remote.ProductRepositoryImpl;
import com.example.globalgaming.data.remote.SaleRepositoryImpl;
import com.example.globalgaming.data.remote.SoftwareRepositoryImpl;
import com.example.globalgaming.data.remote.UserRepositoryImpl;
import com.example.globalgaming.domain.repository.UserRepository;

public class AppModuleImpl extends AppModule {

    public AppModuleImpl(Context appContext) {
         this.context = appContext;
         this.userRepository = new UserRepositoryImpl();
         this.saleRepository = new SaleRepositoryImpl();
         this.softwareRepository = new SoftwareRepositoryImpl();
         this.hardwareRepository = new HardwareRepositoryImpl();
         this.productRepository = new ProductRepositoryImpl();
    }
}
