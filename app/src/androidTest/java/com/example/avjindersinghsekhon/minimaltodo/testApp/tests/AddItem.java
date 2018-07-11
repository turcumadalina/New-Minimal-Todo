package com.example.avjindersinghsekhon.minimaltodo.testApp.tests;

import android.widget.EditText;
import android.widget.ImageButton;

import com.example.avjindersinghsekhon.minimaltodo.R;

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
}