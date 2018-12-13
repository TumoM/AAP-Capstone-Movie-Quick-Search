package com.example.android.themoviecompanion;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity{

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    // Broadcast Receiver to checks the wifi state on a status change.
    MyBroadcastReceiver networkReciever = new MyBroadcastReceiver();
//    {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Log.i("DEV INFO",intent.getExtras().toString());
//            Toast.makeText(SearchActivity.this, intent.getExtras().toString(), Toast.LENGTH_SHORT).show();
//            //Toast.makeText(SearchActivity.this, "We are registered fam", Toast.LENGTH_SHORT).show();
//        }
//    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Log.i("Start", "---------------------------------------------------------------------");
        // Declares and intent filter for wifi and airplane mode changes.
        IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        this.registerReceiver(networkReciever, filter);

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
