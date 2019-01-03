package com.example.android.themoviecompanion.Misc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

/*
 *  This is a broadcast receiver class that is used to notify and handle network change requests to the app.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
    static boolean flag = false;

    static public boolean getFlag(){return flag;}

    /*
    *  A utility method that returns true if the device is connected to network (Wifi or Mobile)
    */
    public boolean isOnline(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connMgr != null;
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected()); // True if not null and network is connected.
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
        context.sendBroadcast(i); // Returns the update of network change to the registered receivers.

        // Checks if network is available
        if (connected) {
            assert networkManager != null;
            currentConnection = networkManager.getActiveNetworkInfo();
            String type = currentConnection.getTypeName();
            flag = true;
        } else { // If not connected
            flag = false;
            Toast.makeText(context, "Network is unavailable", Toast.LENGTH_LONG).show();
        }
    }
}
