package com.softserve.edu.greencity.ui.tools;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

/**
 * DateUtil class
 */
public final class DateUtil {

    /**
     * Method to get current date in format "MMMM d, yyyy (example авг 13, 2020)"
     * @return String
     */
    public static String getCurrentDate(DateTimeFormatter formatter) {
        LocalDate date = LocalDate.now();
        return date.format(formatter);
    }

    public static String getCurrentDateMonthFirst() {
        return getCurrentDate(
                DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
                        .withLocale(new Locale("en"))
        );
    }

    public static String getCurrentDateDayFirst() {
        return getCurrentDate(
                new DateTimeFormatterBuilder().appendPattern("d MMM, yyyy")
                        .toFormatter(new Locale("en"))
        );
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
