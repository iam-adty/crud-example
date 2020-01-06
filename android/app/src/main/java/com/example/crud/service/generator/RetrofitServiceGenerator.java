package com.example.crud.service.generator;

import com.example.crud.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceGenerator {
    private static final String BASE_URL = "https://api.github.com/";
    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = retrofitBuilder.build();

    public static <S> S create(Class<S> serviceClass) {
        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(httpLoggingInterceptor);
            retrofitBuilder.client(httpClient.build());
            retrofit = retrofitBuilder.build();
        }

        return retrofit.create(serviceClass);
    }
}
