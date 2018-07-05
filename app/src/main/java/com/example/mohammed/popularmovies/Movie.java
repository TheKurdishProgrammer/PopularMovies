package com.example.mohammed.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {


    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
    private double vote_average;
    private String overview;
    private String title;
    private double popularity;
    private String poster_path;
    private String backdrop_path;
    private String original_language;
    private String release_date;

    private Movie(Parcel in) {
        vote_average = in.readDouble();
        overview = in.readString();
        title = in.readString();
        popularity = in.readDouble();
        poster_path = in.readString();
        backdrop_path = in.readString();
        original_language = in.readString();
        release_date = in.readString();
    }

    public Movie() {
        //empty constructor for deserializing
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(vote_average);
        dest.writeString(overview);
        dest.writeString(title);
        dest.writeDouble(popularity);
        dest.writeString(poster_path);
        dest.writeString(backdrop_path);
        dest.writeString(original_language);
        dest.writeString(release_date);
    }
}
