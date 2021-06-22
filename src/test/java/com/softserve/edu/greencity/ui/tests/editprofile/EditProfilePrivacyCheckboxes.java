package com.softserve.edu.greencity.ui.tests.editprofile;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.MyHabitPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class EditProfilePrivacyCheckboxes extends GreenCityTestRunner {
    private User getTemporaryUser() {
        return UserRepository.get().temporary();
    }

    @Test(testName = "GC-1532", description = "GC-1532")
    @Description("Verify Show Location Checkbox")
    public void verifyShowLocationCheckbox() {
        logger.info("Starting verifyCheckboxes");
        MyHabitPage showLocationCheckbox = loadApplication()
                .loginIn(getTemporaryUser())
                .clickEditButton()
                .clearCityField()
                .fillCityField("Lviv")
                .clickShowLocationCheckBoxCheck()
                .clickSaveButton();

        Assert.assertEquals(showLocationCheckbox.getCityLabelText(), "Lviv", "City label is not present while Show my location checkbox is checked");
    }

    @Test(testName = "GC-1532", description = "GC-1532")
    @Description("Verify Show Location Checkbox Negative")
    public void verifyShowLocationCheckboxNegative() {
        logger.info("Starting verifyCheckboxes");
        MyHabitPage showLocationCheckbox = loadApplication()
                .loginIn(getTemporaryUser())
                .clickEditButton()
                .clearCityField()
                .fillCityField("Lviv")
                .clickShowLocationCheckBoxUncheck()
                .clickSaveButton();

        Assert.assertEquals(showLocationCheckbox.isCityLabelPresent(), false, "City label present while Show my location checkbox is unchecked" );
    }

    @Test(testName = "GC-1532", description = "GC-1532")
    @Description("Verify Shopping list Checkbox")
    public void verifyShoppingListCheckbox() {
        logger.info("Starting verifyShoppingListCheckboxNegative");
        MyHabitPage shoppingListCheckbox = loadApplication()
                .loginIn(getTemporaryUser())
                .clickEditButton()
                .clearCityField()
                .fillCityField("Lviv")
                .clickShowShoppingListCheckBoxCheck()
                .clickSaveButton();

        Assert.assertEquals(shoppingListCheckbox.isShoppingListPresent(), true, "Shopping list label is not present while Show my Shopping List checkbox is checked");
    }
    @Test(testName = "GC-1532", description = "GC-1532")
    @Description("Verify Shopping list Checkbox Negative")
    public void verifyShoppingListCheckboxNegative() {
        logger.info("Starting verifyShoppingListCheckboxNegative");
        MyHabitPage shoppingListCheckbox = loadApplication()
                .loginIn(getTemporaryUser())
                .clickEditButton()
                .clearCityField()
                .fillCityField("Lviv")
                .clickShowShoppingListCheckBoxCheckUncheck()
                .clickSaveButton();

        Assert.assertEquals(shoppingListCheckbox.isShoppingListPresent(), false, "Shopping list label present while Show my Shopping List checkbox is unchecked");
    }

    @Test(testName = "GC-1532", description = "GC-1532")
    @Description("Verify Shopping list Checkbox")
    public void verifyShoppingListCheckboxes() {
        logger.info("Starting verifyShoppingListCheckboxNegative");
        MyHabitPage twoCheckboxes = loadApplication()
                .loginIn(getTemporaryUser())
                .clickEditButton()
                .clearCityField()
                .fillCityField("Lviv")
                .clickShowShoppingListCheckBoxCheck()
                .clickShowLocationCheckBoxCheck()
                .clickSaveButton();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(twoCheckboxes.getShoppingListLabel().isDisplayedLabel(), true);
        softAssert.assertEquals(twoCheckboxes.getCityLabelText(), "Lviv");
    }
}

