package com.softserve.edu.greencity.ui.tools;

import org.apache.commons.lang3.RandomStringUtils;

public class GenerateString {
    public static String generateString(int count){
        return RandomStringUtils.randomAlphanumeric(count);
    }
}