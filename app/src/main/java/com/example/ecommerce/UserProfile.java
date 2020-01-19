package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        //load garbage image
        ImageView imageView=(ImageView) findViewById(R.id.profilepic);
        Glide.with(getApplicationContext()).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_background)).load("https://cdn3.iconfinder.com/data/icons/vector-icons-6/96/256-512.png").into(imageView);
    }
}
