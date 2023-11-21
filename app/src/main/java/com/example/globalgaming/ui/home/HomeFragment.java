package com.example.globalgaming.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.globalgaming.R;
import com.example.globalgaming.databinding.FragmentHomeBinding;
import com.example.globalgaming.domain.model.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.google.android.material.carousel.CarouselStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initCategoryAdapter();
        initProductAdapter();

        return root;
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

        categoryAdapter.setOnItemClickListener((imageView, path) -> {

        });


    }

    private void initProductAdapter() {
        RecyclerView rvProduct = binding.rvProduct;
        ArrayList<Product> productList = new ArrayList<>();

        for (int i = 0; i < 20 ; i++) {
            Product p = new Product(i, "Global Gaming Test", 79.99, 19.00);
            productList.add(p);
        }

        ProductAdapter productAdapter = new ProductAdapter(this.getContext(), productList);
        rvProduct.setAdapter(productAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}