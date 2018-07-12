package com.example.avjindersinghsekhon.minimaltodo.testApp.tests;

import junit.framework.Assert;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddButtonVisibility extends EspressoTestBase {

    @Test
    public void testAAdd20Buttons() throws InterruptedException {
        //Step 1. Add 20 items
        Helpers.typeNewItemAction(20);
        Thread.sleep(5000);
    }

    @Test
    public void testBSwipeUp() throws InterruptedException {
        //Step 1. SwipeUp the list
        AddItem.swipeUpAction();
        Thread.sleep(5000);

        //Expected Result: Add button is NOT displayed
        Assert.assertTrue("Add button is displayed", Home.isAddButtonNotVisible());
    }

    @Test
    public void testCSwipeDown() throws InterruptedException {
        //Step 1. SwipeDown the list
        AddItem.swipeDownAction();
        Thread.sleep(5000);

        //Expected Result: Add button is displayed
        Assert.assertTrue("Add button is not displayed", Home.isAddButtonVisible());
    }
}
