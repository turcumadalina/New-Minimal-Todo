package com.example.avjindersinghsekhon.minimaltodo.testApp.tests;

import android.support.test.espresso.Espresso;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static junit.framework.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ToDoList extends EspressoTestBase {

    @Test
    public void testAHomePageValidation() {
        // Step 1. Start the app

        // Expected Result 1."Minimal" text is visible
        assertTrue("Minimal text is not displayed.", Home.isMinimalVisible());

        // Expected Result 1.“You don’t have any todos” text is visible
        assertTrue("You don't have any todos text is not displayed.", Home.isYouDontHaveAnyTodosVisible());

        // Expected Result 2.“Minimal” text is displayed in a toolbar
        assertTrue("Minimal text is not displayed in toolbar.", Home.isMinimalVisibleInToolbar());

        // Expected Result 3. An image is displayed above the “You don’t have any todos” text
        assertTrue("You don't have any todos text is not displayed above that image.", Home.isYouDontHaveAnyTodosTextAboveImageView());

        // Expected Result 4."Add" button is displayed and clickable
        assertTrue("Add button is not displayed.", Home.isAddButtonVisible());
        assertTrue("Add button is not clickable.", Home.isAddButtonClickable());

        // Expected Result 5.“More options” button is displayed and it has a sibling with the text "Minimal"
        assertTrue("More options button and Minimal text is not displayed.", Home.isMoreOptionsSiblingWithMinimalVisible());
    }

    @Test
    public void testBAddButtonsValidation() {
        // Step 1.Start the app

        //Expected Result : “Minimal” text is displayed
        assertTrue("Minimal text is not displayed.", Home.isMinimalVisible());

        // Step 2. Click "Add" button
        AddItem.clickAddButton();

        // Expected Result : "X" button is visible
        assertTrue("X button is not displayed.", AddItem.isXButtonVisible());

        // Step 3. Click "X" button
        AddItem.clickXButton();

        // Expected Result : “You don’t have any todos” text is visible
        assertTrue("You don't have any todos text is not visible.", Home.isYouDontHaveAnyTodosVisible());
    }

    @Test
    public void testCAddNewItemValidation() {
        // Step 1.Start the app

        // Expected Result : “Minimal” text is displayed
        assertTrue("Minimal text is not displayed.", Home.isMinimalVisible());

        // Step 2. Click "Add" button
        AddItem.clickAddButton();

        // Expected Result: FloatingActionButton is visible
        assertTrue("FloatingActionButton is not displayed.", AddItem.isFloatingActionButtonVisible());

        // Step 3.Close keyboard
        Espresso.closeSoftKeyboard();

        // Expected Result : EditText is displayed as a descendent of a LinearLayout
        assertTrue("EditText is not displayed as a descendent of a LinearLayout.", AddItem.isEditTextVisible());

        //Expected Result : “Remind me” text is displayed between two buttons
        assertTrue("Reminder me is not displayed to left.", Home.isRemindeMeLeftOfDateSwitch());
        assertTrue("Reminder me is not displayed to right.", Home.isRemindeMeRightOfDateSwitch());

        // Step 4. Add new item: "MY TODO"(uppercase)
        AddItem.typeMyTodoText();

        // Expected Result : "MY TODO" is displayed above "Remind me"
        assertTrue("My todo text is not displyed above Remind me text.", AddItem.isMyTodoTextAboveRemindMe());

        // Step 5. Turn the switch On
        AddItem.clickSwitchOn();

        // Expected Result : Text starting with "Reminder set" is displayed
        assertTrue("Reminder set is not displayed.", AddItem.isReminderSetVisible());

        // String date1 = Helpers.getText(withId(newToDoDateTimeReminderTextView));

        // Step 6.Click "FloatingActionButton" button
        AddItem.clickFloatActionButton();

        // String date2 = Helpers.getText(withId(R.id.todoListItemTimeTextView));

        // Expected Result : The date displayed when you added "MY TODO" is now visible below this item
        assertTrue("The date is not displayed below MY TODO text.", AddItem.isDateBelowToMyTodoText());

        // boolean result2 = date1.equals(date2);
    }
}
