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

public class ToDoList extends EspressoTestBase {

    @Test
    public void HomePageValidation() {

        // Step 1.Start the app :
        // Expected Result 1."Minimal" text is visible
        // ->textul "Minimal"(strings.xml)      ->verificarea(ViewAssetions), Da

        onView(withText(R.string.app_name)).check(matches(isDisplayed()));

        // Expected Result 1.“You don’t have any todos” text is visible
        // -> textul ""(strings.xml)       ->verificarea(ViewAssetions), Da

        onView(withText(R.string.no_to_dos)).check(matches(isDisplayed()));

        // Expected Result 2.“Minimal” text is displayed in a toolbar
        // -> parintele direct al lui minimal este toolbar , "Minimal" este in toolbar?
        // ->parintele al carui id este toolbar , contine textul "Minimal"(strings.xml), Da

        onView(allOf(withParent(withId(R.id.toolbar)), (withText(R.string.app_name)))).check(matches(isDisplayed()));

        // Expected Result 3. An image is displayed above the “You don’t have any todos” text
        // -> se iau toate instantele clasei ImageView,a caror parinte este toDoEmptyView(LinearLayout->parintele direct)
        //                                                                                -> se verifica daca imaginea este deasupra textului(strings.xml)

        onView(allOf(is(instanceOf(ImageView.class)), withParent(withId(R.id.toDoEmptyView)))).check((isAbove(withText(R.string.no_to_dos))));

        // Expected Result 4."Add" button is displayed and clickable
        // id-ul (UI automator)

        onView(withId(R.id.addToDoItemFAB)).check(matches((isDisplayed())));
        onView(withId(R.id.addToDoItemFAB)).check(matches(isClickable()));

        // Expected Result 5.“More options” button is displayed and it has a sibling with the text "Minimal"
        // -> se verifica daca butonul "More options" este in toolbar, unde este si textul "Minimal"

        onView(allOf(is(instanceOf(ImageView.class)), withParent(withId(R.id.toolbar)), withText(R.string.app_name))).check(matches(isDisplayed()));

        // -> se iau in considerare doar obiectele din toolbar si se verifica daca textul "Minimal" si butonul "More options" sunt vecine

        onView(allOf(withParent(withId(R.id.toolbar)), instanceOf(LinearLayoutCompat.class), hasSibling(withText(R.string.app_name)))).check(matches((isDisplayed())));

    }

    @Test
    public void AddButtonsValidation() {
        // Step 1.Start the app
        // Expected Result : “Minimal” text is displayed

        onView(withText(R.string.app_name)).check(matches(isDisplayed()));

        // Step 2. Click "Add" button

        onView((withId(R.id.addToDoItemFAB))).perform(click());

        // Expected Result : "X" button is visible
        // ->id-ul butonului este addToDoItemFab (UI automator)
        // -> butonul X este in toolbar

        onView(allOf(is(instanceOf(ImageButton.class)), withParent(withId(R.id.toolbar)))).check(matches((isDisplayed())));

        // Step 3. Click "X" button

        onView(allOf(is(instanceOf(ImageButton.class)), withParent((withId(R.id.toolbar))))).perform(click());

        // Expected Result : “You don’t have any todos” text is visible
        // ->display-ul initial

        onView((allOf(withText(R.string.no_to_dos)))).check(matches(isDisplayed()));

    }


    @Test
    public void AddNewItemValidation() {
        // Step 1.Start the app
        // Expected Result : “Minimal” text is displayed

        onView(withText(R.string.app_name)).check(matches(isDisplayed()));

        // Step 2. Click "Add" button

        onView(withId(R.id.makeToDoFloatingActionButton)).perform(click());

        // Expected Result: FloatingActionButton is visible
        // ->id-ul butonului este makeToDoFloatingActionButton

        onView(allOf(withId(R.id.makeToDoFloatingActionButton))).check(matches(isDisplayed()));

        // Step 3.Close keyboard
        // Expected Result : EditText is displayed as a descendent of a LinearLayout “Remind me” text is displayed between two buttons


        // Step 4. Add new item: "MY TODO"(uppercase)
        // Expected Result : "MY TODO" is displayed above "Remind me"


        // Step 5. Turn the switch On
        // Expected Result : Text starting with "Reminder set" is displayed


        // Step 6.Click "FloatingActionButton" button
        // Expected Result : The date displayed when you added "MY TODO" is now visible below this item


    }
