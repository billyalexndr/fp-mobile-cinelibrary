package com.example.crud_api;

public class MovieItem {
    String Title;
    String Released;
    String Runtime;
    String Genre;
    String Country;
    String imdbRating;
    String Images[];

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) { this.Title = Title; }

    public String getReleased() {
        return Released;
    }

    public void setReleased(String Released) {
        this.Released = Released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String Runtime) {
        this.Runtime = Runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImages() {
        return Images[0];
    }

    public void setImages(String Image) {
        Images[0] = Image;
    }
}
