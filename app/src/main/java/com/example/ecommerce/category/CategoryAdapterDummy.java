package com.example.ecommerce.category;

import android.content.Context;
import android.graphics.Paint;
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
import com.example.ecommerce.R;
import com.example.ecommerce.products.Products;

import java.util.List;

public class CategoryAdapterDummy extends RecyclerView.Adapter<CategoryAdapterDummy.Viewholder> {

    List<Products> listProducts;
    CustomInterface customInterface;


    public CategoryAdapterDummy(List<Products> returnedProducts,CustomInterface customInterface) {
        listProducts=returnedProducts;
        this.customInterface=customInterface;
        System.out.println("In the adapter"+listProducts);
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context=parent.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.displayproductscardview,parent,false);
        Viewholder viewHolder=new Viewholder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, final int position) {
        Products object=listProducts.get(position);
        holder.prodName.setText(object.getProductName());
        holder.prodPrice.setText("₹"+Double.toString(object.getPrice()));
        holder.prodPrice.setPaintFlags(holder.prodPrice.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        holder.discountedPrice.setText(Double.toString(object.getDiscountedPrice()));
        holder.ratingBar.setRating((float) object.getRating());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customInterface.onClick(listProducts.get(position));

            }
        });
        Glide.with(holder.imageView.getContext()).load(object.getImgurls().get(0)).into(holder.imageView);


    }

    @Override
    public int getItemCount() {

        return listProducts.size();

    }


    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView prodName;
        TextView prodPrice;
        CardView cardView;
        RatingBar ratingBar;
        TextView discountedPrice;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.display_image);
            prodName=itemView.findViewById(R.id.display_prod_name);
            prodPrice=itemView.findViewById(R.id.display_price);
            cardView=itemView.findViewById(R.id.card_view);
            ratingBar=itemView.findViewById(R.id.ratingId);
            discountedPrice=itemView.findViewById(R.id.discountedPrie);

        }
    }

    public interface CustomInterface
    {
        void onClick(Products products);
    }

}
