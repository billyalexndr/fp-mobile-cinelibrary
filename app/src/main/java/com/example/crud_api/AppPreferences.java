package com.example.crud_api;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Objects;

public class AppPreferences {

    private static final String PREF_NAME = "app_pref";
    private static final String LOGGED_ACCOUNT_USERNAME = "logged_account_username";

    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;

    public AppPreferences(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setCurrentAccountId(String accountId) {
        editor.putString(LOGGED_ACCOUNT_USERNAME, accountId);
        editor.apply();
    }

    public String getCurrentAccountId() {
        return preferences.getString(LOGGED_ACCOUNT_USERNAME, "");
    }

    public boolean isLoggedIn() {
        return !Objects.equals(getCurrentAccountId(), "");
    }

    public void logout() {
        editor.remove(LOGGED_ACCOUNT_USERNAME);
        editor.apply();
    }

}
