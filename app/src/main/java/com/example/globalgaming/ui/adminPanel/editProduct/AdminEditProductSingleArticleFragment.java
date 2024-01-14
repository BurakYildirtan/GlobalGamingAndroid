package com.example.globalgaming.ui.adminPanel.editProduct;

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
import com.example.globalgaming.databinding.FragmentEditProductBinding;
import com.google.android.material.button.MaterialButton;

public class AdminEditProductSingleArticleFragment extends Fragment {
    private FragmentEditProductBinding binding;
    private NavController navController;

    private String title;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString("title");
        }
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentEditProductBinding.inflate(inflater, container, false);
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
            goToAdminEditProductFragment();
        });
    }

    private void goToAdminEditProductFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        navController.navigate(R.id.action_adminEditProductSingleArticleFragment_to_adminEditProductFragment, bundle);
    }
}
