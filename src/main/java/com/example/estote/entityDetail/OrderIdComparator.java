package com.example.estote.entityDetail;

import com.example.estote.entity.Order;

import java.util.Comparator;

public class OrderIdComparator implements Comparator<Order> {
    /**
     *     The class is necessary to sort the orders of the User's page
     */
    @Override
    public int compare(Order o1, Order o2) {
        if (o1==o2) {return 0;}
        else if (o1.getId() > o2.getId()) {
            return 1;
        }
        else return -1;
    }
}