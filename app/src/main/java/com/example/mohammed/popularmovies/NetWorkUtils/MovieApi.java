package com.example.mohammed.popularmovies.NetWorkUtils;

import com.example.mohammed.popularmovies.jsonModels.MovieRoot;
import com.example.mohammed.popularmovies.jsonModels.ReviewRoot;
import com.example.mohammed.popularmovies.jsonModels.TrailersRoot;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.example.mohammed.popularmovies.NetWorkUtils.MovieLinkConstants.apiKey;

public interface MovieApi {

    @GET("popular?" + apiKey)
    Call<MovieRoot> getPopularMovies(@Query("page") int page);

    @GET("top_rated?" + apiKey)
    Call<MovieRoot> getTopRatedMovies(@Query("page") int page);

    @GET("{id}/videos?" + apiKey)
    Call<TrailersRoot> getMovieTrailers(@Path("id") int id);

    @GET("{id}/reviews?" + apiKey)
    Call<ReviewRoot> getMovieReviews(@Path("id") int id);
}
