package com.softserve.edu.greencity.ui.tools.grid;
import org.openqa.grid.selenium.GridLauncherV3;


public class GridHub {

    public static void startLocally() throws Exception {
        GridLauncherV3.main(new String[] { "-role", "hub", "-port", "4444" });
    }

    public static void startLocally(String port) throws Exception {
        GridLauncherV3.main(new String[] { "-role", "hub", "-port", port });
    }

    public static void startLocally(int port) throws Exception {
        GridLauncherV3.main(new String[] { "-role", "hub", "-port", String.valueOf(port)});
    }

}