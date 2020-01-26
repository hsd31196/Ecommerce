package com.example.ecommerce.cart;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

import com.example.ecommerce.app.App;
import com.example.ecommerce.order.CheckOut;
import com.example.ecommerce.NavigationHome;
import com.example.ecommerce.R;
import com.example.ecommerce.entity.CartRoomEntity;
import com.example.ecommerce.ui.account.AccountFragment;
import com.example.ecommerce.viewmodel.CartViewModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class cart extends AppCompatActivity implements CartAdapterBeforeLogin.CustomInterface, CartAdapterAfterLogin.CustomInterface, Callback<List<CartRoomEntity>>
{
RecyclerView recyclerView;
private CartViewModel cartViewModel;
Button checkoutButton;
Button homeButton;
TextView cartEmpty;
CartAdapterBeforeLogin cartAdapterBeforeLogin;
CartAdapterAfterLogin cartAdapterAfterLogin;
List<CartRoomEntity> cartItems;
SharedPreferences preferences;
Retrofit retrofit= App.getRetrofit();
CartAPIInterface cartAPIInterface=retrofit.create(CartAPIInterface.class);
Call<List<CartRoomEntity>> call;
Call<ResponseBody> callUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_cart);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        cartViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        recyclerView = findViewById(R.id.recyclerViewforCart);
        checkoutButton = findViewById(R.id.checkout);
        cartEmpty = findViewById(R.id.textforEmpty);
        homeButton = findViewById(R.id.homeButton);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        if (preferences.getBoolean("isLoggedIn",false)==true)
        {
        call=cartAPIInterface.getAll("Bearer "+preferences.getString("accessToken",""),preferences.getString("username",""));
        call.enqueue(this);


        }

        else {
            cartViewModel.getAllItems().observe(this, new Observer<List<CartRoomEntity>>() {
                @Override
                public void onChanged(@Nullable final List<CartRoomEntity> cartRoomEntities) {
                    generateList(cartRoomEntities);
                }
            });
        }

            getSupportActionBar().setTitle("Cart");// Display the name in the app bar as per wish---> decide later
            //checkoutButton=findViewById(R.id.checkout);
            checkoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (preferences.getBoolean("isLoggedIn", false)) {
                        System.out.println("logion status .........."+preferences.getBoolean("isLoggedIn",false));
                        Intent i = new Intent(getApplicationContext(), CheckOut.class);
                        startActivity(i);
                    } else {
                        System.out.println("Login status...........");
                         AccountFragment fragmentS1 = new AccountFragment();
                       // int id=fragmentS1.getView().getParent();
                        getSupportFragmentManager().beginTransaction().replace(R.id.cartpage, fragmentS1).commit();
                        Bundle bundle=new Bundle();
                        bundle.putString("place","msg from checkout");
                        fragmentS1.setArguments(bundle);
                        checkoutButton.setVisibility(View.GONE);


//                    Intent i1=new Intent(getApplicationContext(),AccountFragment.class);
//                    i1.putExtra("place","msg from checkout");
//                    startActivity(i1);
                    }
                }
            });

            homeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(getApplicationContext(), NavigationHome.class);
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
    public void onClick(final String id) {   // called for without login
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


    @Override
    protected void onResume() {
        super.onResume();
        if(preferences.getBoolean("isLoggedIn",false)) {
            call = cartAPIInterface.getAll("Bearer " + preferences.getString("accessToken", ""), preferences.getString("username", ""));
            call.enqueue(this);
        }

    }

    public void generateListCart(List<CartRoomEntity> cartRoomEntities)
    {

        if(cartRoomEntities==null|| cartRoomEntities.size()==0)
        {
            recyclerView.setVisibility(View.GONE);
            cartEmpty.setVisibility(View.VISIBLE);
            homeButton.setVisibility(View.VISIBLE);
            checkoutButton.setVisibility(View.GONE);
        }
        else {
            cartItems = cartRoomEntities;
            System.out.println("username    "+preferences.getString("username","username"));
            checkoutButton.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            cartAdapterAfterLogin=new CartAdapterAfterLogin(cart.this,cartItems);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(cartAdapterAfterLogin);
            //cartAdapterAfterLogin.setItems(cartRoomEntities);
        }





    }


    @Override
    public void onClickIncCart(String id, String merchantId, final int position) {
        callUpdate=cartAPIInterface.incrementCart("Bearer "+preferences.getString("accessToken",""),preferences.getString("username","username"),id,merchantId);
        callUpdate.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call1, Response<ResponseBody> response) {
                System.out.println(response.body());
                CartRoomEntity cartRoomEntity = cartItems.get(position);
                cartRoomEntity.setQty(cartRoomEntity.getQty() + 1);
                cartItems.set(position, cartRoomEntity);
                cartAdapterAfterLogin.notifyDataSetChanged();
                //call.enqueue(this);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClickDecCart(String id, String merchantId, final int position) {
        callUpdate=cartAPIInterface.decrementCart("Bearer "+preferences.getString("accessToken",""),preferences.getString("username","username"),id,merchantId);
        callUpdate.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                CartRoomEntity cartRoomEntity=cartItems.get(position);
                if(cartRoomEntity.getQty()==1)
                {
                    cartItems.remove(position);
                }
                else
                {
                    cartRoomEntity.setQty(cartRoomEntity.getQty()-1);
                    cartItems.set(position,cartRoomEntity);
                }

                cartAdapterAfterLogin.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    @Override
    public void delIfZeroCart(String id, String mechantId, final int position) {
        callUpdate=cartAPIInterface.deleteCart("Bearer "+preferences.getString("accessToken",""),id);
        callUpdate.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                CartRoomEntity cartRoomEntity=cartItems.get(position);
                cartItems.remove(position);
                cartAdapterAfterLogin.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


    }

    @Override
    public void onResponse(Call<List<CartRoomEntity>> call, Response<List<CartRoomEntity>> response) {

        generateListCart(response.body());
    }

    @Override
    public void onFailure(Call<List<CartRoomEntity>> call, Throwable t) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
