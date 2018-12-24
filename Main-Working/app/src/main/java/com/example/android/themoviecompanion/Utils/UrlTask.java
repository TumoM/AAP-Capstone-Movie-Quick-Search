package com.example.android.themoviecompanion.Utils;

import android.os.AsyncTask;

import java.util.ArrayList;

// The AsyncTask that handles the background download task.
public abstract class UrlTask extends AsyncTask<String,String,ArrayList<Movie>> {

    @Override
    public ArrayList<Movie> doInBackground(String... params) {

        JSONhelper jsonhelper = new JSONhelper();
        ArrayList<Movie>  data = JSONhelper.getJSON(params[0], params[1]);


        return data;
    }


}