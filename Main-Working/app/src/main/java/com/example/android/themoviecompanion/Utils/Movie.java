package com.example.android.themoviecompanion.Utils;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Movie implements Serializable {
    String title, year, posterPath, plot, type;
    int id;
    Bitmap poster;

    public Movie (){}

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
