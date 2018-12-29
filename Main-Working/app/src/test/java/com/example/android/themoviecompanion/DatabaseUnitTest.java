package com.example.android.themoviecompanion;

import android.content.Context;

import com.example.android.themoviecompanion.DataBase.DatabaseHelper;
import com.example.android.themoviecompanion.Utils.Movie;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

//import androidx.test.core.app.ApplicationProvider;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(MockitoJUnitRunner.class)
public class DatabaseUnitTest {


    Movie movie;
    Context context2;

    @Mock
    private DatabaseHelper dbTest;

    @Mock
    private Context context, newContext, mContext;



    @Before
    public void setUp() throws Exception{
        // context = RuntimeEnvironment.application;

//        dbTest  = new DatabaseHelper(context2);
        mContext = Mockito.mock(Context.class);
        Mockito.when(mContext.getApplicationContext()).thenReturn(mContext);
        newContext = InstrumentationRegistry.getInstrumentation().getContext();
        context = mock(Context.class);
        dbTest = new DatabaseHelper(newContext);
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
        assertEquals(0, dbTest.getAllFavouriteMovies().size());
        dbTest.insertMovie(movie);
        assertEquals(1, dbTest.getAllFavouriteMovies().size());
    }

    @Test
    public void databse_present(){
        assertFalse(dbTest.isPresent(movie));
        dbTest.insertMovie(movie);
        assertTrue(dbTest.isPresent(movie));
    }

    @After
    public void tearDown() {
        dbTest.clearDb();
    }
}