package com.example.crud_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    MyMovie myMovie;
    MovieAdapter adapter;
    RecyclerView rv_movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_movie = findViewById(R.id.rv_player);
        myMovie = new MyMovie(this);
        rv_movie.setHasFixedSize(true);
        rv_movie.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MovieAdapter(myMovie);
        rv_movie.setAdapter(adapter);
        getDataPlayer();
    }

    // Function to handle bookmark action
    private void handleBookmarkAction(MovieItem movieItem) {
        // Implement logic to handle bookmarking a movie and saving it locally
        BookmarkManager.saveBookmark(this, movieItem);
    }

    // Function to navigate to the bookmarked movies page
    private void navigateToBookmarkedMovies() {
        Intent intent = new Intent(MainActivity.this, BookmarkedMoviesActivity.class);
        startActivity(intent);
    }

    public void getDataPlayer(){
        myMovie.getInstance().getMovie().enqueue(new Callback<List<MovieItem>>() {
            @Override
            public void onResponse(Call<List<MovieItem>> call, Response<List<MovieItem>> response) {
                List<MovieItem> resp = response.body();
                if (resp != null && resp.size() > 0){
                    adapter = new MovieAdapter(resp, MainActivity.this);
                    rv_movie.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<List<MovieItem>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}