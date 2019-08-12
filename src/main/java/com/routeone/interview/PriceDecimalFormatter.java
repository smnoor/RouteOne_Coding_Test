package com.routeone.interview;

import java.text.DecimalFormat;

public class PriceDecimalFormatter {

    public static String formattedValue(double totalVal) {
        String totalPrice = null;
        String formattedTotalPrice = null;
        DecimalFormat df = new DecimalFormat("#,###,##0.00");

        formattedTotalPrice = df.format(totalVal);
        totalPrice = "$" + formattedTotalPrice;
        return totalPrice;
    }
}
