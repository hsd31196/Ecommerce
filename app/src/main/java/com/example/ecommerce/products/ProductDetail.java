package com.example.ecommerce.products;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;
        import androidx.lifecycle.ViewModelProviders;
        import androidx.viewpager.widget.ViewPager;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.preference.PreferenceManager;
        import android.text.method.ScrollingMovementMethod;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.RatingBar;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.ecommerce.NavigationHome;
        import com.example.ecommerce.app.App;
        import com.example.ecommerce.R;
        import com.example.ecommerce.cart.CartAPIInterface;
        import com.example.ecommerce.cart.cart;
        import com.example.ecommerce.entity.CartRoomEntity;
        import com.example.ecommerce.search.searchActivity;
        import com.example.ecommerce.viewmodel.CartViewModel;
        // import com.example.ecommerce.adapters.ImageAdapter;
        //import com.example.ecommerce.pojo.Products;

        import java.util.ArrayList;
        import java.util.List;

        import okhttp3.ResponseBody;
        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;
        import retrofit2.Retrofit;

public class ProductDetail extends AppCompatActivity {

    private List<String> imagesurlList;
    CartViewModel cartViewModel;
    Retrofit retrofit= App.getRetrofit();
    CartAPIInterface cartAPIInterface=retrofit.create(CartAPIInterface.class);
    MerchantAPI merchantAPI=retrofit.create(MerchantAPI.class);
    Call<ResponseBody> call;
    Call<List<Merchants>> callMerchants;
    SharedPreferences preferences;
SharedPreferences.Editor editor;
Spinner spinner;
    List<String> categories=new ArrayList<>();
    ArrayAdapter<String> spinadapter;
    String merchnatValue;
    String[] values;
    String merchantName;
    Double merhcnatPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor=preferences.edit();

        categories.add("Select");

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        categories.add("Price: Low to High");
//        categories.add("Price: High to Low");
//        spinadapter=new ArrayAdapter<Merchants>(ProductDetail.this, android.R.layout.simple_spinner_dropdown_item, categories);
//        spinadapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
//        spinner=findViewById(R.id.selectMerchants);
//        spinner.setAdapter(spinadapter);


        //extract data from previous activity
        Intent i=getIntent();
        final Products products=(Products) i.getSerializableExtra("product");

        callMerchants=merchantAPI.getAllMerchants(products.getProductId());
        callMerchants.enqueue(new Callback<List<Merchants>>() {
            @Override
            public void onResponse(Call<List<Merchants>> call, Response<List<Merchants>> response) {
                List<Merchants> listMerchants=response.body();
//                int count=listMerchants.size();
//                for(int i=0;i<count;i++) {
//                    String value=listMerchants.get(i).getMerchantId()+" "+listMerchants.get(i).getMerchantPrice();
//                    categories.add(value);
//                }

            }

            @Override
            public void onFailure(Call<List<Merchants>> call, Throwable t) {

            }
        });

        spinadapter=new ArrayAdapter<String>(ProductDetail.this, android.R.layout.simple_spinner_dropdown_item, categories);
        spinadapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner=findViewById(R.id.selectMerchants);
        spinner.setAdapter(spinadapter);
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
        price.setText("₹ "+String.valueOf(products.getDiscountedPrice()));
        rating.setRating((float)products.getRating());
        desc.setText(products.getDescription());
        specifications.setText(products.getSpecification());
        review.setText(products.getReviews());

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                merchnatValue=spinner.getSelectedItem().toString();
                if(merchnatValue.equals("Select"))
                {
                    merchantName=products.getMerchantId();
                    merhcnatPrice=products.getDiscountedPrice();
                }
                else {
                    values = merchnatValue.split(" ");
                    System.out.println(values[0]);
                    merchantName=values[0];
                    merhcnatPrice=Double.parseDouble(values[1]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Button addCart=findViewById(R.id.addtocart);
        addCart.setText("Add to Cart");
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (preferences.getBoolean("isLoggedIn", false)) {
                    CartRoomEntity entity = new CartRoomEntity();

                    if(addCart.getText().equals("Add to Cart"))
                    {
                        TextView produtName = findViewById(R.id.productname);
                        System.out.println(products.getProductName());
                        entity.setProductName(products.getProductName());
                       // if(values[1])
                        entity.setTotalAmount(merhcnatPrice);
                        entity.setImageUrl(products.getImgurls().get(0));
                        //entity.setProductId();
                        entity.setMerchantId(merchantName);
                        entity.setQty(1);
                        entity.setProductId(products.getProductId());
                        entity.setUserId(preferences.getString("username","username"));
                        call=cartAPIInterface.addProduct("Bearer "+preferences.getString("accessToken",""),entity);
                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                addCart.setText("Go To Cart");

                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                            }
                        });

                    }
                    else if(addCart.getText().equals("Go To Cart"))
                    {
                        Intent i=new Intent(getApplicationContext(), cart.class);
                        startActivity(i);

                    }


                } else {


                    if(addCart.getText().equals("Add to Cart")) {
                        // Intent replyIntent = new Intent();
                        CartRoomEntity entity = new CartRoomEntity();
                        TextView produtName = findViewById(R.id.productname);
                        System.out.println(products.getProductName());
                        entity.setProductName(products.getProductName());
                        entity.setTotalAmount(merhcnatPrice);
                        entity.setImageUrl(products.getImgurls().get(0));
                        //entity.setProductId();
                        entity.setMerchantId(merchantName);
                        entity.setQty(1);
                        entity.setProductId(products.getProductId());
                        // System.out.println(entity.getProductId()+entity.getProductName());
                       // int count = cartViewModel.getById(products.getProductId());
                        //if (count == 0) {
                            cartViewModel.insert(entity);
                        addCart.setText("Go To Cart");
                        // } else {
                         //   cartViewModel.update(products.getProductId());
                        //}
                        //System.out.println(cartViewModel.getAllItems());

                        Toast.makeText(getApplicationContext(), "Added to Cart", Toast.LENGTH_SHORT).show();
                    }
                    else if(addCart.getText().equals("Go To Cart"))
                    {
                        Intent i=new Intent(getApplicationContext(),cart.class);
                        startActivity(i);
                        finish();
                    }


                }
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
