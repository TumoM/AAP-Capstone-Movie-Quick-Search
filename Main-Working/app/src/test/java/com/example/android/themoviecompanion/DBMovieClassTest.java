package com.example.android.themoviecompanion;

import com.example.android.themoviecompanion.DataBase.DbMovie;
import com.example.android.themoviecompanion.Utils.Movie;

import org.junit.Before;
import org.junit.Test;

import static com.example.android.themoviecompanion.MovieClassTest.testPlot;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/*
* A unit test for the DBMovie Class.
**/
public class DBMovieClassTest {

    private DbMovie dbMovie;
    private Movie movie;

    // Sets up a Movie object for future testing.
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

        dbMovie = new DbMovie();

    }

    // Tests that the DbMovie object was created, with fields at expected null/0 values
    @Test
    public void testDbMovieObjectCreated(){
        dbMovie = new DbMovie();
        assertNotNull(dbMovie);
        assertEquals(0, dbMovie.getId());
        assertNull(dbMovie.getTitle());
        assertNull(dbMovie.getYear());
        assertNull(dbMovie.getPlot());
        assertNull(dbMovie.getType());
        assertNull(dbMovie.getTitle());
    }

    // Tests the constructor taking in a Movie Object parameter.
    @Test
    public void testDbMovieObjectConstructor(){
        dbMovie = new DbMovie(movie);
        assertNotNull(dbMovie);
        assertEquals(9999, dbMovie.getId());
        assertEquals("Batman Test Case", dbMovie.getTitle());
        assertEquals("3018-12-1", dbMovie.getYear());
        assertEquals("Movie", dbMovie.getType());
        assertEquals(testPlot, dbMovie.getPlot());
        assertNull(dbMovie.getPoster());
    }

    // Tests the constructor taking in parameters for each local field.
    @Test
    public void testDbMovieObjectConstructor2(){
        DbMovie dbMovie = new DbMovie(0000,"Batman 2nd Test Case","4018-12-1",
                testPlot + " 2nd", null,"TV");
        assertEquals(0000, dbMovie.getId());
        assertEquals("Batman 2nd Test Case", dbMovie.getTitle());
        assertEquals("4018-12-1", dbMovie.getYear());
        assertEquals("TV", dbMovie.getType());
        assertEquals(testPlot  + " 2nd", dbMovie.getPlot());
        assertNull(dbMovie.getPoster());
    }

    // Tests the setter methods.
    @Test
    public void testDbMovieObjectSetters(){
        DbMovie dbMovie = new DbMovie();
        assertNull(dbMovie);
        dbMovie.setId(0020);
        dbMovie.setTitle("Batman 3rd Test Case");
        dbMovie.setYear("2018");
        dbMovie.setPlot("A B C D E F G");
        dbMovie.setType("TV");
        dbMovie.setPoster(null);

        assertEquals(0020, dbMovie.getId());
        assertEquals("Batman 3rd Test Case", dbMovie.getTitle());
        assertEquals("2018", dbMovie.getYear());
        assertEquals("TV", dbMovie.getType());
        assertEquals("A B C D E F G", dbMovie.getPlot());
        assertNull(dbMovie.getPoster());
    }


    // Tests the conversion method from a DbMovie object into a Movie object.
    @Test
    public void testDbMovieToMovie(){
        DbMovie dbMovie = new DbMovie(movie);
        Movie movie2 = DbMovie.toMovie(dbMovie);

        assertNotNull(movie2);
        assertEquals(9999, movie2.getId());
        assertEquals("Batman Test Case", movie2.getTitle());
        assertEquals("3018-12-1", movie2.getYear());
        assertEquals("Movie", movie2.getType());
        assertEquals(testPlot, movie2.getPlot());
        assertNull(movie2.getPosterPath());
        assertNull(movie2.getPoster());
    }
}
