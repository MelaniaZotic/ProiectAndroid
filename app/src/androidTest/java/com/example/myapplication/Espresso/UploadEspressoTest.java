package com.example.myapplication.Espresso;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.widget.DatePicker;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.myapplication.R;
import com.example.myapplication.UploadActivity;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

@RunWith(AndroidJUnit4.class)
public class UploadEspressoTest {

    @Rule
    public ActivityScenarioRule<UploadActivity> activityScenarioRule = new ActivityScenarioRule<>(UploadActivity.class);

    @Before
    public void setUp() {
        Intents.init();  // Inițializează Intents înainte de fiecare test
    }

    @After
    public void tearDown() {
        Intents.release();  // Eliberează Intents după fiecare test
    }

    @Test
    public void testSelectImage() {
        // Prepară un rezultat de intent cu o imagine mock
        Uri imageUri = Uri.parse("file:///sdcard/Download/food.png");

        Intent resultData = new Intent();
        resultData.setData(imageUri);
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

        // Intenționăm să răspundem cu acest rezultat când se lansează un picker de imagini
        intending(not(isInternal())).respondWith(result);

        // Simulează click pe ImageView pentru a lansa picker-ul de imagini
        onView(withId(R.id.uploadImage)).perform(click());

    }

    @Test
    public void testFillTitle() {
        onView(withId(R.id.uploadTitle)).perform(scrollTo(), click(), replaceText("TestTitle"));
        onView(withId(R.id.uploadTitle)).check(matches(withText("TestTitle")));
    }

    @Test
    public void testFillDescription() {
        onView(withId(R.id.uploadDesc)).perform(scrollTo(), click(), replaceText("Test Description"));
        onView(withId(R.id.uploadDesc)).check(matches(withText("Test Description")));
    }

    @Test
    public void testFillBudget() {
        onView(withId(R.id.uploadBudg)).perform(scrollTo(), click(), replaceText("1000"));
        onView(withId(R.id.uploadBudg)).check(matches(withText("1000")));
    }

    @Test
    public void testSelectTodayDate() {
        // Deschide dialogul DatePicker
        Espresso.onView(withId(R.id.buttonDate))
                .perform(ViewActions.click());

        // Selectează data curentă în dialogul DatePicker
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        // Setează data în DatePickerDialog
        Espresso.onView(ViewMatchers.withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .inRoot(RootMatchers.isDialog())
                .perform(PickerActions.setDate(year, month + 1, day));

        // Apasă pe butonul OK pentru a confirma data
        Espresso.onView(withId(android.R.id.button1))
                .inRoot(RootMatchers.isDialog())
                .perform(ViewActions.click());

        // Verifică dacă TextView-ul care arată data a fost actualizat
        String expectedDate = String.format("%02d-%02d-%d", day, month + 1, year);
        Espresso.onView(withId(R.id.uploadDate))
                .check(ViewAssertions.matches(ViewMatchers.withText(expectedDate)));
    }

}
