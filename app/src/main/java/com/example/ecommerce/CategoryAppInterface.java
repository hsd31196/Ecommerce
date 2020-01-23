package com.example.ecommerce;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;
public interface CategoryAppInterface {

    @GET("search/searchService/getByCategory/{categoryName}")
    Call<List<Products>> getProducts(@Path("categoryName") String name);

    @GET("search/searchService/bestSeller")
    Call<List<Products>> getBestSellers();

    @GET("search/searchService/getAll/{keyword}")
    Call<List<Products>> getSearchedProduts(@Path("keyword") String keyword);
}


