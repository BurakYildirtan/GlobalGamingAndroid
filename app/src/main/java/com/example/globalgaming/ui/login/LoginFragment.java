package com.example.globalgaming.ui.login;

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
import com.example.globalgaming.databinding.FragmentLoginBinding;
import com.example.globalgaming.ui.main.MainFragment;
import com.example.globalgaming.ui.registration.RegistrationFragment;
import com.google.android.material.button.MaterialButton;

public class LoginFragment extends Fragment {


   @Nullable private FragmentLoginBinding binding;
   private FragmentTransaction fragmentTransaction;
   private LoginViewModel loginViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false );
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initBtnLogin();
        initBtnGoToRegistration();
    }

    private void initBtnLogin() {
        MaterialButton btnLogin = binding.btnLogin;
        btnLogin.setOnClickListener( view -> {
            goToMainFragment();
        });
    }

    private void goToMainFragment() {
        MainFragment mainFragment = new MainFragment();
        fragmentTransaction = getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, mainFragment, Constants.TAG_MAIN)
                .setReorderingAllowed(true);
//                .addToBackStack(null);

        fragmentTransaction.commit();
    }

    private void initBtnGoToRegistration() {
        MaterialButton btnGoToRegistration = binding.btnGoToRegistration;
        btnGoToRegistration.setOnClickListener( view -> {
            goToRegistration();
        });
    }

    private void goToRegistration() {
//        @Nullable Fragment registrationFragment = getParentFragmentManager().findFragmentByTag(Constants.TAG_REGISTRATION);
//
//        if (registrationFragment != null) {
//            fragmentTransaction = getParentFragmentManager().beginTransaction()
//                    .replace(R.id.fragment_container, registrationFragment, Constants.TAG_REGISTRATION)
//                    .setReorderingAllowed(true)
//                    .addToBackStack(null);
//        }
//        else {
//            RegistrationFragment newRegistrationFragment = new RegistrationFragment();
//
//            fragmentTransaction = getParentFragmentManager().beginTransaction()
//                    .replace(R.id.fragment_container, newRegistrationFragment, Constants.TAG_REGISTRATION)
//                    .setReorderingAllowed(true)
//                    .addToBackStack(null);
//        }
        RegistrationFragment newRegistrationFragment = new RegistrationFragment();
        fragmentTransaction = getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, newRegistrationFragment, Constants.TAG_REGISTRATION)
                .setReorderingAllowed(true);
        fragmentTransaction.commit();
    }
}
