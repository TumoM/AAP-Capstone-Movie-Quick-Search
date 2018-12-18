package com.example.android.themoviecompanion.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.themoviecompanion.DataBase.Movie;
import com.example.android.themoviecompanion.MovieRecyclerViewAdapter;
import com.example.android.themoviecompanion.R;
import com.example.android.themoviecompanion.Utils.HTTPConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ResultsActivity extends AppCompatActivity {
//    String[] myDataset = {"1","2","3","4","5","6","7"};
    // Declares the RecyclerView object, as well as an Adapter and Layout Manager
    // to handle list updating and row layout respectively.
    private RecyclerView recyclerView;
    private MovieRecyclerViewAdapter movieAdapapter;
    private RecyclerView.LayoutManager recyclerLayoutManager;
    private List<Movie> movieList;
    private RequestQueue queue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        queue = Volley.newRequestQueue(this);

        recyclerView = (RecyclerView) findViewById(R.id.displayRV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(movieAdapapter);
        recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        movieList = new ArrayList<>();

        /*final int capacity = 100;
        // fills a dummy array for testing
        ArrayList<String> myDataset = new ArrayList<>(100);
        for (int i = 0; i < capacity; i++){
            myDataset.add(Integer.toString(i+1));
        }*/

      /*  recyclerAdapter = new MovieRecyclerViewAdapter(myDataset);
        recyclerView.setAdapter(recyclerAdapter);*/
        getMovies("Jack%20Reacher");
    }

    public List<Movie> getMovies(String searchTerm){
        Log.d("CHECK-IN", "Running getMovies");
        movieList.clear();
        String url = HTTPConstants.baseURLSerach + searchTerm;
        Log.i("Search URL", url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray moviesArray = response.getJSONArray("results");
                    for (int i = 0; i < moviesArray.length(); i++) {
                        JSONObject movieObj = moviesArray.getJSONObject(i);
                        Movie movie = new Movie();
                        movie.setTitle(movieObj.getString("title"));
                        movie.setYear(movieObj.getString("release_date"));
                        movie.setPoster(movieObj.getString("poster_path"));
                        movie.setOverview(movieObj.getString("overview"));

                        Log.d("Movies: ", movie.getTitle()); }
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(jsonObjectRequest);
        return movieList;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }
}
