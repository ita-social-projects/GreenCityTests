package com.softserve.edu.greencity.ui.tools.grid;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.grid.selenium.GridLauncherV3;


public class RegisterFirefox {

    public static void startNode() throws Exception {
        try {
            GridLauncherV3.main(new String[]{"-role", "node", "-hub",
                    "http://localhost:4444/grid/register", "-browser",
                    "browserName=firefox,version=54", "-port", "5556"});
        } catch (Exception e) {e.printStackTrace();}
    }

    public static void startNode(String port) throws Exception {
        try {
            GridLauncherV3.main(new String[]{"-role", "node", "-hub",
                    "http://localhost:4444/grid/register", "-browser",
                    "browserName=firefox,version=54", "-port", port});
        } catch (Exception e) {e.printStackTrace();}
    }

    public static void startNode(String serverLink, String port) throws Exception {
        try {
            GridLauncherV3.main(new String[]{"-role", "node", "-hub",
                    serverLink, "-browser",
                    "browserName=firefox,version=54", "-port", port});
        } catch (Exception e) {e.printStackTrace();}
    }

    public static void startNode(int port) throws Exception {
        try {
            GridLauncherV3.main(new String[]{"-role", "node", "-hub",
                    "http://localhost:4444/grid/register", "-browser",
                    "browserName=firefox,version=54", "-port", String.valueOf(port)});
        } catch (Exception e) {e.printStackTrace();}
    }

    public static void startNode(String serverLink, int port) throws Exception {
        try {
            GridLauncherV3.main(new String[]{"-role", "node", "-hub",
                    serverLink, "-browser",
                    "browserName=firefox,version=54", "-port", String.valueOf(port)});
        } catch (Exception e) {e.printStackTrace();}
    }

}
