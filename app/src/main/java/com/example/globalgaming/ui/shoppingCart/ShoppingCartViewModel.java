package com.example.globalgaming.ui.shoppingCart;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.globalgaming.common.Constants;
import com.example.globalgaming.common.mapper.Result;
import com.example.globalgaming.domain.model.HardwareModel;
import com.example.globalgaming.domain.model.ProductModel;
import com.example.globalgaming.domain.model.ShoppingCartModel;
import com.example.globalgaming.domain.model.SoftwareModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartViewModel extends ViewModel {


    private MutableLiveData<List<ShoppingCartModel>> shoppingCart = new MutableLiveData<>();

    public ShoppingCartViewModel() {
        shoppingCart.postValue(new ArrayList<>());
    }

    public MutableLiveData<List<ShoppingCartModel>> getShoppingCart() { return shoppingCart; }

    public Boolean  addProduct(ProductModel newProduct) {
        List<ShoppingCartModel> shoppingCartList = shoppingCart.getValue();
        if (shoppingCartList != null) {
            if (shoppingCartList.size() > 0) {
                for (ShoppingCartModel productInSc : shoppingCartList) {
                    int productTypeSc = productInSc.getProduct().getProductType();

                    if (productTypeSc == newProduct.getProductType() && productTypeSc == Constants.PRODUCT_TYPE_SOFTWARE) {
                        SoftwareModel softwareModelSc = (SoftwareModel) productInSc.getProduct();
                        SoftwareModel newSoftwareModel = (SoftwareModel) newProduct;

                        if (softwareModelSc.getProductId() == newSoftwareModel.getProductId()) {
                            productInSc.addOne();
                            shoppingCart.postValue(shoppingCartList);
                            return true;
                        }
                    } else if (productTypeSc == newProduct.getProductType() && productTypeSc == Constants.PRODUCT_TYPE_HARDWARE) {
                        HardwareModel hardwareModelSc = (HardwareModel) productInSc.getProduct();
                        HardwareModel newHardwareModel = (HardwareModel) newProduct;

                        if (hardwareModelSc.getProductId() == newHardwareModel.getProductId()) {
                            productInSc.addOne();
                            shoppingCart.postValue(shoppingCartList);
                            return true;
                        }
                    }
                }
            }
            ShoppingCartModel shoppingCartModel = new ShoppingCartModel(shoppingCartList.size(), 1, newProduct);
            shoppingCartList.add(shoppingCartModel);
            shoppingCart.postValue(shoppingCartList);
            return true;
        }
        return false;
    }

    public Boolean changeQuantity(ShoppingCartModel oldProductSc, int btnClick) {
        List<ShoppingCartModel> shoppingCartList = shoppingCart.getValue();
        if (shoppingCartList != null) {
            for (ShoppingCartModel productInSc : shoppingCartList) {
                if (productInSc.getId() == oldProductSc.getId()) {
                    if (btnClick == Constants.BTN_ADD) {
                        productInSc.addOne();
                    } else {
                        productInSc.removeOne();
                        if (productInSc.getQuantity() == 0) {
                            List<ShoppingCartModel> filteredList = shoppingCartList.stream()
                                    .filter(item -> item.getQuantity() != 0)
                                            .collect(Collectors.toList());
                            shoppingCart.postValue(filteredList);
                            return true;
                        }
                    }
                    shoppingCart.postValue(shoppingCartList);
                    return true;
                }
            }
        }
        return true;
    }

    public void buyShoppingCart() {
        shoppingCart.postValue(new ArrayList<>());
    }
}