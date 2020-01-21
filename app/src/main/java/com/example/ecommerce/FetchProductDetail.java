package com.example.ecommerce;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FetchProductDetail {

    @GET("5e2684982f00005700a4f43b.json")
    Call<List<Products>> getData();
}
