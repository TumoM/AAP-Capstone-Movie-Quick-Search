package com.example.android.themoviecompanion;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private String[] mDataset; // The array that stores each data item

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.display_item, parent, false);
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder((TextView)layoutInflater.inflate(R.layout.display_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mTextView.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public MyViewHolder(TextView text){
            super(text);
            mTextView = text;
        }

    }

    public RecyclerAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

}
