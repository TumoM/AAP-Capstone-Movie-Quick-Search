package com.example.android.themoviecompanion.Utils;

import android.os.AsyncTask;

import java.util.ArrayList;


public abstract class UrlTask extends AsyncTask<String,String,ArrayList<Movie>> {

    @Override
    public ArrayList<Movie> doInBackground(String... params) {

        JSONhelper jsonhelper = new JSONhelper();
        ArrayList<Movie>  data = JSONhelper.getJSON(params[0]);


        return data;
    }


}