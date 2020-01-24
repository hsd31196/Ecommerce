package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.ecommerce.ui.category2.Category2Fragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class searchActivity extends AppCompatActivity implements  CategoryAdapterDummy.CustomInterface {

    RecyclerView recyclerView;
    CategoryAdapterDummy categoryAdapterDummy;
    Retrofit retrofit= App.getRetrofit();
    CategoryAppInterface appInterface=retrofit.create(CategoryAppInterface.class);
    Call<List<Products>> call;
    List<Products> searchResultList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView=findViewById(R.id.recyclerforsearch);
        search();

    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(item.getItemId()==R.id.search_view)
//        {
//            search(item);
//            return  true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return  true;
    }

    private void generateDataList(List<Products> productsList) {
        System.out.println("in the generated list");
        if(productsList==null)
        {
            recyclerView.setVisibility(View.GONE);
           // productsList.clear();
        }
        else {
        recyclerView.setVisibility(View.VISIBLE);
            categoryAdapterDummy = new CategoryAdapterDummy(productsList, searchActivity.this);
            recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
            recyclerView.setAdapter(categoryAdapterDummy);
        }
    }

    public void search() {

    SearchView searchView = (SearchView) findViewById(R.id.searchProducts);
    SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
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
                call = appInterface.getSearchedProduts(newText);
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
                //return true;
                if (newText.length() == 0) {
                   // searchResultList.clear();
                    generateDataList(searchResultList);
                    categoryAdapterDummy.notifyDataSetChanged();
                }
                return true;
            }
        });
        }

    @Override
    public void onClick(Products products) {

        System.out.println("Onclick function");
        Intent i = new Intent(getApplicationContext(), ProductDetail.class);
        i.putExtra("product",products);
        startActivity(i);

    }


}


