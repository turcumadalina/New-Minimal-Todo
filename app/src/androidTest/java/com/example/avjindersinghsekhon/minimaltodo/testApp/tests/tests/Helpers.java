package com.example.avjindersinghsekhon.minimaltodo.testApp.tests.tests;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;
import android.widget.TextView;
import org.hamcrest.Matcher;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;

public class Helpers {

    public static void waitForXSeconds(int waitTimeInSeconds) {
        long endTime = System.currentTimeMillis() + 1000 * waitTimeInSeconds;
        while (System.currentTimeMillis() <= endTime) {
        }
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
                TextView tv = (TextView) view;
                stringHolder[0] = tv.getText().toString();
            }
        });

        return stringHolder[0];
    }
}