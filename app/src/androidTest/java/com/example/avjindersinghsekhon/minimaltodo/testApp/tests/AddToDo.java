package com.example.avjindersinghsekhon.minimaltodo.testApp.tests;

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

public class AddToDo {
    public static boolean isXButtonVisible() {
        return Helpers.isCheckDisplay(allOf(is(instanceOf(ImageButton.class)), withParent(withId(R.id.toolbar))));
    }

    public static boolean isFloatingActionButtonVisible() {
        return Helpers.isCheckDisplay(withId(R.id.makeToDoFloatingActionButton));
    }

    public static boolean isEditTextDescendantOfLinearLayout() {
        return Helpers.isCheckDisplay(allOf((withId(R.id.userToDoEditText)), isDescendantOfA(withId(R.id.editTextParentLinearLayout))));
    }

    public static boolean isReminderSetDisplayed() {
        return Helpers.isCheckDisplay(withText(startsWith("Reminder set")));
    }

    public static void clickXButton() {
        Helpers.clickEl(allOf(is(instanceOf(ImageButton.class)), withParent(withId(R.id.toolbar))));
    }

    public static boolean isToDoAbove() {
        return Helpers.isTextAbove(withText("MY TODO"), withText(R.string.remind_me));
    }

    public static void turnSwitchOn() {
        Helpers.clickEl(withId(R.id.toDoHasDateSwitchCompat));
    }

    public static boolean isLeft() {
        return Helpers.isTextLeft(withId(R.id.userToDoRemindMeTextView), withId(R.id.toDoHasDateSwitchCompat));
    }

    public static boolean isRight() {
        return Helpers.isTextRight(withId(R.id.userToDoRemindMeTextView), withId(R.id.userToDoReminderIconImageButton));
    }

    public static void clickFloatingActionButton() {
        Helpers.clickEl(withId(R.id.makeToDoFloatingActionButton));
    }

    public static boolean isBelow() {
        return Helpers.isTextBelow(withId(R.id.todoListItemTimeTextView), withId(R.id.toDoListItemTextview));
    }

    public static void typeToDo(String args) {
        Helpers.performUppercase(withId(R.id.userToDoEditText), args);
    }

    public static String getSecondDate() {
        return Helpers.getText(withId(R.id.todoListItemTimeTextView));
    }

    public static String getFirstDate() {
        return Helpers.getText(withId(R.id.newToDoDateTimeReminderTextView));
    }
}