package com.softserve.edu.greencity.ui.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class QuantityItems {
    
    public int quantityItems(String text) {

        final Matcher matcher = Pattern.compile("\\d+").matcher(text);
        if (matcher.find()) {
           text.substring(matcher.start(), matcher.end());
        }

        return Integer.valueOf(text.substring(matcher.start(), matcher.end()));
    }
}
