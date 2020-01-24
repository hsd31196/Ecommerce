package com.example.ecommerce.cart;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ecommerce.CheckOut;
import com.example.ecommerce.NavigationHome;
import com.example.ecommerce.Products;
import com.example.ecommerce.R;
import com.example.ecommerce.entity.CartRoomEntity;
import com.example.ecommerce.ui.account.AccountFragment;
import com.example.ecommerce.viewmodel.CartViewModel;

import java.util.List;

public class cart extends AppCompatActivity  implements CartAdapterBeforeLogin.CustomInterface{
RecyclerView recyclerView;
private CartViewModel cartViewModel;
Button checkoutButton;
Button homeButton;
TextView cartEmpty;
CartAdapterBeforeLogin cartAdapterBeforeLogin;
SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_cart);
        preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //Intent i = getIntent();
        //if(personLoggenIn){
    //}
        cartViewModel= ViewModelProviders.of(this).get(CartViewModel.class);
        recyclerView=findViewById(R.id.recyclerViewforCart);
        checkoutButton=findViewById(R.id.checkout);
        cartEmpty=findViewById(R.id.textforEmpty);
        homeButton=findViewById(R.id.homeButton);
        cartViewModel.getAllItems().observe(this, new Observer<List<CartRoomEntity>>() {
            @Override
            public void onChanged(@Nullable final List<CartRoomEntity> cartRoomEntities) {

                generateList(cartRoomEntities);


            }
        });

        getSupportActionBar().setTitle("Cart");// Display the name in the app bar as per wish---> decide later
        //checkoutButton=findViewById(R.id.checkout);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), CheckOut.class);
                startActivity(i);
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(), NavigationHome.class);
                startActivity(i1);
                finish();
            }
        });

    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        finish();
//    }

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

public void generateList(List<CartRoomEntity> cartRoomEntities)
{

    if(cartRoomEntities.size()==0)
    {
        recyclerView.setVisibility(View.GONE);
        cartEmpty.setVisibility(View.VISIBLE);
        homeButton.setVisibility(View.VISIBLE);
        checkoutButton.setVisibility(View.GONE);
    }
    else {
        System.out.println("username    "+preferences.getString("username","username"));
        checkoutButton.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        cartAdapterBeforeLogin=new CartAdapterBeforeLogin(cart.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartAdapterBeforeLogin);
        cartAdapterBeforeLogin.setItems(cartRoomEntities);
    }





}



}
