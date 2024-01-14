package com.example.globalgaming.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.globalgaming.R;
import com.example.globalgaming.common.Constants;
import com.example.globalgaming.databinding.FragmentSettingsBinding;
import com.example.globalgaming.ui.login.LoginFragment;
import com.example.globalgaming.ui.login.UserViewModel;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    private UserViewModel userViewModel;

    private NavController navController;

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
        navController = Navigation.findNavController(view);
        setContactBtnListener();
        setPrivacyPolicyBtnListener();
        setTermsAndConditionsBtnListener();
        setImprintBtnListener();
        setLogoutListener();
        setSharedObserver();
        setAddProductListener();
        setEditProductListener();
        setRemoveProductListener();
    }


    private void setSharedObserver() {
        userViewModel.getUserModelResult().observe(getViewLifecycleOwner(), userModelResult -> {
            if(userModelResult.isSuccess()) {
                String role = userModelResult.getValue().getRole();
                if (role.equals(Constants.ROLE_ADMIN)) {
                    setAdminLayout(true);
                } else  {
                    setAdminLayout(false);
                }
            }
        });
    }

    private void setAdminLayout(boolean isAdmin) {
        if (isAdmin) {
            binding.groupIsAdmin.setVisibility(View.VISIBLE);
        } else {
            binding.groupIsAdmin.setVisibility(View.GONE);
        }
    }

    private void setPrivacyPolicyBtnListener() {
        MaterialButton privacyPolicyBtn = binding.btnPrivacyPolicy;
        privacyPolicyBtn.setOnClickListener( view -> {
            goToPrivacyPolicyFragment();
        });
    }

    private void goToPrivacyPolicyFragment() {
        navController.navigate(R.id.action_settingsFragment_to_privacyPolicyFragment);
    }

    private void setTermsAndConditionsBtnListener() {
        MaterialButton termsAndConditionsBtn = binding.btnTermsAndConditions;
        termsAndConditionsBtn.setOnClickListener( view -> {
            goToTermsAndConditionsFragment();
        });
    }

    private void goToTermsAndConditionsFragment() {
        navController.navigate(R.id.action_settingsFragment_to_termsAndConditionsFragment);
    }

    private void setContactBtnListener() {
        MaterialButton contactBtn = binding.btnContact;
        contactBtn.setOnClickListener( view -> {
            goToContactFragment();
        });
    }
    
    private void goToContactFragment() {
        navController.navigate(R.id.action_settingsFragment_to_contactFragment);
    }

    private void setImprintBtnListener() {
        MaterialButton imprintBtn = binding.btnImprint;
        imprintBtn.setOnClickListener( view -> {
            goToImprintFragment();
        });
    }

    private void goToImprintFragment() {
        navController.navigate(R.id.action_settingsFragment_to_imprintFragment);
    }

    private void setLogoutListener() {
        MaterialButton btnLogout = binding.btnLogout;
        btnLogout.setOnClickListener( view -> {
            goToLoginFragment();
        });
    }

    private void goToLoginFragment() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        LoginFragment loginFragment = new LoginFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, loginFragment, Constants.TAG_LOGIN)
                .setReorderingAllowed(true)
                .addToBackStack(null);

        fragmentTransaction.commit();
        userViewModel.logoutUser();
    }

    private void setAddProductListener() {
        binding.btnAddProduct.setOnClickListener(view -> {
            goToAddProductFragment();
        });
    }

    private void goToAddProductFragment() {
        navController.navigate(R.id.action_settingsFragment_to_addProductFragment);
    }

    private void setEditProductListener() {
        binding.btnEditProduct.setOnClickListener(view -> {
            goToAdminEditProductFragment();
        });
    }

    private void goToAdminEditProductFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("title", getResources().getString(R.string.edit_product));
        navController.navigate(R.id.action_settingsFragment_to_adminEditProductFragment, bundle);
    }

    private void setRemoveProductListener() {
        binding.btnRemoveProduct.setOnClickListener(view -> {
            goToRemoveProductFragment();
        });
    }

    private void goToRemoveProductFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("title", getResources().getString(R.string.remove_product));
        navController.navigate(R.id.action_settingsFragment_to_adminEditProductFragment, bundle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}