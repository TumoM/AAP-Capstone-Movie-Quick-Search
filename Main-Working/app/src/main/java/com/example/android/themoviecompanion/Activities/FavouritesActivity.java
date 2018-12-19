package com.example.android.themoviecompanion.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.themoviecompanion.DataBase.DatabaseHelper;
import com.example.android.themoviecompanion.DataBase.DbMovie;
import com.example.android.themoviecompanion.FavouritesRecyclerViewAdapter;
import com.example.android.themoviecompanion.R;

import java.util.ArrayList;


public class FavouritesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FavouritesRecyclerViewAdapter favouritesRecyclerViewAdapter;
    private LinearLayoutManager recyclerLayoutManager;
    private ArrayList<DbMovie> movieList;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        recyclerView = (RecyclerView) findViewById(R.id.displayRV);

        movieList = new ArrayList<>();
        db = new DatabaseHelper(this);
        movieList.addAll((ArrayList<DbMovie>)db.getAllFavouriteMovies());
        recyclerView.setHasFixedSize(true);
        recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        favouritesRecyclerViewAdapter = new FavouritesRecyclerViewAdapter(this,movieList);

    }
}
