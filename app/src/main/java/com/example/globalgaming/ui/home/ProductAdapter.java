package com.example.globalgaming.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.globalgaming.R;
import com.example.globalgaming.domain.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    Context context;

    List<Product> productList;


    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);

        initTitle(holder, product);

        if (product.getSaleInPercent() != 0.0 ) {
            initProductPriceSale(holder,product);
        } else  {
            initProductPrice(holder, product);
        }
//        holder.itemView.setOnClickListener(view -> onItemClickListener.onClick(holder.imageView, arrayList.get(position)));
    }

    private void initTitle(ViewHolder holder, Product product) {
        String title = product.getTitle();
        holder.productTitle.setText(title);
    }

    private void initProductPrice(ViewHolder holder, Product product) {
        Double productPrice = product.getPrice();
        holder.productPrice.setText(String.valueOf(productPrice));
    }

    private void initProductPriceSale(ViewHolder holder, Product product) {
        Double productPrice = product.getPrice();
        Double productSaleInPercent = product.getSaleInPercent();
        Double productPriceSale = productPrice * ( 1 -  productSaleInPercent / 100 );

        holder.productPrice.setText(String.valueOf(productPriceSale));
        holder.productPriceSale.setText(String.valueOf(productPrice));
    }

    @Override
    public int getItemCount() {return productList.size();}

    public static class ViewHolder extends RecyclerView.ViewHolder {
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

//    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }

    public interface OnItemClickListener {
        void onClick(ImageView imageView, String path);
    }
}
