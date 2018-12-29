package com.example.android.themoviecompanion.DataBase;

import com.example.android.themoviecompanion.Utils.DbBitmapUtility;
import com.example.android.themoviecompanion.Utils.Movie;

import java.io.Serializable;

/*
A class that defines the schema of Database. The name, columns and methods for this object.
*/

public class DbMovie implements Serializable {

    // Declare static fields for referencing throughout the package.
    static final String TABLE_NAME = "movies";
    static final String COLUMN_ID = "id";
    static final String COLUMN_TITLE = "title";
    static final String COLUMN_YEAR = "year";
    static final String COLUMN_PLOT = "plot";
    static final String COLUMN_POSTER = "poster";
    static final String COLUMN_TYPE = "type";

    private int id;
    private  String title;
    private  String year;
    private  String plot;
    private  byte[] poster;
    private  String type;


    // Create table SQL query
    static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TITLE + " Movie Title,"
                    + COLUMN_PLOT + " The movie plot,"
                    + COLUMN_YEAR + " Year Released,"
                    + COLUMN_POSTER + " Bitmap storing the poster,"
                    + COLUMN_TYPE + " Type of media"
                    + ")";

    public DbMovie() {
    }

    /*
    * A constructor for the raw inputs
    */
    public DbMovie(int id, String title, String year, String plot, byte[] poster, String type) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.plot = plot;
        this.poster = poster;
        this.type = type;

    /*
     * A constructor taking in a Movie object, the kind used when getting the JSON data.
     */
    }public DbMovie(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.year = movie.getYear();
        this.plot = movie.getPlot();
        this.poster = DbBitmapUtility.getBytes(movie.getPoster());
        this.type = movie.getType();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getType(){return type;}

    public String getYear() {
        return year;
    }

    public String getPlot() {
            return plot;
        }

    public byte[] getPoster() {
            return poster;
        }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setPoster(byte[] img) {
        this.poster = img;
    }

    public void setType(String type){ this.type = type;}

    /*
     * A static method for converting this class, DbMovie to a standard Movie object.
     */
    public static Movie toMovie(DbMovie o){
        Movie movie = new Movie();
        movie.setId(o.getId());
        movie.setTitle(o.getTitle());
        movie.setPlot(o.getPlot());
        movie.setPoster(DbBitmapUtility.getImage(o.getPoster()));
        movie.setYear(o.getYear());
        movie.setType(o.getType());
        return movie;
    }
}

