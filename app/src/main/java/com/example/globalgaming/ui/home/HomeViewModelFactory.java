package com.example.globalgaming.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.globalgaming.domain.repository.ProductRepository;

public class HomeViewModelFactory implements ViewModelProvider.Factory {

    private final ProductRepository productRepository;
    public HomeViewModelFactory(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.equals(HomeViewModel.class)) {
            return (T) new HomeViewModel(productRepository);
        } else {
            throw new IllegalArgumentException("Unbekanntes ViewModel: " + modelClass.getName());
        }
    }
}