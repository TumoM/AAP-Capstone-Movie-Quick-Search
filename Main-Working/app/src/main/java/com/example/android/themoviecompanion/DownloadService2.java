package com.example.android.themoviecompanion;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.ContentValues.TAG;

public class DownloadService2 extends IntentService {

//    public DownloadService2(){
//        super(new String("test"));
//    }

    // eg https://api.themoviedb.org/3/movie/550?api_key=eeab5da6854350c8bf390f554ae7f997
    final String urlString = "https://api.themoviedb.org/3/movie/550?api_key=";
    final String KEY = "eeab5da6854350c8bf390f554ae7f997";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DownloadService2(String name) {
        super(name);
    }

    public void downloadString(){
        // Instantiate the RequestQueue.
        RequestQueue requestQueue  = Volley.newRequestQueue(getApplicationContext());

        String url ="https://api.themoviedb.org/3/movie/550?api_key=eeab5da6854350c8bf390f554ae7f997";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("SWAG SERVER", "Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR", "onErrorResponse: Error");
            }
        }
        );

// Add the request to the RequestQueue.
        requestQueue.add(stringRequest);
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i("SWAGGER", "Entered DLS2");
        //String urlString = (intent.getExtras()).getString("URL");
        //Todo 1 Impliment the functionality to turn this string into the download request
        downloadString();

    }
}
//    URL url = new URL(urlString);
//    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//   try {
//        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//        readStream(in);
//    } finally {
//        urlConnection.disconnect();
//    }

