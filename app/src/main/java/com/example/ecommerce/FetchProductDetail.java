package com.example.ecommerce;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FetchProductDetail {

    @GET("file:///Users/harshdesai/Desktop/product.json")
    Call<List<Products>> getData();
}
