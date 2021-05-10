package com.softserve.edu.greencity.ui.tests.editprofile;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.editprofile.CancelEditingPopUpComponent;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CancelFunctionalityTests extends GreenCityTestRunner {

    private User getTemporaryUser() {
        return UserRepository.get().temporary();
    }

    @Test(testName = "GC-1636", description = "GC-1636")
    @Description("Verify 'Cancel' pop-up message text on different localizations")
    public void cancelOnEditProfile() {
        logger.info("Starting verifyPopUpMessageTestOnEditProfileCancel");
        CancelEditingPopUpComponent cancelPopUp = loadApplication()
                .loginIn(getTemporaryUser())
                .goToEditProfile()
                .fillCityField("LvivLvivLviv")
                .clickCancelButton();

        String titleOfPopUpOnEn = cancelPopUp.getTitleOfCancelPopUpComponent();
        String subTitleOfPopUpOnEn = cancelPopUp.getSubTitleOfCancelComponent();

        cancelPopUp.clickCancelButton()
                .switchRuLanguage()
                .goToEditProfile()
                .fillCityField("LvivL")
                .clickCancelButton();

        String titleOfPopUpOnRu = cancelPopUp.getTitleOfCancelPopUpComponent();
        String subTitleOfPopUpOnRu = cancelPopUp.getSubTitleOfCancelComponent();

        cancelPopUp.clickCancelButton()
                .switchUaLanguage()
                .goToEditProfile()
                .fillCityField("ASgasga")
                .clickCancelButton();

        String titleOfPopUpOnUa = cancelPopUp.getTitleOfCancelPopUpComponent();
        String subTitleOfPopUpOnUa = cancelPopUp.getSubTitleOfCancelComponent();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(titleOfPopUpOnEn, "All created content will be lost.");
        softAssert.assertEquals(subTitleOfPopUpOnEn, "Do you still want to cancel profile editing?");
        softAssert.assertEquals(titleOfPopUpOnRu, "Все изменения будут потеряны.");
        softAssert.assertEquals(subTitleOfPopUpOnRu, "Отменить редактирование профиля?");
        softAssert.assertEquals(titleOfPopUpOnUa, "Внесені зміни будуть втрачені.");
        softAssert.assertEquals(subTitleOfPopUpOnUa, "Скасувати редагування профілю?");
        softAssert.assertAll();
    }
}
