package com.example.android.themoviecompanion.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.themoviecompanion.DataBase.DatabaseHelper;
import com.example.android.themoviecompanion.R;
import com.example.android.themoviecompanion.Utils.Movie;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    private Movie movie;
    TextView title;
    TextView year;
    TextView plot;
    ImageView poster;
    FloatingActionButton addFave, deleteFave;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        db = new DatabaseHelper(this);

        title = (TextView) findViewById(R.id.movieTitle);
        year = (TextView) findViewById(R.id.movieYear);
        plot = (TextView) findViewById(R.id.plotText);
        poster = (ImageView) findViewById(R.id.movieImage);
        addFave = (FloatingActionButton) findViewById(R.id.favouriteAddBT);
        deleteFave = (FloatingActionButton) findViewById(R.id.favouriteDeleteBT);

        movie = (Movie) getIntent().getSerializableExtra("movie");
        title.setText(movie.getTitle());
        year.setText(movie.getYear().substring(14));
        plot.setText(movie.getPlot());
        plot.setMovementMethod(new ScrollingMovementMethod());
        Picasso.get().load(movie.getPosterPath()).placeholder(android.R.drawable.ic_btn_speak_now)
                .into(poster);
    }

    public void addClick(View view){
        if (db.isPresent(movie)){
            Toast.makeText(this, "Added to Favourites", Toast.LENGTH_SHORT).show();
            db.insertMovie(movie);
        }else {
            Toast.makeText(this, "Already in Favourites", Toast.LENGTH_SHORT).show();
        }
    }

    public void removeClick(View view){
        if (!db.isPresent(movie)){
            Toast.makeText(this, "Removing", Toast.LENGTH_SHORT).show();
            db.deleteMovie(movie.getId());
        }else {
            Toast.makeText(this, "Movie not in Favourites", Toast.LENGTH_SHORT).show();
        }
    }
}
