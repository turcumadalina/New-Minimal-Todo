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
import static org.hamcrest.core.StringStartsWith.startsWith;

public class AddItem {
    public static boolean isXButtonVisible() {
        return Helpers.isVisible(allOf(is(instanceOf(ImageButton.class)), withParent(withId(R.id.toolbar))));
    }

    public static boolean isFloatingActionButtonVisible() {
        return Helpers.isVisible(withId(R.id.makeToDoFloatingActionButton));
    }

    public static boolean isEditTextVisible() {
        return Helpers.isVisible(allOf(is(instanceOf(EditText.class)), isDescendantOfA(withId(R.id.editTextParentLinearLayout))));
    }

    public static boolean isReminderSetVisible() {
        return Helpers.isVisible(withText(startsWith("Reminder set")));
    }

    public static void clickAddButton() {
        Helpers.clickAction(withId(R.id.addToDoItemFAB));
    }

    public static void clickXButton() {
        Helpers.clickAction(allOf(is(instanceOf(ImageButton.class)), withParent((withId(R.id.toolbar)))));
    }

    public static void clickSwitchOn() {
        Helpers.clickAction(withId(R.id.toDoHasDateSwitchCompat));
    }

    public static void clickFloatActionButton() {
        Helpers.clickAction(withId(R.id.makeToDoFloatingActionButton));
    }

    public static boolean isAboveText() {
        return Helpers.isAboveCheck(withText("MY TODO"), withText(R.string.remind_me));
    }

    public static boolean isBelowItem() {
        return Helpers.isBelowCheck(withId(R.id.todoListItemTimeTextView), withId(R.id.toDoListItemTextview));
    }

    public static void typeText() {
        Helpers.typeTextAction(withId(R.id.userToDoEditText), "my todo");
    }
}
