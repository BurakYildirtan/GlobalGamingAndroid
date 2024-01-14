package com.example.globalgaming.ui.adminPanel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.globalgaming.domain.repository.HardwareRepository;
import com.example.globalgaming.domain.repository.ProductRepository;
import com.example.globalgaming.domain.repository.SaleRepository;
import com.example.globalgaming.domain.repository.SoftwareRepository;
import com.example.globalgaming.ui.category.CategoryViewModel;

public class AdminViewModelFactory implements ViewModelProvider.Factory {

    private final ProductRepository productRepository;
    public AdminViewModelFactory(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.equals(AdminViewModel.class)) {
            return (T) new AdminViewModel(productRepository);
        } else {
            throw new IllegalArgumentException("Unbekanntes ViewModel: " + modelClass.getName());
        }
    }
}