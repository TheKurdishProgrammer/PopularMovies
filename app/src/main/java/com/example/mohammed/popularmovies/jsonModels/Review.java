package com.example.mohammed.popularmovies.jsonModels;

import com.google.gson.annotations.SerializedName;

public class Review {
    /**
     * author : Law
     * content : I felt embarrassed to be watching this. It's an embarrassing fever dream. I abandoned it halfway through its runtime.
     * id : 5b2d4c080e0a264aea001943
     * url : https://www.themoviedb.org/review/5b2d4c080e0a264aea001943
     */

    @SerializedName("author")
    private String author;
    @SerializedName("content")
    private String content;
    @SerializedName("url")
    private String url;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
