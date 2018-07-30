package com.example.mohammed.popularmovies.interfaces;

import com.example.mohammed.popularmovies.jsonModels.Movie;
import com.example.mohammed.popularmovies.jsonModels.Review;
import com.example.mohammed.popularmovies.jsonModels.Trailer;

import java.util.List;

public interface OnMovieFetched extends MovieData {

    void onMovieListFetched(List<Movie> movies);

    void onMovieFetched(Movie movie);

    void onReviewsFetched(List<Review> reviews);

    void onTrailersFetched(List<Trailer> reviews);

}
