package com.example.android.themoviecompanion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private ArrayList<String> mDataset;
    private RecyclerViewClickListener mListener;



    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        TextView mTextView;
        MyViewHolder(View v, RecyclerViewClickListener listener) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.titleTextView);
            mListener = listener;
            v.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    RecyclerAdapter(ArrayList<String> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                           int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // create a new view
        // Inflate the custom layout
        View resultsView = inflater.inflate(R.layout.display_item, parent, false);

        // Return a new holder instance
        return new MyViewHolder(resultsView, mListener);
        /*TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.display_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;*/
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        if (holder instanceof MyViewHolder) {
            MyViewHolder rowHolder = (MyViewHolder) holder;
            String title = mDataset.get(position);
            TextView textView = holder.mTextView;
            textView.setText(title);
            //set values of data here
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}