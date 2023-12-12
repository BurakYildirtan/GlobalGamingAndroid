package com.example.globalgaming.ui.registration;

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
import com.example.globalgaming.databinding.FragmentRegistrationBinding;
import com.example.globalgaming.ui.login.LoginFragment;
import com.example.globalgaming.ui.main.MainFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.transition.platform.MaterialSharedAxis;

public class RegistrationFragment extends Fragment {


   @Nullable private FragmentRegistrationBinding binding;
   private RegistrationViewModel registrationViewModel;
   private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MaterialSharedAxis forward = new MaterialSharedAxis(MaterialSharedAxis.Z, true);
        setReenterTransition(forward);

        MaterialSharedAxis backward = new MaterialSharedAxis(MaterialSharedAxis.Z, false);
        setReenterTransition(backward);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false );
        registrationViewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);
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
        initBtnRegistration();
        initBtnGoToLogin();
    }

    private void initBtnRegistration() {
        MaterialButton btnRegistration = binding.btnRegistration;
        btnRegistration.setOnClickListener( view1 -> {
            goToMainFragment();
        });
    }

    private void goToMainFragment() {
        MainFragment mainFragment = new MainFragment();
        fragmentTransaction = getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, mainFragment, Constants.TAG_MAIN)
                .setReorderingAllowed(true)
                .addToBackStack(null);

        fragmentTransaction.commit();
    }

    private void initBtnGoToLogin() {
        assert binding != null;
        MaterialButton btnGoToLogin = binding.btnGoToLogin;
        btnGoToLogin.setOnClickListener( view1 -> {
            goToLogin();
        });
    }

    private void goToLogin() {
        LoginFragment newLoginFragment = new LoginFragment();

        fragmentTransaction = getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, newLoginFragment, Constants.TAG_LOGIN)
                .setReorderingAllowed(true);
        fragmentTransaction.commit();
    }
}
