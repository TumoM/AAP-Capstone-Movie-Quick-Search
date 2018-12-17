package com.example.android.themoviecompanion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {
    private List<Movie> movieList;
    private RecyclerViewClickListener mListener;
    private Context context;

    // Provide a suitable constructor (depends on the kind of dataset)
    MovieRecyclerViewAdapter(Context context, List<Movie> movies) {
        this.context = context;
        movieList = movies;
    }



    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // create a new view
        // Inflate the custom layout
        View resultsView = inflater.inflate(R.layout.display_item, parent, false);

        // Return a new holder instance
        return new ViewHolder(resultsView);
        /*TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.display_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;*/
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        if (holder instanceof ViewHolder) {
            ViewHolder rowHolder = (ViewHolder) holder;
            Movie title = movieList.get(position);
            TextView textView = holder.mTitle;
            //textView.setText(title);
            //set values of data here

        }

    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mTitle, mYear, mCatagory;
        ImageView mPoster;

        ViewHolder(View v) {
            super(v);
            mTitle = (TextView) v.findViewById(R.id.movieTitle);
            mYear = (TextView) v.findViewById(R.id.releaseYear);
            mCatagory = (TextView) v.findViewById(R.id.movieCatoagory);
            mPoster = (ImageView) v.findViewById(R.id.posterImage);

            // mListener = listener;
//            v.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }
}