package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Checkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Button buy_now=(Button)findViewById(R.id.placeorder);
        buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //todo: call post api to update orders
                Toast.makeText(Checkout.this,"Order Placed",Toast.LENGTH_LONG);
            }

        });
    }



}


