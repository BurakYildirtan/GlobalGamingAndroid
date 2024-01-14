package com.example.globalgaming.ui.adminPanel.editProduct;

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
import com.example.globalgaming.common.helper.FormatHelpers;
import com.example.globalgaming.common.mapper.Result;
import com.example.globalgaming.databinding.FragmentEditProductBinding;
import com.example.globalgaming.domain.model.HardwareModel;
import com.example.globalgaming.domain.model.ProductModel;
import com.example.globalgaming.domain.model.SoftwareModel;
import com.example.globalgaming.domain.repository.ProductRepository;
import com.example.globalgaming.ui.adminPanel.AdminViewModel;
import com.example.globalgaming.ui.adminPanel.AdminViewModelFactory;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class AdminEditProductSingleArticleFragment extends Fragment {
    private FragmentEditProductBinding binding;
    private NavController navController;
    private String title;
    private ProductModel productModel;

    private AdminViewModel adminViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString("title");
            productModel = (ProductModel) getArguments().getSerializable("productModel");
        }
        initViewModel();
    }

    private void initViewModel() {
        ProductRepository productRepository = TheApp.appModule.getProductRepository();
        AdminViewModelFactory adminViewModelFactory = new AdminViewModelFactory(productRepository);
        adminViewModel = new ViewModelProvider(this, adminViewModelFactory).get(AdminViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentEditProductBinding.inflate(inflater, container, false);
        switchImageListener();
        setInformation();
        return binding.getRoot();
    }

    private void setInformation() {
        binding.etPicPath.setText(productModel.getPicPath());
        binding.etProductName.setText(productModel.getDesignation());
        binding.etProductPrice.setText(String.valueOf(productModel.getPrice()));
        binding.etSaleInPerCent.setText(String.valueOf(productModel.getSaleInPercent()));
        binding.etReleaseDate.setText(String.valueOf(FormatHelpers.formatDataDateToViewDate(productModel.getReleaseDate())));
        binding.etRating.setText(String.valueOf(productModel.getRating()));
        int productType = productModel.getProductType();
        setProductTypeSpecs(productType);

    }

    private void setProductTypeSpecs(int productType) {
        if (productType == Constants.PRODUCT_TYPE_SOFTWARE) {
            binding.tilSpec1.setHint(getResources().getString(R.string.genre));
            binding.tilSpec2.setHint(getResources().getString(R.string.fsk));
            SoftwareModel softwareModel = (SoftwareModel) productModel;
            binding.etSpec1.setText(softwareModel.getGenre());
            binding.etSpec2.setText(String.valueOf(softwareModel.getFsk()));
        } else {
            binding.tilSpec1.setHint(getResources().getString(R.string.manufacturer));
            binding.tilSpec2.setHint(getResources().getString(R.string.type));
            HardwareModel hardwareModel = (HardwareModel) productModel;
            binding.etSpec1.setText(hardwareModel.getManufacturer());
            binding.etSpec2.setText(hardwareModel.getType());
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        setBtnGoBackListener();
        setBtnConfirmListener();
    }

    private void setBtnGoBackListener() {
        MaterialButton btnGoBack = binding.btnGoBack;
        btnGoBack.setOnClickListener( view -> {
            goToAdminEditProductFragment();
        });
    }

    private void goToAdminEditProductFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        navController.navigate(R.id.action_adminEditProductSingleArticleFragment_to_adminEditProductFragment, bundle);
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

    private void setBtnConfirmListener() {
        binding.btnConfirm.setOnClickListener(view -> {
            JSONObject productData = createJsonData();
            adminViewModel.updateProduct(productData, new ResultCallback<ProductModel>() {
                @Override
                public void onSuccess(Result<ProductModel> response) {
                    Toast.makeText(getContext(), getResources().getString(R.string.update_successful), Toast.LENGTH_SHORT).show();
                    goToAdminEditProductFragment();
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
        int productType = productModel.getProductType();

        String imgPath = binding.etPicPath.getText().toString();
        String designation = binding.etProductName.getText().toString();
        String price = binding.etProductPrice.getText().toString();
        String saleInPercent = binding.etSaleInPerCent.getText().toString();
        String publication = FormatHelpers.formatViewDateToDataDate(binding.etReleaseDate.getText().toString());
        String rating = binding.etRating.getText().toString();
        String spec1 = binding.etSpec1.getText().toString();
        String spec2 = binding.etSpec2.getText().toString();

        String productId;
        if (productType == Constants.PRODUCT_TYPE_SOFTWARE) {
            SoftwareModel softwareModel = (SoftwareModel) productModel;
            productId = String.valueOf(softwareModel.getProductId());
        } else {
            HardwareModel hardwareModel = (HardwareModel) productModel;
            productId = String.valueOf(hardwareModel.getProductId());
        }

        try {
            resultData.put(Constants.PRODUCT_MODEL_PRODUCT_ID, productId);
            resultData.put(Constants.PRODUCT_MODEL_PRODUCT_TYPE, productModel.getProductType());
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
}
