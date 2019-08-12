package com.routeone.interview;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class StoreRegisterTest {

    StoreRegister storeRegister = new StoreRegister();
    List<String> inputItems = new ArrayList<String>();

    @BeforeEach
    void setUp() {
        inputItems.add("PC1033");
        inputItems.add("GenericMotherboard");
        inputItems.add("genericMotherboard");
        inputItems.add("Mouse");
        inputItems.add("LCD");
    }

    @Test
    void testCorrectOrderItemsForCheckout() {
        //storeRegister.loadInventory();
        Receipt testReceipt = storeRegister.checkoutOrder(inputItems);
        List<String> orderedItems = testReceipt.getOrderedItems();
        List<String> expectedList = new ArrayList<String>(Arrays.asList("GenericMotherboard", "LCD", "PC1033", "Mouse"));
        assertEquals(expectedList,orderedItems);
    }

    @Test
    void testInvalidItemInOrderItemsForCheckout() {
        inputItems.add("GenericMotherboardhgjjhjh");
        Receipt testReceipt = storeRegister.checkoutOrder(inputItems);
        assertNull(testReceipt);
    }

    @Test
    void testWhenPassingNoItem() {
        inputItems.removeAll(inputItems);
        Receipt testReceipt = storeRegister.checkoutOrder(inputItems);
        assertNull(testReceipt);
    }

    @AfterEach
    void tearDown() {
        inputItems.removeAll(inputItems);
    }
}