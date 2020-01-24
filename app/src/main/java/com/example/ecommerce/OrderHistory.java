package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.content.Intent;
import android.preference.PreferenceManager;
import android.util.Log;
        import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;
        import retrofit2.Retrofit;

public class OrderHistory extends AppCompatActivity implements OrderHistoryAdapter.CustomInterface{

    TempOrders ordersList;
    private OrderHistoryAdapter orderHistoryAdapter;
    private RecyclerView recyclerView;
SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Retrofit retrofit=App.getRetrofit();
        FetchOrderHistory fetchOrderHistory = retrofit.create(FetchOrderHistory.class);

        //call API
        Call<TempOrders> call = fetchOrderHistory.getData("Bearer "+preferences.getString("accessToken",""));
        call.enqueue(new Callback<TempOrders>() {

            @Override
            public void onResponse(Call<TempOrders> call, Response<TempOrders> response) {

                if (!response.isSuccessful())
                    Log.i("TAG", "Error: " + response.code());
                else {
                    ordersList = response.body();
                    generateDataList(ordersList);
                }
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
    public void onClick(OrderProductsItem orderItems) {
        Intent i = new Intent(this, OrderDetail.class);
        i.putExtra("orderObject", orderItems);
        i.putExtra("orderId",ordersList.getOrderId());
        DateFormat df=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        i.putExtra("orderDate",df.format(ordersList.getOrderDate()));
        System.out.println(df.format(ordersList.getOrderDate()));
        startActivity(i);
    }


}
