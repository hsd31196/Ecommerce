package com.example.ecommerce.ui.home;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.App;
import com.example.ecommerce.CategoryAppInterface;
import com.example.ecommerce.HomeAdapter;
import com.example.ecommerce.ProductDetail;
import com.example.ecommerce.Products;
import com.example.ecommerce.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.concurrent.Callable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment implements Callback<List<Products>>, HomeAdapter.CustomInterface {

    private HomeViewModel homeViewModel;
    RecyclerView recyclerView;
    CarouselView carouselView;
    Retrofit retrofit= App.getRetrofit();
    CategoryAppInterface categoryAppInterface;
    List<Products> returnedProducts;


    int[] carouselImages={R.drawable.corossel_image1,R.drawable.corossel_image2,R.drawable.corossel_image3};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

        categoryAppInterface=retrofit.create(CategoryAppInterface.class);
        Call<List<Products>> call=categoryAppInterface.getBestSellers();
        call.enqueue(this);

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        carouselView=root.findViewById(R.id.carouselView);
        carouselView.setPageCount(carouselImages.length);
        carouselView.setImageListener(imageListener);
        recyclerView=root.findViewById(R.id.recyclerViewforHome);
        //HomeAdapter homeAdapter=new HomeAdapter();
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        return root;
    }
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(carouselImages[position]);
        }
    };


    @Override
    public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
        returnedProducts=response.body();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        HomeAdapter adapter=new HomeAdapter(returnedProducts, HomeFragment.this);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onFailure(Call<List<Products>> call, Throwable t) {

    }

    @Override
    public void onClick(Products products) {
        Intent i=new Intent(getContext(), ProductDetail.class);
        startActivity(i);
    }
}

