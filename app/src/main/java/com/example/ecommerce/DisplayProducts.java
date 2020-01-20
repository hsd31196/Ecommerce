package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FetchProductDetail fetchProductDetail = retrofit.create(FetchProductDetail.class);


        //call API
        Call<List<Products>> call = fetchProductDetail.getData();
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                Toast.makeText(DisplayProducts.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void generateDataList(List<Products> productsList) {
        recyclerView = findViewById(R.id.recycler);
        recyclerViewAdapter = new RecyclerViewAdapter(productsList, DisplayProducts.this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onClick(Products products) {
        Intent i = new Intent(getApplicationContext(), ProductDetail.class);
        i.putExtra("Product",products);
        startActivity(i);
    }


}
