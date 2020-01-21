package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ecommerce.orderhistory.OrderHistory;
import com.example.ecommerce.productdetail.ProductDetail;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        ImageView imageView=(ImageView) findViewById(R.id.profilepic);
        TextView username=(TextView) findViewById(R.id.personname);
        Button order_history=(Button) findViewById(R.id.orderhistory);
        Button change_cred=(Button) findViewById(R.id.changepassword);
        Button update_address=(Button) findViewById(R.id.changedelivery);
        Button login_history=(Button) findViewById(R.id.loginhistory);
        Button logout=findViewById(R.id.logout);

        order_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),OrderHistory.class);
                startActivity(i);

            }
        });

//        change_cred.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
        update_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),AddressUpdate.class);
                startActivity(i);


            }
        });
//
//        login_history.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });
//
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });



        //load user image
        Glide.with(getApplicationContext()).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_background)).load("https://cdn3.iconfinder.com/data/icons/vector-icons-6/96/256-512.png").into(imageView);
    }
}
