package com.example.ecommerce;
import com.example.ecommerce.TempOrders;

        import java.util.List;

        import retrofit2.Call;
        import retrofit2.http.GET;

public interface FetchOrderHistory
{
    @GET("order/orderService/orderHistory/1")
    Call<TempOrders> getData();
}
