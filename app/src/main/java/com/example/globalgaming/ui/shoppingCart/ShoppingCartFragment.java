package com.example.globalgaming.ui.shoppingCart;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.globalgaming.R;
import com.example.globalgaming.databinding.FragmentShoppingCartBinding;
import com.example.globalgaming.domain.model.ProductModel;
import com.google.android.material.button.MaterialButton;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        initBtnBuyNowListener(navController);
    }

    private void initShoppingCartProductAdapter() {
        RecyclerView rvShoppingCartProduct = binding.rvShoppingCartProduct;
        ArrayList<ProductModel> productList = new ArrayList<>();

//        ShoppingCartProductAdapter.SwipeToDeleteCallback swipeToDeleteCallback = new ShoppingCartProductAdapter.SwipeToDeleteCallback();

        ShoppingCartProductAdapter shoppingCartProductAdapter = new ShoppingCartProductAdapter(getContext(), productList);
        rvShoppingCartProduct.setAdapter(shoppingCartProductAdapter);
    }

    private void initBtnBuyNowListener(NavController navController) {
        MaterialButton buyNow = binding.btnBuyNow;
        buyNow.setOnClickListener( view -> {
            navController.navigate(R.id.action_shoppingCartFragment_to_orderTakenFragment);
        });
    }

}