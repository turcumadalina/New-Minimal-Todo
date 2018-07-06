package com.example.avjindersinghsekhon.minimaltodo.testApp.tests;

import android.support.v7.widget.LinearLayoutCompat;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.avjindersinghsekhon.minimaltodo.R;

import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.PositionAssertions.isAbove;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class StartTheApp extends EspressoTestBase {
    @Test
    public void homePageValidation() {
        //“Minimal” and “You don’t have any todos” texts are visible
        onView(withText(R.string.app_name)).check(matches(isDisplayed()));
        onView(withText(R.string.no_to_dos)).check(matches(isDisplayed()));
        //“Minimal” text is displayed in a toolbar
        onView(allOf(withParent(withId(R.id.toolbar)), withText(R.string.app_name))).check(matches(isDisplayed()));
        //An image is displayed above the “You don’t have any todos” text
        onView(allOf(is(instanceOf(ImageView.class)), withParent(withId(R.id.toDoEmptyView)))).check(isAbove(withText(R.string.no_to_dos)));
        //"Add" button is displayed and clickable
        onView(withId(R.id.addToDoItemFAB)).check(matches(isDisplayed()));
        onView(withId(R.id.addToDoItemFAB)).check(matches(isClickable()));
        //“More options” button is displayed and it has a sibling with the text "Minimal"
        onView(allOf(is(instanceOf(ImageView.class)), withParent(withParent(withId(R.id.toolbar))))).check(matches(isDisplayed()));
        onView(allOf(withParent(withId(R.id.toolbar)), instanceOf(LinearLayoutCompat.class),
                hasSibling(withText(R.string.app_name)))).check(matches(isDisplayed()));
    }

    @Test
    public void buttonsValidation() {
        //“Minimal” text is displayed
        onView(withText(R.string.app_name)).check(matches(isDisplayed()));
        //Click "Add" button and "X" button is visible
        onView(withId(R.id.addToDoItemFAB)).perform(click());
        onView(allOf(is(instanceOf(ImageButton.class)), withParent(withId(R.id.toolbar)))).check(matches(isDisplayed()));
        //Click "X" button and “You don’t have any todos” text is visible
        onView(allOf(is(instanceOf(ImageButton.class)), withParent(withId(R.id.toolbar)))).perform(click());
        onView(withText(R.string.no_to_dos)).check(matches(isDisplayed()));

    }
}
