package com.example.avjindersinghsekhon.minimaltodo.testApp.tests;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Matcher;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.PositionAssertions.isAbove;
import static android.support.test.espresso.assertion.PositionAssertions.isBelow;
import static android.support.test.espresso.assertion.PositionAssertions.isLeftOf;
import static android.support.test.espresso.assertion.PositionAssertions.isRightOf;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.core.IsNot.not;

public class Helpers {

    public static String getText(final Matcher<View> matcher) {
        final String[] stringHolder = {null};
        onView(matcher).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "getting text from a TextView";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TextView tv = (TextView) view; //Save, because of check in getConstraints()
                stringHolder[0] = tv.getText().toString();
            }
        });
        return stringHolder[0];
    }

    public static String formatDate(String actualDate) throws ParseException {
        SimpleDateFormat actualFormatDate = new SimpleDateFormat("dd MMM, yyyy, hh:mm", Locale.US);
        Date actualDate1 = actualFormatDate.parse(actualDate);
        actualFormatDate.applyPattern("MMM dd, yyyy  hh:mm");

        return actualFormatDate.format(actualDate1);
    }

    public static boolean isVisible(Matcher<View> matcher) {
        try {
            onView(matcher).check(matches(isDisplayed()));
            return true;
        } catch (NoMatchingViewException e) {
            // View is not in hierarchy
            return false;
        }
    }

    public static boolean isNotVisible(Matcher<View> matcher) {
        try {
            onView(matcher).check(matches(not(isDisplayed())));
            return true;
        } catch (NoMatchingViewException e) {
            // View is not in hierarchy
            return false;
        }
    }

    public static boolean isUIObjectClickable(Matcher<View> matcher) {
        try {
            onView(matcher).check(matches(isClickable()));
            return true;
        } catch (NoMatchingViewException e) {
            // View is not in hierarchy
            return false;
        }
    }

    public static void clickAction(Matcher<View> matcher) {
        onView(matcher).perform(click());
    }

    public static boolean isFirstMatcherAboveSecondMatcher(Matcher<View> matcher1, Matcher<View> matcher2) {
        try {
            onView(matcher1).check(isAbove(matcher2));
            return true;
        } catch (NoMatchingViewException e) {
            // View is not in hierarchy
            return false;
        }
    }

    public static boolean isFirstMatcherToTheLeftOfSecondMatcher(Matcher<View> matcher1, Matcher<View> matcher2) {
        try {
            onView(matcher1).check(isLeftOf(matcher2));
            return true;
        } catch (NoMatchingViewException e) {
            // View is not in hierarchy
            return false;
        }
    }

    public static boolean isFirstMatcherToTheRightOfSecondMatcher(Matcher<View> matcher1, Matcher<View> matcher2) {
        try {
            onView(matcher1).check(isRightOf(matcher2));
            return true;
        } catch (NoMatchingViewException e) {
            // View is not in hierarchy
            return false;
        }
    }

    public static boolean isFirstMatcherBelowSecondMatcher(Matcher<View> matcher1, Matcher<View> matcher2) {
        try {
            onView(matcher1).check(isBelow(matcher2));
            return true;
        } catch (NoMatchingViewException e) {
            // View is not in hierarchy
            return false;
        }
    }

    public static void typeUpperCaseTextAction(Matcher<View> matcher, String args) {
        onView(matcher).perform(typeText(args.toUpperCase()));
    }

    public static void typeTextAction(Matcher<View> matcher, String itemName) {
        onView(matcher).perform(typeText(itemName));
    }

    public static String getRandomString(int newItemStringLength) {
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(newItemStringLength);

        for (int i = 0; i < newItemStringLength; i++) {
            sb.append(AB.charAt(random.nextInt(AB.length())));
        }

        return sb.toString();
    }

    public static void typeNewItemAction(int xItemsAdded) {
        for (int i = 1; i <= xItemsAdded; i++) {
            AddItem.clickAddButton();
            AddItem.typeRandomText();
            AddItem.clickFloatActionButton();
        }
    }

    public static void swipeUpAction(Matcher<View> matcher) {
        onView(matcher).perform(swipeUp());
    }

    public static void swipeDownAction(Matcher<View> matcher) {
        onView(matcher).perform(swipeDown());
    }
}
