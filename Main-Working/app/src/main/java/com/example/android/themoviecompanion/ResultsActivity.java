package com.example.android.themoviecompanion;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class ResultsActivity extends AppCompatActivity {
//    String[] myDataset = {"1","2","3","4","5","6","7"};
    // Declares the RecyclerView object, as well as an Adapter and Layout Manager
    // to handle list updating and row layout respectively.
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager recyclerLayoutManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        recyclerView = (RecyclerView) findViewById(R.id.displayRV);
        recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerLayoutManager);

        /*final int capacity = 100;
        // fills a dummy array for testing
        ArrayList<String> myDataset = new ArrayList<>(100);
        for (int i = 0; i < capacity; i++){
            myDataset.add(Integer.toString(i+1));
        }*/

        recyclerView.setHasFixedSize(true);

      /*  recyclerAdapter = new MovieRecyclerViewAdapter(myDataset);
        recyclerView.setAdapter(recyclerAdapter);*/



    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }
}
