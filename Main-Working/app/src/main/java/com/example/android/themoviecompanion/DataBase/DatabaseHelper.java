package com.example.android.themoviecompanion.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.themoviecompanion.Utils.DbBitmapUtility;
import com.example.android.themoviecompanion.Utils.Movie;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "movies";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(DbMovie.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DbMovie.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertMovie(Movie movie) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        byte[] imgConvert = DbBitmapUtility.getBytes(movie.getPoster());
        values.put(DbMovie.COLUMN_ID, movie.getId());
        values.put(DbMovie.COLUMN_TITLE, movie.getTitle());
        values.put(DbMovie.COLUMN_YEAR, movie.getYear());
        values.put(DbMovie.COLUMN_PLOT, movie.getPlot());
        values.put(DbMovie.COLUMN_POSTER, imgConvert);

        // insert row
        long id = db.insert(DbMovie.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public DbMovie getMovie(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DbMovie.TABLE_NAME,
                new String[]{DbMovie.COLUMN_ID, DbMovie.COLUMN_TITLE, DbMovie.COLUMN_YEAR, DbMovie.COLUMN_PLOT,
                DbMovie.COLUMN_POSTER},
                DbMovie.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        DbMovie note = new DbMovie(
                cursor.getInt(cursor.getColumnIndex(DbMovie.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(DbMovie.COLUMN_TITLE)),
                cursor.getString(cursor.getColumnIndex(DbMovie.COLUMN_YEAR)),
                cursor.getString(cursor.getColumnIndex(DbMovie.COLUMN_PLOT)),
                cursor.getBlob(cursor.getColumnIndex(DbMovie.COLUMN_POSTER)));

        // close the db connection
        cursor.close();

        return note;
    }

    public boolean isPresent(Movie movie){
        List<DbMovie> list = getAllFavouriteMovies();
        if (list.size() == 0){return false;}
        for (DbMovie dbmovie: list) {
            if (dbmovie.getId() == movie.getId()){return true;}
        }
        return false;
    }

    public List<DbMovie> getAllFavouriteMovies() {
        List<DbMovie> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + DbMovie.TABLE_NAME + " ORDER BY " +
                DbMovie.COLUMN_TITLE + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DbMovie movie = new DbMovie();
                movie.setId(cursor.getInt(cursor.getColumnIndex(DbMovie.COLUMN_ID)));
                movie.setTitle(cursor.getString(cursor.getColumnIndex(DbMovie.COLUMN_TITLE)));
                movie.setYear(cursor.getString(cursor.getColumnIndex(DbMovie.COLUMN_YEAR)));
                movie.setPlot(cursor.getString(cursor.getColumnIndex(DbMovie.COLUMN_PLOT)));
                movie.setImg(cursor.getBlob(cursor.getColumnIndex(DbMovie.COLUMN_POSTER)));

                notes.add(movie);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();
        cursor.close();

        // return notes list
        return notes;
    }

    public int getMoviesCount() {
        String countQuery = "SELECT  * FROM " + DbMovie.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateMovie(DbMovie movie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbMovie.COLUMN_TITLE, movie.getTitle());

        // updating row
        return db.update(DbMovie.TABLE_NAME, values, DbMovie.COLUMN_ID + " = ?",
                new String[]{String.valueOf(movie.getId())});
    }

    public void deleteMovie(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DbMovie.TABLE_NAME, id + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }
}

