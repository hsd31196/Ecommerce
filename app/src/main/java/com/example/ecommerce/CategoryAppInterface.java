package com.example.ecommerce;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;
public interface CategoryAppInterface {

    @GET("5e2684982f00005700a4f43b.json")
    Call<List<Products>> getProducts();
}


