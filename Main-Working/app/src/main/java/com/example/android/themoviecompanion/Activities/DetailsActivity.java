package com.example.android.themoviecompanion.Activities;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.themoviecompanion.DataBase.DatabaseHelper;
import com.example.android.themoviecompanion.DataBase.DbMovie;
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

        // The Database
        db = new DatabaseHelper(this);

        // Android related variables and views
        title = findViewById(R.id.movieTitle);
        year = findViewById(R.id.movieYear);
        plot = findViewById(R.id.plotText);
        poster = findViewById(R.id.movieImage);
        addFave = findViewById(R.id.favouriteAddBT);
        deleteFave = findViewById(R.id.favouriteDeleteBT);

        // A try block to distinguish between a Movie object and DbMovie object
        try {
            int iDtest = getIntent().getIntExtra("movie",0);
        DbMovie movieTemp = db.getMovie(iDtest);
        movie = DbMovie.toMovie(movieTemp);
        }
        catch (Exception e){
            movie = ((Movie) getIntent().getSerializableExtra("movie"));
        }

        // If the poster field is null (i.e a Movie object), it is fetched via a network request.
        if (movie.getPoster() == null){
        Picasso.get().load(movie.getPosterPath()).placeholder(android.R.drawable.ic_btn_speak_now).into(poster);}
                else{poster.setImageBitmap(movie.getPoster());
        }

        // Sets the data that is displayed in the activity.
        title.setText(movie.getTitle());
        year.setText(movie.getYear());
        String plotText = "Plot: " + movie.getPlot();
        plot.setText(plotText);
        plot.setMovementMethod(new ScrollingMovementMethod());

    }

    public void addClick(View view){
        if (!db.isPresent(movie)){
            Toast.makeText(this, "Added to Favourites", Toast.LENGTH_SHORT).show();
            Bitmap posterBit = ((BitmapDrawable)poster.getDrawable()).getBitmap();
            movie.setPoster(posterBit);
            db.insertMovie(movie);
        }else {
            Toast.makeText(this, "Already in Favourites", Toast.LENGTH_SHORT).show();
        }
    }

    public void removeClick(View view){
        if (db.isPresent(movie)){
            Toast.makeText(this, "Removing", Toast.LENGTH_SHORT).show();
            db.deleteMovie(movie.getId());
        }else {
            Toast.makeText(this, "Movie not in Favourites", Toast.LENGTH_SHORT).show();
        }
    }
}
