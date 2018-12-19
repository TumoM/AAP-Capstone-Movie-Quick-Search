package com.example.android.themoviecompanion.Utils;

import java.io.Serializable;

public class Movie implements Serializable {
    String title, year, posterPath, plot;
    int id;
    boolean favourite;

    public Movie (){}

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

    public boolean getFavourite() {return favourite;}


    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setPoster(String poster_path) {
        this.posterPath = poster_path;
    }

    public void setPlot(String overview) {
        this.plot = overview;
    }

    public void setId(int id) {this.id = id;}

    public void setFavourite(boolean flag){ this.favourite = flag;}
}
