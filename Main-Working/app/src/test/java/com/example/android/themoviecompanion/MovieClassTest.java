package com.example.android.themoviecompanion;

import android.app.Activity;
import android.graphics.Bitmap;

import com.example.android.themoviecompanion.Utils.Movie;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


public class MovieClassTest extends Activity {

    Movie movie;
    public static String testPlot = "This is a test plot for test purposes";
    public static String testPath = "/adw6Lq9FiC9zjYEpOqfq03ituwp.jpg";
    private Bitmap poster;

    @Before
    public void setUp(){
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
    public void testMovieObjectCreated(){
        movie = new Movie();
        assertNotNull(movie);
        assertTrue(movie.getId() == 0);
        assertNull(movie.getTitle());
        assertNull(movie.getYear());
        assertNull(movie.getPlot());
        assertNull(movie.getPosterPath());
        assertNull(movie.getType());
        assertNull(movie.getTitle());
        assertNull(movie.getPoster());
    }
    @Test
    public void testMovieObjectMethods(){
        assertNotNull(movie);
        assertEquals(9999, movie.getId());
        assertEquals("Batman Test Case", movie.getTitle());
        assertEquals("3018-12-1", movie.getYear());
        assertEquals("Movie", movie.getType());
        assertEquals(testPlot, movie.getPlot());
        assertEquals(testPath, movie.getPosterPath());
        assertNull(movie.getPoster());

    }
}
