package com.example.ecommerce;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {

    private List<Products> productsList;
    CustomInterface customInterface;

    public RecyclerViewAdapter(List<Products> productsList, CustomInterface customInterface) {

        this.productsList = productsList;
        this.customInterface=customInterface;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater=LayoutInflater.from(parent.getContext());
        view=mInflater.inflate(R.layout.displayproductscardview,parent,false);
        return new CustomViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {

        holder.title.setText(productsList.get(position).getProductName());
        holder.price.setText(String.valueOf(productsList.get(position).getPrice()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customInterface.onClick(productsList.get(position));

            }
        });

        Glide.with(holder.productImage.getContext()).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_background)).load(productsList.get(position).getImgurls()).into(holder.productImage);

    }

    @Override
    public int getItemCount() {

        return productsList.size();
    }


    public static class CustomViewHolder extends RecyclerView.ViewHolder
    {
        ImageView productImage;
        CardView cardView;
        TextView title;
        TextView price;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            title=(TextView) itemView.findViewById(R.id.display_prod_name);
            productImage=(ImageView) itemView.findViewById(R.id.display_image);
            price=(TextView) itemView.findViewById(R.id.display_price);
            cardView=(CardView) itemView.findViewById(R.id.card_view);

        }
    }

    public interface CustomInterface
    {
        void onClick(Products products);
    }




}