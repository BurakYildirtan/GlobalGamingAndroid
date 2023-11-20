package com.example.globalgaming.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.globalgaming.R;

import java.util.ArrayList;

public class CatergoryAdapter extends RecyclerView.Adapter<CatergoryAdapter.ViewHolder> {
    Context context;
    ArrayList<Integer> imgList;
    ArrayList<String> titleList;
    OnItemClickListener onItemClickListener;

    public CatergoryAdapter(Context context, ArrayList<Integer> imgList, ArrayList<String> titleList) {
        this.context = context;
        this.imgList = imgList;
        this.titleList = titleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(imgList.get(position));
        holder.tvTitle.setText(titleList.get(position));
//        holder.itemView.setOnClickListener(view -> onItemClickListener.onClick(holder.imageView, arrayList.get(position)));
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.list_item_image);
            tvTitle = itemView.findViewById(R.id.list_item_title);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(ImageView imageView, String path);
    }
}
