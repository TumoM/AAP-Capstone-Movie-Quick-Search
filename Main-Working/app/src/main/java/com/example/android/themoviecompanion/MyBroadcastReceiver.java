package com.example.android.themoviecompanion;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class MyBroadcastReceiver extends BroadcastReceiver {

    public boolean isOnline(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }


    @Override
    public void onReceive(Context context, Intent intent) {

        //Log.i("Network INFO", "YOLO");
        //Toast.makeText(context, "Yolo YOLOOOO", Toast.LENGTH_SHORT).show();
        // Creates a ConnectivityManager that is registered to the system "Connectivity" service.
        final ConnectivityManager networkManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo currentConnection;

        Log.i("Test Tag", "Testing if network is available");
        // Checks if network is available
        if (isOnline(context)) {
            currentConnection = networkManager.getActiveNetworkInfo();
            String type = currentConnection.getTypeName();
            Toast.makeText(context, type +" is available", Toast.LENGTH_LONG).show();
            Log.i("NETWORK INFO", type);
        } else {
            Log.i("NETWORK INFO", "Network Unavailable");
            Toast.makeText(context, "Network is unavailable", Toast.LENGTH_LONG).show();
        }
    }
}
