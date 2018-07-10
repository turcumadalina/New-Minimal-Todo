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
        return Helpers.isCheckDisplay(withText(R.string.app_name));
    }

    public static boolean isNotTodosVisible() {
        return Helpers.isCheckDisplay(withText(R.string.no_to_dos));
    }

    public static boolean isMinimalInToolbar() {
        return Helpers.isCheckDisplay(allOf(withParent(withId(R.id.toolbar)), withText(R.string.app_name), isCompletelyDisplayed()));
    }

    public static boolean isAddButtonDisplayed() {
        return Helpers.isCheckDisplay(withId(R.id.addToDoItemFAB));
    }

    public static void clickAddButton() {
        Helpers.clickEl(withId(R.id.addToDoItemFAB));
    }

    public static boolean isMoreOptionsDisplayed() {
        return Helpers.isCheckDisplay(allOf(is(instanceOf(ImageView.class)), withParent(withParent(withId(R.id.toolbar)))));
    }

    public static boolean isMoreOptionSiblingMinimal() {
        return Helpers.isCheckDisplay(allOf(withParent(withId(R.id.toolbar)), instanceOf(android.support.v7.widget.LinearLayoutCompat.class), hasSibling(withText(R.string.app_name))));
    }

    public static boolean isImageAbove() {
        return Helpers.isTextAbove(allOf(is(instanceOf(ImageView.class)), withParent(withId(R.id.toDoEmptyView))), withText(R.string.no_to_dos));
    }

    public static boolean isAddButtonClickable() {
        return Helpers.isElemClickable(withId(R.id.addToDoItemFAB));
    }
}