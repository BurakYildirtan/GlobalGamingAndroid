package com.example.globalgaming.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.globalgaming.R;
import com.example.globalgaming.common.Constants;
import com.example.globalgaming.databinding.FragmentSettingsBinding;
import com.example.globalgaming.ui.login.LoginFragment;
import com.example.globalgaming.ui.login.UserViewModel;
import com.example.globalgaming.ui.main.MainFragment;
import com.google.android.material.button.MaterialButton;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    private UserViewModel userViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSharedViewModel();
    }

    private void initSharedViewModel() {
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSettingsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initLogoutListener();
    }

    private void initLogoutListener() {
        MaterialButton btnLogout = binding.btnLogout;
        btnLogout.setOnClickListener( view -> {
            goToLoginFragment();
        });
    }

    private void goToLoginFragment() {
        LoginFragment loginFragment = new LoginFragment();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, loginFragment, Constants.TAG_LOGIN)
                .setReorderingAllowed(true)
                .addToBackStack(null);

        fragmentTransaction.commit();
//        userViewModel.logoutUser();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}