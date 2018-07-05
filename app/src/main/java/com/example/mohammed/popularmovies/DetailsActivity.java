package com.example.mohammed.popularmovies;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mohammed.popularmovies.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    private Movie movie;
    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        movie = getIntent().getParcelableExtra("MOVIE");
        setMovieData();
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(movie.getTitle());
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setMovieData() {
        binding.movieDetails.averateRate.setText(getString(R.string.movie_rate, String.valueOf(movie.getVote_average())));

        Glide.with(this).load("http://image.tmdb.org/t/p/w185/" + movie.getBackdrop_path())
                .apply(new RequestOptions().centerCrop())
                .into(binding.movieDetails.backDrop);

        binding.movieDetails.overview.setText(movie.getOverview());
        binding.movieDetails.language.setText(getString(R.string.movie_lang, movie.getOriginal_language()));

        binding.movieDetails.popilarity.setText(getString(R.string.movie_popularity, String.valueOf(movie.getPopularity())));
        binding.movieDetails.releaseDate.setText(getString(R.string.movie_release, movie.getRelease_date()));
        Glide.with(this).load("http://image.tmdb.org/t/p/w185/" + movie.getPoster_path())
                .apply(new RequestOptions().error(R.drawable.warning).centerCrop())
                .into(binding.moviePoster);
    }


}
