package com.example.android.themoviecompanion.Utils;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.themoviecompanion.DataBase.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DownloadService3 extends IntentService {

    public DownloadService3(){
        super(new String("test"));
    }

    // eg https://api.themoviedb.org/3/movie/550?api_key=eeab5da6854350c8bf390f554ae7f997
    final String urlString = "https://api.themoviedb.org/3/movie/550?api_key=";
    final String KEY = "eeab5da6854350c8bf390f554ae7f997";
    private List<Movie> movieList;
    private RequestQueue queue;
    // eg https://api.themoviedb.org/3/search/movie?api_key=eeab5da6854350c8bf390f554ae7f997&query=Jack+Reacher

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DownloadService3(String name) {
        super(name);
    }

    public void downloadString(){
        // Instantiate the RequestQueue.
        queue  = Volley.newRequestQueue(getApplicationContext());

        getMovies("Jack");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e("SWAGGER", "Entered DLS2");
        //String urlString = (intent.getExtras()).getString("URL");
        //Todo 1 Impliment the functionality to turn this string into the download request
        downloadString();

    }
    public List<Movie> getMovies(String searchTerm){
        Log.d("CHECK-IN", "Running getMovies");
        movieList.clear();
        String url = HTTPConstants.baseURLSerach + searchTerm;
        Log.i("Search URL", url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray moviesArray = response.getJSONArray("results");
                    for (int i = 0; i < moviesArray.length(); i++) {
                        JSONObject movieObj = moviesArray.getJSONObject(i);
                        Movie movie = new Movie();
                        movie.setTitle(movieObj.getString("title"));
                        movie.setYear(movieObj.getString("release_date"));
                        movie.setPoster(movieObj.getString("poster_path"));
                        movie.setOverview(movieObj.getString("overview"));

                        Log.d("Movies: ", movie.getTitle()); }
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(jsonObjectRequest);
        return movieList;
    }
}
//    URL url = new URL(urlString);
//    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//   try {
//        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//        readStream(in);
//    } finally {
//        urlConnection.disconnect();
//    }

