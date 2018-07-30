package com.example.mohammed.popularmovies.AppDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.mohammed.popularmovies.Daos.MovieDao;
import com.example.mohammed.popularmovies.jsonModels.Movie;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase getDefaultAppDatabaseInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    return INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class,
                                    "movie-database")
                                    .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract MovieDao movieDao();

}
