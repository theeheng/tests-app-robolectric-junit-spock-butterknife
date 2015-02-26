package com.blundell.tests;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

import com.blundell.RobolectricGradleTestRunner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricGradleTestRunner.class)
public class MyAndroidClassTest2 {

    @Test
    public void testWhenActivityCreatedHelloTextViewIsVisible() throws Exception {
        MyActivity activity = new MyActivity();

        ActivityController.of(activity).attach().create();

        int visibility = activity.findViewById(R.id.my_hello_text_view).getVisibility();
        assertEquals(visibility, View.VISIBLE);
    }

    @Test
    public void testWhenActivityCreatedHelloTextViewShouldDisplayHelloWorld() throws Exception {
        MyActivity activity = new MyActivity();

        ActivityController.of(activity).attach().create();

        String helloText = ((TextView)activity.findViewById(R.id.my_hello_text_view)).getText().toString();

        Resources r = activity.getResources();

        assertEquals(r.getString(R.string.hello_world), helloText);
    }
}
