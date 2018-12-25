package com.example.android.themoviecompanion.Utils;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.android.themoviecompanion.Activities.ResultsActivity;

public class DownloadService extends IntentService {

    public DownloadService(){
        super(null);
    }
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DownloadService(String name) {
        super(name);
    }


    // handles the service calls, in tern calling the AsyncTask method to download json in the background.
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        ResultsActivity.getData();

    }

}