package com.softserve.edu.greencity.ui.tools;

import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * class allow use cmd.exe, example CommandLine.run(" ")
 * class allow use cmd.exe, example CommandLine.run(" DROP DATABASE [IF EXISTS] *")
 */
public class CommandLine {
    @SneakyThrows(IOException.class)
    @Step("Run command from windows cmd, flexible")
    public static void run(String command) {
        Process cmd = Runtime.getRuntime().exec("cmd /c " + command);
    }
    @Test
    void cmd(){
        CommandLine.run("Allure serve");
    }
}
