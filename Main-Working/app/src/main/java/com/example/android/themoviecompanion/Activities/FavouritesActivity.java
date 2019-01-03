package com.example.android.themoviecompanion.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.themoviecompanion.DataBase.DatabaseHelper;
import com.example.android.themoviecompanion.DataBase.DbMovie;
import com.example.android.themoviecompanion.Misc.FavouritesRecyclerViewAdapter;
import com.example.android.themoviecompanion.R;

import java.util.ArrayList;


public class FavouritesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FavouritesRecyclerViewAdapter favouritesRecyclerViewAdapter;
    private LinearLayoutManager recyclerLayoutManager;
    private ArrayList<DbMovie> movieList;

    private DatabaseHelper db;
    private TextView noResultTextView;


    // Updates the Recycler View in the case that media is removed from the favourites database in the Details
    // Activity, and focus is returned to this Activity.
    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (movieList != null){movieList.clear();}
        movieList.addAll((ArrayList<DbMovie>) db.getAllFavouritesList());
        if (favouritesRecyclerViewAdapter != null) {
            favouritesRecyclerViewAdapter.notifyDataSetChanged();
        }
        if (db.getMoviesCount() == 0) {
            noResultTextView.setText("No results found!!!");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        recyclerView = (RecyclerView) findViewById(R.id.displayRV);
        noResultTextView = (TextView) findViewById(R.id.statusResultsTextView);

        movieList = new ArrayList<>();
        db = new DatabaseHelper(this);
        if (db.getMoviesCount() > 0) { // If database is not empty.
            // Sets up the Recycler View.
            noResultTextView.setVisibility(View.INVISIBLE);
            movieList.addAll((ArrayList<DbMovie>) db.getAllFavouritesList());
            recyclerView.setHasFixedSize(true);
            recyclerLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(recyclerLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            favouritesRecyclerViewAdapter = new FavouritesRecyclerViewAdapter(this, movieList);
            recyclerView.setAdapter(favouritesRecyclerViewAdapter);
        }
        else{ noResultTextView.setVisibility(View.VISIBLE);
            // Updates on-screen text to notify the user that no records where found.
            noResultTextView.setText("No records found!!!");}
    }
}
