package com.example.mohammed.popularmovies.interfaces;

public interface NetrokResponselistener<Response> {

    void onResponseReceived(Response response);

    void onError();
}
