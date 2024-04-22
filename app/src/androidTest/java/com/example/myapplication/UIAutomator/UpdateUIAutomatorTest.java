package com.example.myapplication.UIAutomator;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UpdateUIAutomatorTest {

    private UiDevice device;
    private static final String PACKAGE_NAME = "com.example.myapplication";
    private static final int TIMEOUT = 5000;

    @Before
    public void setUp() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void testUpdateTitle() throws UiObjectNotFoundException {
        // Simulatează completarea câmpului de titlu cu un text nou
        device.findObject(By.res(PACKAGE_NAME, "updateTitle")).setText("New Title");
    }

    @Test
    public void testUpdateDescription() throws UiObjectNotFoundException {
        // Simulează completarea câmpului de descriere cu un text nou
        device.findObject(By.res(PACKAGE_NAME, "updateDesc")).setText("New Description");
    }

    @Test
    public void testUpdateBudget() throws UiObjectNotFoundException {
        // Simulează completarea câmpului de buget cu o sumă nouă
        device.findObject(By.res(PACKAGE_NAME, "updateBudg")).setText("1500");
    }

    @Test
    public void testUpdateDate() {
        // Simulează selectarea unei date noi
        device.findObject(By.res(PACKAGE_NAME, "buttonDate")).click();
        device.wait(Until.hasObject(By.res("android:id/button1")), TIMEOUT);
        device.findObject(By.res("android:id/button1")).click();
    }
}