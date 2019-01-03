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

import static com.example.android.themoviecompanion.DataBase.DbMovie.TABLE_NAME;

/*
An SQLiteOpenHelper class that defines methods for creating the database as well as CRUD operations.
*/
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 6;

    // Database Name
    private static final String DATABASE_NAME = "favourites";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create favourites table
        db.execSQL(DbMovie.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public void insertMovie(Movie movie) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        // Setting the values to be stored
        ContentValues values = new ContentValues();
        byte[] imgConvert = DbBitmapUtility.getBytes(movie.getPoster());
        values.put(DbMovie.COLUMN_ID, movie.getId());
        values.put(DbMovie.COLUMN_TITLE, movie.getTitle());
        values.put(DbMovie.COLUMN_YEAR, movie.getYear());
        values.put(DbMovie.COLUMN_PLOT, movie.getPlot());
        values.put(DbMovie.COLUMN_POSTER, imgConvert);
        values.put(DbMovie.COLUMN_TYPE, movie.getType());

        // insert row
        long id = 0;
        if (values != null) {
            id = db.insert(TABLE_NAME, null, values);
        }

        // close db connection
        db.close();

        // return newly inserted row id
    }

    public DbMovie getMovie(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                new String[]{DbMovie.COLUMN_ID, DbMovie.COLUMN_TITLE, DbMovie.COLUMN_YEAR, DbMovie.COLUMN_PLOT,
                DbMovie.COLUMN_POSTER, DbMovie.COLUMN_TYPE},
                DbMovie.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare DbMovie object that will be returned from the query.
        DbMovie note = new DbMovie(
                cursor.getInt(cursor.getColumnIndex(DbMovie.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(DbMovie.COLUMN_TITLE)),
                cursor.getString(cursor.getColumnIndex(DbMovie.COLUMN_YEAR)),
                cursor.getString(cursor.getColumnIndex(DbMovie.COLUMN_PLOT)),
                cursor.getBlob(cursor.getColumnIndex(DbMovie.COLUMN_POSTER)),
                cursor.getString(cursor.getColumnIndex(DbMovie.COLUMN_TYPE)));


        // close the db connection
        cursor.close();

        return note;
    }

    /**
     *  Checks if an equivalent Movie object is present in the Database that stores DbMovie objects.
     * @param movie The media to check if present in database.
     * @return true/present or false/not-found,
     */
    public boolean isPresent(Movie movie){
        List<DbMovie> list = getAllFavouritesList();
        if (list.size() == 0){return false;}
        for (DbMovie dbmovie: list) {
            if (dbmovie.getId() == movie.getId()){return true;}
        }
        return false;
    }


    /**
     * Returns a List of type DbMovie, that contains the list of all media stored in the database.
     * @return
     */
    public List<DbMovie> getAllFavouritesList() {
        List<DbMovie> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " ORDER BY " +
                DbMovie.COLUMN_TITLE + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list after building it in a DbObject movie
        if (cursor.moveToFirst()) {
            do {
                DbMovie movie = new DbMovie();
                movie.setId(cursor.getInt(cursor.getColumnIndex(DbMovie.COLUMN_ID)));
                movie.setTitle(cursor.getString(cursor.getColumnIndex(DbMovie.COLUMN_TITLE)));
                movie.setYear(cursor.getString(cursor.getColumnIndex(DbMovie.COLUMN_YEAR)));
                movie.setPlot(cursor.getString(cursor.getColumnIndex(DbMovie.COLUMN_PLOT)));
                movie.setPoster(cursor.getBlob(cursor.getColumnIndex(DbMovie.COLUMN_POSTER)));
                movie.setType(cursor.getString(cursor.getColumnIndex(DbMovie.COLUMN_TYPE)));

                notes.add(movie);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();
        cursor.close();

        // return notes list
        return notes;
    }

    /**
     * Gets the number of items stored in the database.
     * @return
     */
    public int getMoviesCount() {
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }


    /**
     * Deletes a media record from the database.
     * @param id
     */
    public void deleteMovie(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, DbMovie.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public void clearDb(){
        SQLiteDatabase db = this.getWritableDatabase();
        if (db != null) {
            db.execSQL("DELETE FROM " + TABLE_NAME);
            db.close();
        }

    }

}

