package com.softserve.edu.greencity.ui.tools.jenkins;

import lombok.SneakyThrows;

import java.util.Properties;


public class Jenkins {
    private Properties property = new Properties();
    @SneakyThrows
    public static boolean isItYou(){
        try {
            boolean jenkins = Boolean.parseBoolean(System.getenv("Jenkins"));
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}