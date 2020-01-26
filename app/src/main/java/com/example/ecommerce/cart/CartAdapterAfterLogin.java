package com.example.ecommerce.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerce.R;
import com.example.ecommerce.entity.CartRoomEntity;

import java.util.List;

public class CartAdapterAfterLogin extends RecyclerView.Adapter<CartAdapterAfterLogin.Viewholder> {
    private List<CartRoomEntity> listCartItems;
    //private CartViewModel cartViewModel;
   CustomInterface customInterface;

    public CartAdapterAfterLogin(CustomInterface customInterface,List<CartRoomEntity> cartRoomEntities)
    {
        this.customInterface=customInterface;
        listCartItems=cartRoomEntities;
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("in the create viewHolder");
        Context context=parent.getContext();
        // cartViewModel= ViewModelProviders.of(context).get(CartViewModel.class);

        // LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=LayoutInflater.from(context).inflate(R.layout.display_cart,parent,false);
        CartAdapterAfterLogin.Viewholder viewholder=new CartAdapterAfterLogin.Viewholder(view);
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
                    customInterface.onClickIncCart(current.getProductId(),current.getMerchantId(), holder.getAdapterPosition());
                }
            });

            holder.decrementButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //if(current.getQty()>0) {
                        customInterface.onClickDecCart(current.getProductId(),current.getMerchantId(),holder.getAdapterPosition());
                   // }
                    //cartViewModel.updateDecrement(holder.productName.toString());
                }
            });
//            if(current.getQty()==0)
//            {
//                customInterface.delIfZeroCart(current.getProductId(),current.getMerchantId(),position);
//            }
            holder.deleteCartItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customInterface.delIfZeroCart(current.getProductId(),current.getMerchantId(),holder.getAdapterPosition());
                }
            });

        }
        else
        {
            holder.displatIfEmpty.setText("NO items in cart");
            holder.displatIfEmpty.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return listCartItems.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

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

        }
    }

    public interface  CustomInterface {
        void onClickIncCart(String id,String merchantId, int position);
        void onClickDecCart(String id,String  merchantId,int position);
        void delIfZeroCart(String id,String merchantId,int position);
    }
}
