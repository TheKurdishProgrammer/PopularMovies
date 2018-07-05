package com.example.mohammed.popularmovies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiClientInterface {

    String apiKey = "&api_key=YOUR_API_KEY";
    String mostPopular = "popularity.desc";
    String topRated = "vote.average.desc";

    @POST("movie?" + apiKey)
    Call<List<Movie>> getTopRated();


    @POST("movie?" + apiKey)
    Call<MovieDBJsonResult> getMovies(@Query("page") int page,
                                      @Query("sort_by") String sortType
    );

}
