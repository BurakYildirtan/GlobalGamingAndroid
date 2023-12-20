package com.example.globalgaming;

import android.os.Bundle;

import com.example.globalgaming.common.Constants;
import com.example.globalgaming.domain.repository.UserRepository;
import com.example.globalgaming.ui.login.LoginFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.globalgaming.databinding.ActivityMainBinding;
import com.example.globalgaming.ui.login.LoginViewModelFactory;
import com.example.globalgaming.ui.login.UserViewModel;
import com.example.globalgaming.ui.shoppingCart.ShoppingCartViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initLogin();
        initSharedViewModel();
    }

    private void initLogin() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, LoginFragment.class, null, Constants.TAG_LOGIN)
                .addToBackStack(null)
                .setReorderingAllowed(true);
        fragmentTransaction.commit();
    }

    private void initSharedViewModel() {
        UserRepository userRepository = TheApp.appModule.getUserRepository();
        LoginViewModelFactory loginViewModelFactory = new LoginViewModelFactory(userRepository);
        new ViewModelProvider(this, loginViewModelFactory).get(UserViewModel.class);
        new ViewModelProvider(this).get(ShoppingCartViewModel.class);
    }

}