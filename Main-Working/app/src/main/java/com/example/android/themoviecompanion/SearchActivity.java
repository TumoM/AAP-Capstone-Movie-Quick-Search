package com.example.android.themoviecompanion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    //
    // Listerners
    //

    // Handles clicks to go to Favourites Activity
    public void favouriteClick(View view){
        // TODO 1 Implement favouriteClick() method
        Toast.makeText(this, "Going to favourites", Toast.LENGTH_SHORT).show();
    }
}
