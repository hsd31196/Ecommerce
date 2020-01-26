package com.example.ecommerce.order;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerce.address.UserAddress;
import com.example.ecommerce.entity.CartRoomEntity;
import com.example.ecommerce.login.APIInterface;
import com.example.ecommerce.ui.account.AddressUpdate;
import com.example.ecommerce.app.App;
import com.example.ecommerce.R;
import com.example.ecommerce.cart.CartAPIInterface;

import okhttp3.Address;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import java.util.List;
import java.util.Random;

public class CheckOut extends AppCompatActivity {
Button button;
Button placeOrder;
Retrofit retrofit= App.getRetrofit();
CartAPIInterface cartAPIInterface=retrofit.create(CartAPIInterface.class);
APIInterface apiInterface=retrofit.create(APIInterface.class);
FetchOrderHistory fetchOrderHistory=retrofit.create(FetchOrderHistory.class);
SharedPreferences preferences;
Call<ResponseBody> call;
Call<List<CartRoomEntity>> listCart;
Call<List<String>> callAddress;
List<CartRoomEntity> list;
double total=0;
TextView itemsValue;
TextView totalValue;
TextView addresssValue;
    public static final int REQUEST_CODE = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        setContentView(R.layout.activity_check_out);
        button=findViewById(R.id.updateaddress);
        placeOrder=findViewById(R.id.placeorder);
        itemsValue=findViewById(R.id.itemstotalvalue);
        totalValue=findViewById(R.id.totalvalue);
        addresssValue=findViewById(R.id.deliveryaddressvalue);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("Check Out");// Display the name in the app bar as per wish---> decide later


        listCart=cartAPIInterface.getAll("Bearer "+preferences.getString("accessToken",""),preferences.getString("username",""));
        listCart.enqueue(new Callback<List<CartRoomEntity>>() {
            @Override
            public void onResponse(Call<List<CartRoomEntity>> call, Response<List<CartRoomEntity>> response) {

                int count=0;
                list=response.body();
                if(list.size()!=0) {
                     count = list.size();
                }
                for(int i=0;i<count;i++)
                {
                    int qty=list.get(i).getQty();
                    double price=list.get(i).getTotalAmount();
                    total=total+qty*price;
                }
                itemsValue.setText("₹"+Double.toString(total));
                totalValue.setText("₹"+Double.toString(total));
                System.out.println(total);
            }

            @Override
            public void onFailure(Call<List<CartRoomEntity>> call, Throwable t) {

            }
        });


        callAddress=apiInterface.getAddress("Bearer "+preferences.getString("accessToken",""),preferences.getString("username",""));
        callAddress.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                System.out.println("response ......"+response.body());
                addresssValue.setText(response.body().get(0));
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), AddressUpdate.class);
                startActivity(i);

            }
        });




        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(addresssValue.getText().toString().equals(""))
                {

                    Toast.makeText(getApplicationContext(),"Address is not valid to deliver products",Toast.LENGTH_SHORT).show();
                }
                else {

                    System.out.println("Address value....." + addresssValue.getText());
                    Random r = new Random();
                    call = cartAPIInterface.buyNow("Bearer " + preferences.getString("accessToken", ""), preferences.getString("username", ""), r.nextInt(1000));
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            System.out.println(response.code());
                            Toast.makeText(getApplicationContext(), "Order Placed successfully ", Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });

//                call=cartAPIInterface.deleteCartByUser("Bearer "+preferences.getString("accessToken",""),preferences.getString("username",""));
//                call.enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        Log.d("msg","deleted successfuly");
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                    }
//                });

                }
            }
        });


    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode== REQUEST_CODE)
//        {
//            if(resultCode== Activity.RESULT_OK)
//            {
//                String  value=data.getStringExtra("address");
//                addresssValue.setText(value);
//            }
//        }
//
//    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return  true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        callAddress=apiInterface.getAddress("Bearer "+preferences.getString("accessToken",""),preferences.getString("username",""));
        callAddress.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                System.out.println(response.body());
                addresssValue.setText(response.body().get(0));
                //addresssValue.setText((CharSequence) response.body());
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });


    }
}
