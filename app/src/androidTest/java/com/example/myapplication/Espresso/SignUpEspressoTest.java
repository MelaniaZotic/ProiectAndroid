package com.example.myapplication.Espresso;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.R;
import com.example.myapplication.SignUpActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SignUpEspressoTest {

    @Rule
    public ActivityScenarioRule<SignUpActivity> activityScenarioRule = new ActivityScenarioRule<>(SignUpActivity.class);

    @Before
    public void setUp() {
        Intents.init(); // Inițializare Intents pentru a monitoriza navigațiile
    }

    @Test
    public void testUserSignUp() {
        // Introducere detalii utilizator
        onView(withId(R.id.signup_email)).perform(typeText("test@example.com"), closeSoftKeyboard());
        onView(withId(R.id.signup_password)).perform(typeText("password123"), closeSoftKeyboard());

        // Apasă pe butonul de înregistrare
        onView(withId(R.id.signup_button)).perform(click());

        // Verifică dacă navighează către activitatea următoare (înlocuiește LoginActivity.class cu activitatea ta de destinație)
        intending(hasComponent(LoginActivity.class.getName()));
    }

    @After
    public void tearDown() {
        Intents.release(); // Curăță Intents după fiecare test
    }
}


//@RunWith(AndroidJUnit4.class)
//public class SignUpEspressoTest {
//
//    @Rule
//    public ActivityScenarioRule<SignUpActivity> activityScenarioRule = new ActivityScenarioRule<>(SignUpActivity.class);
//
//    // Pentru a înregistra
//    //IdlingRegistry.getInstance().register(meuIdlingResource);
//    ToastIdlingResource toastIdlingResource = new ToastIdlingResource();
//
//    @Before
//    public void setUp() {
//        toastIdlingResource = new ToastIdlingResource();
//        IdlingRegistry.getInstance().register(toastIdlingResource);
//    }
//
//    @Test
//    public void testUserSignUp() {
//        onView(ViewMatchers.withId(R.id.signup_email)).perform(typeText("test@example.com"), closeSoftKeyboard());
//        onView(withId(R.id.signup_password)).perform(typeText("password123"), closeSoftKeyboard());
//        onView(withId(R.id.signup_button)).perform(click());
//
//        try {
//            Thread.sleep(2000); // Așteaptă 2 secunde pentru ca Toast-ul să fie afișat
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        onView(withText("SignUp Successful")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
//    }
//
//    @After
//    public void tearDown() {
//        if (toastIdlingResource != null) {
//            IdlingRegistry.getInstance().unregister(toastIdlingResource);
//        }
//    }
//}
//    @Test
//    public void testGoogleSignIn() {
//        Intents.init();
//        try {
//            onView(withId(R.id.googleBtn)).perform(click());
//            intended(hasAction(equalTo(Intent.ACTION_CHOOSER)));
//        } finally {
//            Intents.release();
//        }
//    }
