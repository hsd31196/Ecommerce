package com.example.ecommerce;

import android.content.Intent;
import android.os.Bundle;

import com.example.ecommerce.cart.cart;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.List;
import android.view.Menu;
import android.view.SubMenu;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NavigationHome extends AppCompatActivity implements Callback<List<String>>  {

    private AppBarConfiguration mAppBarConfiguration;
SearchView searchView;
 public static   List<String> list;
    NavigationView navigationView;
Retrofit retrofit=App.getRetrofit();
ListCategoryInterface listCategory;
DrawerLayout drawer;

private Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listCategory=retrofit.create(ListCategoryInterface.class);
        Call<List<String>> call=listCategory.getListOfCategory();
        call.enqueue((Callback<List<String>>) this);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_category1,R.id.nav_category2,R.id.nav_category3,
                R.id.nav_category4,R.id.nav_category5,R.id.nav_account)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_home, menu);
           // searchView=(SearchView)findViewById(R.id.search_view);

            return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void opencart(MenuItem item) {

        Intent intent=new Intent(getApplicationContext(), cart.class);
        startActivity(intent);
    }


    @Override
    public void onResponse(Call<List<String>> call, Response<List<String>> response) {
        list=response.body();
        if(response.isSuccessful()) {
            menu = navigationView.getMenu();
            menu.findItem(R.id.nav_category1).setTitle(list.get(0)).setIcon(R.drawable.ic_tablet_mac);
            menu.findItem(R.id.nav_category2).setTitle(list.get(1)).setIcon(R.drawable.ic_book);
            menu.findItem(R.id.nav_category3).setTitle(list.get(2)).setIcon(R.drawable.ic_collections_black_24dp);
            menu.findItem(R.id.nav_category4).setTitle(list.get(3)).setIcon(R.drawable.ic_style);
            menu.findItem(R.id.nav_category5).setTitle(list.get(4)).setIcon(R.drawable.ic_shop);
        }

    }

    @Override
    public void onFailure(Call<List<String>> call, Throwable t) {

        drawer.closeDrawers();

    }
}
