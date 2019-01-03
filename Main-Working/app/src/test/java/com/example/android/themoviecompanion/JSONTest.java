package com.example.android.themoviecompanion;

import com.example.android.themoviecompanion.Activities.ResultsActivity;
import com.example.android.themoviecompanion.Utils.Movie;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.example.android.themoviecompanion.Utils.JSONHelper.getJSON;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

// @RunWith(MockitoJUnitRunner.class)
public class JSONTest {

    private ArrayList<Movie> movieList;
    private String search = "Batman";
    private String[] inputs = {search, "Movie"};

    @Before
    public void setUp(){
        movieList = new ArrayList<>();
    }

    // Tests that the JSONHelper Utility class returns some data, in the appropriate format.
    @Test
    public void json_download() {
        assertNotNull(movieList);
        assertEquals(0,movieList.size());
        new ResultsActivity.Viewdata().execute(inputs);
        movieList.addAll(getJSON(search, "Movie"));
        assertEquals(20,movieList.size());
        movieList.clear();
        movieList.addAll(getJSON(search, "TV"));
        assertTrue(movieList.size() > 0);


    }
}