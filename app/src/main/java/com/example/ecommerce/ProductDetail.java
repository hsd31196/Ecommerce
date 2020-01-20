package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.w3c.dom.Text;

public class ProductDetail extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ViewPager viewPager=findViewById(R.id.productimages);
        ImageAdapter imageAdapter=new ImageAdapter(this);
        viewPager.setAdapter(imageAdapter);

        //make text scrollable
        TextView specs=findViewById(R.id.Specification);
        TextView description=findViewById(R.id.Description);
        TextView reviews=findViewById(R.id.reviews);

        reviews.setMovementMethod(new ScrollingMovementMethod());
        description.setMovementMethod(new ScrollingMovementMethod());
        specs.setMovementMethod(new ScrollingMovementMethod());


    }


}
