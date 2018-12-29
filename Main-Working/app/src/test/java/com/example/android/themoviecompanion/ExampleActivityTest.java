package com.example.android.themoviecompanion;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;

import com.example.android.themoviecompanion.Activities.FavouritesActivity;
import com.example.android.themoviecompanion.Activities.SearchActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

// import org.robolectric.RobolectricGradleTestRunner;

@RunWith(RobolectricTestRunner.class)
//@Config(constants = BuildConfig.class, sdk = 19, packageName = "package com.example.android.themoviecompanion;")
public class ExampleActivityTest {
    Activity activity;
    ActivityController controller;
    FloatingActionButton fab;

    @Before
    public void setup(){
        activity = Robolectric.buildActivity(SearchActivity.class).create().get();
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