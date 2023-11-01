package com.example.globalgaming;

import android.os.Bundle;

import com.example.globalgaming.common.Constants;
import com.example.globalgaming.ui.login.LoginFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.globalgaming.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initLogin();
    }

    private void initLogin() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, LoginFragment.class, null, Constants.TAG_LOGIN)
                .addToBackStack(null)
                .setReorderingAllowed(true);
        fragmentTransaction.commit();
    }

}