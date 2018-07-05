package com.example.avjindersinghsekhon.minimaltodo.testApp.tests;

import com.example.avjindersinghsekhon.minimaltodo.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class AnotherTest {
    public void testAddItems() {
        // Step: Click add button
        onView(withId(R.id.addToDoItemFAB)).perform(click());
    }
}
