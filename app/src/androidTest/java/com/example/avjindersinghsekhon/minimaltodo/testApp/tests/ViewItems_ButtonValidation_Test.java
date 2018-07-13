package com.example.avjindersinghsekhon.minimaltodo.testApp.tests;

import android.support.test.espresso.Espresso;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ViewItems_ButtonValidation_Test extends EspressoTestBase {

    @Test
    public void testAViewItems() {

        // Verify: “Minimal” and “You don’t have any todos” texts are visible
        assertTrue("Minimal is not visible", Home.isMinimalVisible());
        assertTrue("You don’t have any todos is not visible", Home.isNotTodosVisible());

        // Verify: “Minimal” text is displayed in a toolbar
        assertTrue("Minimal is not displayed in toolbar", Home.isMinimalInToolbar());

        // Verify: An image is displayed above the “You don’t have any todos” text
        assertTrue("An image is not displayed above todo text", Home.isImageAboveYouDontHaveTodos());

        // Verify: "Add" button is displayed and clickable
        assertTrue("Add button is not displayed", Home.isAddButtonDisplayed());
        assertTrue("Add button is not clickable", Home.isAddButtonClickable());

        // Verify: “More options” button is displayed and it has a sibling with the text "Minimal"
        assertTrue("More options is not displayed", Home.isMoreOptionsDisplayed());
        assertTrue("More options is nor sibling with Minimal", Home.isMoreOptionSiblingMinimal());
    }

    @Test
    public void testBbuttonsValidation() {
        // Verify: “Minimal” text is displayed
        assertTrue("Minimal is not visible", Home.isMinimalVisible());

        // Step: Click "Add" button
        Home.clickAddButton();

        // Verify: "X" button is visible
        assertTrue("Button X is not visible", AddToDo.isXButtonVisible());

        // Step: Click "X" button
        AddToDo.clickXButton();

        // Verify: “You don’t have any todos” text is visible
        assertTrue("You don t have any todos in not visible", Home.isNotTodosVisible());
    }

    @Test
    public void testCAddNewItem() {
        // Verify: "Minimal" text is visible
        assertTrue("Minimal is not visible", Home.isMinimalVisible());

        // Step: Click "Add" button
        Home.clickAddButton();

        // Verify: FloatingActionButton is visible
        assertTrue("FloatingActionButton is not visible", AddToDo.isFloatingActionButtonVisible());

        // Step: Close keyboard
        Espresso.closeSoftKeyboard();

        // Verify: EditText is displayed as a descendant of a LinearLayout
        // “Remind me” text is displayed between two buttons
        assertTrue("EditText is not displayed as a descendant of a LinearLayout", AddToDo.isEditTextDescendantOfLinearLayout());
        assertTrue("Remind me is not between two buttons", AddToDo.isRemindMeLeftOfSwitchButton());
        assertTrue("Remind me is not between two buttons", AddToDo.isRemindMeRightOfIconImageButton());

        // Step: Add new item: "MY_TODO" (uppercase)
        AddToDo.typeToDoInUppercase("my todo");

        // Verify: "MY_TODO" is displayed above "Remind me"
        assertTrue("My todo is not displayed above remind me", AddToDo.isToDoAboveRemindMe());

        // Step: Turn the switch On
        AddToDo.turnSwitchOn_Off();

        // Verify: Text starting with "Reminder set" is displayed
        assertTrue("Reminder set is not displayed", AddToDo.isReminderSetDisplayed());

        // Get the date from AddToDo screen
        String theFirstDate = AddToDo.getFirstDate();

        // Step: Click "FloatingActionButton" button
        AddToDo.clickFloatingActionButton();

        // Verify: The date displayed when you added "MY_TODO" is now visible below this item
        String theSecondDate = Home.getSecondDate();
        String theNewFirstDate = Helpers.dateFormat(theFirstDate);
        if (theNewFirstDate.equals(theSecondDate)) {
            Assert.assertTrue("The date is not visible below", AddToDo.dateIsBelowToDo());
        }
    }

    @Test
    public void testDCheckNumberOfItemsAndDeleteOne() {
        // Step: Add 5 items (1 of them with text "My new to do item")
        AddToDo.addACertainNumberOfItems("My new to do item", 5, 1);

        // Verify: In the RecyclerView there are 5 items
        assertTrue("There are not 5 items in Recycle View", Home.is5ItemsinRecycleView());

        // Step: Remove the item with text "My new to do item", but don't create the matcher using the text
        Home.deleteASpecificItem();

        // Verify: Item isn't displayed anymore
        assertFalse("Item is displayed", Home.isSpecificItemNotDisplayed());
    }

    @Test
    public void testEEditASpecificItem() {
        // Step: Add 15 items
        // Step: The 8th item has name "This is what I need to test"
        AddToDo.addACertainNumberOfItems("This is what I need to test", 15, 7);

        // Verify: Item with text "This is what I need to test" is displayed
        assertTrue("\"This is what I need to test\" is not displayed", Home.isThisIsWhatINeedToTestDisplayed());

        // Step: Edit the name of the 8th item and go back to the list
        AddToDo.editToDoOn8thPosition(7, "This is my new to do test");

        // Verify: At the same position in the list, the item with text "This is what I need to test" is NOT displayed
        assertFalse("\"This is what I need to test\" is displayed", Home.isElementDisplayedOnPosition(7, withText("This is what I need to test")));

        // Verify: At the same position, the new text is displayed
        assertTrue("\"This is my new to do test\" is not displayed", Home.isElementDisplayedOnPosition(7, withText("This is my new to do test")));
    }

    @Test
    public void testFAddButtonVisibility() {
        // Step: Add 20 items
        AddToDo.addACertainNumberOfItems("My new to do item", 20, 1);

        // Step: SwipeUp the list
        Home.swipeUpToDoList();

        // Verify: Add button is NOT displayed
        assertFalse("Add button is displayed", Home.isAddButtonDisplayed());

        // Step: SwipeDown the list
        Home.swipeDownToDoList();

        // Verify: Add button is displayed
        assertTrue("Add button is not displayed", Home.isAddButtonDisplayed());

    }
}