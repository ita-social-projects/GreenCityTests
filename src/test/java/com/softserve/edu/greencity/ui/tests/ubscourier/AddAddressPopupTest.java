package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.UBS.UBSDataStrings;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.locators.ubs.AddAddressPopupLocators;

import com.softserve.edu.greencity.ui.pages.ubs.OrderDetailsPage;
import com.softserve.edu.greencity.ui.pages.ubs.PersonalDataPage;
import com.softserve.edu.greencity.ui.pages.ubs.UserAddress;

import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithoutLogin;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import com.softserve.edu.greencity.ui.pages.ubs.AddAddressPopupComponent;

import io.qameta.allure.Description;
import org.testng.annotations.*;



public class AddAddressPopupTest extends GreenCityTestRunnerWithoutLogin {
    private OrderDetailsPage orderDetailsPage;
    private PersonalDataPage personalDataPage;

    private AddAddressPopupComponent addAddressPopupComponent;
    private WaitsSwitcher waitsSwitcher;


    @BeforeClass
    public void signIn() {
        User user = UserRepository.get().temporary();
        loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user);
    }


    @BeforeMethod
    public void navigateToPersonalDataPage() {
        personalDataPage = loadApplication()
                .navigateMenuUBSCourier()
                .fillOldClothes20L("20")
                .clickOnPersonalDataButton();
        if (personalDataPage.isAddAddressButtonActive()) {
            addAddressPopupComponent = personalDataPage.clickOnAddAddressButton();
        } else {
            addAddressPopupComponent = personalDataPage.deleteAddressOfIndex(personalDataPage.getQuantityOfAddresses() - 1).clickOnAddAddressButton();
        }
    }

    @AfterMethod
    public void navigateToWelcomePage() {
        addAddressPopupComponent.clickOnCancelButton().clickOnCancelButton().clickCancelOrderButton();
    }

    @AfterClass
    public void signOut() {
        personalDataPage.signOut();
    }

    @Test(testName = "GC-", description = "verify that 'Add Address' button is disabled while input fields of city, street and district are empty")
    @Description("GC-")
    public void verifyAddAddressButtonIsDisabledWhenNeededDataFieldsIsEmpty() {
        UserAddress userAddress = new UserAddress(AddAddressPopupLocators.CITY_KIEV).generateRandomAddressValues();
        //TODO Bug: button is always active
        softAssert.assertFalse(addAddressPopupComponent.isAddAddressButtonActive());
        addAddressPopupComponent.chooseCity(AddAddressPopupLocators.CITY_KIEV);
        softAssert.assertFalse(addAddressPopupComponent.isAddAddressButtonActive());
        addAddressPopupComponent.inputStreet(userAddress);
        softAssert.assertFalse(addAddressPopupComponent.isAddAddressButtonActive());
        addAddressPopupComponent.inputDistrict(userAddress);
        softAssert.assertTrue(addAddressPopupComponent.isAddAddressButtonActive());
        softAssert.assertAll();
    }


    @Test(testName = "GC-2067", description = "GC-2067")
    @Description("GC-2067")
    public void checkCityMessageInfo() {
        logger.info("Verify that add address pop-up window shall display with the message “At the moment we serve only the city of Kiev");
        String cityMessage = addAddressPopupComponent.getCityValidationTextInfo();
        softAssert.assertEquals(UBSDataStrings.ADDRESS_CITY_INFO_MESSAGE.getMessage(), cityMessage, "The messages aren't the same!");
    }

    @Test(testName = "GC-2068", description = "GC-2068")
    @Description("GC-2068")
    public void checkDataMessageInfo() {
        logger.info("Verify that ");

        addAddressPopupComponent.chooseCity(AddAddressPopupLocators.CITY_KIEV);

        addAddressPopupComponent.getStreetInput().click();
        addAddressPopupComponent.getDistrictInput().click();
        String streetMessage = addAddressPopupComponent.getStreetValidationErrorText();
        addAddressPopupComponent.getStreetInput().click();
        addAddressPopupComponent.getStreetInput().sendKeys("Хоткевича");
        addAddressPopupComponent.getDistrictInput().click();
        addAddressPopupComponent.getHouseInput().click();
        String districtMessage = addAddressPopupComponent.getDistrictValidationErrorText();
        addAddressPopupComponent.getDistrictInput().sendKeys("Печерський");
        String houseMessage = addAddressPopupComponent.getHouseValidationErrorText();
        addAddressPopupComponent.getHouseInput().click();
        addAddressPopupComponent.getHouseInput().sendKeys("1");


        softAssert.assertEquals(UBSDataStrings.ADDRESS_WARNING_MESSAGE.getMessage(), streetMessage, "The messages aren't the same!");
        softAssert.assertEquals(UBSDataStrings.ADDRESS_WARNING_MESSAGE.getMessage(), districtMessage, "The messages aren't the same!");
        softAssert.assertEquals(UBSDataStrings.ADDRESS_WARNING_MESSAGE.getMessage(), houseMessage, "The messages aren't the same!");
        softAssert.assertAll();
    }

    @Test(testName = "GC-1925", description = "GC-1925")
    @Description("GC-1925")
    public void verifyThatDataWillLoseAfterClosingTheWindow() {
        logger.info("Verify that data will lose after closing the window");
        addAddressPopupComponent.fillAllFields(new UserAddress(AddAddressPopupLocators.CITY_KIEV, "Sadova", "Kiev", 1, "1", 1)).clickOnCancelButton();
        //TODO add assert
    }
}