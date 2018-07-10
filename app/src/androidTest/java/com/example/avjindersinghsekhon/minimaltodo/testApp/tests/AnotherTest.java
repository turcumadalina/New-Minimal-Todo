package com.example.avjindersinghsekhon.minimaltodo.testApp.tests;

import android.support.test.espresso.Espresso;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class AnotherTest extends EspressoTestBase {
    @Test
    public void testViewItems() {

        // Verify: “Minimal” and “You don’t have any todos” texts are visible
        Assert.assertTrue("Minimal is not visible", Home.isMinimalVisible());
        Assert.assertTrue("You don’t have any todos is not visible", Home.isNotTodosVisible());

        // Verify: “Minimal” text is displayed in a toolbar
        Assert.assertTrue("Minimal is not displayed in toolbar", Home.isMinimalInToolbar());

        // Verify: An image is displayed above the “You don’t have any todos” text
        Assert.assertTrue("An image is not displayed above todo text", Home.isImageAbove());

        // Verify: "Add" button is displayed and clickable
        Assert.assertTrue("Add button is not displayed", Home.isAddButtonDisplayed());
        Assert.assertTrue("Add button is not clickable", Home.isAddButtonClickable());

        // Verify: “More options” button is displayed and it has a sibling with the text "Minimal"
        Assert.assertTrue("More options is not displayed", Home.isMoreOptionsDisplayed());
        Assert.assertTrue("More options is nor sibling with Minimal", Home.isMoreOptionSiblingMinimal());
    }

    @Test
    public void buttonsValidation() {
        // Verify: “Minimal” text is displayed
        Assert.assertTrue("Minimal is not visible", Home.isMinimalVisible());

        // Step: Click "Add" button
        Home.clickAddButton();

        // Verify: "X" button is visible
        Assert.assertTrue("Button X is not visible", AddToDo.isXButtonVisible());

        // Step: Click "X" button
        AddToDo.clickXButton();

        // Verify: “You don’t have any todos” text is visible
        Assert.assertTrue("You don t have any todos in not visible", Home.isNotTodosVisible());
    }

    @Test
    public void addNewItem() {
        // Verify: "Minimal" text is visible
        assertTrue("Minimal is not visible", Home.isMinimalVisible());

        // Step: Click "Add" button
        Home.clickAddButton();

        // Verify: FloatingActionButton is visible
        assertTrue("FloatinActionButton is not visible", AddToDo.isFloatingActionButtonVisible());

        // Step: Close keyboard
        Espresso.closeSoftKeyboard();

        // Verify: EditText is displayed as a descendent of a LinearLayout
        // “Remind me” text is displayed between two buttons
        assertTrue("EditText is not displayed as a descendent of a LinearLayout", AddToDo.isEditTextDescendantOfLinearLayout());
        assertTrue("Remind me is not between two buttons", AddToDo.isLeft());
        assertTrue("Remind me is not between two buttons", AddToDo.isRight());

        // Step: Add new item: "MY_TODO" (uppercase)
        AddToDo.typeToDo("my todo");

        // Verify: "MY_TODO" is displayed above "Remind me"
        assertTrue("My todo is not displayed above remind me", AddToDo.isToDoAbove());

        // Step: Turn the switch On
        AddToDo.turnSwitchOn();

        // Verify: Text starting with "Reminder set" is displayed
        assertTrue("Reminder set is not displayed", AddToDo.isReminderSetDisplayed());
        String theFirstDate = Home.getFirstDate();

        // Step: Click "FloatingActionButton" button
        AddToDo.clickFloatingActionButton();

        // Verify: The date displayed when you added "MY_TODO" is now visible below this item
        String theSecondDate = AddToDo.getSecondDate();
        String theNewFirstDate = Helpers.substring(theFirstDate);
        if (theNewFirstDate.equals(theSecondDate)) {
            Assert.assertTrue("The date is not visible below", AddToDo.isBelow());
        }
    }
}
