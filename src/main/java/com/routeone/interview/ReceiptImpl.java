package com.routeone.interview;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;


public class ReceiptImpl implements Receipt {

    private String formattedTotal = null;
    private List<String> orderedItems = null;

    public ReceiptImpl() {
        //auto generated constructor
    }

    public String getFormattedTotal() {
        return formattedTotal;
    }

    public void setFormattedTotal(String formattedTotal) {
        this.formattedTotal = formattedTotal;
    }

    public void setOrderedItems(List<String> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public List<String> getOrderedItems() {
        LinkedHashSet<String> hashSet = new LinkedHashSet<>(orderedItems);
        List<String> orderedItemsWithoutDuplicates = new ArrayList<>(hashSet);
        return orderedItemsWithoutDuplicates;
    }

}