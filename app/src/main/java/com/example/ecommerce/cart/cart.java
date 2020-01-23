package com.example.ecommerce.cart;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ecommerce.CheckOut;
import com.example.ecommerce.R;
import com.example.ecommerce.entity.CartRoomEntity;
import com.example.ecommerce.viewmodel.CartViewModel;

import java.util.List;

public class cart extends AppCompatActivity  implements CartAdapterBeforeLogin.CustomInterface{
RecyclerView recyclerView;
private CartViewModel cartViewModel;
Button checkoutButton;
CartAdapterBeforeLogin cartAdapterBeforeLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_cart);
        //Intent i = getIntent();
        //if(personLoggenIn){
    //}
        cartViewModel= ViewModelProviders.of(this).get(CartViewModel.class);
        recyclerView=findViewById(R.id.recyclerViewforCart);
         cartAdapterBeforeLogin=new CartAdapterBeforeLogin(cart.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartAdapterBeforeLogin);
        cartViewModel.getAllItems().observe(this, new Observer<List<CartRoomEntity>>() {
            @Override
            public void onChanged(@Nullable final List<CartRoomEntity> cartRoomEntities) {
                    cartAdapterBeforeLogin.setItems(cartRoomEntities);
            }
        });

        getSupportActionBar().setTitle("Cart");// Display the name in the app bar as per wish---> decide later
        checkoutButton=findViewById(R.id.checkout);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), CheckOut.class);
                startActivity(i);
            }
        });

    }


    @Override
    public void onClick(final String id) {
        System.out.println("id of string"+id);
        cartViewModel.update(id);
    }
    @Override
    public void onClickDec(final String id) {
                cartViewModel.updateDecrement(id);
    }

    @Override
    public void delIfZero(String id) {
        cartViewModel.delById(id);
    }
}
