package com.example.avjindersinghsekhon.minimaltodo.testApp.tests;

import android.support.v7.widget.LinearLayoutCompat;
import android.widget.ImageView;

import com.example.avjindersinghsekhon.minimaltodo.R;

import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.avjindersinghsekhon.minimaltodo.testApp.tests.Helpers.isVisible;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class Home {
    public static boolean isMinimalVisible() {
        return isVisible(withText(R.string.app_name));
    }

    public static boolean isYouDontHaveAnyTodosVisible() {
        return isVisible(withText(R.string.no_to_dos));
    }

    public static boolean isMinimalVisibleInToolbar() {
        return isVisible(allOf(withParent(withId(R.id.toolbar)), withText(R.string.app_name)));
    }

    public static boolean isAddButtonVisible() {
        return isVisible(withId(R.id.addToDoItemFAB));
    }

    public static boolean isMoreOptionsSiblingWithMinimalVisible() {
        return isVisible(allOf(withParent(withId(R.id.toolbar)), instanceOf(LinearLayoutCompat.class), hasSibling(withText(R.string.app_name))));
    }

    public static boolean isAddButtonClickable() {
        return Helpers.isUIObjectClickable(withId(R.id.addToDoItemFAB));
    }

    public static boolean isYouDontHaveAnyTodosTextAboveImageView() {
        return Helpers.isFirstMatcherAboveSecondMatcher(allOf(is(instanceOf(ImageView.class)), withParent(withId(R.id.toDoEmptyView))), withText(R.string.no_to_dos));
    }

    public static boolean isRemindeMeLeftOfDateSwitch() {
        return Helpers.isFirstMatcherToTheLeftOfSecondMatcher(withText(R.string.remind_me), withId(R.id.toDoHasDateSwitchCompat));
    }

    public static boolean isRemindeMeRightOfDateSwitch() {
        return Helpers.isFirstMatcherToTheRightOfSecondMatcher(withText(R.string.remind_me), withId(R.id.userToDoReminderIconImageButton));
    }

}
