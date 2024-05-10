package com.example.myapplication.UIAutomator;

import static org.junit.Assert.assertNotNull;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.Until;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UpdateUIAutomatorTest {

    private UiDevice device;
    private static final int LAUNCH_TIMEOUT = 5000;
    private static final String PACKAGE_NAME = "com.example.myapplication";

    @Before
    public void launchActivity() {
        // Inițializarea dispozitivului UI Automator
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        // Lansarea activității UpdateActivity
        device.pressHome();
        device.wait(Until.hasObject(By.pkg(device.getLauncherPackageName()).depth(0)), LAUNCH_TIMEOUT);
        device.findObject(By.text("UpdateActivity")).click();
        // Adăugăm o mică pauză înainte de a continua pentru a asigura încărcarea completă a elementelor UI
        device.waitForIdle();
    }

    @Test
    public void testUIComponentsExist() {
        // Verificăm dacă componentele UI sunt afișate corect
        assertNotNull("Title field is not displayed", device.findObject(By.res(PACKAGE_NAME, "updateTitle")));
        assertNotNull("Description field is not displayed", device.findObject(By.res(PACKAGE_NAME, "updateDesc")));
        assertNotNull("Image view is not displayed", device.findObject(By.res(PACKAGE_NAME, "updateImage")));
        assertNotNull("Budget field is not displayed", device.findObject(By.res(PACKAGE_NAME, "updateBudg")));
        assertNotNull("Date field is not displayed", device.findObject(By.res(PACKAGE_NAME, "updateDate")));
        assertNotNull("Choose Date button is not displayed", device.findObject(By.res(PACKAGE_NAME, "buttonDate")));
        assertNotNull("Update button is not displayed", device.findObject(By.res(PACKAGE_NAME, "updateButton")));
    }
}