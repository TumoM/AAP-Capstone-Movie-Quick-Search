package com.example.android.themoviecompanion;

import android.content.Context;

import com.example.android.themoviecompanion.DataBase.DatabaseHelper;
import com.example.android.themoviecompanion.Utils.Movie;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import androidx.test.core.app.ApplicationProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

//import androidx.test.core.app.ApplicationProvider;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "app/src/main/AndroidManifest.xml", sdk = 19)
public class DatabaseUnitTest {

    DatabaseHelper dbTest, dbTest2;
    Movie movie;
    Context context, context2;

    @Before
    public void setUp(){
        // context = RuntimeEnvironment.application;
        context = ApplicationProvider.getApplicationContext();
        context2 = RuntimeEnvironment.application;
//        dbTest  = new DatabaseHelper(context2);
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
    public void database_insert_init() throws Exception{
        assertNotNull(dbTest);
        dbTest.insertMovie(movie);
        assertEquals(1, dbTest.getMoviesCount());
    }

    @Test
    public void database_count_test() {
        assertTrue(dbTest.getAllFavouriteMovies().size() == 0);
    }

    @Test
    public void databse_present(){
        assertFalse(dbTest.isPresent(movie));
    }

    @After
    public void tearDown() {
        dbTest.clearDb();
    }
}