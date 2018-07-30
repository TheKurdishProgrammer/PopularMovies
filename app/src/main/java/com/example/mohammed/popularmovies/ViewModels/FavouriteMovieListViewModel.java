package com.example.mohammed.popularmovies.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.mohammed.popularmovies.AppDatabase.AppDatabase;
import com.example.mohammed.popularmovies.jsonModels.Movie;

import java.util.List;


public class FavouriteMovieListViewModel extends AndroidViewModel {

    private LiveData<List<Movie>> itemAndPersonList;

    public FavouriteMovieListViewModel(Application application) {
        super(application);


        AppDatabase appDatabase = AppDatabase.getDefaultAppDatabaseInstance(application);

        itemAndPersonList = appDatabase.movieDao().getFavMovies();
    }


    public LiveData<List<Movie>> getItemAndPersonList() {
        return itemAndPersonList;
    }

}
