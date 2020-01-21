package com.example.ecommerce.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.R;

public class CartAdapterAfterLogin extends RecyclerView.Adapter<CartAdapterAfterLogin.Viewholder> {


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.display_cart,parent,false);
       Viewholder viewHolder=new Viewholder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.productName.setText("name");
        holder.productPrice.setText("6788");
        holder.quantity.setText("2");

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView productName;
        TextView productPrice;
        TextView quantity;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.cartImage);
            productName=itemView.findViewById(R.id.cartproductName);
            productPrice=itemView.findViewById(R.id.cartproductPrice);
            quantity=itemView.findViewById(R.id.cartqty);

        }
    }
}
