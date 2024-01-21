package com.example.globalgaming.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.globalgaming.R;
import com.example.globalgaming.databinding.FragmentMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNavigation();
    }

    private void initNavigation() {
        BottomNavigationView navView = binding.navView;
        NavController navController = Navigation.findNavController(requireView().findViewById(R.id.nav_host_fragment_main));

        navView.setOnItemSelectedListener( item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_home) {
                navController.navigate(R.id.homeFragment);
                return true;
            } else if (itemId == R.id.navigation_profile) {
                navController.navigate(R.id.profileFragment);
                return true;
            } else if (itemId == R.id.navigation_shopping_cart) {
                navController.navigate(R.id.shoppingCartFragment);
                return true;
            } else if (itemId == R.id.navigation_settings) {
                navController.navigate(R.id.settingsFragment);
                return true;
            }
            return false;
        });

        NavHostFragment navHostFragment = (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.nav_host_fragment_main);
        if (navHostFragment != null) {
            navHostFragment.getNavController().setGraph(R.navigation.main_navigation);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}