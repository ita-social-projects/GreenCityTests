package com.softserve.edu.greencity.ui.tests.editprofile;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.MyHabitPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EditTextFieldsTest extends GreenCityTestRunner {

    private User getTemporaryUser() {
        return UserRepository.get().temporary();
    }

    @BeforeMethod
    public void logInToTheSite(){
        loadApplication()
                .loginIn(getTemporaryUser());
    }

    @Test(testName = "GC-1542")
    @Description("User is redirected to 'Edit profile' after clicks the 'Edit' icon")
    public void verifyEditButtonOnMyHabitsPage(){
        logger.info("Starting verifyEditButtonOnMyHabitsPage");

        String actualTitleText = new MyHabitPage(driver)
                .clickEditButton()
                .getTextTitleOnEditPage();

        String expectedTitleText = "Edit Profile";
        Assert.assertEquals(actualTitleText, expectedTitleText, "Failed to verify that user go to editing page");
    }

    @Test(testName = "GC-1488")
    @Description("User can edit his profile with valid data")
    public void editProfileInfoWithValidData(){
        logger.info("Starting editProfileInfoWithValidData");

        MyHabitPage openMyHabitPage = new MyHabitPage(driver)
                .clickEditButton()
                .clearNameField()
                .fillNameField("Jackie Chan")
                .clearCityField()
                .fillCityField("Lviv")
                .clearCredoField()
                .fillCredoField("Some credo")
                .clickSaveButton();

        String expectedUserName = "Jackie Chan";
        String actualUserName = openMyHabitPage.getUsernameLabelText();
        String expectedUserCity = "Lviv";
        String actualUserCity = openMyHabitPage.getCityLabelText();
        String expectedUserCredo = "Some credo";
        String actualUserCredo = openMyHabitPage.getCredoLabelText();

        Assert.assertEquals(actualUserName, expectedUserName, "Actual user name and expected do not match");
        Assert.assertEquals(actualUserCity, expectedUserCity, "Actual user city and expected do not match");
        Assert.assertEquals(actualUserCredo, expectedUserCredo, "Actual user credo and expected do not match");
    }

    @Test(testName = "GC-1494")
    @Description("Verify that system doesn't save edited information after clicking on 'Cancel' button")
    public void verifyChangesDoNotSavesAfterCancelButton(){
        logger.info("Starting verifyChangesDoNotSavesAfterCancelButton");

        MyHabitPage goTiMyHabitPage = new MyHabitPage(driver)
                .clickEditButton()
                .clearNameField()
                .fillNameField("No name")
                .clearCityField()
                .fillCityField("Shepetivka")
                .clearCredoField()
                .fillCredoField("Some bad credo")
                .clickCancelButtonWithPopUp()
                .clickCancelButton();

        String expectedUserName = "No name";
        String actualUserName = goTiMyHabitPage.getUsernameLabelText();
        String expectedUserCity = "Shepetivka";
        String actualUserCity = goTiMyHabitPage.getCityLabelText();
        String expectedUserCredo = "Some bad credo";
        String actualUserCredo = goTiMyHabitPage.getCredoLabelText();

        softAssert.assertNotEquals(actualUserName, expectedUserName, "The system was saved the name what wan`t saved");
        softAssert.assertNotEquals(actualUserCity, expectedUserCity, "The system was saved the city what wan`t saved");
        softAssert.assertNotEquals(actualUserCredo, expectedUserCredo, "The system was saved the credo what wan`t saved");
        softAssert.assertAll();
    }
}
