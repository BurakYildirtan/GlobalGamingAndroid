package com.example.globalgaming.common.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.globalgaming.R;
import com.example.globalgaming.common.helper.FormatHelpers;
import com.example.globalgaming.domain.model.ProductModel;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<ProductModel> productList;
    private OnProductClickListener listener;

    private Context context;


    public ProductAdapter(OnProductClickListener listener) {
        this.productList = new ArrayList<>();
        this.listener = listener;
    }

    public void clearListener() {
        this.listener = null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductModel product = productList.get(position);

        setImage(holder, product.getPicPath());
        setTitle(holder, product.getDesignation  ());
        setPriceWithSale(holder, product.getSaleInPercent(), product.getPrice());
        initItemClickListener(holder, product);
    }

    private void setImage(ViewHolder holder, String picPath) {
        Picasso.get()
                .load(picPath)
                .placeholder(Objects.requireNonNull(ContextCompat.getDrawable(context, R.drawable.ic_default_image)))
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .into(holder.productImage);

    }

    private void setTitle(ViewHolder holder, String designation) {
        holder.productTitle.setText(designation);
    }

    private void setPriceWithSale(ViewHolder holder, Double saleInPercent, Double price) {
        //Sale
        if (saleInPercent != 0.0 ) {
            Double priceSale = FormatHelpers.calculatePriceWithSale(price, saleInPercent);

            String productSalePrice = FormatHelpers.formatPriceAndCurrency(priceSale, "€");
            String productPrice = FormatHelpers.formatPriceAndCurrency(price, "€");

            holder.productPrice.setText(productSalePrice);
            holder.productPriceSale.setText(productPrice);
            holder.productPriceSale.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        } else  {
            String productPrice = FormatHelpers.formatPriceAndCurrency(price, "€");
            holder.productPrice.setText(productPrice);
            holder.productPriceSale.setVisibility(View.GONE);
        }
    }

    private void initItemClickListener(ViewHolder holder, ProductModel product) {
        holder.itemView.setOnClickListener(view -> listener.onProductClick(product));
    }

    @Override
    public int getItemCount() {return productList.size();}

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView productImage;
        TextView productTitle;
        TextView productPrice;
        TextView productPriceSale;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.iv_product);
            productTitle = itemView.findViewById(R.id.tv_product_title);
            productPrice = itemView.findViewById(R.id.tv_product_price);
            productPriceSale = itemView.findViewById(R.id.tv_product_price_sale);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addProductList(List<ProductModel> productList) {
        this.productList = productList;
        notifyDataSetChanged();

    }
}
