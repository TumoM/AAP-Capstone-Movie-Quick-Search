package com.example.android.themoviecompanion.Misc;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.themoviecompanion.Activities.DetailsActivity;
import com.example.android.themoviecompanion.R;
import com.example.android.themoviecompanion.Utils.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {
    private List<Movie> movieList;
    private Context context;

    // Provide a suitable constructor (depends on the kind of dataset)
    public MovieRecyclerViewAdapter(Context context, List<Movie> movies) {
        this.context = context;
        movieList = movies;
    }



    // Creates the method that will create and return the new view based on the layout file supplied.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View resultsView = inflater.inflate(R.layout.movie_display, parent, false);


        // Return a new holder instance using custom ViewHolder Class
        return new ViewHolder(resultsView, context);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        if (holder instanceof RecyclerView.ViewHolder) {
            RecyclerView.ViewHolder rowHolder = (RecyclerView.ViewHolder) holder;
            Movie movie = movieList.get(position);
            String posterURI = movie.getPosterPath();
            holder.mTitle.setText(movie.getTitle());
            holder.mYear.setText(movie.getYear());
            Picasso.get().load(posterURI).placeholder(android.R.drawable.ic_btn_speak_now)
            .into(holder.mPoster);
            holder.mCatagory.setText(movie.getType());

        }


    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (movieList == null){return 0;}
        else {return movieList.size();}
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mTitle, mYear, mCatagory;
        public ImageView mPoster;

        ViewHolder(View v, final Context nextContext) {
            super(v);
            context = nextContext;
            mTitle = (TextView) v.findViewById(R.id.movieTitle);
            mYear = (TextView) v.findViewById(R.id.releaseYear);
            mCatagory = (TextView) v.findViewById(R.id.movieCatoagory);
            mPoster = (ImageView) v.findViewById(R.id.posterImage);

            // mListener = listener;
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Movie movie = movieList.get(getAdapterPosition());

                    Intent intent = new Intent(v.getContext(), DetailsActivity.class);

                    intent.putExtra("movie", movie);
                    context.startActivity(intent);

                }
            });

        }

        @Override
        public void onClick(View v) {

        }
    }
}