package com.softserve.edu.greencity.ui.tests.editprofile;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.editprofile.EditProfilePage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithLoginLogout;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.softserve.edu.greencity.ui.tests.editprofile.EditProfileTexts.*;
import static com.softserve.edu.greencity.ui.tools.GenerateString.generateString;

public class EditProfileFieldsWarningsTests extends GreenCityTestRunnerWithLoginLogout {

    private User getTemporaryUser() {
        return UserRepository.get().temporary();
    }

    private EditProfilePage editProfilePage;

    @BeforeMethod
    public void logInAndGoToEditProfilePage() {
        logger.info("Starting logInAndGoToEditProfilePage");

        editProfilePage = loadApplication()
                .loginIn(getTemporaryUser())
                .clickEditButton();
    }

    @AfterMethod
    public void goOutFromEditingPage() {
        editProfilePage.clickCancelButton().clickCancelButtonWithPopUp();
    }

    @DataProvider(name = "notificationsTexts")
    public Object[][] textsForNotifications() {
        return new Object[][]{
                {"en", NOTIFICATION_FOR_NAME_FIELD_EN.getText(),
                        NOTIFICATION_FOR_CITY_FIELD_EN.getText(),
                        NOTIFICATION_FOR_CREDO_FIELD_EN.getText()
                },
                {"ua", NOTIFICATION_FOR_NAME_FIELD_UA.getText(),
                        NOTIFICATION_FOR_CITY_FIELD_UA.getText(),
                        NOTIFICATION_FOR_CREDO_FIELD_UA.getText()
                },
                {"ru", NOTIFICATION_FOR_NAME_FIELD_RU.getText(),
                        NOTIFICATION_FOR_CITY_FIELD_RU.getText(),
                        NOTIFICATION_FOR_CREDO_FIELD_RU.getText()
                }
        };
    }

    @DataProvider(name = "validCities")
    public Object[] validTextsForCityField() {
        return new Object[]{
                CITY_FIELD_VALID_TEXT_WITH_BRACKETS.getText(),
                CITY_FIELD_VALID_TEXT_WITH_COMMA.getText(),
                CITY_FIELD_VALID_TEXT_WITH_DASH.getText(),
                CITY_FIELD_VALID_TEXT_WITH_EXCLAMATION_MARK.getText()
        };
    }

    @DataProvider(name = "invalidCities")
    public Object[] invalidTextsForCityField() {
        return new Object[]{
                CITY_FIELD_INVALID_TEXT_WITH_BRACKETS.getText(),
                CITY_FIELD_INVALID_TEXT_WITH_COMMA.getText(),
                CITY_FIELD_INVALID_TEXT_WITH_DASH.getText(),
                CITY_FIELD_INVALID_TEXT_WITH_EXCLAMATION_MARK.getText()
        };
    }


    @Test(testName = "GC-1550, GC-1551, GC-1552", dataProvider = "notificationsTexts")
    @Description("Correct error message is shown on ‘Name’, 'City' and 'Credo' fields with correct color")
    public void verifyNotificationsUnderTextFieldsOnDifferentLanguages(String language, String expectedName, String expectedCity, String expectedCredo) {
        logger.info("Starting verifyNotificationsUnderTextFieldsOnDifferentLanguages");

        editProfilePage.clearNameField()
                .fillNameField(generateString(31))
                .clearCityField()
                .fillCityField(generateString(86))
                .clearCredoField()
                .fillCredoField(generateString(171));

        editProfilePage.createLanguageSwitchComponent()
                .changeLanguage(language);

        String actualName = editProfilePage.getTextFromNameNotification();
        String actualCity = editProfilePage.getTextFromCityNotification();
        String actualCredo = editProfilePage.getTextFromCredoNotification();
        String actualNameNotificationColor = editProfilePage.getColorFromNameNotificationLabel();
        String actualCityNotificationColor = editProfilePage.getColorFromCityNotificationLabel();
        String actualCredoNotificationColor = editProfilePage.getColorFromCredoNotificationLabel();
        String expectedColor = COLOR_FOR_ERROR_NOTIFICATIONS.getText();

        softAssert.assertEquals(actualName, expectedName, "actual and expected names notifications do not match");
        softAssert.assertEquals(actualNameNotificationColor, expectedColor, "actual and expected colors under names notifications do not match");
        softAssert.assertEquals(actualCity, expectedCity, "actual and expected cities notifications do not match");
        softAssert.assertEquals(actualCityNotificationColor, expectedColor, "actual and expected colors under names notifications do not match");
        softAssert.assertEquals(actualCredo, expectedCredo, "actual and expected credos notifications do not match");
        softAssert.assertEquals(actualCredoNotificationColor, expectedColor, "actual and expected colors under names notifications do not match");
        softAssert.assertAll();
    }

    @Test(testName = "GC-1549")
    @Description("'Name' field is mandatory")
    public void verifyMandatoryOfNameField() {
        logger.info("Starting verifyMandatoryOfNameField");

        editProfilePage
                .clearNameFieldWithBackspase()
                .clickOnTitleOnEditPage();

        boolean saveButtonClickable = editProfilePage.saveButtonClickable();
        String actualColor = editProfilePage.getColorFromNameNotificationLabel();
        String expectedColor = COLOR_FOR_ERROR_NOTIFICATIONS.getText();

        Assert.assertFalse(saveButtonClickable, "the save button is clickable when name field is empty");
        Assert.assertEquals(actualColor, expectedColor, "actual and expected colors under names notifications do not match");
    }

    @Test(testName = "GC-2032")
    @Description("Field 'City' on the 'Edit profile' page should be a maximum of eighty-five (85) characters")
    public void verifyMaxSizeOfCityField() {
        logger.info("Starting verifyImprovementOfCityField");

        editProfilePage
                .clearCityField()
                .fillCityField(generateString(86))
                .clickOnTitleOnEditPage();

        String actualColor = editProfilePage.getColorFromCityNotificationLabel();
        String expectedColor = COLOR_FOR_ERROR_NOTIFICATIONS.getText();

        Assert.assertEquals(actualColor, expectedColor, "actual and expected colors under names notifications do not match");
    }

    @Test(testName = "GC-2033",dataProvider = "validCities")
    @Description("Field 'City' on the 'Edit profile' page can have alphanumeric characters and '()-! ’,'")
    public void verifyValidCharactersInCityField(String city) {
        logger.info("Starting verifyValidCharactersInCityField");

        editProfilePage
                .clearCityField()
                .fillCityField(city);

        String actualColor = editProfilePage.getColorFromCityNotificationLabel();
        String expectedColor = COLOR_FOR_NOTIFICATIONS.getText();

        Assert.assertEquals(actualColor, expectedColor, "actual and expected colors under names notifications do not match");
    }

    @Test(testName = "GC-2034",dataProvider = "invalidCities")
    @Description("Field 'City' on the 'Edit profile' page can’t start with '()-! ’,'.")
    public void verifyInvalidCharactersInCityField(String city) {
        logger.info("Starting verifyInvalidCharactersInCityField");

        editProfilePage
                .clearCityField()
                .fillCityField(city)
                .clickOnTitleOnEditPage();

        String actualColor = editProfilePage.getColorFromCityNotificationLabel();
        String expectedColor = COLOR_FOR_ERROR_NOTIFICATIONS.getText();

        Assert.assertEquals(actualColor, expectedColor, "actual and expected colors under names notifications do not match");
    }
}