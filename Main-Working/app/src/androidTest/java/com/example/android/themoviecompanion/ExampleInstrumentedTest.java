package com.example.android.themoviecompanion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.android.themoviecompanion.Activities.FavouritesActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.android.themoviecompanion", appContext.getPackageName());
    }
    Activity activity;
    FloatingActionButton fab;

    @Before
    public void setup(){
        fab = activity.findViewById(R.id.favouritesFAB);
    }
    // @Test
    public void clickingLogin_shouldStartLoginActivity() {
        // SearchActivity activity = Robolectric.setupActivity(SearchActivity.class);


        Intent expectedIntent = new Intent(activity, FavouritesActivity.class);
        //Intent actual = shadowOf(activity).getNextStartedActivity();
        //assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull( activity );
    }
}
