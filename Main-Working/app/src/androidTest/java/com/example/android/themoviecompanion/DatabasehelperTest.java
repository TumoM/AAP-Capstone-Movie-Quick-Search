package com.example.android.themoviecompanion;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.android.themoviecompanion.DataBase.DatabaseHelper;
import com.example.android.themoviecompanion.Utils.Movie;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DatabasehelperTest {

    DatabaseHelper dbTest;
    Movie movie;
    Context context;

    @Before
    public void setUp(){
        context = InstrumentationRegistry.getTargetContext();
        dbTest  = new DatabaseHelper(context);
        movie = new Movie();
        movie.setId(999);
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
        assertEquals(0, dbTest.getMoviesCount());
        dbTest.insertMovie(movie);
        assertEquals(1, dbTest.getMoviesCount());
    }

    @After
    public void tearDown() {
        dbTest.clearDb();
    }

}