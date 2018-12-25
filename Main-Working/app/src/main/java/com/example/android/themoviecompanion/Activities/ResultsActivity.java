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

public class ResultsActivity extends AppCompatActivity {
    // Declares the RecyclerView object, as well as an Adapter and Layout Manager
    // to handle list updating and row layout respectively.
    private static RecyclerView recyclerView;
    static MovieRecyclerViewAdapter movieRecyclerAdapapter;
    static RecyclerView.LayoutManager recyclerLayoutManager;
    static ArrayList<Movie> movieList;
    //private RequestQueue queue;
    RequestQueue mRequestQueue;
    Cache cache;
    Network network;
    static String search;
    static Context context2;
    static TextView noResult;
    private static String mediaType;


    static DatabaseHelper db;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        context2 = this;
        noResult = (TextView) findViewById(R.id.statusResultsTextView);
        mediaType = getIntent().getStringExtra("Type");
        db = new DatabaseHelper(context2);
        //queue = Volley.newRequestQueue(this);
        // Instantiate the cache
        cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache, network);

        // Start the queue
        mRequestQueue.start();


        recyclerView = (RecyclerView) findViewById(R.id.displayRV);

        movieList = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerLayoutManager = new LinearLayoutManager(context2);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        movieRecyclerAdapapter = new MovieRecyclerViewAdapter(context2,movieList);


        search = getIntent().getStringExtra("Search");
        movieRecyclerAdapapter.notifyDataSetChanged();

        Intent mServiceIntent = new Intent(this, DownloadService.class);
        String search = getIntent().getStringExtra("Search");
        mServiceIntent.putExtra("Search", search);
        startService(mServiceIntent);


    }

    public static void getData(){
        String[] inputs = {search, mediaType};
        new Viewdata().execute(inputs);
    }

    public static void setMovieList(ArrayList<Movie> movies){

    }
    public static class Viewdata extends UrlTask{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            super.onPostExecute(movies);
            movieList = movies;
            if (movieList != null && movieList.size()>0) {

                noResult.setVisibility(View.INVISIBLE);
                recyclerView.setHasFixedSize(true);
                recyclerLayoutManager = new LinearLayoutManager(context2);
                recyclerView.setLayoutManager(recyclerLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                movieRecyclerAdapapter = new MovieRecyclerViewAdapter(context2, movieList);
                recyclerView.setAdapter(movieRecyclerAdapapter);
                movieRecyclerAdapapter.notifyDataSetChanged();
            }
            else{
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
