package com.example.ecommerce.ui.category2;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
//import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import com.example.ecommerce.cart.cart;
import com.example.ecommerce.ui.category1.Category1Fragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Category2Fragment extends Fragment implements Callback<List<Products>>, CategoryAdapterDummy.CustomInterface {


    private Category2ViewModel category2ViewModel;
    Retrofit retrofit= App.getRetrofit();
    CategoryAppInterface appInterface=retrofit.create(CategoryAppInterface.class);
    Call<List<Products>> call;
    RecyclerView recyclerView;
    List<Products> returnedProducts;
    List<Products> searchResultList;
    CategoryAdapterDummy categoryAdapterDummy;
    String name;
    ImageView imageView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        System.out.println(" in the catftyeghg "+ NavigationHome.list.get(1));
        call=appInterface.getProducts(NavigationHome.list.get(1));
        call.enqueue(this);

        category2ViewModel =
                ViewModelProviders.of(this).get(Category2ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_category2, container, false);
        recyclerView=root.findViewById(R.id.recyclerView);
        imageView=root.findViewById(R.id.noInternet);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar=getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
    }

    private void generateDataList(List<Products> productsList) {
        System.out.println("in the generated list");
        for(int i=0;i<productsList.size();i++)
        {
            System.out.println(productsList.get(i));
        }
        categoryAdapterDummy=new CategoryAdapterDummy(productsList, Category2Fragment.this);
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
        returnedProducts=response.body();
        generateDataList(returnedProducts);

    }

    @Override
    public void onFailure(Call<List<Products>> call, Throwable t) {

        imageView.setImageResource(R.drawable.nointernet);
        imageView.setVisibility(View.VISIBLE);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_view:
                search(item);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void search(MenuItem item) {
        SearchView searchView = (SearchView) item.getActionView();
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                call = appInterface.getSearchedProduts(query);
                System.out.println("== Search query " + call.request().url());
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        searchResultList = (List<Products>) response.body();
                        System.out.println("== " + response.body());
                        generateDataList(searchResultList);
                        categoryAdapterDummy.notifyDataSetChanged();
                    /*if (searchResultList != null){
                        searchResultList.clear();
                    }*/
                    }
                    @Override
                    public void onFailure(Call call, Throwable t) {
                        System.out.println("on fail");
                       // Log.i("onFailure()", t.getMessage());
                    }
                });
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() == 0) {
                    generateDataList(returnedProducts);
                    categoryAdapterDummy.notifyDataSetChanged();
                }
                return true;
            }
        });
    }
}
