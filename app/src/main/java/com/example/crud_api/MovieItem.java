package com.example.crud_api;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieItem implements Parcelable {
    String Title;
    String Released;
    String Runtime;
    String Genre;
    String Country;
    String imdbRating;
    String Images[];

    public MovieItem(String title, String released, String genre, String country, String imdbRating, String images) {
        setTitle(title);
        setReleased(released);
        setGenre(genre);
        setCountry(country);
        setImdbRating(imdbRating);
        setImages(images);
    }

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
        Images = new String[]{Image};
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Title);
        dest.writeString(this.Released);
        dest.writeString(this.Runtime);
        dest.writeString(this.Genre);
        dest.writeString(this.Country);
        dest.writeString(this.imdbRating);
        dest.writeStringArray(this.Images);
    }

    public void readFromParcel(Parcel source) {
        this.Title = source.readString();
        this.Released = source.readString();
        this.Runtime = source.readString();
        this.Genre = source.readString();
        this.Country = source.readString();
        this.imdbRating = source.readString();
        this.Images = source.createStringArray();
    }

    public MovieItem() {
    }

    protected MovieItem(Parcel in) {
        this.Title = in.readString();
        this.Released = in.readString();
        this.Runtime = in.readString();
        this.Genre = in.readString();
        this.Country = in.readString();
        this.imdbRating = in.readString();
        this.Images = in.createStringArray();
    }

    public static final Parcelable.Creator<MovieItem> CREATOR = new Parcelable.Creator<MovieItem>() {
        @Override
        public MovieItem createFromParcel(Parcel source) {
            return new MovieItem(source);
        }

        @Override
        public MovieItem[] newArray(int size) {
            return new MovieItem[size];
        }
    };
}
