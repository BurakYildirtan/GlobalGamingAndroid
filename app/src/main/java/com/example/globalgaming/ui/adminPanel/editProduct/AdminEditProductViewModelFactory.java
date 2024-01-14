package com.example.globalgaming.ui.adminPanel.editProduct;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.globalgaming.domain.repository.HardwareRepository;
import com.example.globalgaming.domain.repository.ProductRepository;
import com.example.globalgaming.domain.repository.SaleRepository;
import com.example.globalgaming.domain.repository.SoftwareRepository;

public class AdminEditProductViewModelFactory implements ViewModelProvider.Factory {

    private final ProductRepository productRepository;
    public AdminEditProductViewModelFactory(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.equals(AdminEditProductViewModel.class)) {
            return (T) new AdminEditProductViewModel(productRepository);
        } else {
            throw new IllegalArgumentException("Unbekanntes ViewModel: " + modelClass.getName());
        }
    }
}