package com.example.ecommerce;

import android.app.Application;

import androidx.appcompat.widget.AppCompatImageView;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (null == retrofit) {
            OkHttpClient client = new OkHttpClient.Builder().build();
            retrofit = new Retrofit.Builder().baseUrl("http://www.mocky.io/v2/").addConverterFactory(GsonConverterFactory.create()).client(client).build();
        }
        return retrofit;
    }
}


