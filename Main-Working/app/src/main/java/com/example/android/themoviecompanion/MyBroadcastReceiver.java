package com.example.android.themoviecompanion;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class MyBroadcastReceiver extends BroadcastReceiver {



    static boolean flag = false;
    static public boolean getFlag(){return flag;}


    public boolean isOnline(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        final ConnectivityManager networkManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo currentConnection;
        boolean connected = isOnline(context);

        // Creates a bundle and intent that are used to pass data back to the search activity.
        Bundle extras = intent.getExtras();
        Intent i = new Intent("connectionStatus");
        i.putExtra("connected", connected);
        context.sendBroadcast(i);

        Log.i("Test Tag", "Testing if network is available");
        // Checks if network is available
        if (connected) {
            currentConnection = networkManager.getActiveNetworkInfo();
            String type = currentConnection.getTypeName();
            flag = true;
            Log.i("NETWORK INFO", type);
        } else {
            Log.i("NETWORK INFO", "Network Unavailable");
            flag = false;
            Toast.makeText(context, "Network is unavailable", Toast.LENGTH_LONG).show();
        }
    }
}
