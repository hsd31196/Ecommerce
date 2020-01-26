package com.example.ecommerce.order;
import com.example.ecommerce.order.TempOrders;

import retrofit2.Call;
        import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import java.util.List;
public interface FetchOrderHistory
{

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("order/orderService/orderHistory/{userId}")
    Call<List<TempOrders>> getData(@Header("Authorization") String token, @Path("userId") String userId);


}
