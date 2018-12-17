package com.example.android.themoviecompanion.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.themoviecompanion.Movie;
import com.example.android.themoviecompanion.R;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    private Movie movie;
    TextView title;
    TextView year;
    TextView plot;
    ImageView poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        title = (TextView) findViewById(R.id.movieTitle);
        year = (TextView) findViewById(R.id.movieYear);
        plot = (TextView) findViewById(R.id.plotText);
        poster = (ImageView) findViewById(R.id.movieImage);

        movie = (Movie) getIntent().getSerializableExtra("movie");
        title.setText(movie.getTitle());
        year.setText(movie.getYear().substring(14));
        plot.setText(movie.getOverview());
        Picasso.get().load(movie.getPosterPath()).placeholder(android.R.drawable.ic_btn_speak_now)
                .into(poster);
    }
}
