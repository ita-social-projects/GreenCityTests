package com.softserve.edu.greencity.ui.tools;

public class QuantityItems {
    
    public int quantityItems(String text) {
        return Integer.valueOf(text.replaceAll("[^0-9]", ""));
    }
}
