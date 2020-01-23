package com.example.ecommerce;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.appcompat.widget.AppCompatImageView;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (null == retrofit) {
            OkHttpClient client = new OkHttpClient.Builder().build();
            retrofit = new Retrofit.Builder().baseUrl("http://172.16.20.32:8080/").addConverterFactory(GsonConverterFactory.create()).client(client).build();
        }
        return retrofit;
    }

     SharedPreferences sharedPreferences;
    public  SharedPreferences getsharedPreferences()
    {
        sharedPreferences=getSharedPreferences("loginPrefs",MODE_PRIVATE);
        return sharedPreferences;
    }
}


