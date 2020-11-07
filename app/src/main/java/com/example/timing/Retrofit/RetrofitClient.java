package com.example.timing.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit instance;

    public static Retrofit getRetrofitInstance() {
        if (instance == null) {
            instance = new Retrofit.Builder().baseUrl("http://api.aladhan.com/v1/").addConverterFactory(GsonConverterFactory.create()).build();
        }
        return instance;
    }
}
