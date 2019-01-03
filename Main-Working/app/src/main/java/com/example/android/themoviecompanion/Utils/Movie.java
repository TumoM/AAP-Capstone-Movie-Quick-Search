package com.example.android.themoviecompanion.Utils;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 *  The Movie Class is a template class that is used to store the information of a Movie/TV Show that
 *  has been downloaded in a JSON format.
 */
public class Movie implements Serializable {
    // Local Variables
    private String title, year, posterPath, plot, type;
    private int id;
    private Bitmap poster;

    // Getter Methods.
    public String getType(){return type;}

    public String getPosterPath() {
        return posterPath;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public int getId() {
        return id;
    }

    public String getPlot(){ return plot;}

    public Bitmap getPoster() {
        return poster;
    }


    // Setter Methods.
    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setPosterPath(String poster_path) {
        this.posterPath = poster_path;
    }

    public void setPlot(String overview) {
        this.plot = overview;
    }

    public void setId(int id) {this.id = id;}

    public void setType(String type){this.type = type;}

    public void setPoster(Bitmap poster) {
        this.poster = poster;
    }
}
