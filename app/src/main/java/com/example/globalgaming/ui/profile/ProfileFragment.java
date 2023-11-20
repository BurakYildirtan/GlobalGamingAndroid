package com.example.globalgaming.ui.profile;

import androidx.constraintlayout.widget.Group;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.globalgaming.R;
import com.example.globalgaming.databinding.FragmentProfileBinding;
import com.google.android.material.button.MaterialButton;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    private FragmentProfileBinding binding;
    private Boolean enableEdit = false;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
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