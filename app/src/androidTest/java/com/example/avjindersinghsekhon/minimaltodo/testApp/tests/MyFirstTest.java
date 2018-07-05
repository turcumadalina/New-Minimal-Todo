package com.example.avjindersinghsekhon.minimaltodo.testApp.tests;

import com.example.avjindersinghsekhon.minimaltodo.R;

import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class MyFirstTest {
    @Test
    public void testAddItems() {
        // Step: Click add button
        onView(withId(R.id.addToDoItemFAB)).perform(click());

        // Step: Type name for a new item
        onView(withId(R.id.userToDoEditText)).perform(typeText("a"));

        // Step: Click FloatingActionButton
        onView(withId(R.id.makeToDoFloatingActionButton)).perform(click());

        // Step: Click add button
        onView(withId(R.id.addToDoItemFAB)).perform(click());

    }
}
