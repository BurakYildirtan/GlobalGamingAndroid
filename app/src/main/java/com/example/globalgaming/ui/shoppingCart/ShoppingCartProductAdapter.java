package com.example.globalgaming.ui.shoppingCart;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.globalgaming.R;
import com.example.globalgaming.common.helper.FormatHelpers;
import com.example.globalgaming.domain.model.ProductModel;

import java.util.List;

public class ShoppingCartProductAdapter extends RecyclerView.Adapter<ShoppingCartProductAdapter.ViewHolder> {
    Context context;

    List<ProductModel> productList;


    public ShoppingCartProductAdapter(Context context, List<ProductModel> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_in_shopping_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductModel product = productList.get(position);

        initTitle(holder, product);

//        if (product.getSaleInPercent() != 0.0 ) {
//            initProductPriceSale(holder,product);
//        } else  {
//            initProductPrice(holder, product);
//        }
//        holder.itemView.setOnClickListener(view -> onItemClickListener.onClick(holder.imageView, arrayList.get(position)));
    }

    private void initTitle(ViewHolder holder, ProductModel product) {
//        String title = product.getTitle();
//        holder.productTitle.setText(title);
    }

    private void initProductPrice(ViewHolder holder, ProductModel product) {
//        String productPrice = FormatHelpers.formatPriceAndCurrency(product.getPrice(), "€");
//        holder.productPrice.setText(productPrice);
//        holder.productPriceSale.setVisibility(View.GONE);
    }

    private void initProductPriceSale(ViewHolder holder, ProductModel product) {
//        Double price = product.getPrice();
//        Double saleInPercent = product.getSaleInPercent();
//        Double priceSale = FormatHelpers.calculatePriceWithSale(price, saleInPercent);
//
//        String productSalePrice = FormatHelpers.formatPriceAndCurrency(priceSale, "€");
//        String productPrice = FormatHelpers.formatPriceAndCurrency(price, "€");
//
//        holder.productPrice.setText(productSalePrice);
//        holder.productPriceSale.setText(productPrice);
//        holder.productPriceSale.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
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
