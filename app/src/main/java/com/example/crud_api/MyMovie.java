package com.example.crud_api;

import android.content.Context;

public class MyMovie {
    MovieApi movieApi;

    public MyMovie(Context context) {
        movieApi = RetrofitBuilder
                .builder(context)
                .create(MovieApi.class);
    }

    public MovieApi getInstance() {
        return movieApi;
    }
}
