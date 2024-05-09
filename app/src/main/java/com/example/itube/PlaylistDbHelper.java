package com.example.itube;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class PlaylistDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "playlist.db";
    private static final int DATABASE_VERSION = 1;


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PlaylistContract.PlaylistEntry.TABLE_NAME + " (" +
                    PlaylistContract.PlaylistEntry._ID + " INTEGER PRIMARY KEY," +
                    PlaylistContract.PlaylistEntry.COLUMN_USER_ID + " INTEGER," +
                    PlaylistContract.PlaylistEntry.COLUMN_VIDEO_TITLE + " TEXT," +
                    PlaylistContract.PlaylistEntry.COLUMN_VIDEO_URL + " TEXT)";

    public PlaylistDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrade if needed
    }

    // Method to add a video URL to the playlist
    // Method to add a video URL to the playlist
    public void addToPlaylist(long userId, String videoUrl) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PlaylistContract.PlaylistEntry.COLUMN_USER_ID, userId);
        values.put(PlaylistContract.PlaylistEntry.COLUMN_VIDEO_URL, videoUrl);

        long newRowId = db.insert(PlaylistContract.PlaylistEntry.TABLE_NAME, null, values);
        db.close(); // Close the database connection

        // Check if insertion was successful
        if (newRowId != -1) {
            // Insertion successful
        } else {
            // Insertion failed
        }
    }


    // Method to retrieve playlist data from the database
    public List<String> getPlaylist() {
        List<String> playlist = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                PlaylistContract.PlaylistEntry.COLUMN_VIDEO_URL
        };

        Cursor cursor = db.query(
                PlaylistContract.PlaylistEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            String videoUrl = cursor.getString(cursor.getColumnIndexOrThrow(PlaylistContract.PlaylistEntry.COLUMN_VIDEO_URL));
            playlist.add(videoUrl);
        }

        cursor.close();
        db.close(); // Close the database connection

        return playlist;
    }

}

