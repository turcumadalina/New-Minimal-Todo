package com.example.avjindersinghsekhon.minimaltodo.testApp.tests;

import android.support.test.espresso.action.ViewActions;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.support.test.espresso.Root;

import com.example.avjindersinghsekhon.minimaltodo.R;

import org.hamcrest.Matchers;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.PositionAssertions.isBelow;
import static android.support.test.espresso.assertion.PositionAssertions.isLeftAlignedWith;
import static android.support.test.espresso.assertion.PositionAssertions.isLeftOf;
import static android.support.test.espresso.assertion.PositionAssertions.isRightAlignedWith;
import static android.support.test.espresso.assertion.PositionAssertions.isRightOf;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static android.support.test.espresso.assertion.PositionAssertions.isAbove;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;


public class AnotherTest extends EspressoTestBase {
    @Test
    public void testViewItems() {

        // Verify: “Minimal” and “You don’t have any todos” texts are visible
        onView(allOf(withText(R.string.app_name))).check(matches(isDisplayed()));
        onView(allOf(withText(R.string.no_to_dos))).check(matches(isDisplayed()));

        // Verify: “Minimal” text is displayed in a toolbar
        onView(allOf(withParent(withId(R.id.toolbar)), withText(R.string.app_name), isCompletelyDisplayed())).check(matches(isDisplayed()));

        // Verify: An image is displayed above the “You don’t have any todos” text
        onView(allOf(is(instanceOf(ImageView.class)), withParent(withId(R.id.toDoEmptyView)))).check(isAbove(withText(R.string.no_to_dos)));

        // Verify: "Add" button is displayed and clickable
        onView(withId(R.id.addToDoItemFAB)).check(matches(isDisplayed()));
        onView(withId(R.id.addToDoItemFAB)).check(matches(isClickable()));

        // Verify: “More options” button is displayed and it has a sibling with the text "Minimal"
        onView(allOf(is(instanceOf(ImageView.class)), withParent(withParent(withId(R.id.toolbar))))).check(matches(isDisplayed()));
        onView(allOf(withParent(withId(R.id.toolbar)), instanceOf(android.support.v7.widget.LinearLayoutCompat.class), hasSibling(withText(R.string.app_name)))).check(matches(isDisplayed()));
    }

    @Test
    public void buttonsValidation() {
        // Verify: “Minimal” text is displayed
        onView(allOf(withText(R.string.app_name))).check(matches(isDisplayed()));

        // Step: Click "Add" button
        onView(withId(R.id.addToDoItemFAB)).perform(click());

        // Verify: "X" button is visible
        onView(allOf(is(instanceOf(ImageButton.class)), withParent(withId(R.id.toolbar)))).check(matches(isDisplayed()));

        // Step: Click "X" button
        onView(allOf(is(instanceOf(ImageButton.class)), withParent(withId(R.id.toolbar)))).perform(click());

        // Verify: “You don’t have any todos” text is visible
        onView(allOf(withText(R.string.no_to_dos))).check(matches(isDisplayed()));
    }

    @Test
    public void addNewItem() {
        // Verify: "Minimal" text is visible
        onView(allOf(withText(R.string.app_name))).check(matches(isDisplayed()));

        // Step: Click "Add" button
        onView(withId(R.id.addToDoItemFAB)).perform(click());

        // Verify: FloatingActionButton is visible
        onView(withId(R.id.makeToDoFloatingActionButton)).check(matches(isDisplayed()));

        // Step: Close keyboard
        onView(isRoot()).perform(ViewActions.closeSoftKeyboard());

        // Verify: EditText is displayed as a descendent of a LinearLayout
        // “Remind me” text is displayed between two buttons
        onView(allOf((withId(R.id.userToDoEditText)), isDescendantOfA(withId(R.id.editTextParentLinearLayout)))).check(matches(isDisplayed()));
        onView((withId(R.id.userToDoRemindMeTextView))).check(isLeftOf(withId(R.id.toDoHasDateSwitchCompat)));
        onView((withId(R.id.userToDoRemindMeTextView))).check(isRightOf(withId(R.id.userToDoReminderIconImageButton)));

        // Step: Add new item: "MY_TODO" (uppercase)
        onView(withId(R.id.userToDoEditText)).perform(typeText("MY TODO"));

        // Verify: "MY_TODO" is displayed above "Remind me"
        onView(withText("MY TODO")).check(isAbove(withText(R.string.remind_me)));

        // Step: Turn the switch On
        onView(withId(R.id.toDoHasDateSwitchCompat)).perform(click());

        // Verify: Text starting with "Reminder set" is displayed
        onView(withText(startsWith("Reminder set"))).check(matches(isDisplayed()));

        // Step: Click "FloatingActionButton" button
        onView(withId(R.id.makeToDoFloatingActionButton)).perform(click());

        // Verify: The date displayed when you added "MY_TODO" is now visible below this item
        onView(withId(R.id.todoListItemTimeTextView)).check(isBelow(withId(R.id.toDoListItemTextview)));

    }
}

