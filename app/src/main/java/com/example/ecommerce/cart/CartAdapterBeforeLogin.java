package com.example.ecommerce.cart;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerce.CategoryAdapterDummy;
import com.example.ecommerce.R;
import com.example.ecommerce.entity.CartRoomEntity;
import com.example.ecommerce.viewmodel.CartViewModel;

public class CartAdapterBeforeLogin extends RecyclerView.Adapter<CartAdapterBeforeLogin.Viewholder> {
private List<CartRoomEntity> listCartItems;
//private CartViewModel cartViewModel;
CustomInterface customInterface;


public CartAdapterBeforeLogin(CustomInterface customInterface)
{
    this.customInterface=customInterface;
}

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("in the create viewHolder");
        Context context=parent.getContext();
       // cartViewModel= ViewModelProviders.of(context).get(CartViewModel.class);

        // LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=LayoutInflater.from(context).inflate(R.layout.display_cart,parent,false);
        Viewholder viewholder=new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder holder, int position) {
        System.out.println("size of cart "+listCartItems.size());
        if(listCartItems!=null)
        {
            System.out.println("in the bind");

            final CartRoomEntity current=listCartItems.get(position);
            System.out.println(current.getQty());
            Glide.with(holder.imageView.getContext()).load(current.getImageUrl()).into(holder.imageView);
            holder.productName.setText(current.getProductName());
            holder.productPrice.setText(Double.toString(current.getTotalAmount()));
            holder.quantity.setText(Integer.toString(current.getQty()));
            holder.incrementButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("in the increment");
                    //holder.quantity.setText(Integer.toString(current.getQty())+1);
                    customInterface.onClick(current.getProductId());
                }
            });

            holder.decrementButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(current.getQty()>0) {
                        customInterface.onClickDec(current.getProductId());
                    }
                    //cartViewModel.updateDecrement(holder.productName.toString());
                }
            });
            if(current.getQty()==0)
            {
                customInterface.delIfZero(current.getProductId());
            }
            holder.deleteCartItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customInterface.delIfZero(current.getProductId());
                }
            });

        }
        else
        {
            holder.displatIfEmpty.setText("NO items in cart");
            holder.displatIfEmpty.setVisibility(View.VISIBLE);
        }

    }

    void setItems(List<CartRoomEntity> items)
    {
        listCartItems=items;
        System.out.println("changesfdsf........");
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(listCartItems!=null)
            return listCartItems.size();
        else
        return 0;
    }

    public class Viewholder  extends RecyclerView.ViewHolder {

        TextView displatIfEmpty;
        ImageView imageView;
        TextView productName;
        TextView productPrice;
        TextView quantity;
        Button incrementButton;
        Button decrementButton;
        Button deleteCartItem;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.cartImage);
            productName=itemView.findViewById(R.id.cartproductName);
            productPrice=itemView.findViewById(R.id.cartproductPrice);
            quantity=itemView.findViewById(R.id.cartqty);
            incrementButton=itemView.findViewById(R.id.increaseqty);
            decrementButton=itemView.findViewById(R.id.decreaseqty);
            deleteCartItem=itemView.findViewById(R.id.deleteCartItem);
            displatIfEmpty=itemView.findViewById(R.id.displayIfempty);
//            cartLinerView=itemView.findViewById(R.id.cartLinerView);
        }
    }
    public interface  CustomInterface {
            void onClick(String id);
            void onClickDec(String id);
            void delIfZero(String id);
    }
}


