package com.example.crud_api;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivityNav extends AppCompatActivity {

    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nav);

        FragmentManager fragmentManager = getSupportFragmentManager();
        View containerView = findViewById(R.id.fragmentContainerView);
        MainActivity myFragment = new MainActivity();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(containerView.getId(), myFragment);
        transaction.commit();

        // Add 4 buttons for fragment switching
        ImageButton btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity homeFragment = new MainActivity();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentContainerView, homeFragment);
                transaction.addToBackStack(null); // Allow going back
                transaction.commit();
            }
        });

        ImageButton btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchFragment searchFragment = new SearchFragment();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentContainerView, searchFragment);
                transaction.addToBackStack(null); // Allow going back
                transaction.commit();
            }
        });

        ImageButton btnLibrary = findViewById(R.id.btnLibrary);
        btnLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LibraryFragment libraryFragment = new LibraryFragment();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentContainerView, libraryFragment);
                transaction.addToBackStack(null); // Allow going back
                transaction.commit();
            }
        });

        ImageButton btnAccount = findViewById(R.id.btnAccount);
        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccountFragment accountFragment = new AccountFragment();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentContainerView, accountFragment);
                transaction.addToBackStack(null); // Allow going back
                transaction.commit();
            }
        });
    }

}

