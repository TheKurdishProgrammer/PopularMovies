package com.example.mohammed.popularmovies.activities;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mohammed.popularmovies.Adapters.ReviewAdapter;
import com.example.mohammed.popularmovies.Adapters.TrailerAdapter;
import com.example.mohammed.popularmovies.NetWorkUtils.MovieApi;
import com.example.mohammed.popularmovies.NetWorkUtils.MovieApiService;
import com.example.mohammed.popularmovies.NetWorkUtils.MovieLinkConstants;
import com.example.mohammed.popularmovies.NetWorkUtils.NetWrokManager;
import com.example.mohammed.popularmovies.R;
import com.example.mohammed.popularmovies.ViewModels.DetailsViewModel;
import com.example.mohammed.popularmovies.databinding.ActivityDetailsBinding;
import com.example.mohammed.popularmovies.interfaces.NetrokResponselistener;
import com.example.mohammed.popularmovies.jsonModels.Movie;
import com.example.mohammed.popularmovies.jsonModels.ReviewRoot;
import com.example.mohammed.popularmovies.jsonModels.TrailersRoot;

public class DetailsActivity extends AppCompatActivity {


    private ActivityDetailsBinding binding;
    private boolean isFavoriteSelected;
    private Movie movie;
    private DetailsViewModel viewModel;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        movie = getIntent().getParcelableExtra("MOVIE");
        viewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);

        setSupportActionBar(binding.toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        checkIfFavorite(movie.getMovieDbID());

        setMovieData(movie);
        MovieApi movieApi = MovieApiService.getApiClient().create(MovieApi.class);


        getMovieReviews(movie.getMovieDbID(), movieApi);
        getMovieTrailers(movie.getMovieDbID(), movieApi);

        setListeners();
    }


    private void setListeners() {
        binding.fab.setOnClickListener(v -> {

            if (isFavoriteSelected) {
                unFavouriteFab();
            } else {
                favouriteFab();
            }

            Log.e("FAV1", String.valueOf(isFavoriteSelected));

            isFavoriteSelected = !isFavoriteSelected;
            Log.e("FAV2", String.valueOf(isFavoriteSelected));
        });
    }

    private void getMovieTrailers(int movie, MovieApi movieApi) {

        NetWrokManager.getMovieTrailers(movie, movieApi, new NetrokResponselistener<TrailersRoot>() {
            @Override
            public void onResponseReceived(TrailersRoot trailersRoot) {

                TrailerAdapter adapter = new TrailerAdapter(DetailsActivity.this, trailersRoot.getResults());
                binding.movieDetails.trailerList.setLayoutManager(new LinearLayoutManager(DetailsActivity.this));
                binding.movieDetails.trailerList.setHasFixedSize(true);
                binding.movieDetails.trailerList.setNestedScrollingEnabled(true);

                binding.movieDetails.trailerList.setAdapter(adapter);

            }

            @Override
            public void onError() {

            }
        });
    }

    private void getMovieReviews(int movie, MovieApi movieApi) {

        NetWrokManager.getMovieReviews(movie, movieApi, new NetrokResponselistener<ReviewRoot>() {
            @Override
            public void onResponseReceived(ReviewRoot reviewRoot) {

                ReviewAdapter adapter = new ReviewAdapter(DetailsActivity.this, reviewRoot.getResults());
                binding.movieDetails.reviewList.setLayoutManager(new LinearLayoutManager(DetailsActivity.this));
                binding.movieDetails.reviewList.setNestedScrollingEnabled(true);
                binding.movieDetails.reviewList.setHasFixedSize(true);
                binding.movieDetails.reviewList.setAdapter(adapter);

            }

            @Override
            public void onError() {

            }
        });
    }

    private void checkIfFavorite(int movieId) {


        viewModel.readFavouriteMovieById(m -> {
            if (m == null) {
                isFavoriteSelected = false;
                unFavouriteFab();
            } else {
                isFavoriteSelected = true;
                favouriteFab();
            }

        }, movieId);

    }

    private void setMovieData(Movie movie) {
        binding.movieDetails.averateRate.setText(getString(R.string.movie_rate, String.valueOf(movie.getVoteAverage())));

        Glide.with(this).load(MovieLinkConstants.movieImgURL + movie.getBackdropPath())
                .apply(new RequestOptions().centerCrop())
                .into(binding.moviePoster);

        Glide.with(this).load(MovieLinkConstants.movieImgURL + movie.getPosterPath())
                .apply(new RequestOptions().error(R.drawable.warning).centerCrop())
                .into(binding.movieDetails.backDrop);

        binding.movieDetails.overview.setText(movie.getOverview());
        binding.movieDetails.language.setText(getString(R.string.movie_lang, movie.getOriginalLanguage()));

        binding.movieDetails.popilarity.setText(getString(R.string.movie_popularity, String.valueOf(movie.getPopularity())));

        binding.movieDetails.releaseDate.setText(getString(R.string.movie_release, movie.getReleaseDate()));
        binding.toolbarLayout.setTitle(movie.getTitle());


    }

    void unFavouriteFab() {
        binding.fab.setImageResource(R.drawable.ic_favorite_border_black_24dp);
    }


    void favouriteFab()

    {
        binding.fab.setImageResource(R.drawable.ic_favorite_black_24dp);
    }


    @Override
    protected void onPause() {
        super.onPause();


        if (isFavoriteSelected) {
            //save the favorite in the DB
            viewModel.insertFavoriteItem(movie);

        } else {
            // viewModel.deleteFavoriteItem(movie);
            viewModel.deleteFavoriteItem(movie);
        }

    }
}
