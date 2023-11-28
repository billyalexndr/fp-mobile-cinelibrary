package com.example.crud_api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {
    @GET("/")
    Call<List<MovieItem>> getMovie();
}
