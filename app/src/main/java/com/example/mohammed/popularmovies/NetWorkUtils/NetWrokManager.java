package com.example.mohammed.popularmovies.NetWorkUtils;

import com.example.mohammed.popularmovies.interfaces.NetrokResponselistener;
import com.example.mohammed.popularmovies.jsonModels.MovieRoot;
import com.example.mohammed.popularmovies.jsonModels.ReviewRoot;
import com.example.mohammed.popularmovies.jsonModels.TrailersRoot;

public class NetWrokManager {


    public static void getMovieReviews(int id, MovieApi api, NetrokResponselistener<ReviewRoot> listener) {
        api.getMovieReviews(id).enqueue(new NetWorkResponse<>(listener));
    }


    public static void getMovieTrailers(int id, MovieApi api, NetrokResponselistener<TrailersRoot> listener) {
        api.getMovieTrailers(id).enqueue(new NetWorkResponse<>(listener));
    }

    public static void getPopularMovieList(int page, MovieApi api, NetrokResponselistener<MovieRoot> listener) {
        api.getPopularMovies(page).enqueue(new NetWorkResponse<>(listener));
    }

    public static void getTopRatedMovieList(int page, MovieApi api, NetrokResponselistener<MovieRoot> listener) {
        api.getTopRatedMovies(page).enqueue(new NetWorkResponse<>(listener));
    }
}
