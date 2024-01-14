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
import com.example.globalgaming.common.adapter.ProductAdapter;
import com.example.globalgaming.common.dialog.SimpleBottomSheetDialog;
import com.example.globalgaming.databinding.FragmentAdminEditProductBinding;
import com.example.globalgaming.domain.model.ProductModel;
import com.example.globalgaming.domain.repository.ProductRepository;
import com.google.android.material.button.MaterialButton;

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
        buyNow.setOnClickListener( view -> {
            goToSettingsFragment();
        });
    }

    private void goToSettingsFragment() {
        navController.navigate(R.id.action_adminEditProductFragment_to_settingsFragment);
    }

    private void initProductAdapter() {
        RecyclerView rvProduct = binding.rvProduct;
        productAdapter = new ProductAdapter(this.getContext(), product -> {
            if (title.equals(getResources().getString(R.string.edit_product))) {
                goToEditProductFragment(product);
            } else {
                removeProductProcess();
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



    private void removeProductProcess() {
        SimpleBottomSheetDialog simpleBottomSheetDialog = new SimpleBottomSheetDialog();
        simpleBottomSheetDialog.show(getParentFragmentManager(), SimpleBottomSheetDialog.TAG);
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