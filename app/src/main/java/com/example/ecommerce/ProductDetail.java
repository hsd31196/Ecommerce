package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ProductDetail extends AppCompatActivity {

    private List<String> imagesurlList;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        //extract data from previous activity
        Intent i=getIntent();
        Products products=(Products) i.getSerializableExtra("product");

        //make text scrollable
        TextView specs=findViewById(R.id.Specification);
        TextView description=findViewById(R.id.Description);
        TextView reviews=findViewById(R.id.reviews);
        reviews.setMovementMethod(new ScrollingMovementMethod());
        description.setMovementMethod(new ScrollingMovementMethod());
        specs.setMovementMethod(new ScrollingMovementMethod());

        ViewPager viewPager=findViewById(R.id.productimages);

        //load images in slider
        imagesurlList=new ArrayList<>();


        imagesurlList=products.getImgurls();
        ImageAdapter imageAdapter=new ImageAdapter(this,imagesurlList);
        viewPager.setAdapter(imageAdapter);

        TextView product_name=(TextView) findViewById(R.id.productname);
        TextView price=(TextView) findViewById(R.id.pricevalue);
        TextView rating=(TextView) findViewById(R.id.ratingvalue);
        TextView desc=(TextView) findViewById(R.id.Description);
        TextView specifications=(TextView) findViewById(R.id.Specification);
        TextView review=(TextView) findViewById(R.id.reviews);

        product_name.setText(products.getProductName());
        price.setText(products.getPrice());
        rating.setText(products.getRating());
        desc.setText(products.getDescription());
        review.setText(products.getRating());





    }


}
