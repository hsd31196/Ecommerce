package com.example.ecommerce.orderhistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.ecommerce.OrderDetail;
import com.example.ecommerce.R;
import com.example.ecommerce.adapters.OrderHistoryAdapter;
import com.example.ecommerce.pojo.CartProductsItem;
import com.example.ecommerce.pojo.Orders;
import com.example.ecommerce.pojo.TempOrders;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderHistory extends AppCompatActivity implements OrderHistoryAdapter.CustomInterface{

    TempOrders ordersList;
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
        Call<TempOrders> call = fetchOrderHistory.getData();
        call.enqueue(new Callback<TempOrders>() {

            @Override
            public void onResponse(Call<TempOrders> call, Response<TempOrders> response) {

                if (!response.isSuccessful())
                    Log.i("TAG", "Error: " + response.code());
                else
                    generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<TempOrders> call, Throwable t) {
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

    private void generateDataList(TempOrders ordersList) {
        recyclerView = findViewById(R.id.recyclerorder);
        orderHistoryAdapter = new OrderHistoryAdapter(ordersList, OrderHistory.this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(orderHistoryAdapter);
        //OrderHistoryAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(TempOrders ordersList) {
        Intent i = new Intent(this, OrderDetail.class);
        //i.putExtra("orders",orders);
        startActivity(i);
    }


}
