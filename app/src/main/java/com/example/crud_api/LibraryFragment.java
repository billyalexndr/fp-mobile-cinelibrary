package com.example.crud_api;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class LibraryFragment extends Fragment {

    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private AppDatabase appDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);

        recyclerView = view.findViewById(R.id.rv_player);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new MovieAdapter(requireContext(), "Unbookmark");
        recyclerView.setAdapter(adapter);

        // Inisialisasi database
        appDatabase = new AppDatabase(requireContext());

        // Ambil data film dari database
        List<MovieItem> movies = appDatabase.getAllMovies(); // Metode untuk mendapatkan semua data film dari database

        // Set data ke adapter
        if (movies != null && !movies.isEmpty()) {
            adapter.setData(movies);
        }

        return view;
    }
}
