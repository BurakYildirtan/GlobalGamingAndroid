package com.example.globalgaming.ui.category;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.globalgaming.domain.repository.HardwareRepository;
import com.example.globalgaming.domain.repository.SaleRepository;
import com.example.globalgaming.domain.repository.SoftwareRepository;

public class CategoryViewModelFactory implements ViewModelProvider.Factory {

    private final SaleRepository saleRepository;
    private final SoftwareRepository softwareRepository;
    private final HardwareRepository hardwareRepository;
    public CategoryViewModelFactory(SaleRepository saleRepository, SoftwareRepository softwareRepository, HardwareRepository hardwareRepository) {
        this.saleRepository = saleRepository;
        this.softwareRepository = softwareRepository;
        this.hardwareRepository = hardwareRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.equals(CategoryViewModel.class)) {
            return (T) new CategoryViewModel(saleRepository, softwareRepository, hardwareRepository);
        } else {
            throw new IllegalArgumentException("Unbekanntes ViewModel: " + modelClass.getName());
        }
    }
}