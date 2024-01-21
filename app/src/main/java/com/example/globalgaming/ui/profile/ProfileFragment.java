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
import com.example.globalgaming.common.Constants;
import com.example.globalgaming.common.helper.FormatHelpers;
import com.example.globalgaming.databinding.FragmentProfileBinding;
import com.example.globalgaming.domain.model.UserModel;
import com.example.globalgaming.ui.login.UserViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileFragment extends Fragment {
    private UserViewModel userViewModel;
    private FragmentProfileBinding binding;
    private Boolean enableEdit = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSharedViewModel();
    }

    private void initSharedViewModel() {
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
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

    private void setUserDataInFields(UserModel userModel) {
        assert binding != null;
        TextInputEditText etUserName = binding.etUserName;
        TextInputEditText etEmail = binding.etEmail;
        TextInputEditText etPassword = binding.etPassword;
        TextInputEditText etBirthday = binding.etBirthday;
        TextInputEditText etStreet = binding.etStreet;
        TextInputEditText etPostalCode = binding.etPostCode;
        TextInputEditText etCity = binding.etCity;

        etUserName.setText(userModel.getUserName());
        etEmail.setText(userModel.getEmail());
        etPassword.setText(userModel.getPassword());
        etBirthday.setText(FormatHelpers.formatDataDateToViewDate(userModel.getBirthday()));
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

            try {
                JSONObject userData = createUserDataJson();
                userViewModel.updateUser(userData);
            } catch (Exception e) {
                Toast.makeText(getContext(),  getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private JSONObject createUserDataJson() throws Exception {
        TextInputEditText etUserName = binding.etUserName;
        TextInputEditText etEmail = binding.etEmail;
        TextInputEditText etPassword = binding.etPassword;
        TextInputEditText etBirthday = binding.etBirthday;
        TextInputEditText etStreet = binding.etStreet;
        TextInputEditText etPostalCode = binding.etPostCode;
        TextInputEditText etCity = binding.etCity;

        UserModel user = userViewModel.getUserModelResult().getValue().getValue();
        if (user != null) {
            JSONObject updatedUser = new JSONObject();
            updatedUser.put(Constants.USER_MODEL_ID, user.getId());
            updatedUser.put(Constants.USER_MODEL_USER_NAME, etUserName.getText().toString());
            updatedUser.put(Constants.USER_MODEL_PASSWORD, etPassword.getText().toString());
            updatedUser.put(Constants.USER_MODEL_BIRTHDAY, FormatHelpers.formatViewDateToDataDate(etBirthday.getText().toString()));
            updatedUser.put(Constants.USER_MODEL_EMAIL, etEmail.getText().toString());
            updatedUser.put(Constants.USER_MODEL_STREET, etStreet.getText().toString());
            updatedUser.put(Constants.USER_MODEL_POSTAL_CODE, Integer.parseInt(etPostalCode.getText().toString()));
            updatedUser.put(Constants.USER_MODEL_CITY, etCity.getText().toString());
            updatedUser.put(Constants.USER_MODEL_ROLE, user.getRole());
            return updatedUser;
        }
        throw new Exception();
    }

    private void changeEditFields() {
        if (enableEdit) {
            enableEdit = false;
            binding.btnEdit.setVisibility(View.VISIBLE);
            binding.btnCancel.setVisibility(View.GONE);
            binding.btnConfirm.setVisibility(View.GONE);

        } else {
            enableEdit = true;
            binding.btnEdit.setVisibility(View.GONE);
            binding.btnCancel.setVisibility(View.VISIBLE);
            binding.btnConfirm.setVisibility(View.VISIBLE);

        }

        binding.tilEmail.setEnabled(enableEdit);
        binding.tilPassword.setEnabled(enableEdit);
        binding.tilBirthday.setEnabled(enableEdit);
        binding.tilStreet.setEnabled(enableEdit);
        binding.tilPostalCode.setEnabled(enableEdit);
        binding.tilCity.setEnabled(enableEdit);
        binding.tilUserName.setEnabled(enableEdit);
    }
}