package com.example.estote.entityDetail;

import com.example.estote.entity.Receipt;

import java.util.Comparator;

public class ReceiptComporator implements Comparator<Receipt> {
    @Override
    public int compare(Receipt r1, Receipt r2) {
        if (r1==r2) {return 0;}
        else if (r1.getId() > r2.getId()) {
            return 1;
        }
        else return -1;
    }
}
