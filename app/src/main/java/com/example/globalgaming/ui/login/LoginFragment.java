package com.example.globalgaming.ui.login;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.globalgaming.R;
import com.example.globalgaming.TheApp;
import com.example.globalgaming.common.Constants;
import com.example.globalgaming.common.Result;
import com.example.globalgaming.databinding.FragmentLoginBinding;
import com.example.globalgaming.domain.model.UserModel;
import com.example.globalgaming.domain.repository.UserRepository;
import com.example.globalgaming.ui.main.MainFragment;
import com.example.globalgaming.ui.registration.RegistrationFragment;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginFragment extends Fragment {


   @Nullable private FragmentLoginBinding binding;
   private FragmentTransaction fragmentTransaction;
   private LoginViewModel loginViewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
    }

    private void initViewModel() {
        UserRepository userRepository = TheApp.appModule.getUserRepository();
        LoginViewModelFactory loginViewModelFactory = new LoginViewModelFactory(userRepository);
        loginViewModel = new ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false );
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
            String typedUserNameRes = binding.etEmail.getText().toString();
            String typedPasswordRes = binding.etPassword.getText().toString();

            JSONObject userData = new JSONObject();
            try {
                userData.put(Constants.USER_MODEL_USER_NAME,  typedUserNameRes);
                userData.put(Constants.USER_MODEL_PASSWORD, typedPasswordRes);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            loginViewModel.loginUser(userData);


//            Connection.getResponse(Constants.USER_LOGIN, new ResponseCallback() {
//                //gets the current extractions as a json array
//                @Override
//                public void onSuccess(JSONArray response) {
//                    try {
//                        Log.d("usersAll", ""+response);
//                        checkUser(response);
//                    } catch (Exception e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//
//                @Override
//                public void onError(Exception e) {
//                    // Behandle den Fehler
//                    e.printStackTrace();
//                }
//            });
        });

        loginViewModel.getUserModelResult().observe(getViewLifecycleOwner(), new Observer<Result<UserModel>>() {
            @Override
            public void onChanged(Result<UserModel> userModelResult) {
                if (userModelResult.isSuccess()) {
                    goToMainFragment();
                }
                else {
                    Toast.makeText(getContext(), "Hallo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkUser(JSONArray users) {
        if (binding != null) {
            String typedUserNameRes = binding.etEmail.getText().toString();
            String typedPasswordRes = binding.etPassword.getText().toString();

            for (int i = 0; i < users.length(); i++) {
                try {
                    JSONObject user = users.getJSONObject(i);

                    String userName = user.getString("userName");
                    String password = user.getString("password");

                    if (userName.equals(typedUserNameRes) && password.equals(typedPasswordRes)) {
                        goToMainFragment();
                    }
                    else {
                        Toast.makeText(this.getContext(), "UserName oder Password falsch", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                }
            }
        }
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
