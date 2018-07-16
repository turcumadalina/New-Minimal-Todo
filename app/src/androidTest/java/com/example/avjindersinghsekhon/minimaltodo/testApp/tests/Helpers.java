package com.example.avjindersinghsekhon.minimaltodo.testApp.tests;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.PositionAssertions.isAbove;
import static android.support.test.espresso.assertion.PositionAssertions.isBelow;
import static android.support.test.espresso.assertion.PositionAssertions.isLeftOf;
import static android.support.test.espresso.assertion.PositionAssertions.isRightOf;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.allOf;


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
                TextView tv = (TextView) view;
                stringHolder[0] = tv.getText().toString();
            }
        });
        return stringHolder[0];
    }

    public static String dateFormat(String dateInput) {
        String input = dateInput.substring(16, 29);
        SimpleDateFormat parser = new SimpleDateFormat("dd MMM,yyyy");
        Date date = null;
        try {
            date = parser.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM, yyyy");
        return formatter.format(date);
    }

    public static void clickElement(Matcher<View> matcher) {
        onView(matcher).perform(click());
    }

    public static boolean isObjectDisplayed(Matcher<View> matcher) {
        try {
            onView(allOf(matcher, isCompletelyDisplayed())).check(matches(isDisplayed()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isFirstObjectAboveSecondObject(Matcher<View> matcher, Matcher<View> matcher2) {
        try {
            onView(matcher).check(isAbove(matcher2));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isElementClickable(Matcher<View> matcher) {
        try {
            onView(matcher).check(matches(isClickable()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isFirstObjectLeftOfSecondObject(Matcher<View> matcher, Matcher<View> matcher2) {
        try {
            onView(matcher).check(isLeftOf(matcher2));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isFirstObjectRightOfSecondObject(Matcher<View> matcher, Matcher<View> matcher2) {
        try {
            onView(matcher).check(isRightOf(matcher2));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isFirstObjectBelowSecondObject(Matcher<View> matcher, Matcher<View> matcher2) {
        try {
            onView(matcher).check(isBelow(matcher2));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void TypeTextToUppercase(Matcher<View> matcher, String textInput) {
        onView(matcher).perform(typeText(textInput.toUpperCase()));
    }

    private static String generateRandomString(int stringLength) {
        final String AB = "abcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(stringLength);
        for (int i = 0; i < stringLength; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public static void AddItems(Matcher<View> matcher, String specialItem, int numberOfItems, int positionOfSpecialItem) {
        for (int i = 0; i < numberOfItems; i++) {
            if (i == positionOfSpecialItem) {
                Home.clickAddButton();
                onView(matcher).perform(typeText(specialItem));
                AddToDo.clickFloatingActionButton();
            } else {
                Home.clickAddButton();
                onView(matcher).perform(typeText(generateRandomString(10)));
                AddToDo.clickFloatingActionButton();
            }
        }
    }

    public static Matcher<View> nthChildOf(final Matcher<View> parentMatcher, final int childPosition) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("with " + childPosition + " child view of type parentMatcher");
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view.getParent() instanceof ViewGroup)) {
                    return parentMatcher.matches(view.getParent());
                }

                ViewGroup group = (ViewGroup) view.getParent();
                return parentMatcher.matches(view.getParent()) && group.getChildAt(childPosition).equals(view);
            }
        };
    }

    public static void deleteTheSpecialItem(Matcher<View> matcher, int positionOfItem) {
        onView(nthChildOf(matcher, positionOfItem)).perform(swipeLeft());
    }

    public static int getRecyclerViewChildCount(Matcher<View> matcher) {
        final int[] count = {0};
        onView(matcher).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(RecyclerView.class);
            }

            @Override
            public String getDescription() {
                return "getting child count";
            }

            @Override
            public void perform(UiController uiController, View view) {
                RecyclerView rv = (RecyclerView) view;
                count[0] = rv.getChildCount();
            }
        });
        return count[0];
    }

    public static boolean checkNumberOfItemsInRecycleView(Matcher<View> matcher, int numbersToCheck) {
        if (getRecyclerViewChildCount(matcher) == numbersToCheck) {
            return true;
        } else {
            return false;
        }
    }

    public static void swipeUpList(Matcher<View> matcher) {
        onView(matcher).perform(swipeUp());
    }

    public static void swipeDownList(Matcher<View> matcher) {
        onView(matcher).perform(swipeDown());
    }

    public static void editTheSpecialItem(Matcher<View> matcher, Matcher<View> elementToModifyMatcher, Matcher<View> floatingButton, int positionOfItem, String myNewToDo) {
        onView(nthChildOf(matcher, positionOfItem)).perform(click());
        onView((elementToModifyMatcher)).perform(clearText());
        onView((elementToModifyMatcher)).perform(typeText(myNewToDo));
        onView((floatingButton)).perform(click());
    }

    public static boolean isObjectDisplayedOnASpecificPosition(Matcher<View> parentMatcher, int childPosition, Matcher<View> descendantMatcher) {
        try {
            onView(allOf(nthChildOf(parentMatcher, childPosition), hasDescendant(descendantMatcher), isCompletelyDisplayed())).check(matches(isDisplayed()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}