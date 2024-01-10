package com.example.globalgaming.ui.shoppingCart;

import android.annotation.SuppressLint;
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
import com.example.globalgaming.common.Constants;
import com.example.globalgaming.common.adapter.OnProductClickListener;
import com.example.globalgaming.common.helper.FormatHelpers;
import com.example.globalgaming.domain.model.ProductModel;
import com.example.globalgaming.domain.model.ShoppingCartModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartProductAdapter extends RecyclerView.Adapter<ShoppingCartProductAdapter.ViewHolder> {
    Context context;

    List<ShoppingCartModel> shoppingCartList;

    private OnQuantityChangedListener listener;


    public ShoppingCartProductAdapter(Context context, OnQuantityChangedListener listener) {
        this.context = context;
        this.shoppingCartList = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_in_shopping_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShoppingCartModel productSc = shoppingCartList.get(position);

        initTitle(holder, productSc);
        initImage(holder, productSc.getProduct().getPicPath());
        initQuantity(holder, productSc.getQuantity());
        if (productSc.getProduct().getSaleInPercent() != 0.0 ) {
            initProductPriceSale(holder,productSc.getProduct(), productSc.getQuantity());
        } else  {
            initProductPrice(holder, productSc.getProduct(), productSc.getQuantity());
        }
        setBtnAddListener(holder, productSc);
        setBtnRemoveListener(holder, productSc);

    }

    private void initQuantity(ViewHolder holder, int quantity) {
        holder.productAmount.setText(String.valueOf(quantity));
    }

    private void initImage(ViewHolder holder, String picPath) {
        Picasso.get().load(picPath).into(holder.productImage);
    }

    private void initTitle(ViewHolder holder, ShoppingCartModel productSc) {
        String title = productSc.getProduct().getDesignation();
        holder.productTitle.setText(title);
    }

    private void initProductPrice(ViewHolder holder, ProductModel product, int quantity) {
        String productPrice = FormatHelpers.formatPriceAndCurrency(product.getPrice() * quantity, "€");
        holder.productPrice.setText(productPrice);
        holder.productPriceSale.setVisibility(View.GONE);
    }

    private void initProductPriceSale(ViewHolder holder, ProductModel product, int quantity) {
        Double price = product.getPrice();
        Double saleInPercent = product.getSaleInPercent();
        Double priceSale = FormatHelpers.calculatePriceWithSale(price, saleInPercent);

        String productSalePrice = FormatHelpers.formatPriceAndCurrency(priceSale * quantity , "€");
        String productPrice = FormatHelpers.formatPriceAndCurrency(price * quantity, "€");

        holder.productPrice.setText(productSalePrice);
        holder.productPriceSale.setText(productPrice);
        holder.productPriceSale.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    private void setBtnAddListener(ViewHolder holder, ShoppingCartModel productSc) {
        holder.btnAdd.setOnClickListener(view -> listener.onQuantityChanged(productSc, Constants.BTN_ADD));
    }

    private void setBtnRemoveListener(ViewHolder holder, ShoppingCartModel productSc) {
        holder.btnRemove.setOnClickListener(view -> listener.onQuantityChanged(productSc, Constants.BTN_REMOVE));
    }

    @Override
    public int getItemCount() {return shoppingCartList.size();}


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productTitle;
        TextView productPrice;
        TextView productPriceSale;
        MaterialTextView productAmount;

        MaterialButton btnAdd;

        MaterialButton btnRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.iv_product);
            productTitle = itemView.findViewById(R.id.tv_product_title);
            productPrice = itemView.findViewById(R.id.tv_product_price);
            productPriceSale = itemView.findViewById(R.id.tv_product_price_sale);
            productAmount = itemView.findViewById(R.id.tv_product_amount);
            btnAdd = itemView.findViewById(R.id.btn_plus);
            btnRemove = itemView.findViewById(R.id.btn_minus);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addList(List<ShoppingCartModel> shoppingCartList) {
        this.shoppingCartList = shoppingCartList;
        notifyDataSetChanged();
    }
}
