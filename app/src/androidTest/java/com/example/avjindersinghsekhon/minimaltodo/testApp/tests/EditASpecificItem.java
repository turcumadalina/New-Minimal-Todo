package com.example.avjindersinghsekhon.minimaltodo.testApp.tests;

import org.junit.Assert;
import org.junit.Test;

public class EditASpecificItem extends EspressoTestBase {

    @Test
    public void testAdd15Items() {
        //Step 1. Add 15 items. The 8th item has name "This is what I need to test."
        Helpers.typeNewItemAction(15, 7);

        //Expected Result: Item with text "This is what I need to test" is displayed.
        Assert.assertTrue("This is what I need to test is not displayed.", AddItem.isThe8thItemTextVisible());

        //Step 2. Edit the name of the 8th item and go back to the list.
        AddItem.clickOnThe8thItemOfTheList();
        AddItem.typeNewTextInTheList();
        AddItem.clickFloatActionButton();

        //Expected Result: At the same position in the list, the item with text "This is what I need to test" is NOT displayed. At the same position, the new text is displayed.
        Assert.assertFalse("This is what i need to test displayed.", AddItem.isThe8thItemDisplayedAtSpecificPosition("This is what I need to test.", 7));
        Assert.assertTrue("The new text i need to test is not displayed ", AddItem.isThe8thItemDisplayedAtSpecificPosition("This is my new text I have to test.", 7));

    }
}
