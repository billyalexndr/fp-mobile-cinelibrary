package com.example.crud_api;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKMARKS);
        onCreate(sqLiteDatabase);
    }
}
