package com.example.ecommerce;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.lifecycle.LiveData;
        import androidx.lifecycle.ViewModelProviders;
        import androidx.viewpager.widget.ViewPager;

        import android.content.Intent;
        import android.os.Bundle;
        import android.text.method.ScrollingMovementMethod;
        import android.view.View;
        import android.widget.Button;
        import android.widget.RatingBar;
        import android.widget.TextView;

        import com.example.ecommerce.R;
        import com.example.ecommerce.entity.CartRoomEntity;
        import com.example.ecommerce.viewmodel.CartViewModel;
        // import com.example.ecommerce.adapters.ImageAdapter;
        //import com.example.ecommerce.pojo.Products;

        import java.util.ArrayList;
        import java.util.List;

public class ProductDetail extends AppCompatActivity {

    private List<String> imagesurlList;
    CartViewModel cartViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        //extract data from previous activity
        Intent i=getIntent();
        final Products products=(Products) i.getSerializableExtra("product");
        cartViewModel= ViewModelProviders.of(this).get(CartViewModel.class);
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

        final TextView product_name=(TextView) findViewById(R.id.productname);
        final TextView price=(TextView) findViewById(R.id.pricevalue);
        RatingBar rating=(RatingBar) findViewById(R.id.ratingvalue);
        TextView desc=(TextView) findViewById(R.id.Description);
        TextView specifications=(TextView) findViewById(R.id.Specification);
        TextView review=(TextView) findViewById(R.id.reviews);

        product_name.setText(products.getProductName());
        price.setText(String.valueOf(products.getPrice()));
        rating.setRating(products.getRating());
        desc.setText(products.getDescription());
        specifications.setText(products.getSpecification());
        review.setText(products.getReviews());

        Button addCart=findViewById(R.id.addtocart);
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent replyIntent = new Intent();
                CartRoomEntity entity=new CartRoomEntity();
                TextView produtName=findViewById(R.id.productname);
                entity.setProductName(produtName.getText().toString());
                entity.setTotalAmount(R.id.pricevalue);
                entity.setImageUrl(products.getImgurls().get(0));
                //entity.setProductId();
                //entity.setMerchantId();
                entity.setQty(1);
                entity.setProductId("12345");
                System.out.println(entity.getProductId()+entity.getProductName());
                int count=cartViewModel.getById("12345");
                if(count==0) {
                    cartViewModel.insert(entity);
                }
                else
                {
                    cartViewModel.update("12345");
                }
                //System.out.println(cartViewModel.getAllItems());

            }
        });


    }


}
