package com.example.avjindersinghsekhon.minimaltodo.testApp.tests;

import org.junit.Test;

public class AddOrRemoveItems extends EspressoTestBase {

    @Test
    public void EditItems(){
        //Step 1. Add 5 items (1 of them with text "My new to do item")
        Helpers.typeNewItemAction(4, 3);
        //Expected Result: In the RecyclerView there are 5 items

        //Step 2. Remove the item with text "My new to do item", but don't create the matcher using the text

        //Expected Result: Item isn't displayed anymore
    }
}
