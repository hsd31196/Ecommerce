package com.example.ecommerce.order;
import com.example.ecommerce.order.TempOrders;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.net.CacheResponse;
import java.util.List;
public interface FetchOrderHistory
{

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("order/orderService/orderHistory/{userId}")
    Call<List<TempOrders>> getData(@Header("Authorization") String token, @Path("userId") String userId);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("search/searchService/updateSearchProduct/{pid}/{mid}/{rating}")
    Call<ResponseBody> giveRating(@Header("Authorization") String  token,@Path("pid") String pid,@Path("mid") String mid,@Path("rating") float value);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("product/productService/updateRating/{pid}/{rating}")
    Call<ResponseBody> giveProductRating(@Header("Authorization") String token,@Path("pid") String pid,@Path("rating") float value);

}
