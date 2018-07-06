package com.example.mohammed.popularmovies;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieApiService {

    public static Retrofit retrofit = null;


    public static Retrofit getApiClient() {
        if (retrofit == null) {
            return retrofit = new Retrofit.Builder()
                    .baseUrl(MovieLinkConstants.baseMovieURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        } else
            return retrofit;
    }


}
