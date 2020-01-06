package com.example.crud.singleton;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {
    private static final RetrofitSingleton ourInstance = new RetrofitSingleton();

    private Retrofit retrofit = null;
    private Retrofit.Builder retrofitBuilder = null;
    private OkHttpClient.Builder httpClient = null;

    public static RetrofitSingleton getInstance() {
        return ourInstance;
    }

    private RetrofitSingleton() {
        this.httpClient = new OkHttpClient.Builder();
        this.retrofitBuilder = new Retrofit.Builder().baseUrl("https://api.github.com/").addConverterFactory(GsonConverterFactory.create());
        this.retrofit = retrofitBuilder.client(httpClient.build()).build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
