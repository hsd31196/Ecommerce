package com.example.ecommerce.products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MerchantAPI {


    @GET("merchant/merchantProduct/getMerchant/{productId}")
    Call<List<Merchants>> getAllMerchants(@Path("productId") String productId);
}
