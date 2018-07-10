package com.example.avjindersinghsekhon.minimaltodo.testApp.tests;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Matcher;

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


    public static String substring(String str) {
        //String str= new String();
        String day = str.substring(16, 18);
        String month = str.substring(19, 22);
        String year = str.substring(24, 28);
        return (month + " " + day + "," + year);
    }

    public static void clickEl(Matcher<View> matcher) {
        onView(matcher).perform(click());
    }

    public static boolean isCheckDisplay(Matcher<View> matcher) {
        try {
            onView(matcher).check(matches(isDisplayed()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isTextAbove(Matcher<View> matcher, Matcher<View> matcher2) {
        try {
            onView(matcher).check(isAbove(matcher2));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isElemClickable(Matcher<View> matcher) {
        try {
            onView(matcher).check(matches(isClickable()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isTextLeft(Matcher<View> matcher, Matcher<View> matcher2) {
        try {
            onView(matcher).check(isLeftOf(matcher2));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isTextRight(Matcher<View> matcher, Matcher<View> matcher2) {
        try {
            onView(matcher).check(isRightOf(matcher2));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isTextBelow(Matcher<View> matcher, Matcher<View> matcher2) {
        try {
            onView(matcher).check(isBelow(matcher2));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void performUppercase(Matcher<View> matcher, String args) {
        onView(matcher).perform(typeText(args.toUpperCase()));
    }
}
