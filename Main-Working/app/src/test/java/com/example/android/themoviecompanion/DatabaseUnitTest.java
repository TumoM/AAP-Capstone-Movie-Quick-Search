package com.example.android.themoviecompanion;

import android.content.Context;

import com.example.android.themoviecompanion.DataBase.DatabaseHelper;
import com.example.android.themoviecompanion.Utils.Movie;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import static org.junit.Assert.assertNotNull;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class DatabaseUnitTest {

    DatabaseHelper dbTest;
    Movie movie;
    Context context;

    @Before
    public void setUp(){
        context = RuntimeEnvironment.application;
        dbTest  = new DatabaseHelper(context);
        movie = new Movie();
        movie.setId(9999);
        movie.setTitle("Batman Test Case");
        movie.setYear("3018-12-1");
        movie.setPlot("This is a test plot for test purposes");
        movie.setType("Movie");
        movie.setPosterPath("/adw6Lq9FiC9zjYEpOqfq03ituwp.jpg");
        movie.setPoster(null);

    }

    @Test
    public void database_helper_init() {
        assertNotNull(dbTest);
        dbTest.insertMovie(movie);
        // assertEquals(1, dbTest.getMoviesCount());
    }

    @After
    public void tearDown() {
        int x = 0;
    }
}