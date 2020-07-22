package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import org.openqa.selenium.Dimension;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class CustomScreenResolutionsTests extends GreenCityTestRunner{

    @DataProvider
    public Object[][] sizes() {
        return new Object[][]{};
    }

    //GC-487
    //Screen resolutions:
    //1440 x 1024
    //1024 x 760
    //768 x 1024
//    @BeforeMethod
//    public void changeResolution(ITestContext context) throws IOException {
//        String size = context.getCurrentXmlTest().getLocalParameters()
//                .get("windowSize");
//        driver.manage().window().setSize(size);
//    }

    @Test(dataProvider = "successRegistrationUserCreds", description = "GC-513")
    public void registrationCheckIfMailReceived(User userLoginCredentials) {

    }
}
