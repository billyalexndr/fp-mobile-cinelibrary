package com.example.crud_api;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BookmarkManager {
    private static final String PREF_KEY_BOOKMARK = "bookmarked_movies";

    // Function to save bookmarked movie locally
    public static void saveBookmark(Context context, MovieItem movieItem) {
        List<MovieItem> bookmarkedMovies = getBookmarkedMovies(context);
        if (bookmarkedMovies == null) {
            bookmarkedMovies = new ArrayList<>();
        }
        bookmarkedMovies.add(movieItem);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(bookmarkedMovies);
        editor.putString(PREF_KEY_BOOKMARK, json);
        editor.apply();
    }

    // Function to get bookmarked movies from local storage
    public static List<MovieItem> getBookmarkedMovies(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(PREF_KEY_BOOKMARK, null);
        Type type = new TypeToken<ArrayList<MovieItem>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
