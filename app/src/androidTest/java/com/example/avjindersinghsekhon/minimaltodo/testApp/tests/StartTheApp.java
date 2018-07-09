package com.example.avjindersinghsekhon.minimaltodo.testApp.tests;

import android.support.test.espresso.action.ViewActions;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.avjindersinghsekhon.minimaltodo.R;

import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.PositionAssertions.isAbove;
import static android.support.test.espresso.assertion.PositionAssertions.isBelow;
import static android.support.test.espresso.assertion.PositionAssertions.isLeftOf;
import static android.support.test.espresso.assertion.PositionAssertions.isRightOf;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

public class StartTheApp extends EspressoTestBase {
    @Test
    public void homePageValidation() {
        // Verify: “Minimal” and “You don’t have any todos” texts are visible
        onView(withText(R.string.app_name)).check(matches(isDisplayed()));
        onView(withText(R.string.no_to_dos)).check(matches(isDisplayed()));

        // Verify: “Minimal” text is displayed in a toolbar
        onView(allOf(withParent(withId(R.id.toolbar)), withText(R.string.app_name))).check(matches(isDisplayed()));

        // Verify: An image is displayed above the “You don’t have any todos” text
        onView(allOf(is(instanceOf(ImageView.class)), withParent(withId(R.id.toDoEmptyView)))).check(isAbove(withText(R.string.no_to_dos)));

        // Verify: "Add" button is displayed and clickable
        onView(withId(R.id.addToDoItemFAB)).check(matches(isDisplayed()));
        onView(withId(R.id.addToDoItemFAB)).check(matches(isClickable()));

        // Verify: “More options” button is displayed and it has a sibling with the text "Minimal"
        onView(allOf(is(instanceOf(ImageView.class)), withParent(withParent(withId(R.id.toolbar))))).check(matches(isDisplayed()));
        onView(allOf(withParent(withId(R.id.toolbar)), instanceOf(LinearLayoutCompat.class),
                hasSibling(withText(R.string.app_name)))).check(matches(isDisplayed()));
    }

    @Test
    public void buttonsValidation() {
        // Verify: “Minimal” text is displayed
        onView(withText(R.string.app_name)).check(matches(isDisplayed()));

        // Step: Click "Add" button
        onView(withId(R.id.addToDoItemFAB)).perform(click());

        // Veryfy: "X" button is visible
        onView(allOf(is(instanceOf(ImageButton.class)), withParent(withId(R.id.toolbar)))).check(matches(isDisplayed()));

        // Click "X" button
        onView(allOf(is(instanceOf(ImageButton.class)), withParent(withId(R.id.toolbar)))).perform(click());

        // Verify: “You don’t have any todos” text is visible
        onView(withText(R.string.no_to_dos)).check(matches(isDisplayed()));
    }

    @Test
    public void addItem() {
        // Verify: "Minimal" text is visible
        onView(withText(R.string.app_name)).check(matches(isDisplayed()));

        // Step: Click "Add" button and check if FloatingActionButton is visible
        onView(withId(R.id.addToDoItemFAB)).perform(click());
        onView(withId(R.id.toDoCustomTextInput)).check(matches(isDisplayed()));

        // Step: Close keyboard
        onView(isRoot()).perform(ViewActions.closeSoftKeyboard());

        // Verify: EditText is displayed as a descendent of a LinearLayout
        //         “Remind me” text is displayed between two buttons
        onView(allOf(is(instanceOf(EditText.class)), withParent(withId(R.id.toDoCustomTextInput)))).check(matches(isDisplayed()));
        onView(withText(R.string.remind_me)).check(isRightOf(withId(R.id.userToDoReminderIconImageButton)));
        onView(withText(R.string.remind_me)).check(isLeftOf(withId(R.id.toDoHasDateSwitchCompat)));

        // Step: Add new item: "MY_TODO" (uppercase)
        onView(withId(R.id.userToDoEditText)).perform(typeText("my todo".toUpperCase()));

        // Verify: "MY_TODO" is displayed above "Remind me"
        onView(withText("MY TODO")).check(isAbove(withText(R.string.remind_me)));

        // Step: Turn the switch On
        onView(withId(R.id.toDoHasDateSwitchCompat)).perform(click());

        // Verify: Text starting with "Reminder set" is displayed
        onView(withText(startsWith("Reminder set"))).check(matches(isDisplayed()));

        // Verify: The data format
        String firstDate = Helpers.getText(withId(R.id.newToDoDateTimeReminderTextView));

        // Step: Click "FloatingActionButton" button
        onView(withId(R.id.makeToDoFloatingActionButton)).perform(click());

        // Verify: The date displayed when you added "MY_TODO" is now visible below this item
        String secondDate = Helpers.getText(withId(R.id.todoListItemTimeTextView));
        String newFirstDate = Helpers.substring(firstDate);
        if(newFirstDate.equals(secondDate)){
            onView(withId(R.id.todoListItemTimeTextView)).check(isBelow(withId(R.id.toDoListItemTextview)));
        }
    }
}
