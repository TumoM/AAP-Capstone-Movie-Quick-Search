package com.example.android.themoviecompanion.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.example.android.themoviecompanion.DataBase.Movie;
import com.example.android.themoviecompanion.MovieRecyclerViewAdapter;
import com.example.android.themoviecompanion.R;
import com.example.android.themoviecompanion.Utils.UrlTask;

import java.util.ArrayList;
import java.util.List;

public class ResultsActivity2 extends AppCompatActivity {
    // Declares the RecyclerView object, as well as an Adapter and Layout Manager
    // to handle list updating and row layout respectively.
    private RecyclerView recyclerView;
    private MovieRecyclerViewAdapter movieRecyclerAdapapter;
    private RecyclerView.LayoutManager recyclerLayoutManager;
    private ArrayList<Movie> movieList;
    //private RequestQueue queue;
    RequestQueue mRequestQueue;
    Cache cache;
    Network network;
    private String search;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

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


        search = getIntent().getStringExtra("Search");
        // movieList = getMovies(search);
        // movieList = JSONhelper.getJSON(search);
        new Viewdata().execute(search);



    }
    class Viewdata extends UrlTask{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            super.onPostExecute(movies);
            movieList = (ArrayList<Movie>) movies;
            recyclerView.setHasFixedSize(true);
            recyclerLayoutManager = new LinearLayoutManager(ResultsActivity2.this);
            recyclerView.setLayoutManager(recyclerLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            movieRecyclerAdapapter = new MovieRecyclerViewAdapter(ResultsActivity2.this,movieList);
            recyclerView.setAdapter(movieRecyclerAdapapter);
            movieRecyclerAdapapter.notifyDataSetChanged();



        }

    }



/*

    public List<Movie> getMovies(String searchTerm) {
        movieList.clear();
        String url = "https://api.themoviedb.org/3/search/movie?api_key=eeab5da6854350c8bf390f554ae7f997&query="+searchTerm;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{
                    JSONArray moviesArray = response.getJSONArray("results");

                    for (int i = 0; i < moviesArray.length(); i++) {

                        JSONObject movieObj = moviesArray.getJSONObject(i);

                        // Creates a new movie item, and then rips the appropriate JSON Data
                        Movie movie = new Movie();
                        movie.setTitle(movieObj.getString("title"));
                        movie.setYear("Year Released: " + movieObj.getString("release_date"));
                        //.substring(0,4));
                        movie.setPoster(HTTPConstants.baseImageURL + movieObj.getString("poster_path"));
                        movie.setOverview(movieObj.getString("overview"));


                        Log.d("Movies: ", movie.getTitle());
                        Log.d("Poster URL", movie.getPosterPath());
                        movieList.add(movie);


                    }
                    */
/**
                     * Very important!! Otherwise, we wont see anything being displayed.
                     *//*

                    movieRecyclerAdapapter.notifyDataSetChanged();//Important!!


                }catch (JSONException e) {
                    e.printStackTrace();
                }catch (Exception p) {
                    p.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mRequestQueue.add(jsonObjectRequest);

        return movieList;

    }
*/

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }
}
