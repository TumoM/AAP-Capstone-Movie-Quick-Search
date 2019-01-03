package com.example.android.themoviecompanion.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.example.android.themoviecompanion.DataBase.DatabaseHelper;
import com.example.android.themoviecompanion.Misc.MovieRecyclerViewAdapter;
import com.example.android.themoviecompanion.R;
import com.example.android.themoviecompanion.Utils.DownloadService;
import com.example.android.themoviecompanion.Utils.Movie;
import com.example.android.themoviecompanion.Utils.UrlTask;

import java.util.ArrayList;

/*
*  An activity that displays movie/show's in a Recycler View.
*/
public class ResultsActivity extends AppCompatActivity {

    /*
      Declares the RecyclerView object, as well as an Adapter and Layout Manager
      to handle list updating and row layout respectively.
     */
    private static RecyclerView recyclerView;
    static MovieRecyclerViewAdapter movieRecyclerAdapapter;
    static RecyclerView.LayoutManager recyclerLayoutManager;
    static ArrayList<Movie> movieList;

    // Network related variables
    RequestQueue mRequestQueue;
    Cache cache;
    Network network;

    // Android
    static String search;
    static Context context2;
    static Context AppContext;
    static TextView noResult;

    // The Database to be used.
    static DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Declaring local variables.
        context2 = this;
        movieList = new ArrayList<>();
        ResultsActivity.AppContext = getApplicationContext();
        noResult = (TextView) findViewById(R.id.statusResultsTextView);
        db = new DatabaseHelper(context2);

        // Instantiate the cache.
        cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache, network);

        // Start the queue to allow downloads in the app.
        mRequestQueue.start();

        // Sets up the Recycler View
        recyclerView = (RecyclerView) findViewById(R.id.displayRV);
        recyclerView.setHasFixedSize(true);
        recyclerLayoutManager = new LinearLayoutManager(context2);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        movieRecyclerAdapapter = new MovieRecyclerViewAdapter(context2,movieList);
        movieRecyclerAdapapter.notifyDataSetChanged();

        // The Search Term and media Type are pulled out of the intent this activity was launched with
        search = getIntent().getStringExtra("Search");
        String type = getIntent().getStringExtra("Type");

        // Starts the Service that will make the network request and handle the JSON.
        Intent mServiceIntent = new Intent(this, DownloadService.class);
        String search = getIntent().getStringExtra("Search");
        mServiceIntent.putExtra("Search", search); // The Search term to query.
        mServiceIntent.putExtra("Type",type ); // The type of media - Movie or TV Show.
        startService(mServiceIntent); // Service is started.


    }

    /**
     * Handles the results of the UrlTask that is used to fetch the jason data. The results are handled
     * here, and the Recycler View is re-setup and updated.
     */
    public static class Viewdata extends UrlTask{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            super.onPostExecute(movies);
            movieList = movies;
            if (movieList != null && movieList.size()>0) { // If not null and not empty

                // Removes the message about no results being found
                noResult.setVisibility(View.INVISIBLE);

                // Sets up the Recycler View to be updated again
                recyclerView.setHasFixedSize(true);
                recyclerLayoutManager = new LinearLayoutManager(context2);
                recyclerView.setLayoutManager(recyclerLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                movieRecyclerAdapapter = new MovieRecyclerViewAdapter(context2, movieList);
                recyclerView.setAdapter(movieRecyclerAdapapter);
                movieRecyclerAdapapter.notifyDataSetChanged();
            }
            else{
                // Resets the warning text and sets it to visible.
                noResult.setText("No results found for: \n" + search + "!!!");
                noResult.setVisibility(View.VISIBLE);
            }


        }

    }


    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }
}
