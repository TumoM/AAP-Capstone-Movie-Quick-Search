package com.example.android.themoviecompanion;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.ContentValues.TAG;

public class Utils {
    public static void testHTTP(){
        try {
            Log.i("SWAG", "testHTTP: ");
            URL url = new URL("https://api.themoviedb.org/3/movie/550?api_key=eeab5da6854350c8bf390f554ae7f997");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
           // conn.setRequestProperty("api_key", "eeab5da6854350c8bf390f554ae7f997");


            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            else {
                Log.i(TAG, "Good Connection made");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                Log.i("testHTTP: ", output);
            }

            conn.disconnect();

        } catch (IOException e) {

            e.printStackTrace();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }


}
