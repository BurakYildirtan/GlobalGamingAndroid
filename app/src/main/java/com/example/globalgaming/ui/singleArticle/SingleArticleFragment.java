package com.example.globalgaming.ui.singleArticle;

import android.graphics.Paint;
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

import com.example.globalgaming.R;
import com.example.globalgaming.TheApp;
import com.example.globalgaming.common.Constants;
import com.example.globalgaming.common.callbacks.ResultCallback;
import com.example.globalgaming.common.helper.FormatHelpers;
import com.example.globalgaming.common.mapper.Result;
import com.example.globalgaming.databinding.FragmentSingleArticleBinding;
import com.example.globalgaming.domain.model.HardwareModel;
import com.example.globalgaming.domain.model.ProductModel;
import com.example.globalgaming.domain.model.SoftwareModel;
import com.example.globalgaming.domain.repository.HardwareRepository;
import com.example.globalgaming.ui.login.UserViewModel;
import com.example.globalgaming.ui.shoppingCart.ShoppingCartViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SingleArticleFragment extends Fragment {
    private static final String ARG_PRODUCT = "product";

    private FragmentSingleArticleBinding binding;
    private NavController navController;
    private ProductModel product;
    private Boolean isCategory;

    private SingleArticleViewModel singleArticleViewModel;

    private String title;
    private int imgId;
    private ShoppingCartViewModel shoppingCartViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            product = (ProductModel) getArguments().getSerializable("productModel");
            isCategory = getArguments().getBoolean("isCategory", false);
            title = getArguments().getString("title", "");
            imgId = getArguments().getInt("img", 0);
        }
        initSharedViewModel();
        initViewModel();

    }

    private void initViewModel() {
        HardwareRepository hardwareRepository = TheApp.appModule.getHardwareRepository();
        SingleArticleViewModelFactory singleArticleViewModelFactory = new SingleArticleViewModelFactory(hardwareRepository);
        singleArticleViewModel = new ViewModelProvider(this, singleArticleViewModelFactory).get(SingleArticleViewModel.class);
    }

    private void initSharedViewModel() {
        shoppingCartViewModel = new ViewModelProvider(requireActivity()).get(ShoppingCartViewModel.class);
    }

    public static SingleArticleFragment newInstance(ProductModel product) {
        SingleArticleFragment fragment = new SingleArticleFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PRODUCT, product);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSingleArticleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initBackButton();
        initData();
        return root;
    }

    private void initData() {
        String picPath = product.getPicPath();
        String title = product.getDesignation();

        String spec1Title = getResources().getString(R.string.publication);
        String spec1 = product.getReleaseDate();

        String spec2;
        String spec3;
        String spec2Title;
        String spec3Title;

        if (product.isInSale()) {
            Double price = product.getPrice();
            Double saleInPercent = product.getSaleInPercent();
            Double priceSale = FormatHelpers.calculatePriceWithSale(price, saleInPercent);

            String productSalePrice = FormatHelpers.formatPriceAndCurrency(priceSale, "€");
            String productPrice = FormatHelpers.formatPriceAndCurrency(price, "€");

            binding.tvSum.setText(productSalePrice);
            binding.tvSumSale.setText(productPrice);
            binding.tvSumSale.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            String productPrice = FormatHelpers.formatPriceAndCurrency(product.getPrice(), "€");
            binding.tvSum.setText(productPrice);
            binding.tvSumSale.setVisibility(View.GONE);
        }

        if (product.getProductType() == Constants.PRODUCT_TYPE_SOFTWARE) {
            SoftwareModel p = (SoftwareModel) product;

            spec2Title = getResources().getString(R.string.genre);
            spec2 = p.getGenre();
            spec3Title = getResources().getString(R.string.fsk);
            spec3 = String.valueOf(p.getFsk());
        } else {
            HardwareModel p = (HardwareModel) product;

            spec2Title = getResources().getString(R.string.type);
            spec2 = p.getType();
            spec3Title = getResources().getString(R.string.manufacturer);
            spec3 = p.getManufacturer();
        }


        binding.tvToolbarTitle.setText(title);
        binding.tvSpec1Title.setText(spec1Title);
        binding.tvSpec2Title.setText(spec2Title);
        binding.tvSpec3Title.setText(spec3Title);
        binding.tvSpec1Content.setText(spec1);
        binding.tvSpec2Content.setText(spec2);
        binding.tvSpec3Content.setText(spec3);
        Picasso.get().load(picPath).into(binding.ivSingleArticle);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        setAddBtnListener();

        if (product.getProductType() == Constants.PRODUCT_TYPE_SOFTWARE) {
            initMinimumRequirements();
            initRecommendedRequirements();
            showRequirements();
        } else {
            hideRequirements();
        }
    }

    private void showRequirements() {
        binding.clMinimumRequirements.setVisibility(View.VISIBLE);
        binding.clRecommendedRequirements.setVisibility(View.VISIBLE);
    }

    private void hideRequirements() {
        binding.clMinimumRequirements.setVisibility(View.GONE);
        binding.clRecommendedRequirements.setVisibility(View.GONE);
    }

    private void initMinimumRequirements() {
        singleArticleViewModel.getMinimumRequirements(String.valueOf(product.getRating()), new ResultCallback<List<ProductModel>>() {
            @Override
            public void onSuccess(Result<List<ProductModel>> response) {
                Log.d("succ", "Success Min");
                setMinimumRequirements(response.getValue());
            }

            @Override
            public void onError(Result<List<ProductModel>> error) {
                Toast.makeText(getContext(), getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setMinimumRequirements(List<ProductModel> productList) {
        ProductModel cpu = productList.get(0);
        ProductModel gpu = productList.get(1);
        ProductModel ram = productList.get(2);
        binding.tvMinimumCpuContent.setText(cpu.getDesignation());
        binding.tvMinimumGraphicCardContent.setText(gpu.getDesignation());
        binding.tvMinimumRamContent.setText(ram.getDesignation());

        binding.btnAddMinimumRequirementsCpu.setOnClickListener(view -> {
            shoppingCartViewModel.addProduct(cpu);
            Toast.makeText(getContext(), getResources().getString(R.string.add), Toast.LENGTH_SHORT).show();
        });

        binding.btnAddMinimumRequirementsGraphicCard.setOnClickListener(view -> {
            shoppingCartViewModel.addProduct(gpu);
            Toast.makeText(getContext(), getResources().getString(R.string.add), Toast.LENGTH_SHORT).show();
        });

        binding.btnAddMinimumRequirementsRam.setOnClickListener(view -> {
            shoppingCartViewModel.addProduct(ram);
            Toast.makeText(getContext(), getResources().getString(R.string.add), Toast.LENGTH_SHORT).show();
        });
    }

    private void initRecommendedRequirements() {
        singleArticleViewModel.getRecommendedRequirements(String.valueOf(product.getRating()), new ResultCallback<List<ProductModel>>() {
            @Override
            public void onSuccess(Result<List<ProductModel>> response) {
                setRecommendedRequirements(response.getValue());
            }

            @Override
            public void onError(Result<List<ProductModel>> error) {
                Toast.makeText(getContext(), getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setRecommendedRequirements(List<ProductModel> productList) {
        ProductModel cpu = productList.get(0);
        ProductModel gpu = productList.get(1);
        ProductModel ram = productList.get(2);
        binding.tvRecommendedCpuContent.setText(cpu.getDesignation());
        binding.tvRecommendedGraphicCardContent.setText(gpu.getDesignation());
        binding.tvRecommendedRamContent.setText(ram.getDesignation());

        binding.btnAddRecommendedRequirementsCpu.setOnClickListener(view -> {
            shoppingCartViewModel.addProduct(cpu);
            Toast.makeText(getContext(), getResources().getString(R.string.add), Toast.LENGTH_SHORT).show();
        });

        binding.btnAddRecommendedRequirementsGraphicCard.setOnClickListener(view -> {
            shoppingCartViewModel.addProduct(gpu);
            Toast.makeText(getContext(), getResources().getString(R.string.add), Toast.LENGTH_SHORT).show();
        });

        binding.btnAddRecommendedRequirementsRam.setOnClickListener(view -> {
            shoppingCartViewModel.addProduct(ram);
            Toast.makeText(getContext(), getResources().getString(R.string.add), Toast.LENGTH_SHORT).show();
        });
    }

    private void setAddBtnListener() {
        binding.btnBuyNow.setOnClickListener( view -> {
            if(shoppingCartViewModel.addProduct(product)) {
                Toast.makeText(getContext(), getResources().getString(R.string.add), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initBackButton() {
        binding.btnGoBack.setOnClickListener( view -> {
            if (isCategory) {
                Bundle bundle = new Bundle();
                bundle.putInt("img", imgId);
                bundle.putString("title", title);
                navController.navigate(R.id.action_singleArticleFragment_to_categoryFragment, bundle);
            } else {
                navController.navigate(R.id.action_singleArticleFragment_to_homeFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}