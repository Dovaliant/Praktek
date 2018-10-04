package com.example.dovaliant.praktek;

import com.example.dovaliant.praktek.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Abhi on 06 Sep 2017 006.
 */

public class RetroClient {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private static Retrofit retrofit;
    /**
     * Get Retrofit Instance
     */
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
