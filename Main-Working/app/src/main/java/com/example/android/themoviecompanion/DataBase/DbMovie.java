package com.example.android.themoviecompanion.DataBase;

import com.example.android.themoviecompanion.Utils.DbBitmapUtility;
import com.example.android.themoviecompanion.Utils.Movie;

import java.io.Serializable;

public class DbMovie implements Serializable {
    public static final String TABLE_NAME = "movies";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_PLOT = "plot";
    public static final String COLUMN_POSTER = "poster";

    private int id;
    private  String title;
    private  String year;
    private  String plot;
    private  byte[] img;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TITLE + " Movie Title,"
                    + COLUMN_PLOT + " The movie plot,"
                    + COLUMN_YEAR + " Year Released,"
                    + COLUMN_POSTER + " Bitmap storing the img"
                    + ")";

    public DbMovie() {
    }

    public DbMovie(int id, String title, String year, String plot, byte[] img) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.plot = plot;
        this.img = img;
    }public DbMovie(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.year = movie.getYear();
        this.plot = movie.getPlot();
        this.img = DbBitmapUtility.getBytes(movie.getPoster());
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


    public String getYear() {
        return year;
    }

    public String getPlot() {
            return plot;
        }

    public byte[] getImg() {
            return img;
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

    public void setImg(byte[] img) {
        this.img = img;
    }

    public Movie toMovie(DbMovie o){
        Movie movie = new Movie();
        movie.setId(o.getId());
        movie.setTitle(o.getTitle());
        movie.setPlot(o.getPlot());
        movie.setPoster(DbBitmapUtility.getImage(o.getImg()));
        movie.setYear(o.getYear());
        return movie;
    }
}

