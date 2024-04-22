package com.example.myapplication.UIAutomator;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.Until;

import com.example.myapplication.UploadActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UploadUIAutomatorTest {

    private UiDevice device;
    private static final String PACKAGE_NAME = "com.example.myapplication";
    private static final int TIMEOUT = 5000;

    @Rule
    public ActivityScenarioRule<UploadActivity> activityScenarioRule =
            new ActivityScenarioRule<>(UploadActivity.class);

    @Before
    public void setUp() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void testSelectImage() throws UiObjectNotFoundException {
        // Simulează selectarea unei imagini
        device.findObject(By.res(PACKAGE_NAME, "uploadImage")).click();
        device.wait(Until.hasObject(By.pkg("com.android.documentsui")), TIMEOUT);

        // Găsește direct un element vizibil pe ecran, de exemplu, primul fișier vizibil
        UiObject2 file = device.findObject(By.res("com.android.documentsui:id/date"));
        if (file != null) {
            file.click();
            // Așteaptă ca imaginea să fie încărcată sau continuă cu alte acțiuni
        } else {
            System.out.println("No image found");
        }
    }

    @Test
    public void testFillTitle() throws UiObjectNotFoundException {
        // Testează completarea câmpului de titlu
        device.findObject(By.res(PACKAGE_NAME, "uploadTitle")).setText("Sample Title");
    }

    @Test
    public void testFillDescription() throws UiObjectNotFoundException {
        // Testează completarea câmpului de descriere
        device.findObject(By.res(PACKAGE_NAME, "uploadDesc")).setText("Sample Description");
    }

    @Test
    public void testFillBudget() throws UiObjectNotFoundException {
        // Testează completarea câmpului de buget
        device.findObject(By.res(PACKAGE_NAME, "uploadBudg")).setText("1000");
    }

    @Test
    public void testSelectDate() {
        // Selectează data de astăzi
        device.findObject(By.res(PACKAGE_NAME, "buttonDate")).click();
        device.wait(Until.hasObject(By.res("android:id/button1")), TIMEOUT);
        device.findObject(By.res("android:id/button1")).click();
    }
}