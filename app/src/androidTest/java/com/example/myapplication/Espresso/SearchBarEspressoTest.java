package com.example.myapplication.Espresso;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

import android.content.Intent;
import android.view.View;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchBarEspressoTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() {
        Intents.init();
        Intent startIntent = new Intent(ApplicationProvider.getApplicationContext(), MainActivity.class);
        startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Important when starting from outside of an Activity context
        ApplicationProvider.getApplicationContext().startActivity(startIntent);
    }

    @Test
    public void testSearchBarFiltering() {
        onView(isRoot()).perform(waitFor(5000));

        onView(withId(R.id.search)).perform(click());
        onView(withId(R.id.search)).perform(typeText("Banana"));

        // Implement a custom Matcher to check items in RecyclerView
        // Example assumes that RecyclerView items contain TextViews with IDs or text that can be matched
        onView(withId(R.id.recyclerView))
                .check(matches(RecyclerViewMatcher.withItemText(containsString("Banana"))));

        onView(withId(R.id.search)).perform(clearText());

        onView(withId(R.id.search)).perform(click());
        onView(withId(R.id.search)).perform(typeText("Apple"));

        onView(withId(R.id.recyclerView))
                .check(matches(RecyclerViewMatcher.withItemText(containsString("Apple"))))
                .check(matches(not(RecyclerViewMatcher.withItemText(containsString("Banana")))))
                .check(matches(not(RecyclerViewMatcher.withItemText(containsString("Cherry")))));
    }

    private static ViewAction waitFor(final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isDisplayed();
            }

            @Override
            public String getDescription() {
                return "wait for " + millis + " milliseconds";
            }

            @Override
            public void perform(UiController uiController, View view) {
                uiController.loopMainThreadForAtLeast(millis);
            }
        };
    }

    @After
    public void tearDown() {
        Intents.release(); // Clean up intents monitoring after each test
    }
}
