package com.example.globalgaming.common.adapter;

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
import com.example.globalgaming.domain.model.HardwareModel;
import com.example.globalgaming.domain.model.ProductModel;
import com.example.globalgaming.domain.model.SoftwareModel;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private final Context context;
    private final List<ProductModel> productList;
    private OnProductClickListener listener;


    public ProductAdapter(Context context, List<ProductModel> productList, OnProductClickListener listener) {
        this.context = context;
        this.productList = productList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductModel product = productList.get(position);
        Object modelData = product.getModelData();
        if (modelData instanceof SoftwareModel) {
            SoftwareModel softwareModel = (SoftwareModel) modelData;

            //Title
            String title = softwareModel.getDesignation();
            holder.productTitle.setText(title);

            //Sale
            if (softwareModel.getSaleInPercent() != 0.0 ) {
                Double price = softwareModel.getPrice();
                Double saleInPercent = softwareModel.getSaleInPercent();
                Double priceSale = FormatHelpers.calculatePriceWithSale(price, saleInPercent);

                String productSalePrice = FormatHelpers.formatPriceAndCurrency(priceSale, "€");
                String productPrice = FormatHelpers.formatPriceAndCurrency(price, "€");

                holder.productPrice.setText(productSalePrice);
                holder.productPriceSale.setText(productPrice);
                holder.productPriceSale.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            } else  {
                String productPrice = FormatHelpers.formatPriceAndCurrency(softwareModel.getPrice(), "€");
                holder.productPrice.setText(productPrice);
                holder.productPriceSale.setVisibility(View.GONE);
            }

            //Image
            Picasso.get().load(softwareModel.getPicPath()).into(holder.productImage);

        } else {
            HardwareModel hardwareModel = (HardwareModel) modelData;

            //Title
            String title = hardwareModel.getDesignation();
            holder.productTitle.setText(title);

            //Sale
            if (hardwareModel.getSaleInPercent() != 0.0 ) {
                Double price = hardwareModel.getPrice();
                Double saleInPercent = hardwareModel.getSaleInPercent();
                Double priceSale = FormatHelpers.calculatePriceWithSale(price, saleInPercent);

                String productSalePrice = FormatHelpers.formatPriceAndCurrency(priceSale, "€");
                String productPrice = FormatHelpers.formatPriceAndCurrency(price, "€");

                holder.productPrice.setText(productSalePrice);
                holder.productPriceSale.setText(productPrice);
                holder.productPriceSale.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            } else  {
                String productPrice = FormatHelpers.formatPriceAndCurrency(hardwareModel.getPrice(), "€");
                holder.productPrice.setText(productPrice);
                holder.productPriceSale.setVisibility(View.GONE);
            }

            //Image
            Picasso.get().load(hardwareModel.getPicPath()).into(holder.productImage);
        }


        initItemClickListener(holder, product);
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
}
