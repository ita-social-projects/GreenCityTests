package com.softserve.edu.greencity.ui.tests;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.softserve.edu.greencity.ui.pages.common.LoginDropdown;

public class LoginTest extends GreenCityTestRunner {
    @Test
    public void checkDropdown() { // for debugging
        LoginDropdown dropdown = null;
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @Test
    public void checkPage() { // for debugging

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }
}
