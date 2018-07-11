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

    public static boolean isNoToDosText() {
        return Helpers.checkIfUIObjectIsVisible(withText(R.string.no_to_dos));
    }

    public static boolean isMinimalInToolbar() {
        return Helpers.checkIfUIObjectIsVisible((allOf(withParent(withId(R.id.toolbar)), withText(R.string.app_name))));
    }

    public static boolean isAddButtonClickable() {
        return Helpers.checkIfUIObjectIsClickable(withId(R.id.addToDoItemFAB));
    }

    public static boolean isImageAbove() {
        return Helpers.isUIObjectAbove(allOf(is(instanceOf(ImageView.class)), withParent(withId(R.id.toDoEmptyView))), withText(R.string.no_to_dos));
    }

    public static boolean isDateBelow() {
        return Helpers.isUIObjectBelow(withId(R.id.todoListItemTimeTextView), withId(R.id.toDoListItemTextview));
    }

    public static boolean isAddButton() {
        return Helpers.checkIfUIObjectIsVisible(withId(R.id.addToDoItemFAB));
    }

    public static boolean isMoreOptions() {
        return Helpers.checkIfUIObjectIsVisible(allOf(is(instanceOf(ImageView.class)), withParent(withParent(withId(R.id.toolbar)))));
    }

    public static boolean isMoreOptionSibling() {
        return Helpers.checkIfUIObjectIsVisible(allOf(withParent(withId(R.id.toolbar)), instanceOf(LinearLayoutCompat.class),
                hasSibling(withText(R.string.app_name))));
    }

    public static void clickAddButton() {
        Helpers.clickEl(withId(R.id.addToDoItemFAB));
    }

    public static boolean isItemTextAboveRemindMe() {
        return Helpers.isUIObjectAbove(withText("MY TODO"), withText(R.string.remind_me));
    }

}
