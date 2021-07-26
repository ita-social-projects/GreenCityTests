package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.UBS.UBSDataStrings;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.locators.ubs.AddAddressPopupLocators;
import com.softserve.edu.greencity.ui.pages.ubs.AddAddressPopupComponent;
import com.softserve.edu.greencity.ui.pages.ubs.OrderDetailsPage;
import com.softserve.edu.greencity.ui.pages.ubs.UserAddress;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddAddressPopupTest extends GreenCityTestRunner {
    private OrderDetailsPage orderDetailsPage;
    private AddAddressPopupComponent addAddressPopupComponent;
    private WaitsSwitcher waitsSwitcher;

    @BeforeMethod
    public void signIn() {
        User user = UserRepository.get().temporary();
        orderDetailsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuUBSCourier();
    }

    @AfterMethod
    public void signOut() {
        orderDetailsPage.signOut();
    }

    @Test(testName = "GC-2067", description = "GC-2067")
    @Description("GC-2067")
    public void checkCityMessageInfo() {
        logger.info("Verify that add address pop-up window shall display with the message “At the moment we serve only the city of Kiev");
        orderDetailsPage
                .getServicesComponents()
                .get(0)
                .getInput()
                .sendKeys("10");

        orderDetailsPage
                .clickOnNextButton()
                .clickOnAddAddressButton()
                .getCityMessageInfo()
                .getText();
        addAddressPopupComponent = new AddAddressPopupComponent(driver);
        addAddressPopupComponent.clickOnCancelButton();

        String cityMessage = addAddressPopupComponent.getCityValidationTextInfo();
        softAssert.assertEquals(UBSDataStrings.ADDRESS_CITY_INFO_MESSAGE.getMessage(), cityMessage, "At the moment we serve only the city of Kiev");
    }

    @Test(testName = "GC-2068", description = "GC-2068")
    @Description("GC-2068")
    public void checkDataMessageInfo() {
        logger.info("Verify that ");
        orderDetailsPage
                .getServicesComponents()
                .get(0)
                .getInput()
                .sendKeys("10");

        orderDetailsPage
                .clickOnNextButton()
                .clickOnAddAddressButton()
                .chooseCity(AddAddressPopupLocators.CITY_KIEV)
                .getStreetInput();

        addAddressPopupComponent = new AddAddressPopupComponent(driver);
        addAddressPopupComponent.getStreetInput().click();
        addAddressPopupComponent.getDistrictInput().click();
        addAddressPopupComponent.getStreetInput().click();
        addAddressPopupComponent.getStreetInput().sendKeys("Садова");
        addAddressPopupComponent.getDistrictInput().click();
        addAddressPopupComponent.getHouseInput().click();
        addAddressPopupComponent.getDistrictAlertMessage().getText();
        addAddressPopupComponent.getDistrictInput().sendKeys("Печерський");
        addAddressPopupComponent.getHouseInput().click();
        addAddressPopupComponent.getHouseInput().sendKeys("1");

        addAddressPopupComponent = new AddAddressPopupComponent(driver);
        addAddressPopupComponent.clickOnCancelButton();

        String streetMessage = addAddressPopupComponent.getStreetValidationErrorText();
        String districtMessage = addAddressPopupComponent.getDistrictValidationErrorText();
        String houseMessage = addAddressPopupComponent.getHouseValidationErrorText();
        softAssert.assertEquals(UBSDataStrings.ADDRESS_WARNING_MESSAGE.getMessage(), streetMessage, "This field is required.");
        softAssert.assertEquals(UBSDataStrings.ADDRESS_WARNING_MESSAGE.getMessage(), districtMessage, "This field is required.");
        softAssert.assertEquals(UBSDataStrings.ADDRESS_WARNING_MESSAGE.getMessage(), houseMessage, "This field is required.");
        softAssert.assertAll();
    }

    @Test(testName = "GC-2068 Street", description = "GC-2068")
    @Description("GC-2068")
    public void checkStreetMessageInfo() {
        logger.info("Verify that user can see an error message if not fill street data");
        orderDetailsPage
                .getServicesComponents()
                .get(0)
                .getInput()
                .sendKeys("10");
        orderDetailsPage
                .clickOnNextButton()
                .clickOnAddAddressButton()
                .chooseCity(AddAddressPopupLocators.CITY_KIEV)
                .getStreetInput().click();
        addAddressPopupComponent = new AddAddressPopupComponent(driver);

        addAddressPopupComponent.getDistrictInput().click();
        addAddressPopupComponent = new AddAddressPopupComponent(driver);
        addAddressPopupComponent.clickOnCancelButton();

//        String actualErrorStreetMessage = "This field is required.";
//        String errorStreetMessage = addAddressPopupComponent.getStreetValidationErrorText();
//        softAssert.assertEquals(errorStreetMessage, actualErrorStreetMessage);
        softAssert.assertTrue(addAddressPopupComponent.isDisplayedStreetErrorMessage(), "This field is required.");
    }

    @Test(testName = "GC-1925", description = "GC-1925")
    @Description("GC-1925")
    public void verifyThatDataWillLoseAfterClosingTheWindow() {
        logger.info("Verify that data will lose after closing the window");
        orderDetailsPage
                .getServicesComponents()
                .get(0)
                .getInput()
                .sendKeys("10");

        orderDetailsPage
                .clickOnNextButton()
                .clickOnAddAddressButton()
                .fillAllFields(new UserAddress(AddAddressPopupLocators.CITY_KIEV, "Sadova", "Kiev", 1, "1", 1))
                .clickOnCancelButton();
        //TODO add assert
    }
}