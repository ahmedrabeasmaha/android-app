package me.ahmedsmaha.project1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
//    public ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("me.ahmedsmaha.project1", appContext.getPackageName());
    }

//    @Test
//    public void nameTest() {
//        activityScenario.onActivity(activity -> assertThat().isEqualTo("something"));
//        //TODO
//    }
}