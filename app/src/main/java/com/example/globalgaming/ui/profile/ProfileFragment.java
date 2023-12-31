package com.example.globalgaming.ui.profile;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.globalgaming.R;
import com.example.globalgaming.databinding.FragmentProfileBinding;
import com.example.globalgaming.domain.model.UserModel;
import com.example.globalgaming.ui.login.UserViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private UserViewModel userViewModel;
    private FragmentProfileBinding binding;
    private Boolean enableEdit = false;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSharedViewModel();
        initViewModel();
    }

    private void initSharedViewModel() {
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
    }

    private void initViewModel() {
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initLiveDataObserver();
        initBtnListeners();
    }

    private void initLiveDataObserver() {
        userViewModel.getUserModelResult().observe(getViewLifecycleOwner(), userModelResult -> {
            if (userModelResult.isSuccess()) {
                UserModel userModel = userModelResult.getValue();
                setUserDataInFields(userModel);

            } else {
                Toast.makeText(getContext(), getString(R.string.user_data_could_not_load), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //TODO BirthDay look up for this
    private void setUserDataInFields(UserModel userModel) {
        assert binding != null;
        TextInputEditText etEmail = binding.etEmail;
        TextInputEditText etPassword = binding.etPassword;
        TextInputEditText etBirthday = binding.etBirthday;
        TextInputEditText etStreet = binding.etStreet;
        TextInputEditText etPostalCode = binding.etPostCode;
        TextInputEditText etCity = binding.etCity;

        etEmail.setText(userModel.getEmail());
        etPassword.setText(userModel.getPassword());
        etBirthday.setText("20.04.1998");
        etStreet.setText(userModel.getStreet());
        etPostalCode.setText(userModel.getPostalCode());
        etCity.setText(userModel.getCity());
    }

    private void initBtnListeners() {
        MaterialButton btnEdit = binding.btnEdit;
        btnEdit.setOnClickListener( view1 -> {
            changeEditFields();
        });

        MaterialButton btnCancel = binding.btnCancel;
        btnCancel.setOnClickListener( view1 -> {
            changeEditFields();
        });

        MaterialButton btnConfirm = binding.btnConfirm;
        btnConfirm.setOnClickListener( view1 -> {
            changeEditFields();
        });
    }

    private void changeEditFields() {
        if (enableEdit) {
            enableEdit = false;
            binding.btnEdit.setVisibility(View.VISIBLE);
            binding.btnCancel.setVisibility(View.GONE);
            binding.btnConfirm.setVisibility(View.GONE);

            binding.tilEmail.setEnabled(enableEdit);
            binding.tilPassword.setEnabled(enableEdit);
            binding.tilBirthday.setEnabled(enableEdit);
            binding.tilStreet.setEnabled(enableEdit);
            binding.tilPostalCode.setEnabled(enableEdit);
            binding.tilCity.setEnabled(enableEdit);

        } else {
            enableEdit = true;
            binding.btnEdit.setVisibility(View.GONE);
            binding.btnCancel.setVisibility(View.VISIBLE);
            binding.btnConfirm.setVisibility(View.VISIBLE);

            binding.tilEmail.setEnabled(enableEdit);
            binding.tilPassword.setEnabled(enableEdit);
            binding.tilBirthday.setEnabled(enableEdit);
            binding.tilStreet.setEnabled(enableEdit);
            binding.tilPostalCode.setEnabled(enableEdit);
            binding.tilCity.setEnabled(enableEdit);
        }
    }
}