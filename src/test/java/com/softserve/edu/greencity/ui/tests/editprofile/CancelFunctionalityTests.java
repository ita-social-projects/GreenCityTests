package com.softserve.edu.greencity.ui.tests.editprofile;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.editprofile.CancelEditingPopUpComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.editprofile.EditProfilePage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CancelFunctionalityTests extends GreenCityTestRunner {

    private User getTemporaryUser() {
        return UserRepository.get().temporary();
    }

    @Test(testName = "GC-1636")
    @Description("Verify 'Cancel' pop-up message text on different localizations")
    public void cancelOnEditProfile() {
        logger.info("Starting verifyPopUpMessageTestOnEditProfileCancel");
        CancelEditingPopUpComponent cancelPopUp = loadApplication()
                .loginIn(getTemporaryUser())
                .clickEditButton()
                .fillCityField("LvivLvivLviv")
                .clickCancelButtonWithPopUp();

        String titleOfPopUpOnEn = cancelPopUp.getTitleOfCancelPopUpComponent();
        String subTitleOfPopUpOnEn = cancelPopUp.getSubTitleOfCancelComponent();

        cancelPopUp.clickContinueEditingButton()
                .switchRuLanguage()
                .fillCityField("LvivL")
                .clickCancelButtonWithPopUp();

        String titleOfPopUpOnRu = cancelPopUp.getTitleOfCancelPopUpComponent();
        String subTitleOfPopUpOnRu = cancelPopUp.getSubTitleOfCancelComponent();

        cancelPopUp.clickContinueEditingButton()
                .switchUaLanguage()
                .clearNameField()
                .fillCityField("LvivLv")
                .clickCancelButtonWithPopUp()
                .clickContinueEditingButton()
                .fillCityField("ASgasga")
                .clickCancelButton();

        String titleOfPopUpOnUa = cancelPopUp.getTitleOfCancelPopUpComponent();
        String subTitleOfPopUpOnUa = cancelPopUp.getSubTitleOfCancelComponent();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(titleOfPopUpOnEn, EditProfileTexts.TITLE_OF_CANCEL_POP_UP_TEXT_EN.getText());
        softAssert.assertEquals(subTitleOfPopUpOnEn, EditProfileTexts.SUBTITLE_OF_CANCEL_POP_UP_TEXT_EN.getText());
        softAssert.assertEquals(titleOfPopUpOnRu, EditProfileTexts.TITLE_OF_CANCEL_POP_UP_TEXT_RU.getText());
        softAssert.assertEquals(subTitleOfPopUpOnRu, EditProfileTexts.SUBTITLE_OF_CANCEL_POP_UP_TEXT_RU.getText());
        softAssert.assertEquals(titleOfPopUpOnUa, EditProfileTexts.TITLE_OF_CANCEL_POP_UP_TEXT_UA.getText());
        softAssert.assertEquals(subTitleOfPopUpOnUa, EditProfileTexts.SUBTITLE_OF_CANCEL_POP_UP_TEXT_UA.getText());
    }

    @Test(testName = "GC-1554")
    @Description("Verify that the User stays on the edit form after clicking 'Cancel' and then 'Continue editing' buttons")
    public void staysOnEditingPage() {
        logger.info("Starting verifyUserStaysOnEditFormAfterClickingContinueEditing");
        String checkUserStaysOnEditingPage = loadApplication()
                        .loginIn(getTemporaryUser())
                        .clickEditButton()
                        .clearNameField()
                        .fillNameField("NewName")
                        .clickCancelButtonWithPopUp()
                        .clickContinueEditingButton()
                        .getTitleOnEditPage()
                        .getText();

        Assert.assertEquals(checkUserStaysOnEditingPage,"Edit Profile");
    }

    @Test(testName = "GC-1554")
    @Description("Verify possibility of pressing 'Escape' when the 'Cancel' pop-up notification is opened")
    public void pressEscOnKeyboardOnCancelPopUpEditingProfile() {
        logger.info("Starting verifyUserCanPressEscOnKeyboardOnCancelPopUp");
        String verifyUserCanUseEsc = loadApplication()
                .loginIn(getTemporaryUser())
                .clickEditButton()
                .clearNameField()
                .fillNameField("NewNewName")
                .clickCancelButtonWithPopUp()
                .clickEsc()
                .getTitleOnEditPage()
                .getText();

        Assert.assertEquals(verifyUserCanUseEsc, "Edit Profile");
    }
    @Test(testName = "GC-1638")
    @Description("Verify the warning about losing unsaved changes when User goes to another page")
    public void checkWarningWhenUserGoesToAnotherPage() {
        logger.info("Starting verifyWarningPopUpWhenUserUnsavedInformationOnEditingProfileGoesToAnotherPage");
        String warningPopUp = loadApplication()
                .loginIn(getTemporaryUser())
                .clickEditButton()
                .clearNameField()
                .fillNameField("New Name For Test")
                .clickEcoNewsButton()
                .getTitleOfCancelPopUpComponent();

        Assert.assertEquals(warningPopUp, "All created content will be lost.");
    }

    @Test(testName = "GC-1647")
    @Description("Verify clicking on 'Enter' button on the 'Cancel' pop-up window")
    public void checkNameIsNotChangingWhenPressEnterAtCancelPopUp() {
        logger.info("Starting verifyUserCanClickEnterButtonOnCancelPopUpWindow");
        String pressEnterAtCancelPopUp = loadApplication()
                .loginIn(getTemporaryUser())
                .clickEditButton()
                .clearNameField()
                .fillNameField("Petro")
                .clickCancelButtonWithPopUp()
                .clickEnter()
                .getUsernameLabel()
                .getText();

        Assert.assertNotEquals(pressEnterAtCancelPopUp,"Petro");
    }

    @Test(testName = "GC-1494")
    @Description("Verify that all filled fields were not changed after clicking on 'Continue editing")
    public void verifyAllFieldsAreNotChangedAfterClickingContinueEditing() {
        final String NAME = "New User Name";
        final String CITY = "Lviv and Lviv";
        final String CREDO = "New Credo";

        logger.info("Starting verifyAllFieldsAreNotChangedAfterClickingContinueEditing");
        EditProfilePage checkingAllFilledFields = loadApplication()
                .loginIn(getTemporaryUser())
                .clickEditButton()
                .clearNameField()
                .fillNameField(NAME)
                .clearCityField()
                .fillCityField(CITY)
                .clearCredoField()
                .fillCredoField(CREDO)
                .clickCancelButtonWithPopUp()
                .clickContinueEditingButton();

        String name = checkingAllFilledFields.getNameField().getText();
        String city = checkingAllFilledFields.getCityField().getText();
        String credo = checkingAllFilledFields.getCredoField().getText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(name, NAME);
        softAssert.assertEquals(city, CITY);
        softAssert.assertEquals(credo, CREDO);
        softAssert.assertAll();
    }
}
