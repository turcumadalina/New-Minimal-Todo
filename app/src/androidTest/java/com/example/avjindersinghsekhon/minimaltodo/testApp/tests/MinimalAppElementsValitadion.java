package com.example.avjindersinghsekhon.minimaltodo.testApp.tests;

import android.support.test.espresso.Espresso;

import com.example.avjindersinghsekhon.minimaltodo.R;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MinimalAppElementsValitadion extends EspressoTestBase {

    @After
    public void deleteAllItems() {
        int numberOfChildren = Helpers.getRecyclerViewChildCount(withId(R.id.toDoRecyclerView));
        if (numberOfChildren > 0) {
            for (int i = 0; i < numberOfChildren; i++) {
                onView(Helpers.childAtPosition(withId(R.id.toDoRecyclerView), i)).perform(swipeLeft());
            }
        }
    }

    @Test
    public void testAhomePageValidation() {
        // Verify: “Minimal” and “You don’t have any todos” texts are visible
        assertTrue("Minimal is not displayed", Home.isMinimalVisible());
        assertTrue("You don’t have any todos is not displayed", Home.isNoToDosTextVisible());

        // Verify: “Minimal” text is displayed in a toolbar
        assertTrue("Minimal is not displayed", Home.isMinimalDisplayedInToolbar());

        // Verify: An image is displayed above the “You don’t have any todos” text
        assertTrue("Image is not displayed above", Home.isImageAboveNoToDoText());

        // Verify: "Add" button is displayed and clickable
        assertTrue("Add button is not displayd", Home.isAddButtonVisible());
        assertTrue("Add button is not clickable", Home.isAddButtonClickable());

        // Verify: “More options” button is displayed and it has a sibling with the text "Minimal"
        assertTrue("More option is not displayd", Home.isMoreOptionsVisible());
        assertTrue("More option doesen't have a sibling", Home.isMoreOptionSiblingWithMinimalText());
    }

    @Test
    public void testBbuttonsValidation() {
        // Verify: “Minimal” text is displayed
        assertTrue("Minimal is not displayed", Home.isMinimalVisible());

        // Step: Click "Add" button
        Home.clickAddButton();

        // Verify: "X" button is visible
        assertTrue("X button is not displayed", AddItem.isXButtonDisplayed());

        // Click "X" button
        AddItem.clickXButton();

        // Verify: “You don’t have any todos” text is visible
        assertTrue("You don’t have any todos is not displayed", Home.isNoToDosTextVisible());

        // Close keyboard
        Espresso.closeSoftKeyboard();
    }

    @Test
    public void testCaddItem() {
        // Verify: "Minimal" text is visible
        assertTrue("Minimal is not displayed", Home.isMinimalVisible());

        // Step: Click add button
        Home.clickAddButton();

        // Verify: FloatingActionButton is visible
        assertTrue("FloatingActionButton is not displayed", AddItem.isFloatingActionButtonDisplayed());

        // Step: Close keyboard
        Espresso.closeSoftKeyboard();

        // Verify: EditText is displayed as a descendent of a LinearLayout,
        //         “Remind me” text is displayed between two buttons
        assertTrue("EditText is not displayed", AddItem.isEditTextDisplayd());
        assertTrue("Remind me is not to the left of button", AddItem.isRemindLeftOfSwitchButton());
        assertTrue("Remind me is not to the left of button", AddItem.isRemindRightOfImageButton());

        // Step: Add new item: "MY_TODO" (uppercase)
        AddItem.addItemUppercase("my todo");

        // Verify: "MY_TODO" is displayed above "Remind me"
        assertTrue("MY_TODO is not displayed above Remind me", Home.isItemTextAboveRemindMe());

        // Step: Turn the switch On
        AddItem.clickButtonToSwitchOnOrOff();

        // Verify: Text starting with "Reminder set" is displayed
        assertTrue("Reminder set is not displayed", AddItem.isReminderSetDisplayed());

        // Verify: The data format
        String firstDate = AddItem.getData();

        // Step: Click "FloatingActionButton" button
        AddItem.clickFloatingActionButton();

        // Verify: The date displayed when you added "MY_TODO" is now visible below this item
        String secondDate = Home.getSecondData();
        String newFirstDate = Helpers.getDataFormat(firstDate);
        if (newFirstDate.equals(secondDate)) {
            assertTrue("The date is not displayed below", Home.isDateBelowOfToDoItemText());
        }
    }

    @Test
    public void editASpecificToDoItemText() {
        // Step: Add 15 items; The 8th item has name "This is what I need to test"
        AddItem.addSpecificNoOfItems("This is what I need to test", 7, 15);

        // Verify: Is item with text "This is what I need to test" displayed
        assertTrue("Item with text This is what I need to test is not displayed", AddItem.isItemWithTextThisIsWhatINeedToTestIsDisplayed());

        // Step: Edit the name of the 8th item and go back to the list
        AddItem.EditToDoItem8thText();

        // Verify: At the same position in the list, the item with text "This is what I need to test" is NOT displayed
        assertFalse("Item is still visible", AddItem.isToDoItemDisplayedAtSpecificPosition("This is what I need to test", 7));

        // Verify: At the same position, the new text is displayed
        assertTrue("New item is not visible", AddItem.isToDoItemDisplayedAtSpecificPosition(" This is tne new text", 7));
    }

    @Test
    public void checkNoOfToDoItemsAndRemoveOneOfThem() {
        // Step: Add 5 items (1 of them with text "My new to do item")
        AddItem.addSpecificNoOfItems("My new to do item", 2, 5);

        // Verify: In the RecyclerView there are 5 items
        assertTrue("No of items in recyclerView are not the seame as added items", AddItem.isNoOfItemsInRecyclerView());

        // Step: Remove the item with text "My new to do item"
        AddItem.deleteItemWithTextMyNewToDoItem();

        // Verify: Item with text "My new to do item" isn't displayed anymore
        assertFalse("Item is still visible", AddItem.isDeleteItemWithTextMyNewToDoItemDisplayed());
    }

    @Test
    public void addButtonVisibility() {
        // Step: Add 20 items
        AddItem.addSpecificNoOfItems("We still have a specific item", 15, 20);

        // Step: Swipe up the list
        AddItem.swipeUpTheList();

        // Verify: Add button is NOT displayed
        assertFalse("Add button is still visible", AddItem.isAddButtonDisplayed());

        // Step: Swipe down the list
        AddItem.swipeDownTheList();

        // Verify: Add button is displayed
        assertTrue("Add button is not visible", AddItem.isAddButtonDisplayed());
    }
}