package com.example.globalgaming.ui.shoppingCart;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Paint;
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
import com.example.globalgaming.common.helper.FormatHelpers;
import com.example.globalgaming.databinding.FragmentShoppingCartBinding;
import com.example.globalgaming.domain.model.ProductModel;
import com.example.globalgaming.domain.model.ShoppingCartModel;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartFragment extends Fragment implements  OnQuantityChangedListener{

    private ShoppingCartViewModel shoppingCartViewModel;

    private FragmentShoppingCartBinding binding;
    private ShoppingCartProductAdapter shoppingCartProductAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shoppingCartViewModel = new ViewModelProvider(requireActivity()).get(ShoppingCartViewModel.class);
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
        setSharedViewModelObserver();
    }

    private void initShoppingCartProductAdapter() {
        RecyclerView rvShoppingCartProduct = binding.rvShoppingCartProduct;
        shoppingCartProductAdapter = new ShoppingCartProductAdapter(getContext(), this);
        rvShoppingCartProduct.setAdapter(shoppingCartProductAdapter);
    }

    private void initBtnBuyNowListener(NavController navController) {
        MaterialButton buyNow = binding.btnBuyNow;
        buyNow.setEnabled(false);
        buyNow.setOnClickListener( view -> {
            shoppingCartViewModel.buyShoppingCart();
            navController.navigate(R.id.action_shoppingCartFragment_to_orderTakenFragment);
        });
    }

    private void setSharedViewModelObserver() {
        shoppingCartViewModel.getShoppingCart().observe(getViewLifecycleOwner(), shoppingCartList ->  {
            shoppingCartProductAdapter.addList(shoppingCartList);
            setCompletePrice(shoppingCartList);
        });
    }

    private void setCompletePrice(List<ShoppingCartModel> shoppingCartList) {
        Double sumPrice = 0.0;
        Double sumPriceSale = 0.0;
        for (ShoppingCartModel productSc: shoppingCartList) {
            Double priceBefore = productSc.getProduct().getPrice();
            Double saleInPercent = productSc.getProduct().getSaleInPercent();
            int quantity = productSc.getQuantity();

            sumPriceSale += priceBefore * quantity;
            if (saleInPercent != 0.0) {
                Double priceWithSale = FormatHelpers.calculatePriceWithSale(priceBefore, saleInPercent);
                sumPrice += priceWithSale * quantity;
            } else {
                sumPrice += priceBefore * quantity;
            }
        }

        if (sumPrice.equals(sumPriceSale)) {
            binding.tvSumSale.setVisibility(View.GONE);
            if (sumPrice.equals(0.0)) {
                binding.tvSum.setText(getResources().getString(R.string.nothing_yet));
                binding.btnBuyNow.setEnabled(false);
            } else {
                binding.tvSum.setText(FormatHelpers.formatPriceAndCurrency(sumPrice, "€"));
                binding.btnBuyNow.setEnabled(true);
            }
        } else {
            binding.tvSumSale.setVisibility(View.VISIBLE);
            binding.tvSumSale.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            binding.tvSumSale.setText(FormatHelpers.formatPriceAndCurrency(sumPriceSale, "€"));
            binding.tvSum.setText(FormatHelpers.formatPriceAndCurrency(sumPrice, "€"));
            binding.btnBuyNow.setEnabled(true);
        }
    }


    @Override
    public void onQuantityChanged(ShoppingCartModel productSc, int btnClick) {
        shoppingCartViewModel.changeQuantity(productSc,btnClick);
    }
}