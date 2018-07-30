package com.example.mohammed.popularmovies.NetWorkUtils;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.mohammed.popularmovies.interfaces.NetrokResponselistener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NetWorkResponse<ResponseType> implements Callback<ResponseType> {


    private NetrokResponselistener<ResponseType> listener;

    NetWorkResponse(NetrokResponselistener<ResponseType> listener) {
        this.listener = listener;
    }

    @Override
    public void onResponse(@NonNull Call<ResponseType> call, @NonNull Response<ResponseType> response) {

        if (listener != null) {
            listener.onResponseReceived(response.body());

        }
    }

    @Override
    public void onFailure(Call<ResponseType> call, Throwable t) {
        Log.e(getClass().getSimpleName(), "failure");

        if (listener != null)
            listener.onError();
    }


}
