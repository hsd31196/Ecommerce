package com.example.ecommerce;

import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface ListCategoryInterface {

    @GET("product/productService/getAllCategory/")
    Call<List<String>> getListOfCategory();
}
