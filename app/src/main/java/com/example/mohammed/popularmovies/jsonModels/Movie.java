package com.example.mohammed.popularmovies.jsonModels;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


@Entity(tableName = "favourite")

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
    /**
     * vote_count : 1747
     * id : 351286
     * video : false
     * vote_average : 6.6
     * title : Jurassic World: Fallen Kingdom
     * popularity : 266.869
     * poster_path : /c9XxwwhPHdaImA2f1WEfEsbhaFB.jpg
     * original_language : en
     * original_title : Jurassic World: Fallen Kingdom
     * genre_ids : [28,12,878]
     * backdrop_path : /gBmrsugfWpiXRh13Vo3j0WW55qD.jpg
     * adult : false
     * overview : Several years after the demise of Jurassic World, a volcanic eruption threatens the remaining dinosaurs on the island of Isla Nublar. Claire Dearing, the former park manager and founder of the Dinosaur Protection Group, recruits Owen Grady to help prevent the extinction of the dinosaurs once again.
     * release_date : 2018-06-06
     */


    @PrimaryKey(autoGenerate = true)
    private int pid;
    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    private int voteCount;
    @SerializedName("id")
    @ColumnInfo(name = "moivedb_id")
    private int movieDbID;
    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    private double voteAverage;
    @SerializedName("title")
    @ColumnInfo(name = "title")
    private String title;
    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    private double popularity;
    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    private String posterPath;
    @SerializedName("original_language")
    @ColumnInfo(name = "original_language")
    private String originalLanguage;
    @SerializedName("original_title")
    @ColumnInfo(name = "original_title")
    private String originalTitle;
    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    private String backdropPath;
    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    private String overview;
    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    private String releaseDate;


    protected Movie(Parcel in) {
        pid = in.readInt();
        voteCount = in.readInt();
        movieDbID = in.readInt();
        voteAverage = in.readDouble();
        title = in.readString();
        popularity = in.readDouble();
        posterPath = in.readString();
        originalLanguage = in.readString();
        originalTitle = in.readString();
        backdropPath = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
    }


    public Movie() {

    }

    public int getMovieDbID() {
        return movieDbID;
    }

    public void setMovieDbID(int movieDbID) {
        this.movieDbID = movieDbID;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }


    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
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

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }


    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(pid);
        dest.writeInt(voteCount);
        dest.writeInt(movieDbID);
        dest.writeDouble(voteAverage);
        dest.writeString(title);
        dest.writeDouble(popularity);
        dest.writeString(posterPath);
        dest.writeString(originalLanguage);
        dest.writeString(originalTitle);
        dest.writeString(backdropPath);
        dest.writeString(overview);
        dest.writeString(releaseDate);
    }
}
