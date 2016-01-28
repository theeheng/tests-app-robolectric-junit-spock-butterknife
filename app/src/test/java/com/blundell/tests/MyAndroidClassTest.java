package com.blundell.tests;

import android.content.res.Resources;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.assertEquals;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class MyAndroidClassTest {

    private ActivityController<MyActivity> controller;
    private MyActivity activity;

    @Before
    public void setUp() {
        // Return ActivityController that can be used to create Activity
        controller = Robolectric.buildActivity(MyActivity.class);
        activity = controller
                .create()
                .start()
                .resume()
                .visible()
                .get();
    }

    @After
    public void tearDown() {
        // Destory activity in every test
        controller.destroy();
    }

    @Test
    public void testWhenActivityCreatedHelloTextViewIsVisible() throws Exception {

        int visibility = activity.findViewById(R.id.my_hello_text_view).getVisibility();
        assertEquals(visibility, View.VISIBLE);
    }

    @Test
    public void testWhenActivityCreatedHelloTextViewShouldDisplayHelloWorld() throws Exception {

        String helloText = ((TextView)activity.findViewById(R.id.my_hello_text_view)).getText().toString();

        Resources r = activity.getResources();

        assertEquals(r.getString(R.string.hello_world), helloText);
    }
}
