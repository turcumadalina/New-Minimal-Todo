package com.example.avjindersinghsekhon.minimaltodo.testApp.tests.tests;

import com.example.avjindersinghsekhon.minimaltodo.R;

import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class DemoTest extends EspressoTestBase {

    @Test
    public void testAddItem() {
        onView(withId(R.id.addToDoItemFAB)).perform(click());

        onView(withId(R.id.userToDoEditText)).perform(typeText("a"));

        onView(withId(R.id.makeToDoFloatingActionButton)).perform(click());

        onView(withId(R.id.addToDoItemFAB)).perform(click());

        onView(withId(R.id.userToDoEditText)).perform(typeText("b"));

        onView(withId(R.id.makeToDoFloatingActionButton)).perform(click());

        onView(allOf(withId(R.id.toDoListItemTextview), withText("B"), isCompletelyDisplayed())).check(matches(isDisplayed()));

        onView(allOf(withId(R.id.toDoListItemTextview), withText("B"),isCompletelyDisplayed())).perform(swipeLeft());

        onView(allOf(withId(R.id.toDoListItemTextview), withText("A"),isCompletelyDisplayed())).perform(swipeLeft());
    }

    @Test
    public void Remind(){
        onView(withId(R.id.addToDoItemFAB)).perform(click());

        onView(withId(R.id.userToDoEditText)).perform(typeText("Remind Me"));

        onView(withId(R.id.toDoHasDateSwitchCompat)).perform(click());

        onView(withId(R.id.newTodoDateEditText)).check(matches(isDisplayed()));

        onView(withId(R.id.newTodoTimeEditText)).check(matches(isDisplayed()));

        onView(withId(R.id.newToDoDateTimeReminderTextView)).check(matches(isDisplayed()));

        onView(withId(R.id.makeToDoFloatingActionButton)).perform(click());

        onView(allOf(withId(R.id.todoListItemTimeTextView))).check(matches(isDisplayed()));

        onView(withId(R.id.toDoListItemTextview)).perform(swipeLeft());
    }

}