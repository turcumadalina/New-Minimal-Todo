package com.example.avjindersinghsekhon.minimaltodo.testApp.tests;

import android.support.v7.widget.LinearLayoutCompat;
import android.widget.ImageView;

import com.example.avjindersinghsekhon.minimaltodo.R;

import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class Home {

    public static boolean isMinimalVisible() {
        return Helpers.checkIfUIObjectIsVisible(withText(R.string.app_name));
    }

    public static boolean isNoToDosTextVisible() {
        return Helpers.checkIfUIObjectIsVisible(withText(R.string.no_to_dos));
    }

    public static boolean isMinimalDisplayedInToolbar() {
        return Helpers.checkIfUIObjectIsVisible((allOf(withParent(withId(R.id.toolbar)), withText(R.string.app_name))));
    }

    public static boolean isAddButtonClickable() {
        return Helpers.checkIfUIObjectIsClickable(withId(R.id.addToDoItemFAB));
    }

    public static boolean isImageAboveNoToDoText() {
        return Helpers.isUIObjectAboveAnotherObject(allOf(is(instanceOf(ImageView.class)), withParent(withId(R.id.toDoEmptyView))), withText(R.string.no_to_dos));
    }

    public static String getSecondData() {
        return Helpers.getText(withId(R.id.todoListItemTimeTextView));
    }

    public static boolean isDateBelowOfToDoItemText() {
        return Helpers.isUIObjectBelowAnotherObject(withId(R.id.todoListItemTimeTextView), withId(R.id.toDoListItemTextview));
    }

    public static boolean isAddButtonVisible() {
        return Helpers.checkIfUIObjectIsVisible(withId(R.id.addToDoItemFAB));
    }

    public static boolean isMoreOptionsVisible() {
        return Helpers.checkIfUIObjectIsVisible(allOf(is(instanceOf(ImageView.class)), withParent(withParent(withId(R.id.toolbar)))));
    }

    public static boolean isMoreOptionSiblingWithMinimalText() {
        return Helpers.checkIfUIObjectIsVisible(allOf(withParent(withId(R.id.toolbar)), instanceOf(LinearLayoutCompat.class),
                hasSibling(withText(R.string.app_name))));
    }

    public static void clickAddButton() {
        Helpers.clickElement(withId(R.id.addToDoItemFAB));
    }

    public static boolean isItemTextAboveRemindMe() {
        return Helpers.isUIObjectAboveAnotherObject(withText("MY TODO"), withText(R.string.remind_me));
    }
}