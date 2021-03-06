package com.example.android.themoviecompanion.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.android.themoviecompanion.Misc.MyBroadcastReceiver;
import com.example.android.themoviecompanion.R;

public class SearchActivity extends AppCompatActivity{
    private EditText searchET;
    private RadioGroup typeGroup;
    Boolean connected = false;
    public String SELECTION_TYPE;

    public void onSearchClick(View view) {
        if (connected)  // connected to the internet
        {
            if (searchET.getText().length() > 0) // checking for a valid length input
            {
                // Pulls data about media type from the radio buttons.
                int selectedCategory = typeGroup.getCheckedRadioButtonId();
                RadioButton typeSelect = (RadioButton) findViewById(selectedCategory);

                // Builds an intent that launches next Activity (Results), and adds the Search Term
                // and Media Type to the intent before launching.
                Intent intent = new Intent(this, ResultsActivity.class);
                intent.putExtra("Search", searchET.getText().toString());
                SELECTION_TYPE = typeSelect.getTag().toString();
                intent.putExtra("Type", typeSelect.getTag().toString());
                startActivity(intent);

            } else { // empty search box
                Toast.makeText(this, "Please enter a search term", Toast.LENGTH_SHORT).show();
                searchET.setText("");
            }
        }
        else{ // no network connection.
            Toast.makeText(this, "Please try again with network connectivity", Toast.LENGTH_SHORT).show();
        }
    }
    // An anonomys BroadcastReceiver that listens for the connection status from the MyBroadcastReceiver
    BroadcastReceiver connectionBroadcastReceiver =  new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Bundle b = intent.getExtras();
            connected = b != null && b.getBoolean("connected");
        }
    };


    // Broadcast Receiver to checks the wifi state on a status change.
    MyBroadcastReceiver networkReceiver = new MyBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Links components to variables
        Button searchBT = (Button) findViewById(R.id.searchButton);
        searchET = (EditText) findViewById(R.id.searchEditText);
        typeGroup = (RadioGroup) findViewById(R.id.typeRadioGroup);

        // Declares and intent filter for wifi and airplane mode changes.
        IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        this.registerReceiver(networkReceiver, filter);
        registerReceiver(connectionBroadcastReceiver, new IntentFilter("connectionStatus"));
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        this.unregisterReceiver(networkReceiver);
    }

    // Handles clicks to go to Favourites Activity
    public void favouriteClick(View view){
        Intent intent = new Intent(this, FavouritesActivity.class);
        startActivity(intent);
    }
}
