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
        return Helpers.isObjectDisplayed(allOf(is(instanceOf(ImageButton.class)), withParent(withId(R.id.toolbar))));
    }

    public static boolean isFloatingActionButtonVisible() {
        return Helpers.isObjectDisplayed(withId(R.id.makeToDoFloatingActionButton));
    }

    public static boolean isEditTextDescendantOfLinearLayout() {
        return Helpers.isObjectDisplayed(allOf((withId(R.id.userToDoEditText)), isDescendantOfA(withId(R.id.editTextParentLinearLayout))));
    }

    public static boolean isReminderSetDisplayed() {
        return Helpers.isObjectDisplayed(withText(startsWith("Reminder set")));
    }

    public static void clickXButton() {
        Helpers.clickElement(allOf(is(instanceOf(ImageButton.class)), withParent(withId(R.id.toolbar))));
    }

    public static boolean isToDoAboveRemindMe() {
        return Helpers.isFirstObjectAboveSecondObject(withText("MY TODO"), withText(R.string.remind_me));
    }

    public static void turnSwitchOn_Off() {
        Helpers.clickElement(withId(R.id.toDoHasDateSwitchCompat));
    }

    public static boolean isLeftOfSwitchButton() {
        return Helpers.isFirstObjectLeftOfSecondObject(withId(R.id.userToDoRemindMeTextView), withId(R.id.toDoHasDateSwitchCompat));
    }

    public static boolean isRightOfIconImageButton() {
        return Helpers.isFirstObjectRightOfSecondObject(withId(R.id.userToDoRemindMeTextView), withId(R.id.userToDoReminderIconImageButton));
    }

    public static void clickFloatingActionButton() {
        Helpers.clickElement(withId(R.id.makeToDoFloatingActionButton));
    }

    public static boolean dateIsBelowToDo() {
        return Helpers.isTextBelow(withId(R.id.todoListItemTimeTextView), withId(R.id.toDoListItemTextview));
    }

    public static void typeToDoInUppercase(String itemName) {
        Helpers.TypeTextToUppercase(withId(R.id.userToDoEditText), itemName);
    }

    public static String getFirstDate() {
        return Helpers.getText(withId(R.id.newToDoDateTimeReminderTextView));
    }
}