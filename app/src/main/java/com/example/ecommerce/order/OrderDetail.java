package com.example.ecommerce.order;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.ecommerce.R;

public class OrderDetail extends AppCompatActivity {

    TextView orderIdValue;
    TextView MerchantIdValue;
    ImageView image;
    TextView OrderDetailProdName;
    TextView Price;
    TextView qty;
    TextView date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        Intent i=getIntent();
        TempOrders orderObject=(TempOrders) i.getSerializableExtra("orderObject");
        String orderId=i.getStringExtra("orderId");
        String orderDate=i.getStringExtra("orderDate");
        orderIdValue=findViewById(R.id.orderid_value);
        MerchantIdValue=findViewById(R.id.merchantid_value);
        OrderDetailProdName=findViewById(R.id.orderdetail_prod_name);
        Price=findViewById(R.id.orderdetail_price_value);
        qty=findViewById(R.id.orderdetail_qty_value);
        date=findViewById(R.id.order_date_value);
        image=findViewById(R.id.orderdetail_image);
        orderIdValue.setText(orderId);
        MerchantIdValue.setText(orderObject.getMerchantId());
        OrderDetailProdName.setText(orderObject.getProductName());
        Price.setText(Double.toString(orderObject.getPrice()));
        qty.setText(Integer.toString(orderObject.getQuantity()));
        System.out.println("hjwhjhdjhw"+orderDate);
       // date.setText(orderDate);
        Glide.with(getApplicationContext()).load(orderObject.getImage()).into(image);

    }
}
