package com.example.ecommerce.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ecommerce.pojo.Products;
import com.example.ecommerce.R;

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

        //set the data in views
        holder.title.setText(productsList.get(position).getProductName());
        holder.price.setText(String.valueOf(productsList.get(position).getPrice()));
        holder.rating.setRating(productsList.get(position).getRating());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customInterface.onClick(productsList.get(position));

            }
        });

        //Picasso.with(context).load(your image url).into(imageView);
        Glide.with(holder.productImage.getContext()).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_background)).load(productsList.get(position).getImageUrl().get(0)).into(holder.productImage);

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
        RatingBar rating;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            title=(TextView) itemView.findViewById(R.id.display_prod_name);
            productImage=(ImageView) itemView.findViewById(R.id.display_image);
            price=(TextView) itemView.findViewById(R.id.display_price);
            cardView=(CardView) itemView.findViewById(R.id.card_view);
            rating=(RatingBar) itemView.findViewById(R.id.ratingbar);

        }
    }

    public interface CustomInterface
    {
        void onClick(Products products);
    }




}
