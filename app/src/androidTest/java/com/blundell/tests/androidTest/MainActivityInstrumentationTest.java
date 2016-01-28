package com.blundell.tests.androidTest;

import com.blundell.tests.MyActivity;
import com.blundell.tests.R;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityInstrumentationTest {

    private static final String STRING_TO_BE_TYPED = "Peter";

    @Rule
    public ActivityTestRule<MyActivity> mActivityRule = new ActivityTestRule<>(
            MyActivity.class);

    @Test
    public void sayHello(){

        //onView(withId(R.id.editText)).perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard()); //line 1

        onView(withText("New Button")).perform(click()); //line 2

        //String expectedText = "Hello, " + STRING_TO_BE_TYPED + "!";
        String expectedText = "Button clicked !";
        onView(withId(R.id.my_hello_text_view)).check(matches(withText(expectedText))); //line 3
    }

}