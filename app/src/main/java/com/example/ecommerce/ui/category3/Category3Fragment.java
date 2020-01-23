package com.example.ecommerce.ui.category3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.App;
import com.example.ecommerce.CategoryAdapterDummy;
import com.example.ecommerce.CategoryAppInterface;
import com.example.ecommerce.NavigationHome;
import com.example.ecommerce.ProductDetail;
import com.example.ecommerce.Products;
import com.example.ecommerce.R;
import com.example.ecommerce.ui.account.AccountViewModel;
import com.example.ecommerce.ui.category1.Category1Fragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Category3Fragment  extends Fragment implements Callback<List<Products>>, CategoryAdapterDummy.CustomInterface {


    private Category3ViewModel category3ViewModel;

    Retrofit retrofit= App.getRetrofit();
    CategoryAppInterface appInterface;
    RecyclerView recyclerView;

    List<Products> returnedProducts;
    //CategoryAdaptor categoryAdapter;
    CategoryAdapterDummy categoryAdapterDummy;
    String name;
    ImageView imageView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        appInterface=retrofit.create(CategoryAppInterface.class);
        System.out.println(" in the catftyeghg "+ NavigationHome.list.get(2));
        Call<List<Products>> call=appInterface.getProducts(NavigationHome.list.get(2));
        call.enqueue(this);

        category3ViewModel =
                ViewModelProviders.of(this).get(Category3ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_category3, container, false);
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
        categoryAdapterDummy=new CategoryAdapterDummy(productsList, Category3Fragment.this);
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

    }

    @Override
    public void onFailure(Call<List<Products>> call, Throwable t) {
        imageView.setImageResource(R.drawable.nointernet);
        imageView.setVisibility(View.VISIBLE);
    }
}