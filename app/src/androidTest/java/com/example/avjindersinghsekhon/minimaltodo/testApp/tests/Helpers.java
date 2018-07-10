package com.example.avjindersinghsekhon.minimaltodo.testApp.tests;

import android.support.test.espresso.AppNotIdleException;
import android.support.test.espresso.NoMatchingRootException;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;
import android.widget.TextView;

import junit.framework.AssertionFailedError;

import org.hamcrest.Matcher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.PositionAssertions.isAbove;
import static android.support.test.espresso.assertion.PositionAssertions.isBelow;
import static android.support.test.espresso.assertion.PositionAssertions.isLeftOf;
import static android.support.test.espresso.assertion.PositionAssertions.isRightOf;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

public class Helpers {

    public static boolean checkIfUIObjectIsVisible(Matcher<View> matcher) {

        try {
            onView(matcher).check(matches(isDisplayed()));
            return true;
        } catch (NoMatchingViewException | AppNotIdleException | AssertionFailedError | NoMatchingRootException e) {
            return false;
        }
    }

    public static boolean checkIfUIObjectIsClickable(Matcher<View> matcher) {

        try {
            onView(matcher).check(matches(isClickable()));
            return true;
        } catch (NoMatchingViewException | AppNotIdleException | AssertionFailedError | NoMatchingRootException e) {
            return false;
        }
    }

    public static boolean isUIObjectAbove(Matcher<View> matcher, Matcher<View> matcher2) {
        try {
            onView(matcher).check(isAbove(matcher2));
            return true;
        } catch (NoMatchingViewException | AppNotIdleException | AssertionFailedError | NoMatchingRootException e) {
            return false;
        }
    }

    public static boolean isUIObjectBelow(Matcher<View> matcher, Matcher<View> matcher2) {
        try {
            onView(matcher).check(isBelow(matcher2));
            return true;
        } catch (NoMatchingViewException | AppNotIdleException | AssertionFailedError | NoMatchingRootException e) {
            return false;
        }
    }

    public static boolean isRemindLeft(Matcher<View> matcher, Matcher<View> matcher2) {
        try {
            onView(matcher).check(isLeftOf(matcher2));
            return true;
        } catch (NoMatchingViewException | AppNotIdleException | AssertionFailedError | NoMatchingRootException e) {
            return false;
        }
    }

    public static boolean isRemindRight(Matcher<View> matcher, Matcher<View> matcher2) {
        try {
            onView(matcher).check(isRightOf(matcher2));
            return true;
        } catch (NoMatchingViewException | AppNotIdleException | AssertionFailedError | NoMatchingRootException e) {
            return false;
        }
    }

    public static void addItemUppercase(Matcher<View> matcher, String args) {
        onView(matcher).perform(typeText(args.toUpperCase()));
    }

    public static void clickEl(Matcher<View> matcher) {
        onView(matcher).perform(click());
    }

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

    public static String substring(String arg) {
        String formatedData = arg.substring(16, 29);
        SimpleDateFormat parser = new SimpleDateFormat("dd MMM,yyyy");
        Date date = null;
        try {
            date = parser.parse(formatedData);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM, yyyy");
        return formatter.format(date);
    }
}
