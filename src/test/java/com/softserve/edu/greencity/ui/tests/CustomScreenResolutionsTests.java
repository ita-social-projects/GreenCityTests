package com.softserve.edu.greencity.ui.tests;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualRegisterComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.RegisterComponent;
import com.softserve.edu.greencity.ui.pages.common.TopGuestComponent;
import org.openqa.selenium.Dimension;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CustomScreenResolutionsTests extends GreenCityTestRunner{

    @DataProvider
    public Object[] sizes() {
        Object[] returnArray = new Object[3];
        returnArray[0] = new Dimension(1440,1024);// +image
        returnArray[1] = new Dimension(1024,760);//no image
        returnArray[2] = new Dimension(768,1024);//no image
        return returnArray;
    }


//    @BeforeMethod
//    public void changeResolution(ITestContext context) throws IOException {
//        String size = context.getCurrentXmlTest().getLocalParameters()
//                .get("windowSize");
//        driver.manage().window().setSize(size);
//    }

    @Test(dataProvider = "sizes", description = "GC-487")
    public void checkSignUpModalUI(Dimension screenSize) {

        driver.manage().window().setSize(screenSize);

        logger.info("Starting checkSignUpModalUI: ");
        loadApplication();

        logger.info("Click on Sign up button");
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");

        logger.info("Get a subtitle text of the modal window: "
                + registerComponent.getSubtitleString());

        softAssert.assertEquals("Please enter your details to sign up", registerComponent.getSubtitleString(),
                "This is not a register modal:(");

        logger.info("Get a text for registered users: "
                + registerComponent.getSignInLinkText());
        softAssert.assertEquals(registerComponent.getSignInLinkText(), "Do you already have an account? Sign in");

        logger.info("Checking if the rest of the page elements are displayed ");
        softAssert.assertTrue(registerComponent.getRegisterComponentModal().isDisplayed());
        softAssert.assertTrue(registerComponent.getSignInLink().isDisplayed());
        softAssert.assertTrue(registerComponent.getGoogleSignUpButton().isDisplayed());
        //softAssert.assertTrue(registerComponent.getModalImage().isDisplayed());
        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();
        softAssert.assertTrue(manualRegisterComponent.getSignUpButton().isDisplayed());
        softAssert.assertTrue(manualRegisterComponent.getEmailField().isDisplayed());
        softAssert.assertTrue(manualRegisterComponent.getUserNameField().isDisplayed());
        softAssert.assertTrue(manualRegisterComponent.getPasswordField().isDisplayed());
        softAssert.assertTrue(manualRegisterComponent.getPasswordConfirmField().isDisplayed());
        softAssert.assertAll();

    }
}
