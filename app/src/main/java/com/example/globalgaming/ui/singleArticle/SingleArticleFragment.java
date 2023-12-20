package com.example.globalgaming.ui.singleArticle;

import android.graphics.Paint;
import android.os.Bundle;
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
import com.example.globalgaming.common.helper.FormatHelpers;
import com.example.globalgaming.databinding.FragmentSingleArticleBinding;
import com.example.globalgaming.domain.model.HardwareModel;
import com.example.globalgaming.domain.model.ProductModel;
import com.example.globalgaming.domain.model.SoftwareModel;
import com.example.globalgaming.ui.login.UserViewModel;
import com.example.globalgaming.ui.shoppingCart.ShoppingCartViewModel;
import com.squareup.picasso.Picasso;

public class SingleArticleFragment extends Fragment {
    private static final String ARG_PRODUCT = "product";

    private FragmentSingleArticleBinding binding;

    private NavController navController;

    private ProductModel product;
    private ShoppingCartViewModel shoppingCartViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            product = (ProductModel) getArguments().getSerializable("productModel");
        }
        initSharedViewModel();

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
        String picPath;
        String title;
        String spec1;
        String spec2;
        String spec3;
        String spec1Title;
        String spec2Title;
        String spec3Title;

        //Image
        if (product.getModelData() instanceof SoftwareModel) {
            SoftwareModel p = (SoftwareModel) product.getModelData();
            picPath = p.getPicPath();
            title = p.getDesignation();
            spec1 = p.getReleaseDate();
            spec2 = p.getGenre();
            spec3 = String.valueOf(p.getFsk());
            spec1Title = getResources().getString(R.string.publication);
            spec2Title = getResources().getString(R.string.genre);
            spec3Title = getResources().getString(R.string.fsk);

            if (p.isInSale()) {
                Double price = p.getPrice();
                Double saleInPercent = p.getSaleInPercent();
                Double priceSale = FormatHelpers.calculatePriceWithSale(price, saleInPercent);

                String productSalePrice = FormatHelpers.formatPriceAndCurrency(priceSale, "€");
                String productPrice = FormatHelpers.formatPriceAndCurrency(price, "€");

                binding.tvSum.setText(productSalePrice);
                binding.tvSumSale.setText(productPrice);
                binding.tvSumSale.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                String productPrice = FormatHelpers.formatPriceAndCurrency(p.getPrice(), "€");
                binding.tvSum.setText(productPrice);
                binding.tvSumSale.setVisibility(View.GONE);
            }

        } else {
            HardwareModel p = (HardwareModel) product.getModelData();
            picPath = ((HardwareModel) product.getModelData()).getPicPath();
            title = ((HardwareModel) product.getModelData()).getDesignation();
            spec1 = p.getReleaseDate();
            spec2 = p.getType();
            spec3 = p.getManufacturer();
            spec1Title = getResources().getString(R.string.publication);
            spec2Title = getResources().getString(R.string.type);
            spec3Title = getResources().getString(R.string.manufacturer);

            if (p.isInSale()) {
                Double price = p.getPrice();
                Double saleInPercent = p.getSaleInPercent();
                Double priceSale = FormatHelpers.calculatePriceWithSale(price, saleInPercent);

                String productSalePrice = FormatHelpers.formatPriceAndCurrency(priceSale, "€");
                String productPrice = FormatHelpers.formatPriceAndCurrency(price, "€");

                binding.tvSum.setText(productSalePrice);
                binding.tvSumSale.setText(productPrice);
                binding.tvSumSale.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                String productPrice = FormatHelpers.formatPriceAndCurrency(p.getPrice(), "€");
                binding.tvSum.setText(productPrice);
                binding.tvSumSale.setVisibility(View.GONE);
            }
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
            navController.navigate(R.id.action_singleArticleFragment_to_homeFragment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}