package com.example.android.themoviecompanion.Utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class JSONhelper{

    static HttpURLConnection connection;
    static ArrayList<Movie> data;


    public static ArrayList<Movie> getJSON(String search){
        try{
            URL url1 = new URL(HTTPConstants.baseURLSerach + search);

            connection = (HttpURLConnection) url1.openConnection();
            connection.connect();
            InputStream inputStream= connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            StringBuffer buffer = new StringBuffer();
            while ((line=reader.readLine())!=null){

                buffer.append(line);
                String result = buffer.toString();
                JSONObject tempObj = new JSONObject(result);
                JSONArray jsonArray = (JSONArray) tempObj.get("results");
                data = new ArrayList<>();

                for (int i =0; i<jsonArray.length();i++){

                    JSONObject movieObj = jsonArray.getJSONObject(i);

                    // Creates a new movie item, and then rips the appropriate JSON Data
                    Movie movie = new Movie();
                    movie.setTitle(movieObj.getString("title"));
                    movie.setYear("Year Released: " + movieObj.getString("release_date"));
                    //.substring(0,4));
                    movie.setPoster(HTTPConstants.baseImageURL + movieObj.getString("poster_path"));
                    movie.setOverview(movieObj.getString("overview"));

                    Log.d("DEBUG", "Movie Added: "+movie.getTitle());
                    data.add(movie);

                }


            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("DEBUG", "getJSON: Data Returned");
        return data;
    }

}