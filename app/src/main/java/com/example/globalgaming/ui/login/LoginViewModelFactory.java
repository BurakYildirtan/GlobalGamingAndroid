package com.example.globalgaming.ui.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.globalgaming.domain.repository.UserRepository;

public class LoginViewModelFactory implements ViewModelProvider.Factory {
    private final UserRepository userRepository;

    public LoginViewModelFactory(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.equals(LoginViewModel.class)) {
            return (T) new LoginViewModel(userRepository);
        } else {
            throw new IllegalArgumentException("Unbekanntes ViewModel: " + modelClass.getName());
        }
    }
}
