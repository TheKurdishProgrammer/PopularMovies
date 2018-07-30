package com.example.mohammed.popularmovies.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.MenuItem;
import android.view.View;

import com.example.mohammed.popularmovies.Adapters.MovieRecyclerAdapter;
import com.example.mohammed.popularmovies.AppDatabase.AppDatabase;
import com.example.mohammed.popularmovies.R;
import com.example.mohammed.popularmovies.ViewModels.FavouriteMovieListViewModel;
import com.example.mohammed.popularmovies.databinding.ActivityFavouriteBinding;
import com.example.mohammed.popularmovies.jsonModels.Movie;

import java.util.ArrayList;

public class FavouriteActivity extends AppCompatActivity {


    private ActivityFavouriteBinding binding;

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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_favourite);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        makeRecyclerReady();

        FavouriteMovieListViewModel viewModel = ViewModelProviders.of(this).get(FavouriteMovieListViewModel.class);


        viewModel.getItemAndPersonList().observe(this, movies -> {
            if (movies != null) {
                if (movies.size() == 0)
                    binding.noFavoruiteMovie.setVisibility(View.VISIBLE);
                else
                    binding.noFavoruiteMovie.setVisibility(View.GONE);

                ((MovieRecyclerAdapter) binding.movieList.getAdapter()).setMovieList(movies);
            }
        });

    }

    private void makeRecyclerReady() {
        binding.movieList.setLayoutManager(new GridLayoutManager(this, 2));
        binding.movieList.setHasFixedSize(true);
        binding.movieList.setAdapter(new MovieRecyclerAdapter(new ArrayList<>(), this));
    }


    private class TestDelete extends AsyncTask<View, Void, Void> {


        @Override
        protected Void doInBackground(View... views) {
            View view = views[0];
            Movie movie = (Movie) view.getTag();

            AppDatabase.getDefaultAppDatabaseInstance(FavouriteActivity.this).movieDao().delete(movie);

            return null;
        }
    }
}
