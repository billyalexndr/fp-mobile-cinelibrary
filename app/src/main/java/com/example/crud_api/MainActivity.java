package com.example.crud_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Fragment {

    MyMovie myMovie;
    MovieAdapter adapter;
    RecyclerView rv_movie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);

        rv_movie = view.findViewById(R.id.rv_player);
        myMovie = new MyMovie(requireContext());

        rv_movie.setHasFixedSize(true);
        rv_movie.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new MovieAdapter(requireContext());
        rv_movie.setAdapter(adapter);

        getDataPlayer();

        return view;
    }

    private void getDataPlayer() {
        myMovie.getInstance().getMovie().enqueue(new Callback<List<MovieItem>>() {
            @Override
            public void onResponse(Call<List<MovieItem>> call, Response<List<MovieItem>> response) {
                List<MovieItem> resp = response.body();
                if (resp != null && resp.size() > 0) {
                    adapter.setData(resp);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<MovieItem>> call, Throwable t) {
                Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}