package com.example.globalgaming.ui.adminPanel.editProduct;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.globalgaming.R;
import com.example.globalgaming.TheApp;
import com.example.globalgaming.common.Constants;
import com.example.globalgaming.common.adapter.ProductAdapter;
import com.example.globalgaming.common.callbacks.ResultCallback;
import com.example.globalgaming.common.dialog.SimpleBottomSheetDialog;
import com.example.globalgaming.common.helper.FormatHelpers;
import com.example.globalgaming.common.mapper.Result;
import com.example.globalgaming.databinding.FragmentAdminEditProductBinding;
import com.example.globalgaming.domain.model.HardwareModel;
import com.example.globalgaming.domain.model.ProductModel;
import com.example.globalgaming.domain.model.SoftwareModel;
import com.example.globalgaming.domain.repository.ProductRepository;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

public class AdminEditProductFragment extends Fragment {

    private AdminEditProductViewModel adminEditProductViewModel;
    private FragmentAdminEditProductBinding binding;
    private String title;
    private NavController navController;
    private ProductAdapter productAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString("title");
        }
        initViewModel();
    }

    private void initViewModel() {
        ProductRepository productRepository = TheApp.appModule.getProductRepository();

        AdminEditProductViewModelFactory adminEditProductViewModelFactory = new AdminEditProductViewModelFactory(productRepository);
        adminEditProductViewModel = new ViewModelProvider(this, adminEditProductViewModelFactory).get(AdminEditProductViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAdminEditProductBinding.inflate(inflater, container, false);
        initToolbarTitle();
        initProductAdapter();
        return binding.getRoot();
    }

    private void initToolbarTitle() {
        binding.tvToolbarTitle.setText(title);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        productAdapter.clearListener();
        productAdapter = null;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        setBtnGoBackListener();
        setLiveDataObserver();
        setSearchView();

    }

    private void setLiveDataObserver() {
        adminEditProductViewModel.getProductModelResult().observe(getViewLifecycleOwner(), products -> {
            if (products.isSuccess()) {
                productAdapter.addProductList(products.getValue());
            } else {
                Toast.makeText(getContext(), getString(R.string.other_failed), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setBtnGoBackListener() {
        MaterialButton buyNow = binding.btnGoBack;
        buyNow.setOnClickListener( view -> goToSettingsFragment());
    }

    private void goToSettingsFragment() {
        navController.navigate(R.id.action_adminEditProductFragment_to_settingsFragment);
    }

    private void initProductAdapter() {
        RecyclerView rvProduct = binding.rvProduct;
        productAdapter = new ProductAdapter(product -> {
            if (title.equals(getResources().getString(R.string.edit_product))) {
                goToEditProductFragment(product);
            } else {
                removeProductProcess(product);
            }
        });
        rvProduct.setAdapter(productAdapter);
    }

    private void goToEditProductFragment(ProductModel product) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putSerializable("productModel", product);
        navController.navigate(R.id.action_adminEditProductFragment_to_adminEditProductSingleArticleFragment, bundle);
    }



    private void removeProductProcess(ProductModel product) {
        SimpleBottomSheetDialog simpleBottomSheetDialog = new SimpleBottomSheetDialog( isConfirmed -> {
            if (isConfirmed) {
                JSONObject productData = createJsonData(product);
                adminEditProductViewModel.deleteProduct(productData, new ResultCallback<String>() {
                    @Override
                    public void onSuccess(Result<String> response) {
                        Toast.makeText(getContext(), getResources().getString(R.string.removed_successful), Toast.LENGTH_SHORT).show();
                        adminEditProductViewModel.getAllProducts();
                    }

                    @Override
                    public void onError(Result<String> error) {
                        Toast.makeText(getContext(), getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        simpleBottomSheetDialog.show(getParentFragmentManager(), SimpleBottomSheetDialog.TAG);
    }

    private JSONObject createJsonData(ProductModel productModel) {
        JSONObject resultData = new JSONObject();
        int productType = productModel.getProductType();

        String productId;
        if (productType == Constants.PRODUCT_TYPE_SOFTWARE) {
            SoftwareModel softwareModel = (SoftwareModel) productModel;
            productId = String.valueOf(softwareModel.getProductId());
        } else {
            HardwareModel hardwareModel = (HardwareModel) productModel;
            productId = String.valueOf(hardwareModel.getProductId());
        }

        try {
            String viewFormattedReleaseDate = FormatHelpers.formatDataDateToViewDate(productModel.getReleaseDate());
            String putFormat = FormatHelpers.formatViewDateToDataDate(viewFormattedReleaseDate);
            resultData.put(Constants.PRODUCT_MODEL_PRODUCT_ID, productId);
            resultData.put(Constants.PRODUCT_MODEL_PRODUCT_TYPE, productModel.getProductType());
            resultData.put(Constants.PRODUCT_MODEL_PIC_PATH, productModel.getPicPath());
            resultData.put(Constants.PRODUCT_MODEL_DESIGNATION, productModel.getDesignation());
            resultData.put(Constants.PRODUCT_MODEL_PRICE, String.valueOf(productModel.getPrice()));
            resultData.put(Constants.PRODUCT_MODEL_SALE_IN_PERCENT, String.valueOf(productModel.getSaleInPercent()));
            resultData.put(Constants.PRODUCT_MODEL_RELEASE_DATE, putFormat);
            resultData.put(Constants.PRODUCT_MODEL_RATING, String.valueOf(productModel.getRating()));
            if (productType == Constants.PRODUCT_TYPE_SOFTWARE) {
                SoftwareModel softwareModel = (SoftwareModel) productModel;
                resultData.put(Constants.SOFTWARE_MODEL_GENRE, softwareModel.getGenre() );
                resultData.put(Constants.SOFTWARE_MODEL_FSK, String.valueOf(softwareModel.getFsk()));
            } else {
                HardwareModel hardwareModel = (HardwareModel) productModel;
                resultData.put(Constants.HARDWARE_MODEL_MANUFACTURER, hardwareModel.getManufacturer());
                resultData.put(Constants.HARDWARE_MODEL_TYPE, hardwareModel.getType());
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return resultData;
    }

    private void setSearchView() {
        SearchView searchBar = binding.sbProduct;
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                productAdapter.addProductList(adminEditProductViewModel.filterList(s));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                productAdapter.addProductList(adminEditProductViewModel.filterList(s));
                return false;
            }
        });


    }
}