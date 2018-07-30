package com.example.mohammed.popularmovies.jsonModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailersRoot {

    /**
     * id : 351286
     * results : [{"id":"5b3069f9c3a368575d009f8f","iso_639_1":"en","iso_3166_1":"US","key":"vn9mMeWcgoM","name":"Jurassic World: Fallen Kingdom - Official Trailer [HD]","site":"YouTube","size":1080,"type":"Trailer"}]
     */

    @SerializedName("results")
    private List<Trailer> results;

    public List<Trailer> getResults() {
        return results;
    }

    public void setResults(List<Trailer> results) {
        this.results = results;
    }
}
