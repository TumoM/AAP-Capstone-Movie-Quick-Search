package com.example.android.themoviecompanion;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static java.security.AccessController.getContext;

public class SearchActivity extends AppCompatActivity{
    private EditText searchET;
    private Button searchBT;
    private RadioGroup typeGroup;
    Boolean connected = false;

    public void setConnected(Boolean connected) {
        this.connected = connected;
    }



    public void onSearchClick(View view){
        if (connected) {
            /*Intent intent = new Intent(this, ResultsActivity.class);
            startActivity(intent);*/
            // Starts the JobIntentService
            Log.i("SWAG WARNING", "About to start service");
            Intent mServiceIntent = new Intent(this, DownloadService2.class);
            startService(new Intent(this, DownloadService2.class));

        } else {
            Toast.makeText(this, "Please try again whith network connectivity", Toast.LENGTH_SHORT).show();
        }


    }

    // An anonomys BroadcastReciever that listens for the connection status from the MyBroadcastReceiver
    BroadcastReceiver connectionBroadcastReceiver =  new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Bundle b = intent.getExtras();
            connected = b.getBoolean("connected");
            Log.i("TUMO LOG: ", "" + connected);
        }
    };


    // Broadcast Receiver to checks the wifi state on a status change.
    MyBroadcastReceiver networkReciever = new MyBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Links components to variables
        searchBT = (Button) findViewById(R.id.searchButton);
        searchET = (EditText) findViewById(R.id.searchEditText);
        typeGroup = (RadioGroup) findViewById(R.id.typeRadioGroup);
        // Declares and intent filter for wifi and airplane mode changes.
        IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        this.registerReceiver(networkReciever, filter);
        registerReceiver(connectionBroadcastReceiver, new IntentFilter("connectionStatus"));

        Utils.testHTTP();


    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        this.unregisterReceiver(networkReciever);
    }

    //
    // Listerners
    //

    // Handles clicks to go to Favourites Activity
    public void favouriteClick(View view){
        // TODO 1 Implement favouriteClick() method
        Toast.makeText(this, "Going to favourites", Toast.LENGTH_SHORT).show();
    }
}
