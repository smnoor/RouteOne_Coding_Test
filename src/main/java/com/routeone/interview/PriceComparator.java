package com.routeone.interview;

import java.util.Comparator;

class PriceComparator implements Comparator<OrderComponent> {
    public int compare(OrderComponent a, OrderComponent b){
        return a.getPrice() > b.getPrice() ? -1 : a.getPrice() == b.getPrice() ? 0: 1;
    }
}
