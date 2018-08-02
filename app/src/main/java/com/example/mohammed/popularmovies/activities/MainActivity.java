package com.example.mohammed.popularmovies.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mohammed.popularmovies.Adapters.MovieRecyclerAdapter;
import com.example.mohammed.popularmovies.NetWorkUtils.MovieApi;
import com.example.mohammed.popularmovies.NetWorkUtils.MovieApiService;
import com.example.mohammed.popularmovies.R;
import com.example.mohammed.popularmovies.ViewModels.FavouriteMovieListViewModel;
import com.example.mohammed.popularmovies.databinding.ActivityMainBinding;
import com.example.mohammed.popularmovies.jsonModels.Movie;
import com.example.mohammed.popularmovies.jsonModels.MovieRoot;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    public static final int TOP_RATE = 0;
    public static final int MOST_POPULAR = 1;
    private static final String SCROLL = "SCROLL";
    private static final String LIST = "LIST";
    public static int CURRENT_SORT_TYPE = 0;
    private MovieApi moviesAPI;
    private int noOfPages = 1;
    private boolean isAllItemsLoaded;
    private boolean isPaginateLoading;
    private Bundle savedInstanceState;
    private boolean dontComeThisTime;
    private ActivityMainBinding binding;
    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if (!recyclerView.canScrollVertically(1)) {
                if (isAllItemsLoaded && !isPaginateLoading) {
                    fetchMovies(CURRENT_SORT_TYPE);
                    Log.e("OnBOTTOM", "RECYLER");
                    binding.paginationProgress.setVisibility(View.VISIBLE);
                }
                isPaginateLoading = true;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        this.savedInstanceState = savedInstanceState;
        setupRecycler();
        setUpAdapter();


        moviesAPI = MovieApiService.getApiClient().create(MovieApi.class);

        setListeners();

    }


    private void setUpAdapter() {
        MovieRecyclerAdapter adapter = new MovieRecyclerAdapter(new ArrayList<>(), this);
        binding.movieList.addOnScrollListener(scrollListener);

        binding.movieList.setAdapter(adapter);

    }

    private void setupRecycler() {
        binding.movieList.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        binding.movieList.setHasFixedSize(true);
        binding.movieList.addOnScrollListener(scrollListener);
    }

    private void setListeners() {

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                binding.noFavoruiteMovie.setVisibility(View.GONE);
                setUpAdapter();
                noOfPages = 1;
                fetchMovies(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void fetchMovies(int position) {
        switch (position) {
            case TOP_RATE:
                CURRENT_SORT_TYPE = TOP_RATE;
                moviesAPI.getTopRatedMovies(noOfPages).enqueue(new MovieDBJsonResultCallback());
                break;
            case MOST_POPULAR:
                CURRENT_SORT_TYPE = MOST_POPULAR;
                moviesAPI.getPopularMovies(noOfPages).enqueue(new MovieDBJsonResultCallback());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.favourite:
                FetchAndDisplayFavouriteMovies();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void FetchAndDisplayFavouriteMovies() {
        FavouriteMovieListViewModel viewModel = ViewModelProviders.of(this).get(FavouriteMovieListViewModel.class);
        binding.movieList.removeOnScrollListener(scrollListener);

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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SCROLL, binding.movieList.getLayoutManager().onSaveInstanceState());
        ArrayList<Movie> movies = (ArrayList<Movie>) ((MovieRecyclerAdapter) binding.movieList.getAdapter()).getMovieList();
        outState.putParcelableArrayList(LIST, movies);
    }

    private class MovieDBJsonResultCallback implements Callback<MovieRoot> {

        @Override
        public void onResponse(Call<MovieRoot> call, Response<MovieRoot> response) {

            if (response.body() != null) {
                if (response.body() != null && response.body().getResults() != null) {

                    List<Movie> oldMovieList;

                    if (savedInstanceState != null && !dontComeThisTime) {
                        oldMovieList = savedInstanceState.getParcelableArrayList(LIST);
                        Log.e("OLDSIZE", String.valueOf(oldMovieList.size()));
                        ((MovieRecyclerAdapter) binding.movieList.getAdapter()).setMovieList(oldMovieList);
                        binding.movieList.getLayoutManager().onRestoreInstanceState(savedInstanceState.getParcelable(SCROLL));
                        dontComeThisTime = true;
                    } else {

                        oldMovieList = ((MovieRecyclerAdapter) binding.movieList.getAdapter()).getMovieList();
                        oldMovieList.addAll(response.body().getResults());

                        ((MovieRecyclerAdapter) binding.movieList.getAdapter()).setMovieList(oldMovieList);
                        noOfPages++;

                    }


                    isAllItemsLoaded = true;
                    isPaginateLoading = false;
                    binding.paginationProgress.setVisibility(View.GONE);


                }
            } else {
                Toast.makeText(MainActivity.this, "an Error Occurred\nCould not Fetch The Data.", Toast.LENGTH_LONG).show();
            }

            findViewById(R.id.content_progress).setVisibility(View.GONE);
            findViewById(R.id.no_coonn).setVisibility(View.GONE);
        }

        @Override
        public void onFailure(@NonNull Call<MovieRoot> call, @NonNull Throwable t) {

            Log.e(getLocalClassName(), t.getLocalizedMessage() + "\n" + t.getMessage() + "\n" + t.getCause());
            findViewById(R.id.content_progress).setVisibility(View.GONE);
            findViewById(R.id.no_coonn).setVisibility(View.VISIBLE);

        }
    }


}
