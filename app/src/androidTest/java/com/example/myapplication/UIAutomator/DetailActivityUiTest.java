package com.example.myapplication.UIAutomator;

import static org.junit.Assert.assertNotNull;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DetailActivityUiTest {

    private UiDevice device;

    @Before
    public void setUp() {
        // Obțineți instrumentul UiDevice
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void testDetailActivityDisplay() {
        // Lansați activitatea de detalii
        device.pressHome();
        device.findObject(By.desc("Apps")).click();
        UiObject2 appIcon = device.findObject(By.text("MyApplication"));
        appIcon.click();

        // Deschideți activitatea de detalii
        UiObject2 detailButton = device.findObject(By.res("com.example.myapplication:id/detailButton"));
        detailButton.click();

        // Verificați dacă activitatea de detalii este afișată corect
        UiObject2 detailDesc = device.findObject(By.res("com.example.myapplication:id/detailDesc"));
        assertNotNull(detailDesc);

        UiObject2 detailTitle = device.findObject(By.res("com.example.myapplication:id/detailTitle"));
        assertNotNull(detailTitle);

        UiObject2 detailDate = device.findObject(By.res("com.example.myapplication:id/detailDate"));
        assertNotNull(detailDate);

        UiObject2 detailImage = device.findObject(By.res("com.example.myapplication:id/detailImage"));
        assertNotNull(detailImage);
    }
}