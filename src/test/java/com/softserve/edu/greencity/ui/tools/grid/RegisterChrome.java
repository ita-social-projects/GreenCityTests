package com.softserve.edu.greencity.ui.tools.grid;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.grid.selenium.GridLauncherV3;


public class RegisterChrome {
    public static void startNode() {
        try {
            GridLauncherV3.main(new String[]{"-role", "node", "-hub",
                    "http://localhost:4444/grid/register", "-browser",
                    "browserName=chrome", "-port", "5555"});
        } catch (Exception e) {e.printStackTrace();}
    }

    public static void startNode(String port) {
        try {
            GridLauncherV3.main(new String[]{"-role", "node", "-hub",
                    "http://localhost:4444/grid/register", "-browser",
                    "browserName=chrome", "-port", port});
        } catch (Exception e) {e.printStackTrace();}
    }

    public static void startNode(String serverLink, String port) {
        try {
            GridLauncherV3.main(new String[]{"-role", "node", "-hub",
                    serverLink, "-browser",
                    "browserName=chrome", "-port", port});
        } catch (Exception e) {e.printStackTrace();}
    }

    public static void startNode(int port) {
        try {
            GridLauncherV3.main(new String[]{"-role", "node", "-hub",
                    "http://localhost:4444/grid/register", "-browser",
                    "browserName=chrome", "-port", String.valueOf(port)});
        } catch (Exception e) {e.printStackTrace();}
    }

    public static void startNode(String serverLink, int port) {
        try {
            GridLauncherV3.main(new String[]{"-role", "node", "-hub",
                    serverLink, "-browser",
                    "browserName=chrome", "-port", String.valueOf(port)});
        } catch (Exception e) {e.printStackTrace();}
    }

}