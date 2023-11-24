package com.example.globalgaming.ui.orderTaken;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.globalgaming.databinding.FragmentOrderTakenBinding;
import com.example.globalgaming.databinding.FragmentShoppingCartBinding;
import com.example.globalgaming.domain.model.Product;
import com.example.globalgaming.ui.shoppingCart.ShoppingCartProductAdapter;
import com.example.globalgaming.ui.shoppingCart.ShoppingCartViewModel;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class OrderTakenFragment extends Fragment {

    private ShoppingCartViewModel mViewModel;

    private FragmentOrderTakenBinding binding;

    public static OrderTakenFragment newInstance() {
        return new OrderTakenFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ShoppingCartViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderTakenBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initContinueShoppingBtn();
        initCancelBtn();
    }

    private void initContinueShoppingBtn() {
        MaterialButton btnContinueShopping = binding.btnContinueShopping;
        btnContinueShopping.setOnClickListener(view1 -> {
        });
    }

    private void initCancelBtn() {
        MaterialButton btnCancel = binding.btnCancel;
        btnCancel.setOnClickListener(view1 ->{

        });
    }
}