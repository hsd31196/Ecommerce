package com.example.ecommerce.orderhistory;

import com.example.ecommerce.pojo.Orders;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FetchOrderHistory
{
    @GET("5e26a3212f00006900a4f501.json")
    Call<List<Orders>> getData();
}
