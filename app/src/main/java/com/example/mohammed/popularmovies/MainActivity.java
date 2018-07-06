package com.example.mohammed.popularmovies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.alexbykov.nopaginate.callback.OnLoadMoreListener;
import ru.alexbykov.nopaginate.paginate.NoPaginate;

public class MainActivity extends AppCompatActivity {


    public static final int TOP_RATE = 0;
    public static final int MOST_POPULAR = 1;
    public static int CURRENT_SORT_TYPE = 0;
    private ApiClientInterface moviesAPI;
    private RecyclerView movieRecycler;
    private List<Movie> movieList;
    private int noOfPages = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupRecycler();
        movieList = new ArrayList<>();
        setUpAdapter();

        moviesAPI = MovieApiService.getApiClient().create(ApiClientInterface.class);

        setListeners();
        fetchMovies(1);


        NoPaginate noPaginate = NoPaginate.with(movieRecycler)
                .setOnLoadMoreListener(new OnLoadMoreListener() {
                    @Override
                    public void onLoadMore() {
                        findViewById(R.id.content_progress).setVisibility(View.GONE);
                        noOfPages++;
                        fetchMovies(CURRENT_SORT_TYPE);
                    }
                })

                .build();
    }

    private void setUpAdapter() {
        MovieRecyclerAdapter adapter = new MovieRecyclerAdapter(movieList, MainActivity.this);
        movieRecycler.setAdapter(adapter);

    }

    private void setupRecycler() {
        movieRecycler = findViewById(R.id.movie_list);
        movieRecycler.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        movieRecycler.setHasFixedSize(true);
    }

    private void setListeners() {

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                movieList = new ArrayList<>();
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


    private class MovieDBJsonResultCallback implements Callback<MovieDBJsonResult> {
        @Override
        public void onResponse(Call<MovieDBJsonResult> call, Response<MovieDBJsonResult> response) {


            if (response.body() != null) {
                if (response.body().getResult() != null) {
                    movieList.addAll(response.body().getResult());
                    MovieRecyclerAdapter adapter = new MovieRecyclerAdapter(movieList, MainActivity.this);
                    movieRecycler.setAdapter(adapter);


                }
            } else {
                Toast.makeText(MainActivity.this, "an Error Occurred\nCould not Fetch The Data.", Toast.LENGTH_LONG).show();
            }
            findViewById(R.id.content_progress).setVisibility(View.GONE);
            findViewById(R.id.no_coonn).setVisibility(View.GONE);

        }

        @Override
        public void onFailure(@NonNull Call<MovieDBJsonResult> call, @NonNull Throwable t) {

            Log.e(getLocalClassName(), t.getLocalizedMessage() + "\n" + t.getMessage() + "\n" + t.getCause());
            findViewById(R.id.content_progress).setVisibility(View.GONE);
            findViewById(R.id.no_coonn).setVisibility(View.VISIBLE);

        }
    }
}
