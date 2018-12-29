package com.example.android.themoviecompanion;

import android.content.Context;

import com.example.android.themoviecompanion.Activities.ResultsActivity;
import com.example.android.themoviecompanion.Utils.JSONhelper;
import com.example.android.themoviecompanion.Utils.Movie;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.example.android.themoviecompanion.Utils.JSONhelper.getJSON;
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

    ArrayList<Movie> movieList;
    Context context;

    @Before
    public void setUp(){
        movieList = new ArrayList<>();
        JSONhelper jsonHelper = new JSONhelper();
        //String search = "Batman";
        //movieList.addAll(jsonHelper.getJSON(search, "Movie"));
    }

    @Test
    public void database_helper_init() {
        assertNotNull(movieList);
        assertEquals(0,movieList.size());
        String search = "Batman";
        String[] inputs = {search, "Movie"};
        new ResultsActivity.Viewdata().execute(inputs);
        JSONhelper jsonHelper = new JSONhelper();
        movieList.addAll(getJSON(search, "Movie"));
        assertEquals(20,movieList.size());
        movieList.clear();
        movieList.addAll(getJSON(search, "TV"));
        assertTrue(movieList.size() > 0);


    }

    @After
    public void tearDown() {
        int x = 0;
    }
}