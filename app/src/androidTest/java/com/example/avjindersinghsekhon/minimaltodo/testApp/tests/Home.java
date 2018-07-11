package com.example.avjindersinghsekhon.minimaltodo.testApp.tests;

import android.widget.ImageView;

import com.example.avjindersinghsekhon.minimaltodo.R;

import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class Home {
    public static boolean isMinimalVisible() {
        return Helpers.isObjectDisplayed(withText(R.string.app_name));
    }

    public static boolean isNotTodosVisible() {
        return Helpers.isObjectDisplayed(withText(R.string.no_to_dos));
    }

    public static boolean isMinimalInToolbar() {
        return Helpers.isObjectDisplayed(allOf(withParent(withId(R.id.toolbar)), withText(R.string.app_name), isCompletelyDisplayed()));
    }

    public static boolean isAddButtonDisplayed() {
        return Helpers.isObjectDisplayed(withId(R.id.addToDoItemFAB));
    }

    public static void clickAddButton() {
        Helpers.clickElement(withId(R.id.addToDoItemFAB));
    }

    public static boolean isMoreOptionsDisplayed() {
        return Helpers.isObjectDisplayed(allOf(is(instanceOf(ImageView.class)), withParent(withParent(withId(R.id.toolbar)))));
    }

    public static boolean isMoreOptionSiblingMinimal() {
        return Helpers.isObjectDisplayed(allOf(withParent(withId(R.id.toolbar)), instanceOf(android.support.v7.widget.LinearLayoutCompat.class), hasSibling(withText(R.string.app_name))));
    }

    public static boolean isImageAboveYouDontHaveTodos() {
        return Helpers.isFirstObjectAboveSecondObject(allOf(is(instanceOf(ImageView.class)), withParent(withId(R.id.toDoEmptyView))), withText(R.string.no_to_dos));
    }

    public static boolean isAddButtonClickable() {
        return Helpers.isElementClickable(withId(R.id.addToDoItemFAB));
    }

    public static String getSecondDate() {
        return Helpers.getText(withId(R.id.todoListItemTimeTextView));
    }
}