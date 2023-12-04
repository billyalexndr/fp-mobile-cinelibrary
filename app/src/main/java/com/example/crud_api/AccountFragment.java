package com.example.crud_api;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class AccountFragment extends Fragment {

    private AppDatabase appDatabase;
    private AppPreferences appPreferences;
    private EditText usernameTextView, emailTextView, fullnameTextView, passwordTextView;
    private TextView namatv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appDatabase = new AppDatabase(requireContext());
        appPreferences = new AppPreferences(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account, container, false);

        // Initialize TextViews
        namatv = view.findViewById(R.id.tv_nama);
        usernameTextView = view.findViewById(R.id.edt_usn);
        emailTextView = view.findViewById(R.id.edt_email);
        fullnameTextView = view.findViewById(R.id.edt_nama);
        passwordTextView = view.findViewById(R.id.edt_pass);

        // Get current logged-in username from AppPreferences
        String loggedUsername = appPreferences.getCurrentAccountId();

        // Get account details from AppDatabase
        AccountModel account = appDatabase.getAccountByUsername(loggedUsername);

        // Display account details
        if (account != null) {
            namatv.setText("Halo, "+ account.getUsername());
            usernameTextView.setText("  " + account.getUsername());
            emailTextView.setText("  " + account.getEmail());
            fullnameTextView.setText("  " + account.getFullname());
            passwordTextView.setText("  " + account.getPassword());
        }

        return view;
    }
}