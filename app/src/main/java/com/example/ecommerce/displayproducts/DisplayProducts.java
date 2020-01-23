package com.example.ecommerce.displayproducts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.ecommerce.productdetail.FetchProductDetail;
import com.example.ecommerce.productdetail.ProductDetail;
import com.example.ecommerce.R;
import com.example.ecommerce.adapters.RecyclerViewAdapter;
import com.example.ecommerce.pojo.Products;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisplayProducts extends AppCompatActivity implements RecyclerViewAdapter.CustomInterface {

    List<Products> productsList;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_products);

        productsList = new ArrayList<>();

        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.16.20.90:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FetchProductDetail fetchProductDetail = retrofit.create(FetchProductDetail.class);


        //call API
        Call<List<Products>> call = fetchProductDetail.getData();
        call.enqueue(new Callback<List<Products>>() {

            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {

                if (!response.isSuccessful())
                    Log.i("TAG", "Error: " + response.code());
                else
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(DisplayProducts.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                }
                else {
                    Toast.makeText(DisplayProducts.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // log to some central bug tracking service
                }
            }
        });


    }

    private void generateDataList(List<Products> productsList) {
        recyclerView = findViewById(R.id.recycler);
        recyclerViewAdapter = new RecyclerViewAdapter(productsList, DisplayProducts.this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(Products products) {
        Intent i = new Intent(this, ProductDetail.class);
        i.putExtra("product",products);
        startActivity(i);
    }


}
