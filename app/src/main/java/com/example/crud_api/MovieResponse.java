package com.example.crud_api;

import java.util.List;

public class MovieResponse {
    private List<MovieItem> data;

    public List<MovieItem> getData() {
        return data;
    }

    public void setData(List<MovieItem> data) {
        this.data = data;
    }
}


