package com.example.ecommerce.category;

import com.example.ecommerce.products.Products;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;
public interface CategoryAppInterface {

    @GET("search/searchService/getByCategory/{categoryName}")
    Call<List<Products>> getProducts(@Path("categoryName") String name);

    @GET("search/searchService/bestSeller")
    Call<List<Products>> getBestSellers();

    @GET("search/searchService/getAll/{keyword}")
    Call<List<Products>> getSearchedProduts(@Path("keyword") String keyword);


    @GET("search/searchService/sortByAsc/{keyword}")
    Call<List<Products>> getSortByAsc(@Path("keyword") String keyword);

    @GET("search/searchService/sortByDesc/{keyword}")
    Call<List<Products>> getSortByDesc(@Path("keyword") String keyword);

    @GET("search/searchService/sortByRating/{keyword}")
    Call<List<Products>> getSortByRating(@Path("keyword") String keyword);

}


