package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderHistory extends AppCompatActivity implements OrderHistoryAdapter.CustomInterface{

    List<Orders> ordersList;
    private OrderHistoryAdapter orderHistoryAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);


        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.mocky.io/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FetchOrderHistory fetchOrderHistory = retrofit.create(FetchOrderHistory.class);

        //call API
        Call<List<Orders>> call = fetchOrderHistory.getData();
        call.enqueue(new Callback<List<Orders>>() {

            @Override
            public void onResponse(Call<List<Orders>> call, Response<List<Orders>> response) {

                if (!response.isSuccessful())
                    Log.i("TAG", "Error: " + response.code());
                else
                    generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Orders>> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(OrderHistory.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                }
                else {
                    Toast.makeText(OrderHistory.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // log to some central bug tracking service
                }
            }
        });
    }

    private void generateDataList(List<Orders> ordersList) {
        recyclerView = findViewById(R.id.recyclerorder);
        orderHistoryAdapter = new OrderHistoryAdapter(ordersList, OrderHistory.this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(orderHistoryAdapter);
        //OrderHistoryAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(Orders orders) {
//        Intent i = new Intent(this, ProductDetail.class);
//        i.putExtra("orders",orders);
//        startActivity(i);
    }


}
