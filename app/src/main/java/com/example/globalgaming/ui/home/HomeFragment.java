package com.example.globalgaming.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.globalgaming.R;
import com.example.globalgaming.TheApp;
import com.example.globalgaming.common.adapter.ProductAdapter;
import com.example.globalgaming.databinding.FragmentHomeBinding;
import com.example.globalgaming.domain.model.ProductModel;
import com.example.globalgaming.domain.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private FragmentTransaction fragmentTransaction;
    private HomeViewModel homeViewModel;

    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
    }

    private void initViewModel() {
        ProductRepository productRepository = TheApp.appModule.getProductRepository();
        HomeViewModelFactory homeViewModelFactory = new HomeViewModelFactory(productRepository);
        homeViewModel = new ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initCategoryAdapter();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        setLiveDataObserver();
    }

    private void setLiveDataObserver() {
        homeViewModel.getProductModelResult().observe(getViewLifecycleOwner(), products -> {
            if (products.isSuccess()) {
                initProductAdapter(products.getValue());
            } else {
                Toast.makeText(getContext(), getString(R.string.other_failed), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initCategoryAdapter() {
        RecyclerView rvCategory = binding.rvCategory;
        ArrayList<Integer> imgList = new ArrayList<>();
        ArrayList<String> titleList = new ArrayList<>();


        int icSales = getResources().getIdentifier("ic_sales", "drawable", getContext().getPackageName());
        int icHardware = getResources().getIdentifier("ic_hardware", "drawable", getContext().getPackageName());
        int icGames = getResources().getIdentifier("ic_games", "drawable", getContext().getPackageName());

        imgList.add(icSales);
        imgList.add(icHardware);
        imgList.add(icGames);

        String titleSales = getResources().getString(R.string.sales);
        String titleHardware = getResources().getString(R.string.hardware);
        String titleGames = getResources().getString(R.string.games);

        titleList.add(titleSales);
        titleList.add(titleHardware);
        titleList.add(titleGames);


        CatergoryAdapter categoryAdapter = new CatergoryAdapter(this.getContext(), imgList, titleList);
        rvCategory.setAdapter(categoryAdapter);

        categoryAdapter.setOnItemClickListener((title, img) -> {
            goToCategory(title, img);
        });
    }

    private void goToCategory(String title, Integer img) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putInt("img", img);
        navController.navigate(R.id.action_homeFragment_to_categoryFragment, bundle);
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
        navController.navigate(R.id.action_homeFragment_to_singleArticleFragment, bundle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}