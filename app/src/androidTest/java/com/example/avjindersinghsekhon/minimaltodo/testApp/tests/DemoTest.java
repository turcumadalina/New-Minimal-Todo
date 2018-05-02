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

public class DemoTest extends EspressoTestBase {

    @Test
    public void testAddItems() {
        onView(withId(R.id.addToDoItemFAB)).perform(click());

        onView(withId(R.id.userToDoEditText)).perform(typeText("a"));

        onView(withId(R.id.makeToDoFloatingActionButton)).perform(click());

        onView(withId(R.id.addToDoItemFAB)).perform(click());

        onView(withId(R.id.userToDoEditText)).perform(typeText("b"));

        onView(withId(R.id.makeToDoFloatingActionButton)).perform(click());

        onView(allOf(withId(R.id.toDoListItemTextview), withText("B"), isCompletelyDisplayed())).check(matches(isDisplayed()));
    }

    @Test
    public void testAddItems2() {
        onView(withId(R.id.addToDoItemFAB)).perform(click());

        onView(withId(R.id.userToDoEditText)).perform(typeText("a"));

        onView(withId(R.id.makeToDoFloatingActionButton)).perform(click());
    }
}