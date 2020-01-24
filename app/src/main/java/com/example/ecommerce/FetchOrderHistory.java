package com.example.ecommerce;
import com.example.ecommerce.TempOrders;

        import java.util.List;

        import retrofit2.Call;
        import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface FetchOrderHistory
{

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("order/orderService/orderHistory/1")
    Call<TempOrders> getData(@Header("Authorization") String token);
}
