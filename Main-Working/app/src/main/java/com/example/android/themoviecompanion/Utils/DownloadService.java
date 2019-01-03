package com.example.android.themoviecompanion.Utils;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.android.themoviecompanion.Activities.ResultsActivity;

/*
 The download service that downloads the JSON data from the external web service.
*/

public class DownloadService extends IntentService {


    // Default constructor
    public DownloadService(String name) {
        super(name);
    }

    // handles the service calls, in tern calling the AsyncTask method to download JSON data
    // in the background.
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        assert intent != null;
        String[] inputs = {intent.getStringExtra("Search"), intent.getStringExtra("Type")};
        new ResultsActivity.Viewdata().execute(inputs);

    }

}