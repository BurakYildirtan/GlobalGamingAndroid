package com.example.globalgaming.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.globalgaming.R;
import com.example.globalgaming.databinding.FragmentHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.google.android.material.carousel.CarouselStrategy;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initCategoryAdapter();

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


        CatergoryAdapter adapter = new CatergoryAdapter(this.getContext(), imgList, titleList);
        rvCategory.setAdapter(adapter);

        adapter.setOnItemClickListener((imageView, path) -> {

        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}