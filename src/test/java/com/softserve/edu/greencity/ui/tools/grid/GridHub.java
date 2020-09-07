package com.softserve.edu.greencity.ui.tools.grid;

import org.openqa.grid.selenium.GridLauncherV3;


public class GridHub {

    public static void startLocally() {
        try {
            GridLauncherV3.main(new String[]{"-role", "hub", "-port", "4444"});
        } catch (Exception e) {e.printStackTrace();}
    }

    public static void startLocally(String port) {
        try {
            GridLauncherV3.main(new String[]{"-role", "hub", "-port", port});
        } catch (Exception e) {e.printStackTrace();}
    }

    public static void startLocally(int port) {
        try {
            GridLauncherV3.main(new String[]{"-role", "hub", "-port", String.valueOf(port)});
        } catch (Exception e) {e.printStackTrace();}
    }
}