package com.example.mohammed.popularmovies.jsonModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewRoot {


    /**
     * id : 351286
     * page : 1
     * results : [{"author":"Law","content":"I felt embarrassed to be watching this. It's an embarrassing fever dream. I abandoned it halfway through its runtime.","id":"5b2d4c080e0a264aea001943","url":"https://www.themoviedb.org/review/5b2d4c080e0a264aea001943"}]
     * total_pages : 1
     * total_results : 1
     */

    @SerializedName("id")
    private int id;
    @SerializedName("page")
    private int page;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("results")
    private List<Review> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<Review> getResults() {
        return results;
    }

    public void setResults(List<Review> results) {
        this.results = results;
    }
}
