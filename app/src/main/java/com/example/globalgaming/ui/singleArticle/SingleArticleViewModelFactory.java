package com.example.globalgaming.ui.singleArticle;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.globalgaming.domain.repository.HardwareRepository;
import com.example.globalgaming.domain.repository.UserRepository;
import com.example.globalgaming.ui.login.UserViewModel;

public class SingleArticleViewModelFactory implements ViewModelProvider.Factory {
    private final HardwareRepository hardwareRepository;

    public SingleArticleViewModelFactory(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.equals(SingleArticleViewModel.class)) {
            return (T) new SingleArticleViewModel(hardwareRepository);
        } else {
            throw new IllegalArgumentException("Unbekanntes ViewModel: " + modelClass.getName());
        }
    }
}
