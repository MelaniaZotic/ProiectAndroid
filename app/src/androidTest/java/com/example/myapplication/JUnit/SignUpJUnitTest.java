package com.example.myapplication.JUnit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.R;
import com.example.myapplication.SignUpActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SignUpJUnitTest {

    @Rule
    public ActivityScenarioRule<SignUpActivity> activityScenarioRule = new ActivityScenarioRule<>(SignUpActivity.class);

    @Test
    public void testFieldsDisplayed() {
        onView(withId(R.id.signup_email)).check(matches(isDisplayed()));
        onView(withId(R.id.signup_password)).check(matches(isDisplayed()));
        onView(withId(R.id.signup_button)).check(matches(isDisplayed()));
    }

    @Test
    public void testEmptyEmailAndPassword() {
        onView(withId(R.id.signup_email)).perform(typeText(""));
        onView(withId(R.id.signup_password)).perform(typeText(""));
        onView(withId(R.id.signup_button)).perform(click());
        onView(withId(R.id.signup_email)).check(matches(hasErrorText("Email cannot be empty")));
        onView(withId(R.id.signup_password)).check(matches(hasErrorText("Password cannot be empty")));
    }

    @Test
    public void testValidEmailAndPassword() {
        // Assumes that the network request to Firebase is mocked or has a testing environment set up.
        onView(withId(R.id.signup_email)).perform(typeText("testju@example.com"));
        onView(withId(R.id.signup_password)).perform(typeText("password321"));
        onView(withId(R.id.signup_button)).perform(click());
        intended(hasComponent(LoginActivity.class.getName()));
        //intending
    }

    // More tests can be added here
}
