package com.example.ecommerce.cart;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.ecommerce.R;
import com.example.ecommerce.entity.CartRoomEntity;
import com.example.ecommerce.viewmodel.CartViewModel;

import java.util.List;

public class cart extends AppCompatActivity {
RecyclerView recyclerView;
private CartViewModel cartViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_cart);
        Intent i = getIntent();
        //if(personLoggenIn){
    //}
        cartViewModel= ViewModelProviders.of(this).get(CartViewModel.class);
        recyclerView=findViewById(R.id.recyclerViewforCart);
        final CartAdapterBeforeLogin cartAdapterBeforeLogin=new CartAdapterBeforeLogin();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartAdapterBeforeLogin);
        cartViewModel.getAllItems().observe(this, new Observer<List<CartRoomEntity>>() {
            @Override
            public void onChanged(@Nullable final List<CartRoomEntity> cartRoomEntities) {
                    cartAdapterBeforeLogin.setItems(cartRoomEntities);
            }
        });

        getSupportActionBar().setTitle("Cart");// Display the name in the app bar as per wish---> decide later
        getSupportActionBar().setIcon(R.drawable.ic_shopping_cart);



    }
}
