package com.example.ecommerce.ui.home;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerce.R;
import com.example.ecommerce.products.Products;

public class HomeAdapter  extends RecyclerView.Adapter<HomeAdapter.Viewholder> {

    List<Products> listItems;
    CustomInterface customInterface;
public HomeAdapter(List<Products> list,CustomInterface customInterface)
{
    listItems=list;
    this.customInterface=customInterface;
}

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("in the viewhler of on create");
        Context context=parent.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.displayproductscardview,parent,false);
        Viewholder viewholder=new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, final int position) {
    final Products object=listItems.get(position);
        Glide.with(holder.imageView.getContext()).load(object.getImgurls().get(0)).into(holder.imageView);
        holder.productName.setText(object.getProductName());
        holder.productPrice.setText("₹ "+Double.toString(object.getPrice()));
        holder.productPrice.setPaintFlags(holder.productPrice.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        holder.discountedPrice.setText("₹"+Double.toString(object.getDiscountedPrice()));
        holder.ratingBar.setRating((float) object.getRating());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customInterface.onClick(listItems.get(position));
            }
        });

    }



    @Override
    public int getItemCount() {
    if(listItems!=null)
        return listItems.size();
    else
        return 0;
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView productName;
        TextView productPrice;
        TextView discountedPrice;
        RatingBar ratingBar;
        CardView cardView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.display_image);
            productName=itemView.findViewById(R.id.display_prod_name);
            productPrice=itemView.findViewById(R.id.display_price);
            ratingBar=itemView.findViewById(R.id.ratingId);
            cardView=itemView.findViewById(R.id.card_view);
            discountedPrice=itemView.findViewById(R.id.discountedPrie);
        }
    }

    public interface CustomInterface{

    void onClick(Products products);

    }
}
