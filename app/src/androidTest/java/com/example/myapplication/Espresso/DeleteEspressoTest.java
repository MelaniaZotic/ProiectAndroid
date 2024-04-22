package com.example.myapplication.Espresso;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Intent;
import android.os.Bundle;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.myapplication.DetailActivity;
import com.example.myapplication.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DeleteEspressoTest {

    @Rule
    public ActivityScenarioRule<DetailActivity> activityScenarioRule = new ActivityScenarioRule<>(DetailActivity.class);

    @Before
    public void setUp() {
        // Initialize intents monitoring before each test
        Intents.init();

        // Correctly launch the activity with required extras if any
        Intent startIntent = new Intent(ApplicationProvider.getApplicationContext(), DetailActivity.class);
        Bundle extras = new Bundle();
        // Add necessary extras
        extras.putString("Key", "exampleKey"); // Example key
        startIntent.putExtras(extras);
        startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Needed when starting an activity from outside of an Activity context
        ApplicationProvider.getApplicationContext().startActivity(startIntent);
    }

    @Test
    public void testDeleteBudget() {
        // Simulate clicking the FloatingActionMenu to open it
        onView(withId(R.id.fab_menu)).perform(click());

        // Add a short delay to allow the menu to open and the button to become visible
        // You can adjust the duration if needed
        try {
            Thread.sleep(300); // Adjust as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check that the delete button has become visible
        onView(withId(R.id.deleteButton)).check(matches(isDisplayed()));

        // Now, perform the click on the delete button
        onView(withId(R.id.deleteButton)).perform(click());

        // Assuming deletion brings you back to a main activity or changes the UI significantly
        onView(withId(R.id.detail)).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() {
        Intents.release(); // Clean up intents monitoring after each test
    }
}