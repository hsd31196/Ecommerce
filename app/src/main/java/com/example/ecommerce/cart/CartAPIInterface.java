package com.example.ecommerce.cart;

import com.example.ecommerce.entity.CartRoomEntity;
import java.util.Date;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import java.util.List;

public interface CartAPIInterface {
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("order/orderService/addProduct")
    Call<ResponseBody> addProduct(@Header("Authorization") String token,@Body CartRoomEntity entity);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("order/orderService/cart/{userId}")
    Call<List<CartRoomEntity>> getAll(@Header("Authorization") String token,@Path("userId") String userId);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("order/orderService/cart/inc/{userId}/{productId}/{merchantId}")
    Call<ResponseBody> incrementCart(@Header("Authorization") String token,@Path("userId") String userId,@Path("productId") String productId,@Path("merchantId") String merchantId);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("order/orderService/cart/dec/{userId}/{productId}/{merchantId}")
    Call<ResponseBody> decrementCart(@Header("Authorization") String token,@Path("userId") String userId,@Path("productId") String productId,@Path("merchantId") String merchantId);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @DELETE("order/orderService/deleteById/{productId}")
    Call<ResponseBody> deleteCart(@Header("Authorization") String token,@Path("productId") String productId);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @DELETE("order/orderService/deleteCart/{userId}")
    Call<ResponseBody> deleteCartByUser(@Header("Authorization") String token,@Path("userId") String username);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("order/orderService/checkout/{userId}/{orderId}")
    Call<ResponseBody> buyNow(@Header("Authorization") String token,@Path("userId") String username,@Path("orderId") int orderId);




}
