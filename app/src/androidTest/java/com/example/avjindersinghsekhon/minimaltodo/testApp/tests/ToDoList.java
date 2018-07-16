package com.example.avjindersinghsekhon.minimaltodo.testApp.tests;

import android.support.test.espresso.Espresso;

import com.example.avjindersinghsekhon.minimaltodo.R;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.text.ParseException;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
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

        //Expected Result: “Minimal” text is displayed
        assertTrue("Minimal text is not displayed.", Home.isMinimalVisible());

        // Step 2. Click "Add" button
        AddItem.clickAddButton();

        // Expected Result: "X" button is visible
        assertTrue("X button is not displayed.", AddItem.isXButtonVisible());

        // Step 3. Click "X" button
        AddItem.clickXButton();

        // Expected Result: “You don’t have any todos” text is visible
        assertTrue("You don't have any todos text is not visible.", Home.isYouDontHaveAnyTodosVisible());
    }

    @Test
    public void testCAddNewItemValidation() throws ParseException {
        // Step 1. Start the app

        // Expected Result: “Minimal” text is displayed
        assertTrue("Minimal text is not displayed.", Home.isMinimalVisible());

        // Step 2. Click "Add" button
        AddItem.clickAddButton();

        // Expected Result: FloatingActionButton is visible
        assertTrue("FloatingActionButton is not displayed.", AddItem.isFloatingActionButtonVisible());

        // Step 3.Close keyboard
        Espresso.closeSoftKeyboard();

        // Expected Result: EditText is displayed as a descendant of a LinearLayout
        assertTrue("EditText is not displayed as a descendant of a LinearLayout.", AddItem.isEditTextVisible());

        // Expected Result: “Remind me” text is displayed between two buttons
        assertTrue("Reminder me is not displayed to left.", Home.isRemindMeLeftOfDateSwitch());
        assertTrue("Reminder me is not displayed to right.", Home.isRemindMeRightOfDateSwitch());

        // Step 4. Add new item: "MY_TODO"(uppercase)
        AddItem.typeMyTodoText();

        // Expected Result: "MY_TODO" is displayed above "Remind me"
        assertTrue("My todo text is not displayed above Remind me text.", AddItem.isMyTodoTextAboveRemindMe());

        // Step 5. Turn the switch On
        AddItem.clickSwitchOn();

        // Expected Result: Text starting with "Reminder set" is displayed
        assertTrue("Reminder set is not displayed.", AddItem.isReminderSetVisible());

        String date1 = Helpers.getText(withId(R.id.newToDoDateTimeReminderTextView));
        date1 = date1.substring(17, date1.length() - 1);
        date1 = Helpers.formatDate(date1);

        // Step 6. Click "FloatingActionButton" button
        AddItem.clickFloatActionButton();

        String date2 = Helpers.getText(withId(R.id.todoListItemTimeTextView));

        // Expected Result: The date displayed when you added "MY_TODO" is now visible below this item
        assertTrue("The date is not displayed below MY TODO text.", AddItem.isDateBelowToMyTodoText());

        assertEquals("Date1 is not equal with date2.", date1, date2);
    }

    @Test
    public void testAddButtonVisibility() {
        // Step 1. Add 20 items
        Helpers.typeNewItemAction(19, Helpers.getRandomString(5), 7);

        // Step 1. SwipeUp the list
        AddItem.swipeUpAction();

        // Expected Result: Add button is NOT displayed
        assertFalse("Add button is displayed.", AddItem.isAddButtonVisible());

        // Step 1. SwipeDown the list
        AddItem.swipeDownAction();

        // Expected Result: Add button is displayed
        assertTrue("Add button is not displayed.", AddItem.isAddButtonVisible());
    }

    @Test
    public void testAddItems() {
        // Step 1. Add 15 items. The 8th item has name "This is what I need to test."
        Helpers.typeNewItemAction(15, "This is what I need to test.", 7);

        // Expected Result: Item with text "This is what I need to test" is displayed.
        assertTrue("This is what I need to test is not displayed.", AddItem.isItemTextVisible());

        // Step 2. Edit the name of the 8th item and go back to the list.
        AddItem.clickOnASpecificItemOfTheList();
        AddItem.typeNewTextInTheList();
        AddItem.clickFloatActionButton();

        // Expected Result: At the same position in the list, the item with text "This is what I need to test" is NOT displayed. At the same position, the new text is displayed.
        assertFalse("This is what i need to test displayed.", AddItem.isItemDisplayedAtSpecificPosition("This is what I need to test.", 7));
        assertTrue("The new text i need to test is not displayed.", AddItem.isItemDisplayedAtSpecificPosition("This is my new text I have to test.", 7));
    }

    @Test
    public void EditItems() {
        // Step 1. Add 5 items (1 of them with text "My new to do item")
        Helpers.typeNewItemAction(4, "My new to do item.", 2);

        // Expected Result: In the RecyclerView there are 5 items
        assertTrue("There are more/less then 5 items in RecyclerView.", Home.isTheCorrectNumberOfItemsInRecyclerView());

        // Step 2. Remove the item with text "My new to do item", but don't create the matcher using the text
        Home.deleteTheSpecificItem();

        // Expected Result: Item isn't displayed anymore
        assertFalse("Item is displayed.", Home.isSpecificItemDisplayed());
    }
}
