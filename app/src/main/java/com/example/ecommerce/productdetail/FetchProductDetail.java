package com.example.ecommerce.productdetail;

import com.example.ecommerce.pojo.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FetchProductDetail {

    @GET("/search/getByCategory/badminton")
    Call<List<Products>> getData();
}
