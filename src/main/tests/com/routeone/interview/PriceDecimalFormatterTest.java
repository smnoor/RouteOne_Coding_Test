package com.routeone.interview;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceDecimalFormatterTest {

    @Test
    public void testWithNormalTotalAmountInput() {
        PriceDecimalFormatter priceDecimalFormatter = new PriceDecimalFormatter();
        String inputValue = priceDecimalFormatter.formattedValue(1222.98);
        assertEquals("$1,222.98",inputValue);
    }

    @Test
    public void testWithWrongTotalAmountFormat(){
        PriceDecimalFormatter priceDecimalFormatter = new PriceDecimalFormatter();
        String inputValue = priceDecimalFormatter.formattedValue(1222.98);
        assertNotEquals("$1222.98", inputValue);

    }

    @Test
    public void testWithPassingZero(){
        PriceDecimalFormatter priceDecimalFormatter = new PriceDecimalFormatter();
        String inputValue = priceDecimalFormatter.formattedValue(0.00);
        assertEquals("$0.00",inputValue);
    }


}