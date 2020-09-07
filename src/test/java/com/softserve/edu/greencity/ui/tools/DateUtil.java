package com.softserve.edu.greencity.ui.tools;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

/**
 * DateUtil class
 */
public final class DateUtil {

    /**
     * Method to get current date in format "MMMM d, yyyy (example авг 13, 2020)"
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

    /**
     * Method to get current date in format "yyyy-MM-dd 'at' HH:mm:ss z (2020-08-13 at 20:51:56 MSK)"
     * @return String
     */
    public static String getCurrentYearMonthDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date) ;
    }
}