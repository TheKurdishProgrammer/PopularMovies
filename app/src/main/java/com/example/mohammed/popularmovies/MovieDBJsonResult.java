package com.example.mohammed.popularmovies;

import java.util.List;

public class MovieDBJsonResult {
    private List<Movie> results;

    public List<Movie> getResult() {
        return results;
    }

    public void setResult(List<Movie> result) {
        this.results = result;
    }
}
