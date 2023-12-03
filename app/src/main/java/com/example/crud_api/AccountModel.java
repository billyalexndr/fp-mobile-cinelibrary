package com.example.crud_api;

import java.util.List;

public class AccountModel {

    private String fullname;
    private String username;
    private String password;
    private String email;

    private List<MovieItem> bookmarks;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<MovieItem> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(List<MovieItem> bookmarks) {
        this.bookmarks = bookmarks;
    }

    public void addBookmark(MovieItem bookmark) {
        this.bookmarks.add(bookmark);
    }

    public void deleteBookmark(MovieItem bookmark) {
        this.bookmarks.remove(bookmark);
    }

}
