package com.example.ecommerce.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;

import com.example.ecommerce.app.App;
import com.example.ecommerce.category.CategoryAdapterDummy;
import com.example.ecommerce.category.CategoryAppInterface;
import com.example.ecommerce.R;
import com.example.ecommerce.products.ProductDetail;
import com.example.ecommerce.products.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import java.util.ArrayList;

public class searchActivity extends AppCompatActivity implements CategoryAdapterDummy.CustomInterface {

    RecyclerView recyclerView;
    CategoryAdapterDummy categoryAdapterDummy;
    Retrofit retrofit= App.getRetrofit();
    CategoryAppInterface appInterface=retrofit.create(CategoryAppInterface.class);
    Call<List<Products>> call;
    List<Products> searchResultList;
    List<Products> sortResultList;
    List<String> categories=new ArrayList<>();
    ArrayAdapter<String> spinadapter;
    String searchquery;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        categories.add("Rating");
        categories.add("Price: Low to High");
        categories.add("Price: High to Low");
        spinadapter=new ArrayAdapter<String>(searchActivity.this, android.R.layout.simple_spinner_dropdown_item, categories);
        spinadapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);


        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Search Products");
        recyclerView=findViewById(R.id.recyclerforsearch);
        spinner=findViewById(R.id.sortby);
        spinner.setAdapter(spinadapter);
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
            public boolean onQueryTextChange(final String newText) {
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String item = spinner.getSelectedItem().toString();
                        if(item.equals("Price: Low to High"))
                        {
                            call = appInterface.getSortByAsc(newText);
                            call.enqueue(new Callback() {
                                @Override
                                public void onResponse(Call call, Response response) {
                                    sortResultList = (List<Products>) response.body();
                                    generateDataList(sortResultList);
                                    categoryAdapterDummy.notifyDataSetChanged();
                                }
                                @Override
                                public void onFailure(Call call, Throwable t) {
                                    System.out.println("on fail");
                                }
                            });
                        }
                        else if(item.equals("Price: High to Low"))
                        {
                            call = appInterface.getSortByDesc(newText);
                            call.enqueue(new Callback() {
                                @Override
                                public void onResponse(Call call, Response response) {
                                    sortResultList = (List<Products>) response.body();
                                    generateDataList(sortResultList);
                                    categoryAdapterDummy.notifyDataSetChanged();
                                }
                                @Override
                                public void onFailure(Call call, Throwable t) {
                                    System.out.println("on fail");
                                }
                            });
                        }
                        else if(item.equals("Rating"))
                        {
                            call = appInterface.getSortByRating(newText);
                            call.enqueue(new Callback() {
                                @Override
                                public void onResponse(Call call, Response response) {
                                    sortResultList = (List<Products>) response.body();
                                    generateDataList(sortResultList);
                                    categoryAdapterDummy.notifyDataSetChanged();
                                }
                                @Override
                                public void onFailure(Call call, Throwable t) {
                                    System.out.println("on fail");
                                }
                            });
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

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


