package com.example.crud_api;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AppDatabase extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "db_crud";
    public static final int DATABASE_VERSION = 1;
    static final String TABLE_ACCOUNTS = "accounts";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PASSWORD = "password";
    static final String COLUMN_EMAIL = "email";
    static final String TABLE_BOOKMARKS = "bookmarks";
    static final String COLUMN_BOOKMARK_USERNAME = "bookmark_username";
    static final String COLUMN_BOOKMARK_TITLE = "bookmark_id";
    static final String COLUMN_BOOKMARK_RELEASED = "bookmark_released";
     static final String COLUMN_BOOKMARK_RUNTIME = "bookmark_runtime";
   static final String COLUMN_BOOKMARK_GENRE = "bookmark_genre";
     static final String COLUMN_BOOKMARK_COUNTRY = "bookmark_country";
     static final String COLUMN_BOOKMARK_IMDBRATING = "bookmark_imdb";
    static final String COLUMN_BOOKMARK_IMAGE = "bookmark_image";

     static final String CREATE_TABLE_ACCOUNT = "CREATE TABLE " + TABLE_ACCOUNTS + "("
            + COLUMN_USERNAME + " TEXT PRIMARY KEY ,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_PASSWORD + " TEXT,"
            + COLUMN_NAME + " TEXT"
            + ")";

    static final String CREATE_TABLE_BOOKMARKS = "CREATE TABLE " + TABLE_BOOKMARKS + "("
            + COLUMN_BOOKMARK_TITLE + " TEXT PRIMARY KEY,"
            + COLUMN_BOOKMARK_USERNAME + " TEXT,"
            + COLUMN_BOOKMARK_RELEASED + " TEXT,"
            + COLUMN_BOOKMARK_RUNTIME + " TEXT,"
            + COLUMN_BOOKMARK_GENRE + " TEXT,"
            + COLUMN_BOOKMARK_COUNTRY + " TEXT,"
            + COLUMN_BOOKMARK_IMDBRATING + " TEXT,"
            + COLUMN_BOOKMARK_IMAGE + " TEXT,"
            + " FOREIGN KEY (" + COLUMN_BOOKMARK_USERNAME + ") REFERENCES " + TABLE_ACCOUNTS + "(" + COLUMN_USERNAME + ")"
            + ")";
    public AppDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_ACCOUNT);
        sqLiteDatabase.execSQL(CREATE_TABLE_BOOKMARKS);
    }

    public void addMovieToLibrary(MovieItem movie, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_BOOKMARK_TITLE, movie.getTitle());
        values.put(COLUMN_BOOKMARK_RELEASED, movie.getReleased());
        values.put(COLUMN_BOOKMARK_GENRE, movie.getGenre());
        values.put(COLUMN_BOOKMARK_COUNTRY, movie.getCountry());
        values.put(COLUMN_BOOKMARK_IMDBRATING, movie.getImdbRating());
        values.put(COLUMN_BOOKMARK_IMAGE, movie.getImages());

        long newRowId = db.insert(TABLE_BOOKMARKS, null, values);
        db.close();

        if (newRowId > 0) {
            // Data berhasil dimasukkan ke dalam database
            Toast.makeText(context, "Data film berhasil ditambahkan", Toast.LENGTH_LONG).show();
        } else {
            // Gagal memasukkan data ke database
            Toast.makeText(context, "Gagal menambahkan data film", Toast.LENGTH_LONG).show();
        }
    }

    // Metode untuk mendapatkan semua data film dari database
    public List<MovieItem> getAllMovies() {
        List<MovieItem> movies = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_BOOKMARKS,
                new String[]{COLUMN_BOOKMARK_TITLE, COLUMN_BOOKMARK_RELEASED, COLUMN_BOOKMARK_GENRE, COLUMN_BOOKMARK_COUNTRY, COLUMN_BOOKMARK_IMDBRATING, COLUMN_BOOKMARK_IMAGE},
                null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Baca data dari cursor dan buat objek MovieItem baru
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(COLUMN_BOOKMARK_TITLE));
                @SuppressLint("Range") String released = cursor.getString(cursor.getColumnIndex(COLUMN_BOOKMARK_RELEASED));
                @SuppressLint("Range") String genre = cursor.getString(cursor.getColumnIndex(COLUMN_BOOKMARK_GENRE));
                @SuppressLint("Range") String country = cursor.getString(cursor.getColumnIndex(COLUMN_BOOKMARK_COUNTRY));
                @SuppressLint("Range") String imdbRating = cursor.getString(cursor.getColumnIndex(COLUMN_BOOKMARK_IMDBRATING));
                @SuppressLint("Range") String images = cursor.getString(cursor.getColumnIndex(COLUMN_BOOKMARK_IMAGE));

                MovieItem movie = new MovieItem(title, released, genre, country, imdbRating, images);
                movies.add(movie);
            } while (cursor.moveToNext());

            // Tutup cursor setelah selesai
            cursor.close();
        }

        // Tutup koneksi ke database
        db.close();

        return movies;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKMARKS);
        onCreate(sqLiteDatabase);
    }
}
