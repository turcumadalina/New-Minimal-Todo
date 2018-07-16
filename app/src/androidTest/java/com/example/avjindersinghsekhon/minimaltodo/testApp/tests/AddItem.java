package com.example.avjindersinghsekhon.minimaltodo.testApp.tests;

import android.widget.EditText;
import android.widget.ImageButton;

import com.example.avjindersinghsekhon.minimaltodo.R;

import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

public class AddItem {

    public static boolean isXButtonDisplayed() {
        return Helpers.checkIfUIObjectIsVisible(allOf(is(instanceOf(ImageButton.class)), withParent(withId(R.id.toolbar))));
    }

    public static void clickXButton() {
        Helpers.clickElement(allOf(is(instanceOf(ImageButton.class)), withParent(withId(R.id.toolbar))));
    }

    public static boolean isFloatingActionButtonDisplayed() {
        return Helpers.checkIfUIObjectIsVisible(withId(R.id.toDoCustomTextInput));
    }

    public static boolean isEditTextDisplayd() {
        return Helpers.checkIfUIObjectIsVisible(allOf(is(instanceOf(EditText.class)), withParent(withId(R.id.toDoCustomTextInput))));
    }

    public static void clickButtonToSwitchOnOrOff() {
        Helpers.clickElement(withId(R.id.toDoHasDateSwitchCompat));
    }

    public static boolean isReminderSetDisplayed() {
        return Helpers.checkIfUIObjectIsVisible(withText(startsWith("Reminder set")));
    }

    public static void clickFloatingActionButton() {
        Helpers.clickElement(withId(R.id.makeToDoFloatingActionButton));
    }

    public static boolean isRemindLeftOfSwitchButton() {
        return Helpers.isUIObjectLeftOfAnotherUIObject(withText(R.string.remind_me), withId(R.id.toDoHasDateSwitchCompat));
    }

    public static boolean isRemindRightOfImageButton() {
        return Helpers.isUIObjectRightOfAnotherUIObject(withText(R.string.remind_me), withId(R.id.userToDoReminderIconImageButton));
    }

    public static void addItemUppercase(String args) {
        Helpers.typeItemUppercase(withId(R.id.userToDoEditText), args);
    }

    public static String getData() {
        return Helpers.getText(withId(R.id.newToDoDateTimeReminderTextView));
    }

    public static void addSpecificNoOfItems(String specificItem, int positionOfSpecificText, int numberOfItems) {
        Helpers.addToDoItems(withId(R.id.userToDoEditText), specificItem, positionOfSpecificText, numberOfItems);
    }

    public static boolean isItemWithTextThisIsWhatINeedToTestIsDisplayed() {
        return Helpers.checkIfUIObjectIsVisible(withText("This is what I need to test"));
    }

    public static void EditToDoItem8thText() {
        Helpers.clickElement(withText("This is what I need to test"));
        Helpers.editToDoItemTextAndClickFloatingButton(withText("This is what I need to test"), " This is tne new text");
    }

    public static boolean isToDoItemDisplayedAtSpecificPosition(String toDoName, int itemPosition) {
        return Helpers.checkIfUIObjectIsVisible(allOf(withId(R.id.toDoListItemTextview), withText(toDoName), isDescendantOfA(Helpers.childAtPosition(withId(R.id.toDoRecyclerView), itemPosition))));
    }

    public static boolean checkNoOfItemsInRecyclerView() {
        return Helpers.getNoOfItemsInRecyclerView(withId(R.id.toDoRecyclerView), 5);
    }

    public static void deleteItemWithTextMyNewToDoItem() {
        Helpers.removeSpecificItem(withId(R.id.toDoRecyclerView), 2);
    }

    public static boolean isDeleteItemWithTextMyNewToDoItemDisplayed() {
        return Helpers.checkIfUIObjectIsVisible(withText("My new to do item"));
    }

    public static void swipeUpTheList() {
        Helpers.swipeUpListOfToDosItems(withId(R.id.toDoRecyclerView));
    }

    public static boolean isAddButtonDisplayed() {
        return Helpers.checkIfUIObjectIsVisible(withId(R.id.addToDoItemFAB));
    }

    public static void swipeDownTheList() {
        Helpers.swipeDownListOfToDosItems(withId(R.id.toDoRecyclerView));
    }
}