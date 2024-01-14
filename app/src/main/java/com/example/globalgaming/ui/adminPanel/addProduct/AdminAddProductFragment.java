package com.example.globalgaming.ui.adminPanel.addProduct;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.globalgaming.R;
import com.example.globalgaming.databinding.FragmentAddProductBinding;
import com.example.globalgaming.ui.profile.ProfileFragment;
import com.google.android.material.button.MaterialButton;

public class AdminAddProductFragment extends Fragment {
    private FragmentAddProductBinding binding;
    private NavController navController;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAddProductBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        setBtnGoBackListener();
    }

    private void setBtnGoBackListener() {
        MaterialButton buyNow = binding.btnGoBack;
        buyNow.setOnClickListener( view -> {
            goToSettingsFragment();
        });
    }

    private void goToSettingsFragment() {
        navController.navigate(R.id.action_addProductFragment_to_settingsFragment);
    }
}
