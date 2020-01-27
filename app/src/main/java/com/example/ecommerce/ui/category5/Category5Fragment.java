package com.example.ecommerce.ui.category5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.app.App;
import com.example.ecommerce.category.CategoryAdapterDummy;
import com.example.ecommerce.category.CategoryAppInterface;
import com.example.ecommerce.products.ProductDetail;
import com.example.ecommerce.products.Products;
import com.example.ecommerce.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class Category5Fragment extends Fragment implements Callback<List<Products>>,CategoryAdapterDummy.CustomInterface {


   // private Category5ViewModel category5ViewModel;

    Retrofit retrofit= App.getRetrofit();
    CategoryAppInterface appInterface;
    RecyclerView recyclerView;
    ImageView imageView;

    List<Products> returnedProducts;
    //CategoryAdaptor categoryAdapter;
    CategoryAdapterDummy categoryAdapterDummy;
    String name;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        appInterface=retrofit.create(CategoryAppInterface.class);
       // System.out.println(" in the catftyeghg "+ NavigationHome.list.get(4));
        Call<List<Products>> call=appInterface.getProducts("FootBall"/*NavigationHome.list.get(4)*/);
        call.enqueue(this);
        System.out.println("======="+call.request());
//        category5ViewModel =
//                ViewModelProviders.of(this).get(Category5ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_category5, container, false);
        recyclerView=root.findViewById(R.id.recyclerView);
        imageView=root.findViewById(R.id.noInternet);

        return root;
    }
    private void generateDataList(List<Products> productsList) {
        System.out.println("in the generated list");
        for(int i=0;i<productsList.size();i++)
        {
            System.out.println(productsList.get(i));
        }
        categoryAdapterDummy=new CategoryAdapterDummy(productsList, Category5Fragment.this);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(categoryAdapterDummy);
    }
    @Override
    public void onClick(Products products) {
        System.out.println("Onclick function");
        Intent i = new Intent(getContext(), ProductDetail.class);
        i.putExtra("product",products);
        startActivity(i);
    }

    @Override
    public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
        imageView.setVisibility(View.GONE);
        generateDataList(response.body());
        System.out.println(call.request().body());

    }

    @Override
    public void onFailure(Call<List<Products>> call, Throwable t) {
        imageView.setImageResource(R.drawable.nointernet);
        imageView.setVisibility(View.VISIBLE);
        Log.e("error","eeror");
    }
}
