package com.example.mohammed.popularmovies.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.mohammed.popularmovies.AppDatabase.AppDatabase;
import com.example.mohammed.popularmovies.jsonModels.Movie;


public class DetailsViewModel extends AndroidViewModel {

    public static final int DELETE_OP = 0;
    public static final int INSERT_OP = 1;
    private AppDatabase database;

    public DetailsViewModel(@NonNull Application application) {
        super(application);

        database = AppDatabase.getDefaultAppDatabaseInstance(this.getApplication());

    }


    public void deleteFavoriteItem(Movie movie) {
        new DeleteInsertAync(DELETE_OP, database).execute(movie);
    }

    public void insertFavoriteItem(Movie movie) {
        new DeleteInsertAync(INSERT_OP, database).execute(movie);
    }


    public void readFavouriteMovieById(OnMovieReadListener movieListener, int movieId) {
        new ReadFavMovieById(database, movieListener).execute(movieId);
    }


    public interface OnMovieReadListener {
        void OnMovieRead(Movie movie);
    }

    private static class DeleteInsertAync extends AsyncTask<Movie, Void, Void> {


        private AppDatabase database;
        private int opType;

        DeleteInsertAync(int opType, AppDatabase database) {
            this.database = database;
            this.opType = opType;
        }

        @Override
        protected Void doInBackground(Movie... movies) {

            switch (opType) {
                case DELETE_OP:
                    database.movieDao().delete(movies[0]);
                    break;
                case INSERT_OP:
                    database.movieDao().insert(movies[0]);
                    break;
            }
            return null;
        }
    }

    private static class ReadFavMovieById extends AsyncTask<Integer, Void, Movie> {

        private AppDatabase database;


        private OnMovieReadListener movieReadListener;

        public ReadFavMovieById(AppDatabase database, OnMovieReadListener movieReadListener) {
            this.database = database;
            this.movieReadListener = movieReadListener;
        }

        @Override
        protected Movie doInBackground(Integer... integers) {
            Movie movie = database.movieDao().findByFavId(integers[0]);
            return movie;
        }


        @Override
        protected void onPostExecute(Movie movie) {
            super.onPostExecute(movie);

            movieReadListener.OnMovieRead(movie);
        }
    }
}
