package com.example.ecommerce;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    static Retrofit getClient()
    {
        OkHttpClient client = new OkHttpClient.Builder().build();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://172.16.20.32:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;

    }

}
