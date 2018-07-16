package com.example.avjindersinghsekhon.minimaltodo.testApp.tests;

import junit.framework.Assert;

import org.junit.Test;

public class AddButtonVisibility extends EspressoTestBase {

    @Test
    public void testAddButtonVisibility() {
        //Step 1. Add 20 items
        Helpers.typeNewItemAction(19, 7);

        //Step 1. SwipeUp the list
        AddItem.swipeUpAction();

        //Expected Result: Add button is NOT displayed
        Assert.assertFalse("Add button is displayed.", AddItem.isAddButtonVisible());

        //Step 1. SwipeDown the list
        AddItem.swipeDownAction();

        //Expected Result: Add button is displayed
        Assert.assertTrue("Add button is not displayed.", AddItem.isAddButtonVisible());

    }
}
