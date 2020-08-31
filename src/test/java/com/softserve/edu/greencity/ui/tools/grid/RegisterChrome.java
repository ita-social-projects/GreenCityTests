package com.softserve.edu.greencity.ui.tools.grid;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.grid.selenium.GridLauncherV3;


public class RegisterChrome {
    public static void startNode() throws Exception {
        WebDriverManager.chromedriver().setup();
        GridLauncherV3.main(new String[] { "-role", "node", "-hub",
                "http://localhost:4444/grid/register", "-browser",
                "browserName=chrome", "-port", "5555" });
    }

    public static void startNode(String port) throws Exception {
        WebDriverManager.chromedriver().setup();
        GridLauncherV3.main(new String[] { "-role", "node", "-hub",
                "http://localhost:4444/grid/register", "-browser",
                "browserName=chrome", "-port", port });
    }

    public static void startNode(String serverLink, String port) throws Exception {
        WebDriverManager.chromedriver().setup();
        GridLauncherV3.main(new String[] { "-role", "node", "-hub",
                serverLink, "-browser",
                "browserName=chrome", "-port", port });
    }

    public static void startNode(int  port) throws Exception {
        WebDriverManager.chromedriver().setup();
        GridLauncherV3.main(new String[] { "-role", "node", "-hub",
                "http://localhost:4444/grid/register", "-browser",
                "browserName=chrome", "-port", String.valueOf(port)  });
    }

    public static void startNode(String serverLink, int  port) throws Exception {
        WebDriverManager.chromedriver().setup();
        GridLauncherV3.main(new String[] { "-role", "node", "-hub",
                serverLink, "-browser",
                "browserName=chrome", "-port", String.valueOf(port) });
    }

}