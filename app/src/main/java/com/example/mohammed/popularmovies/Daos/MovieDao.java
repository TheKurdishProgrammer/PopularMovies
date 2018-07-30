package com.example.mohammed.popularmovies.Daos;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.mohammed.popularmovies.jsonModels.Movie;

import java.util.List;


@Dao
public interface MovieDao {


    // to read the favourite movie records
    @Query("SELECT * FROM favourite")
    LiveData<List<Movie>> getFavMovies();

    @Query("DELETE FROM favourite")
    void clearAllFavMovies();

    @Delete
    void delete(Movie movie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Movie movie);

    @Query("SELECT * FROM favourite WHERE moivedb_id LIKE :favID ")
    Movie findByFavId(int favID);

    @Update
    void updateMovie(Movie movie);
}


