package com.example.globalgaming.ui.shoppingCart;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.globalgaming.R;
import com.example.globalgaming.databinding.FragmentShoppingCartBinding;
import com.example.globalgaming.domain.model.Product;

import java.util.ArrayList;

public class ShoppingCartFragment extends Fragment {

    private ShoppingCartViewModel mViewModel;

    private FragmentShoppingCartBinding binding;

    public static ShoppingCartFragment newInstance() {
        return new ShoppingCartFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ShoppingCartViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentShoppingCartBinding.inflate(inflater, container, false);
        initShoppingCartProductAdapter();
        return binding.getRoot();
    }

    private void initShoppingCartProductAdapter() {
        RecyclerView rvShoppingCartProduct = binding.rvShoppingCartProduct;
        ArrayList<Product> productList = new ArrayList<>();

//        ShoppingCartProductAdapter.SwipeToDeleteCallback swipeToDeleteCallback = new ShoppingCartProductAdapter.SwipeToDeleteCallback();

        for (int i = 0; i < 5 ; i++) {
            Product p = new Product(i, "Global Gaming Test", 79.99, 19.00);
            productList.add(p);
        }

        ShoppingCartProductAdapter shoppingCartProductAdapter = new ShoppingCartProductAdapter(getContext(), productList);
        rvShoppingCartProduct.setAdapter(shoppingCartProductAdapter);
    }

}