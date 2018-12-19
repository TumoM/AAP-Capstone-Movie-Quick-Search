package com.example.android.themoviecompanion.Utils;

import java.io.Serializable;

public class Movie implements Serializable {
    String title, year, posterPath, overview;

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

    public String getCategory() {
        return null;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setPoster(String poster_path) {
        this.posterPath = poster_path;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOverview() {return overview;}
}
