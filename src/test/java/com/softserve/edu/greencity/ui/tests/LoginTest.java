package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.pages.common.LoginDropdown;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends GreencityTestRunner {
    @Test
    public void checkDropdown() {
        LoginDropdown loginDropdown = loadApplication().signin();

        Assert.assertEquals(loginDropdown.getSignUpLink().getText(), "Sign up");
    }
}
