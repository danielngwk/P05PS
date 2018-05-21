package com.myapplicationdev.android.p04_revisionnotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ndp.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SONGS = "ndp";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGERS = "singers";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STARS = "stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE Songs
        // (id INTEGER PRIMARY KEY AUTOINCREMENT, note_content TEXT, rating
        // INTEGER );
        String createNoteTableSql = "CREATE TABLE " + TABLE_SONGS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_SINGERS + " TEXT, "
                + COLUMN_YEAR + " INTEGER, "
                + COLUMN_STARS + " INTEGER )";
        db.execSQL(createNoteTableSql);
        Log.i("info", "created tables");

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, "Home");
        values.put(COLUMN_SINGERS, "Kit Chan");
        values.put(COLUMN_YEAR, 1998);
        values.put(COLUMN_STARS, 5);
        db.insert(TABLE_SONGS, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONGS);
        onCreate(db);
    }

    public long insertSong(String title, String singers, int year, int stars) {
        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for the db operation
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_SINGERS, singers);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_STARS, stars);
        // Insert the row into the TABLE_NOTE
        long result = db.insert(TABLE_SONGS, null, values);
        // Close the database connection
        db.close();
        if (result == -1) {
            Log.d("DBHelper", "Insert failed");
        }

        Log.d("SQL Insert ", "" + result); //id returned, shouldn’t be -1
        return result;
    }

    public ArrayList<Songs> getAllSongs() {
        ArrayList<Songs> songs = new ArrayList<Songs>();

        String selectQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_TITLE + ", "
                + COLUMN_SINGERS + ", "
                + COLUMN_YEAR + ", "
                + COLUMN_STARS + " FROM " + TABLE_SONGS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int _id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singer = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);

                Songs song = new Songs(_id, title, singer, year, stars);
                songs.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }

    public ArrayList<String> getAllSong() {
        ArrayList<String> notes = new ArrayList<String>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_TITLE + " , "
                + COLUMN_SINGERS + " , "
                + COLUMN_YEAR + " , "
                + COLUMN_STARS + " FROM " + TABLE_SONGS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int _id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singer = cursor.getString(2);
                String year = cursor.getString(3);
                String stars = cursor.getString(4);
                notes.add("ID:" + _id + ", " + title + ", " + singer + ", " + year + ", " + stars);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notes;
    }

    public int updateSong(Songs data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_SINGERS, data.getSingers());
        values.put(COLUMN_YEAR, data.getYear());
        values.put(COLUMN_STARS, data.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.get_id())};
        int result = db.update(TABLE_SONGS, values, condition, args);
        db.close();

        if (result < 1) {
            Log.d("DBHelper", "Update Failed");
        }
        return result;
    }

    public int deleteSong(int _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(_id)};
        int result = db.delete(TABLE_SONGS, condition, args);
        db.close();

        if (result < 1) {
            Log.d("DBHelper", "Delete failed");
        }
        return result;
    }
}
