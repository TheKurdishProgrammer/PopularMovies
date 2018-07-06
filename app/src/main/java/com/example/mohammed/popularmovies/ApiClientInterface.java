package com.example.mohammed.popularmovies;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static com.example.mohammed.popularmovies.MovieLinkConstants.apiKey;

public interface ApiClientInterface {


    @POST("popular?" + apiKey)
    Call<MovieDBJsonResult> getPopularMovies(@Query("page") int page);

    @POST("top_rated?" + apiKey)
    Call<MovieDBJsonResult> getTopRatedMovies(@Query("page") int page);

}
