package com.example.android.themoviecompanion.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class JSONhelper{

    private static ArrayList<Movie> data;


    public static ArrayList<Movie> getJSON(String search, String type){

        try{
            String baseURL;
            // Checks if the user is searching for a Movie entry or TV show.
            if (type.equals("Movie")){
                baseURL = HTTPConstants.baseURLSerachMovie;
            }
            else{
                baseURL = HTTPConstants.baseURLSerachTV;
            }

            URL url1 = new URL(baseURL + search);
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            connection.connect();
            InputStream inputStream= connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            StringBuilder buffer = new StringBuilder();
            while ((line=reader.readLine())!=null){
                buffer.append(line);
                String result = buffer.toString();
                JSONObject tempObj = new JSONObject(result);

                // Checks if any results were found. i.e more than 0
                if ((int)tempObj.get("total_results") > 0) {
                    // Creates an array holding all the results found.
                    JSONArray jsonArray = (JSONArray) tempObj.get("results");
                    data = new ArrayList<>();

                    //
                    for (int i = 0; i < jsonArray.length(); i++) {
                        data.add(parseJSONArray(jsonArray, type, i));
                    }
                    }
                }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    /** Takes in a JSONArray and parses it to assign the correct metadata to a new movie object, returning it.
     * @param jsonArray The Array containing the media queries from the API
     * @param type Movie or TV Show
     * @return A movie object
     */
    private static Movie parseJSONArray(JSONArray jsonArray, String type, int i) throws JSONException {
        // Creates a new movie item, and then rips the appropriate JSON Data
        Movie movie = new Movie();
        // Parses each index in the array to store the relevant data.
            JSONObject movieObj = jsonArray.getJSONObject(i);

            if (type.equals("Movie")) { // Type == Movie
                movie.setTitle(movieObj.getString("title"));
                movie.setYear(movieObj.getString("release_date"));

            } else {  // Type == TV Show
                movie.setTitle(movieObj.getString("name"));
                movie.setYear(movieObj.getString("first_air_date"));
            }
            // These are the same for both Movies and TV Shows
            movie.setId(movieObj.getInt("id"));
            movie.setPlot(movieObj.getString("overview"));
            movie.setPosterPath(HTTPConstants.baseImageURL + movieObj.getString("poster_path"));
            movie.setFavourite(false);
            movie.setType(type);

        return movie;
    }
}