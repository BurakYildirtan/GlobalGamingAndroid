package com.example.globalgaming.ui.shoppingCart;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.globalgaming.common.mapper.Result;
import com.example.globalgaming.domain.model.HardwareModel;
import com.example.globalgaming.domain.model.ProductModel;
import com.example.globalgaming.domain.model.ShoppingCartModel;
import com.example.globalgaming.domain.model.SoftwareModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShoppingCartViewModel extends ViewModel {


    private MutableLiveData<List<ShoppingCartModel>> shoppingCart = new MutableLiveData<>();

    public ShoppingCartViewModel() {
        shoppingCart.postValue(new ArrayList<>());
    }

    public MutableLiveData<List<ShoppingCartModel>> getShoppingCart() { return shoppingCart; }

    public Boolean  addProduct(ProductModel product) {

        int count = 0;

        for( ShoppingCartModel shoppingCart : shoppingCart.getValue()) {

            if (shoppingCart.getModelData() instanceof SoftwareModel && product.getModelData() instanceof SoftwareModel) {
                SoftwareModel pNew = (SoftwareModel)  product.getModelData();
                SoftwareModel p = (SoftwareModel) shoppingCart.getModelData();

                if (p.getDesignation() == pNew.getDesignation()) {
                    count++;
                    return true;
                }


            } else if (shoppingCart.getModelData() instanceof SoftwareModel && product.getModelData() instanceof SoftwareModel) {
                HardwareModel pNew = (HardwareModel) product.getModelData();
                HardwareModel p = (HardwareModel) shoppingCart.getModelData();

                if (p.getDesignation() == pNew.getDesignation()) {
                    count++;
                    return true;
                }
            }
        }
        return false;
    }
}