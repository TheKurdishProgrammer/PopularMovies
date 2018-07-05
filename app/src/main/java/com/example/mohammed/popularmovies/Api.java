package com.example.mohammed.popularmovies;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    public static final String baseUrl = "https://api.themoviedb.org/3/discover/";
    public static Retrofit retrofit = null;

    public static Retrofit getApiClient() {
        if (retrofit == null) {
            return retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        } else
            return retrofit;
    }


}
