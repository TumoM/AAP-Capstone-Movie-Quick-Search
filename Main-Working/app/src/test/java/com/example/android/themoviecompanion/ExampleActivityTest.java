package com.example.android.themoviecompanion;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;

import com.example.android.themoviecompanion.Activities.FavouritesActivity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ExampleActivityTest {
    Activity activity;
    FloatingActionButton fab;

    @Before
    public void setup(){
        fab = activity.findViewById(R.id.favouritesFAB);


        int x = 100;
        assertEquals(100,x);
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