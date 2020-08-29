package com.softserve.edu.greencity.ui.tools.jenkins;

import lombok.SneakyThrows;


public class Jenkins {
    @SneakyThrows
    public static boolean isItYou(){
        return  Boolean.parseBoolean( System.getenv("Jenkins"));
    }
}