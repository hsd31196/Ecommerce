package com.example.ecommerce.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
        import android.content.Intent;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerce.app.App;
import com.example.ecommerce.R;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;
        import retrofit2.Retrofit;

public class OrderHistory extends AppCompatActivity implements OrderHistoryAdapter.CustomInterface{

    List<TempOrders> ordersList;
    private OrderHistoryAdapter orderHistoryAdapter;
    private RecyclerView recyclerView;
    TextView emptyHistory;
SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        emptyHistory=findViewById(R.id.historyEmpty);


        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("Order History");

        preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Retrofit retrofit= App.getRetrofit();
        FetchOrderHistory fetchOrderHistory = retrofit.create(FetchOrderHistory.class);

        //call API
        Call<List<TempOrders>> call = fetchOrderHistory.getData("Bearer "+preferences.getString("accessToken",""),preferences.getString("username",""));
        call.enqueue(new Callback<List<TempOrders>>() {

            @Override
            public void onResponse(Call<List<TempOrders>> call, Response<List<TempOrders>> response) {

//                if (!response.isSuccessful())
//                    Log.i("TAG", "Error: " + response.code());

                 if(response.body()==null || response.body().size()==0 ) {
                    emptyHistory.setVisibility(View.VISIBLE);
                }
                else{
                        emptyHistory.setVisibility(View.GONE);
                        ordersList = response.body();
                        generateDataList(ordersList);
                    }
                }


            @Override
            public void onFailure(Call<List<TempOrders>> call, Throwable t) {
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

    private void generateDataList(List<TempOrders> ordersList) {
        recyclerView = findViewById(R.id.recyclerorder);
        orderHistoryAdapter = new OrderHistoryAdapter(ordersList, OrderHistory.this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(orderHistoryAdapter);
        //OrderHistoryAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(TempOrders orderItems) {
        Intent i = new Intent(this, OrderDetail.class);
        i.putExtra("orderObject", orderItems);
        i.putExtra("orderId",orderItems.getOrderId());
        DateFormat df=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        i.putExtra("orderDate",df.format(orderItems.getOrderDate()));
        System.out.println(df.format(orderItems.getOrderDate()));
        startActivity(i);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return  true;

    }
}
