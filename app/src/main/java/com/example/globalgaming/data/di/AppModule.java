package com.example.globalgaming.data.di;

import android.content.Context;

import com.example.globalgaming.domain.repository.HardwareRepository;
import com.example.globalgaming.domain.repository.ProductRepository;
import com.example.globalgaming.domain.repository.SaleRepository;
import com.example.globalgaming.domain.repository.SoftwareRepository;
import com.example.globalgaming.domain.repository.UserRepository;

public abstract class AppModule {

    protected Context context;
    protected UserRepository userRepository;

    protected SaleRepository saleRepository;

    protected SoftwareRepository softwareRepository;

    protected HardwareRepository hardwareRepository;

    protected ProductRepository productRepository;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public SaleRepository getSaleRepository() {return saleRepository;}

    public SoftwareRepository getSoftwareRepository() {return softwareRepository;}

    public HardwareRepository getHardwareRepository() {return hardwareRepository;}

    public ProductRepository getProductRepository() {return productRepository;}
}
