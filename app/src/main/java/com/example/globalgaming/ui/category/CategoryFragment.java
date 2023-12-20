package com.example.globalgaming.ui.category;

import android.os.Bundle;
import android.util.Log;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.globalgaming.R;
import com.example.globalgaming.TheApp;
import com.example.globalgaming.common.Constants;
import com.example.globalgaming.common.adapter.ProductAdapter;
import com.example.globalgaming.databinding.FragmentCategoryBinding;
import com.example.globalgaming.domain.model.HardwareModel;
import com.example.globalgaming.domain.model.ProductModel;
import com.example.globalgaming.domain.model.SoftwareModel;
import com.example.globalgaming.domain.repository.HardwareRepository;
import com.example.globalgaming.domain.repository.SaleRepository;
import com.example.globalgaming.domain.repository.SoftwareRepository;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class CategoryFragment extends Fragment {

    private CategoryViewModel categoryViewModel;
    private FragmentCategoryBinding binding;
    private String title;
    private int imgId;
    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString("title");
            imgId = getArguments().getInt("img");
        }
        initViewModel();
    }

    private void initViewModel() {
        SaleRepository saleRepository = TheApp.appModule.getSaleRepository();
        SoftwareRepository softwareRepository = TheApp.appModule.getSoftwareRepository();
        HardwareRepository hardwareRepository = TheApp.appModule.getHardwareRepository();

        CategoryViewModelFactory categoryViewModelFactory = new CategoryViewModelFactory(saleRepository, softwareRepository, hardwareRepository);
        categoryViewModel = new ViewModelProvider(this, categoryViewModelFactory).get(CategoryViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        setCategoryName();
        setCategoryImage();
        return binding.getRoot();
    }

    private void setCategoryName() {
        binding.tvCategoryTitle.setText(title);
    }

    private void setCategoryImage() {
        binding.ivCategoryImage.setImageResource(imgId);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        setBtnGoBackListener();
        getProducts();
        setLiveDataObserver();

    }

    private void setLiveDataObserver() {
        categoryViewModel.getProductModelResult().observe(getViewLifecycleOwner(), products -> {
            if (products.isSuccess()) {
                initProductAdapter(products.getValue());
            } else {
                Toast.makeText(getContext(), getString(R.string.other_failed), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getProducts() {
        String titleKey;
        if (title.equals(getResources().getString(R.string.sales))) {
            titleKey = Constants.TITLE_KEY_SALE;
        } else if (title.equals(getResources().getString(R.string.games))) {
            titleKey = Constants.TITLE_KEY_SOFTWARE;
        } else {
            titleKey = Constants.TITLE_KEY_HARDWARE;
        }
        categoryViewModel.getProductCategory(titleKey);
    }

    private void setBtnGoBackListener() {
        MaterialButton buyNow = binding.btnGoBack;
        buyNow.setOnClickListener( view -> {
            navController.navigate(R.id.action_categoryFragment_to_homeFragment);
        });
    }

    private void initProductAdapter(List<ProductModel> products) {
        RecyclerView rvProduct = binding.rvProduct;
        ProductAdapter productAdapter = new ProductAdapter(this.getContext(), products, product -> {
            navigateToSingleArticleFragment(product);
        });
        rvProduct.setAdapter(productAdapter);
    }

    private void navigateToSingleArticleFragment(ProductModel product) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("productModel", product);
        navController.navigate(R.id.action_categoryFragment_to_singleArticleFragment, bundle);
    }

}