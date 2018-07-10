package com.example.avjindersinghsekhon.minimaltodo.testApp.tests;

import android.support.test.espresso.Espresso;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class StartTheApp extends EspressoTestBase {
    @Test
    public void homePageValidation() {
        // Verify: “Minimal” and “You don’t have any todos” texts are visible
        assertTrue("Minimal is not displayed", Home.isMinimalVisible());
        assertTrue("You don’t have any todos is not displayed", Home.isNoToDosText());

        // Verify: “Minimal” text is displayed in a toolbar
        assertTrue("Minimal is not displayed", Home.isMinimalInToolbar());

        // Verify: An image is displayed above the “You don’t have any todos” text
        assertTrue("Image is not displayed above", Home.isImageAbove());

        // Verify: "Add" button is displayed and clickable
        assertTrue("Add button is not displayd", Home.isAddButton());
        assertTrue("Add button is not clickable", Home.isAddButtonClickable());

        // Verify: “More options” button is displayed and it has a sibling with the text "Minimal"
        assertTrue("More option is not displayd", Home.isMoreOptions());
        assertTrue("More option doesen't have a sibling", Home.isMoreOptionSibling());
    }

    @Test
    public void buttonsValidation() {
        // Verify: “Minimal” text is displayed
        assertTrue("Minimal is not displayed", Home.isMinimalVisible());

        // Step: Click "Add" button
        Home.clickAddButton();

        // Veryfy: "X" button is visible
        assertTrue("X button is not displayed", AddItem.isXButton());

        // Click "X" button
        AddItem.clickXButton();

        // Verify: “You don’t have any todos” text is visible
        assertTrue("You don’t have any todos is not displayed", Home.isNoToDosText());
    }

    @Test
    public void addItem() {
        // Verify: "Minimal" text is visible
        assertTrue("Minimal is not displayed", Home.isMinimalVisible());

        // Step: Click "Add" button and check if FloatingActionButton is visible
        Home.clickAddButton();
        assertTrue("FloatingActionButton is not displayed", AddItem.isFloatingActionButton());

        // Step: Close keyboard
        Espresso.closeSoftKeyboard();
        // Verify: EditText is displayed as a descendent of a LinearLayout,
        //         “Remind me” text is displayed between two buttons
        assertTrue("EditText is not displayed", AddItem.isEditText());
        assertTrue("Remind me is not to the left of button", AddItem.isRemindLeft());
        assertTrue("Remind me is not to the left of button", AddItem.isRemindRight());

        // Step: Add new item: "MY_TODO" (uppercase)
        AddItem.addItemUppercase("my todo");

        // Verify: "MY_TODO" is displayed above "Remind me"
        assertTrue("MY_TODO is not displayed above Remind me", Home.isMyToDoText());

        // Step: Turn the switch On
        AddItem.clickSwitchOn();

        // Verify: Text starting with "Reminder set" is displayed
        assertTrue("Reminder set is not displayed", AddItem.isReminderSet());

        // Verify: The data format
        String firstDate = AddItem.getData();

        // Step: Click "FloatingActionButton" button
        AddItem.clickFloatingActionButton();

        // Verify: The date displayed when you added "MY_TODO" is now visible below this item
        String secondDate = AddItem.getSecondData();
        String newFirstDate = Helpers.substring(firstDate);
        if (newFirstDate.equals(secondDate)) {
            assertTrue("The date is not displayed below", Home.isDateBelow());
        }
    }
}
