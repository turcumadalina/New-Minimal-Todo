package com.example.avjindersinghsekhon.minimaltodo.testApp.tests;

import junit.framework.Assert;

import org.junit.Test;

public class AddButtonVisibility extends EspressoTestBase {

    @Test
    public void testAAdd20Buttons() {
        //Step 1. Add 20 items
        Helpers.typeNewItemAction(20, 1);

        //Step 1. SwipeUp the list
        AddItem.swipeUpAction();

        //Expected Result: Add button is NOT displayed
        Assert.assertFalse("Add button is displayed", Home.isAddButtonVisible());

        //Step 1. SwipeDown the list
        AddItem.swipeDownAction();

        //Expected Result: Add button is displayed
        Assert.assertTrue("Add button is not displayed", Home.isAddButtonVisible());
    }
}
