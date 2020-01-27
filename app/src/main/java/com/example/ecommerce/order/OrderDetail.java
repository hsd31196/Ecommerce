package com.example.ecommerce.order;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.ecommerce.R;
import com.example.ecommerce.app.App;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderDetail extends AppCompatActivity {

    TextView orderIdValue;
    TextView MerchantIdValue;
    ImageView image;
    TextView OrderDetailProdName;
    TextView Price;
    TextView qty;
    TextView date;
    Button reviewButton;
    RatingBar ratingBar;
    SharedPreferences preferences;
    Retrofit retrofit= App.getRetrofit();
    FetchOrderHistory fetchOrderHistory=retrofit.create(FetchOrderHistory.class);
    Call<ResponseBody> call;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Intent i=getIntent();
        final TempOrders orderObject=(TempOrders) i.getSerializableExtra("orderObject");
        String orderId=i.getStringExtra("orderId");
        final String orderDate=i.getStringExtra("orderDate");

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        orderIdValue=findViewById(R.id.orderid_value);
        MerchantIdValue=findViewById(R.id.merchantid_value);
        OrderDetailProdName=findViewById(R.id.orderdetail_prod_value);
        Price=findViewById(R.id.orderdetail_price_value);
        qty=findViewById(R.id.orderdetail_qty_value);
        date=findViewById(R.id.order_date_value);
        image=findViewById(R.id.orderdetail_image);
        orderIdValue.setText(orderId);
        MerchantIdValue.setText(orderObject.getMerchantId());
        OrderDetailProdName.setText(orderObject.getProductName());
        Price.setText("₹ "+Double.toString(orderObject.getPrice()));
        qty.setText(Integer.toString(orderObject.getQuantity()));
        Glide.with(getApplicationContext()).load(orderObject.getImage()).into(image);
        ratingBar=findViewById(R.id.ratingBarforReview);


        reviewButton=findViewById(R.id.reviewButton);
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float value=ratingBar.getRating();
                if(value!=0.0)
                {

                    call=fetchOrderHistory.giveRating("Bearer "+preferences.getString("accessToken",""),orderObject.getProductId(),orderObject.getMerchantId(),value);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });

                    call=fetchOrderHistory.giveProductRating("Bearer "+preferences.getString("accessToken",""),orderObject.getProductId(),value);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            System.out.println(orderObject.getProductId());
                            Toast.makeText(getApplicationContext(),"Thanks for Rating",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Give a rating to the product",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return  true;

    }
}
