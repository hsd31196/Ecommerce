package com.example.ecommerce.cart;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.R;
import com.example.ecommerce.entity.CartRoomEntity;

public class CartAdapterBeforeLogin extends RecyclerView.Adapter<CartAdapterBeforeLogin.Viewholder> {
private List<CartRoomEntity> listCartItems;

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
       // LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=LayoutInflater.from(context).inflate(R.layout.display_cart,parent,false);
        Viewholder viewholder=new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        if(listCartItems!=null)
        {

            CartRoomEntity current=listCartItems.get(position);
            holder.productName.setText(current.getProductId());
            holder.productPrice.setText(Double.toString(current.getTotalAmount()));
            holder.quantity.setText(Integer.toString(current.getQty()));
        }
        else
        {
            holder.productName.setText("there are no products");
        }

    }

    void setItems(List<CartRoomEntity> items)
    {
        listCartItems=items;
        System.out.println("changesfdsf........");
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(listCartItems!=null)
            return listCartItems.size();
        else
        return 0;
    }

    public class Viewholder  extends RecyclerView.ViewHolder {

     //   LinearLayout cartLinerView;
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
//            cartLinerView=itemView.findViewById(R.id.cartLinerView);
        }
    }
}
