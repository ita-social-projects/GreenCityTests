package com.softserve.edu.greencity.ui.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.GoogleAccountPage;
import com.softserve.edu.greencity.ui.pages.common.RegisterDropdown;

/**
 * RegisterDropdownGoogleAccountTest class.
 * @author Serg
 *
 */
public class RegisterDropdownGoogleAccountTest extends GreencityTestRunner {

    @DataProvider
    public Object[][] validCredentialGoogleUser() {
        return new Object[][] {
                { UserRepository.get().googleUserCredentials() }, };
    }

    /**
     * A test for register using google account.
     * @param userGoogleLoginCredentials
     */
    @Test(dataProvider = "validCredentialGoogleUser")
    public void checkGoogleSignUpPage(User userGoogleLoginCredentials) {
        logger.info("start test checkGoogleSignUpPage with user = "
                + userGoogleLoginCredentials.toString());
        RegisterDropdown registerDropdown = loadApplication()
                .signup();
        logger.info("get a top title text on the page: "
                + registerDropdown.getTitlePageText());
        GoogleAccountPage googleAccountPage = registerDropdown.clickSignUpGoogleAccountButton();
        googleAccountPage.enterEmail(userGoogleLoginCredentials.getEmail());
        presentationSleep(2); // delay only for presentation
        googleAccountPage.clickEmailNext();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://accounts.google.com/signin/"),
                "you didn't go to Register page");
//        googleAccountPage.enterPassword(userGoogleLoginCredentials.getPassword());
        presentationSleep(5); // delay only for presentation
//        googleAccountPage.clickPasswordNext();
    }
}
