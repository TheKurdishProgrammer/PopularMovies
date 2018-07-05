package com.example.mohammed.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListners();
        fetchMovies(ApiClientInterface.topRated);


    }

    private void setListners() {

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        fetchMovies(ApiClientInterface.topRated);
                        break;
                    case 1:
                        fetchMovies(ApiClientInterface.mostPopular);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void fetchMovies(String sortType) {
        ApiClientInterface movies = Api.getApiClient().create(ApiClientInterface.class);

        Call<MovieDBJsonResult> call = movies.getMovies(1, sortType);

        call.enqueue(new Callback<MovieDBJsonResult>() {
            @Override
            public void onResponse(Call<MovieDBJsonResult> call, Response<MovieDBJsonResult> response) {

                RecyclerView movieRecycler = findViewById(R.id.movie_list);
                movieRecycler.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                movieRecycler.setHasFixedSize(true);

                List<Movie> movieList = response.body().getResult();
                MovieRecylerAdapter adapter = new MovieRecylerAdapter(movieList, MainActivity.this);
                movieRecycler.setAdapter(adapter);
                findViewById(R.id.content_progress).setVisibility(View.GONE);
                findViewById(R.id.no_coonn).setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<MovieDBJsonResult> call, Throwable t) {
                Log.e(getLocalClassName(), t.getLocalizedMessage() + "\n" + t.getMessage() + "\n" + t.getCause());
                findViewById(R.id.content_progress).setVisibility(View.GONE);
                findViewById(R.id.no_coonn).setVisibility(View.VISIBLE);

            }
        });
    }
}
