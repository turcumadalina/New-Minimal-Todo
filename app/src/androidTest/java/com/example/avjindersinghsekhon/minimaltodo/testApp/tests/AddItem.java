package com.example.avjindersinghsekhon.minimaltodo.testApp.tests;

import android.widget.EditText;
import android.widget.ImageButton;

import com.example.avjindersinghsekhon.minimaltodo.R;

import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.avjindersinghsekhon.minimaltodo.R.id.userToDoEditText;
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

    public static boolean isMyTodoTextAboveRemindMe() {
        return Helpers.isFirstMatcherAboveSecondMatcher(withText("MY TODO"), withText(R.string.remind_me));
    }

    public static boolean isDateBelowToMyTodoText() {
        return Helpers.isFirstMatcherBelowSecondMatcher(withId(R.id.todoListItemTimeTextView), withId(R.id.toDoListItemTextview));
    }

    public static void typeMyTodoText() {
        Helpers.typeUpperCaseTextAction(withId(userToDoEditText), "my todo");
    }

    public static void typeRandomText() {
        String itemName = Helpers.getRandomString(5);
        Helpers.typeTextAction(withId(R.id.userToDoEditText), itemName);
    }

    public static void swipeUpAction() {
        Helpers.swipeUpAction(allOf(withId(R.id.toDoRecyclerView)));
    }

    public static void swipeDownAction() {
        Helpers.swipeDownAction(allOf(withId(R.id.toDoRecyclerView)));
    }

    public static void typeRandomItemText() {
        Helpers.typeTextAction(withId(R.id.userToDoEditText), "This is what I need to test.");
    }

    public static boolean isItemTextVisible() {
        return Helpers.isVisible(withText("This is what I need to test."));
    }

    public static void clickOnASpecificItemOfTheList() {
        Helpers.clickAction(withText("This is what I need to test."));
    }

    public static void typeNewTextInTheList() {
        Helpers.replaceTextAction(withId(R.id.userToDoEditText), "This is my new text I have to test.");
    }

    public static boolean isItemDisplayedAtSpecificPosition(String itemNameIVerify, int newItemPosition) {
        return Helpers.isVisible(allOf(withId(R.id.toDoListItemTextview), withText(itemNameIVerify), isDescendantOfA(Helpers.childAtPosition(withId(R.id.toDoRecyclerView), newItemPosition))));
    }

    public static boolean isAddButtonVisible() {
        return Helpers.isVisible(allOf(withId(R.id.addToDoItemFAB), isCompletelyDisplayed()));
    }
}

