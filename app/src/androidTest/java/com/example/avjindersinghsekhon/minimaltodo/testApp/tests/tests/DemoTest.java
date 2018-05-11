package com.example.avjindersinghsekhon.minimaltodo.testApp.tests.tests;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.avjindersinghsekhon.minimaltodo.R;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.PositionAssertions.*;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.Matchers.startsWith;

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

    @Test
    public void TestCase1(){

        //Verify "Minimal" and "You don't have any todos" text is displayed
        onView(withText(R.string.app_name)).check(matches(isDisplayed()));
        onView(withText(R.string.no_to_dos)).check(matches(isDisplayed()));

        //Verify Image is displayed above "You don't have any todos" text
        onView(allOf(withParent(withId(R.id.toDoEmptyView)), Matchers.<View>instanceOf(ImageView.class))).check(isAbove(withText(R.string.no_to_dos)));

        //Verify "Minimal" text is part of the toolbar
        onView(allOf(withText(R.string.app_name), withParent(withId(R.id.toolbar)))).check(matches(isCompletelyDisplayed()));

        //Verify Add button exist
        onView(allOf(withId(R.id.addToDoItemFAB), isCompletelyDisplayed())).check(matches(isClickable()));

        //Verify button with content-desc "More options" exist, has a parent with a brother with "Minimal" text and is part of toolbar
        onView(allOf(withContentDescription("More options"), withParent(hasSibling(withText(R.string.app_name))), isDescendantOfA(withId(R.id.toolbar)))).check(matches(isDisplayed()));
    }

    @Test
    public void TestCase2(){

        //Click the add button and verify x is displayed
        onView(withId(R.id.addToDoItemFAB)).perform(click());
        onView(allOf(withParent(withId(R.id.toolbar)), withContentDescription("Navigate up"))).check(matches(isDisplayed()));

        //Click x and verify "You don't have any todos" is displayed
        onView(allOf(withParent(withId(R.id.toolbar)), withContentDescription("Navigate up"))).perform(click());
        onView(withText(R.string.no_to_dos)).check(matches(isDisplayed()));
        closeSoftKeyboard();

        //Click the add button
        onView(withId(R.id.addToDoItemFAB)).perform(click());

        //Close keyboard. Verify "edit text" is displayed and it has a parent. Verify there is a "Remind me" text positioned in between 2 buttons
        closeSoftKeyboard();
        onView(allOf(withId(R.id.userToDoEditText), withParent(withId(R.id.toDoCustomTextInput)))).check(matches(isDisplayed()));
        onView(withText(R.string.remind_me)).check(isBelow(withId(R.id.makeToDoFloatingActionButton))).check(isLeftOf(withId(R.id.toDoHasDateSwitchCompat)));

        //Add a to-do with "MY TO-DO" text and verify the added text is displayed
        onView(withId(R.id.userToDoEditText)).perform(typeText("MY TODO"));
        onView(withText("MY TODO")).check(matches(isDisplayed()));

        //Turn on toggle and verify text starting with "Reminder set" is displayed under another "Today" text
        onView(withId(R.id.toDoHasDateSwitchCompat)).perform(click());
        onView(withText(startsWith("Reminder set"))).check(isBelow(withText(R.string.date_reminder_default)));

        //Click on makeToDOFLoatingActionButton and verify "MY TO-DO" is displayed and that the date is correct.
        String Date = Helpers.getText(withId(R.id.newToDoDateTimeReminderTextView));
        String Clear_Date = Date.replace("Reminder set for ","");
        try {
            Date date1 = new SimpleDateFormat("dd MMM, yyyy, hh:mm a", Locale.US).parse(Clear_Date);
            Clear_Date = new SimpleDateFormat("MMM dd, yyyy  hh:mm a", Locale.US).format(date1);
        }catch(Exception ex){ex.printStackTrace();}
        onView(withId(R.id.makeToDoFloatingActionButton)).perform(click());
        onView(withText(Clear_Date)).check(matches(isDisplayed()));
        onView(withId(R.id.toDoListItemTextview)).perform(swipeLeft());
    }
}