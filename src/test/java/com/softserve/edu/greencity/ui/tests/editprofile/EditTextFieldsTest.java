package com.softserve.edu.greencity.ui.tests.editprofile;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.MyHabitPage;
import com.softserve.edu.greencity.ui.pages.cabinet.editprofile.EditProfilePage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EditTextFieldsTest extends GreenCityTestRunner {

    private User getTemporaryUser() {
        return UserRepository.get().temporary();
    }

    @Test(testName = "GC-1542", description = "GC-1542")
    @Description("User is redirected to 'Edit profile' after clicks the 'Edit' icon")
    public void verifyEditButtonOnMyHabitsPage(){
        logger.info("Starting verifyEditButtonOnMyHabitsPage");
        String actualTitleText = loadApplication()
                .loginIn(getTemporaryUser())
                .goToEditProfile()
                .getTitleOnEditPage()
                .getText();

        String expectedTitleText = "Edit Profile";
        Assert.assertEquals(actualTitleText, expectedTitleText);
    }

    @Test(testName = "GC-1488", description = "GC-1488")
    @Description("User can edit his profile with valid data")
    public void editProfileInfoWithValidData(){
        logger.info("Starting editProfileInfoWithValidData");
        MyHabitPage actualTitleText = loadApplication()
                .loginIn(getTemporaryUser())
                .goToEditProfile()
                .clearNameField()
                .fillNameField("Jackie Chan")
                .clearCityField()
                .fillCityField("Lviv")
                .clearCredoField()
                .fillCredoField("Some credo")
                .clickSaveButton();

        String expectedUserName = "Jackie Chan";
        String actualUserName = actualTitleText.getUsernameLabelText();
        String expectedUserCity = "Lviv";
        String actualUserCity = actualTitleText.getCityLabelText();
        String expectedUserCredo = "Some credo";
        String actualUserCredo = actualTitleText.getCredoLabelText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualUserName, expectedUserName);
        softAssert.assertEquals(actualUserCity, expectedUserCity);
        softAssert.assertEquals(actualUserCredo, expectedUserCredo);
    }

    @Test(testName = "GC-1494", description = "GC-1494")
    @Description("Verify that system doesn't save edited information after clicking on 'Cancel' button")
    public void verifyChangesDoNotSavesAfterCancelButton(){
        logger.info("Starting verifyChangesDoNotSavesAfterCancelButton");
        MyHabitPage actualTitleText = loadApplication()
                .loginIn(getTemporaryUser())
                .goToEditProfile()
                .clearNameField()
                .fillNameField("No name")
                .clearCityField()
                .fillCityField("Shepetivka")
                .clearCredoField()
                .fillCredoField("Some bad credo")
                .clickCancelButton()
                .ClickConfirmationButtonAfterCancelButtonPopup();

        String expectedUserName = "No name";
        String actualUserName = actualTitleText.getUsernameLabelText();
        String expectedUserCity = "Shepetivka";
        String actualUserCity = actualTitleText.getCityLabelText();
        String expectedUserCredo = "Some bad credo";
        String actualUserCredo = actualTitleText.getCredoLabelText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(actualUserName, expectedUserName);
        softAssert.assertNotEquals(actualUserCity, expectedUserCity);
        softAssert.assertNotEquals(actualUserCredo, expectedUserCredo);
    }
}
