package com.example.ecommerce.orderhistory;

import com.example.ecommerce.pojo.Orders;
import com.example.ecommerce.pojo.TempOrders;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FetchOrderHistory
{
    @GET("5e293f6a3000004d00faedaa.json")
    Call<TempOrders> getData();
}
