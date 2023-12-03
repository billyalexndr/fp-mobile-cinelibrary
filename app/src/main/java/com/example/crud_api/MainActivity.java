package com.example.crud_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);

        rv_movie = view.findViewById(R.id.rv_player);
        myMovie = new MyMovie(requireContext());

        rv_movie.setHasFixedSize(true);
        rv_movie.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new MovieAdapter(requireContext(), "Bookmark");
        rv_movie.setAdapter(adapter);

        // Set a click listener to handle bookmark button click in the adapter
        adapter.setOnBookmarkClickListener(new MovieAdapter.OnBookmarkClickListener() {
            @Override
            public void onBookmarkClick(MovieItem movie) {
                // Handle adding movie to the library here
                addToLibrary(movie);
            }
        });

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

    // Method to add the bookmarked movie to the library
    private void addToLibrary(MovieItem movie) {
        // Create a new instance of LibraryFragment
        LibraryFragment libraryFragment = new LibraryFragment();

        // Pass the movie details to the fragment using Bundle
        Bundle bundle = new Bundle();
        bundle.putParcelable("movie", movie);
        libraryFragment.setArguments(bundle);

        // Replace the current fragment with LibraryFragment
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView, libraryFragment);
        transaction.addToBackStack(null);  // Optional: Add to back stack
        transaction.commit();
    }
}
