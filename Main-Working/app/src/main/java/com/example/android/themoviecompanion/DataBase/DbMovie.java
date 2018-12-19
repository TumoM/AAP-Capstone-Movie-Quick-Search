package com.example.android.themoviecompanion.DataBase;

import java.io.Serializable;

public class DbMovie implements Serializable {
    public static final String TABLE_NAME = "movies";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_PLOT = "plot";
    public static final String COLUMN_POSTER = "poster";
    public static final String COLUMN_FAVOURITE = "favourite";

    private int id;
    private  String title;
    private  String year;
    private  String plot;
    private  byte[] img;
    private boolean favourite;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TITLE + " Movie Title,"
                    + COLUMN_YEAR + " Year Released"
                    + COLUMN_PLOT + " The movie plot"
                    + COLUMN_POSTER + " Bitmap storing the img"
                    + COLUMN_FAVOURITE + " Boolean showing favourites"
                    + ")";

    public DbMovie() {
    }

    public DbMovie(int id, String title, String year, String plot, byte[] img) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.plot = plot;
        this.img = img;
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
        this.year = plot;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}

