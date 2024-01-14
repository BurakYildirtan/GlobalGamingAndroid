package com.example.globalgaming.ui.adminPanel.addProduct;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.globalgaming.R;
import com.example.globalgaming.TheApp;
import com.example.globalgaming.common.Constants;
import com.example.globalgaming.common.callbacks.ResultCallback;
import com.example.globalgaming.common.mapper.Result;
import com.example.globalgaming.databinding.FragmentAddProductBinding;
import com.example.globalgaming.domain.model.ProductModel;
import com.example.globalgaming.domain.repository.ProductRepository;
import com.example.globalgaming.ui.adminPanel.AdminViewModel;
import com.example.globalgaming.ui.adminPanel.AdminViewModelFactory;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class AdminAddProductFragment extends Fragment {
    private FragmentAddProductBinding binding;
    private NavController navController;
    private AdminViewModel adminViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAddProductBinding.inflate(inflater, container, false);
        initViewModel();
        return binding.getRoot();
    }

    private void initViewModel() {
        ProductRepository productRepository = TheApp.appModule.getProductRepository();
        AdminViewModelFactory adminViewModelFactory = new AdminViewModelFactory(productRepository);
        adminViewModel = new ViewModelProvider(this, adminViewModelFactory).get(AdminViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        setBtnCategoryListener();
        setBtnGoBackListener();
        setSwitchListenerForPercent();
        setTypeForProduct();
        checkIsSale();
        switchImageListener();
        setAddProductListener();
    }

    private void switchImageListener() {
        binding.etPicPath.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String picPath = editable.toString();
                if (!picPath.isEmpty() ) { loadPicPathToView(picPath); }
            }
        });
    }

    private void loadPicPathToView(String picPath) {
        Picasso.get().load(picPath).into(binding.ivProductImage);
    }

    private void setTypeForProduct() {
        switchSpecHints(R.id.btn_category_game);
        binding.toggleBtnCategory.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            switchSpecHints(checkedId);
        });
    }

    private void switchSpecHints(int checkedId) {
        if (checkedId == R.id.btn_category_game) {
            binding.tilSpec1.setHint(getResources().getString(R.string.genre));
            binding.tilSpec2.setHint(getResources().getString(R.string.fsk));
        } else {
            binding.tilSpec1.setHint(getResources().getString(R.string.manufacturer));
            binding.tilSpec2.setHint(getResources().getString(R.string.type));
        }
    }

    private void setSwitchListenerForPercent() {
        binding.cbInSale.setOnCheckedChangeListener((compoundButton, checked) ->
                binding.tilSaleInPerCent.setEnabled(checked));
    }

    private void setAddProductListener() {

        binding.btnAddProduct.setOnClickListener(view -> {
            JSONObject productData = createJsonData();
            adminViewModel.addProduct(productData, new ResultCallback<ProductModel>() {
                @Override
                public void onSuccess(Result<ProductModel> response) {
                    Toast.makeText(getContext(), getResources().getString(R.string.added_successfully), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(Result<ProductModel> error) {
                    Toast.makeText(getContext(), getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private JSONObject createJsonData() {
        JSONObject resultData = new JSONObject();
        int productType = checkProductType();

        String imgPath = binding.etPicPath.getText().toString();
        String designation = binding.etProductName.getText().toString();
        String price = binding.etProductPrice.getText().toString();
        String saleInPercent;
        if(checkIsSale()) {
            saleInPercent = binding.etSaleInPerCent.getText().toString();
        } else {
            saleInPercent = "0.0";
        }
        String publication = binding.etReleaseDate.getText().toString();
        String rating = binding.etRating.getText().toString();
        String spec1 = binding.etSpec1.getText().toString();
        String spec2 = binding.etSpec2.getText().toString();


        try {
            resultData.put(Constants.PRODUCT_MODEL_PRODUCT_TYPE, productType);
            resultData.put(Constants.PRODUCT_MODEL_PIC_PATH, imgPath);
            resultData.put(Constants.PRODUCT_MODEL_DESIGNATION, designation);
            resultData.put(Constants.PRODUCT_MODEL_PRICE, price);
            resultData.put(Constants.PRODUCT_MODEL_SALE_IN_PERCENT, saleInPercent);
            resultData.put(Constants.PRODUCT_MODEL_RELEASE_DATE, publication);
            resultData.put(Constants.PRODUCT_MODEL_RATING, rating);
            if (productType == Constants.PRODUCT_TYPE_SOFTWARE) {
                resultData.put(Constants.SOFTWARE_MODEL_GENRE, spec1);
                resultData.put(Constants.SOFTWARE_MODEL_FSK, spec2);
            } else {
                resultData.put(Constants.HARDWARE_MODEL_MANUFACTURER, spec1);
                resultData.put(Constants.HARDWARE_MODEL_TYPE, spec2);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return resultData;
    }

    private int checkProductType() {
        int productTypeId = binding.toggleBtnCategory.getCheckedButtonId();
        if (productTypeId == R.id.btn_category_game) {
            return Constants.PRODUCT_TYPE_SOFTWARE;
        }
        return Constants.PRODUCT_TYPE_HARDWARE;
    }

    private boolean checkIsSale() {
        boolean isSale = binding.cbInSale.isChecked();
        if (isSale) {
            binding.tilSaleInPerCent.setEnabled(isSale);
            return true;
        }
        binding.tilSaleInPerCent.setEnabled(isSale);
        return false;
    }

    private void setBtnCategoryListener() {

    }

    private void setBtnGoBackListener() {
        MaterialButton buyNow = binding.btnGoBack;
        buyNow.setOnClickListener( view -> {
            goToSettingsFragment();
        });
    }

    private void goToSettingsFragment() {
        navController.navigate(R.id.action_addProductFragment_to_settingsFragment);
    }
}
