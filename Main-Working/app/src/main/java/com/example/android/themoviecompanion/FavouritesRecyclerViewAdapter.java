package com.example.android.themoviecompanion;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.themoviecompanion.Activities.DetailsActivity;
import com.example.android.themoviecompanion.DataBase.DbMovie;
import com.example.android.themoviecompanion.Utils.DbBitmapUtility;

import java.util.List;

public class FavouritesRecyclerViewAdapter extends RecyclerView.Adapter<FavouritesRecyclerViewAdapter.ViewHolder> {
    private List<DbMovie> movieList;
    private RecyclerViewClickListener mListener;
    private Context context;

    // Provide a suitable constructor (depends on the kind of dataset)
    public FavouritesRecyclerViewAdapter(Context context, List<DbMovie> movies) {
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
            DbMovie movie = movieList.get(position);
            byte[] posterURI = movie.getImg();
            Bitmap poster = DbBitmapUtility.getImage(posterURI);
            holder.mTitle.setText(movie.getTitle());
            //holder.mCatagory.setText(movie.getCategory());
            holder.mYear.setText(movie.getYear());
            holder.mPoster.setImageBitmap(poster);
            //textView.setText(title);
            //set values of data here

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

                    DbMovie movie = movieList.get(getAdapterPosition());

                    Intent intent = new Intent(v.getContext(), DetailsActivity.class);

                    intent.putExtra("movie", movie);
                    context.startActivity(intent);

                }
            });

        }
        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }
}