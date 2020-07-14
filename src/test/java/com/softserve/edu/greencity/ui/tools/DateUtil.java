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
    public static String getCurrentDate(String format) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern(format).toFormatter();
        return date.format(formatter);
    }
    public static String getCurrentDate() {
        return getCurrentDate("MMM d, yyyy");
    }
}
