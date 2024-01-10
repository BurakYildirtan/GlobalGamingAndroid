package com.example.globalgaming.ui.shoppingCart;

import com.example.globalgaming.domain.model.ShoppingCartModel;

public interface OnQuantityChangedListener {
    void onQuantityChanged(ShoppingCartModel productSc, int btnClick);
}
