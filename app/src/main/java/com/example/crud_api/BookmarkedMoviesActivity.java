package com.example.crud_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

public class BookmarkedMoviesActivity extends AppCompatActivity {

    RecyclerView rvBookmarkedMovies;
    MovieAdapter bookmarkedMoviesAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarked_movies);

        // Initialize RecyclerView and adapter for bookmarked movies
        rvBookmarkedMovies = findViewById(R.id.rv_bookmarked_movies);
        rvBookmarkedMovies.setLayoutManager(new LinearLayoutManager(this));
        bookmarkedMoviesAdapter = new MovieAdapter(BookmarkManager.getBookmarkedMovies(this), this);
        rvBookmarkedMovies.setAdapter(bookmarkedMoviesAdapter);
    }
}
