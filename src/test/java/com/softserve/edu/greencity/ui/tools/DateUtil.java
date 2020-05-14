package com.softserve.edu.greencity.ui.tools;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * DateUtil class
 */
public final class DateUtil {

    /**
     * Method to get current date in format "MMMM d, yyyy"
     * @return String
     */
    public static String getCurrentDate() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("MMMM d, yyyy").toFormatter();
        return date.format(formatter);
    }
}
