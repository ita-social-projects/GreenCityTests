package com.softserve.edu.greencity.ui.tests.editprofile;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.MyHabitPage;
import com.softserve.edu.greencity.ui.pages.cabinet.editprofile.EditProfilePage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithLoginLogout;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//TODO finish tests
public class EditPictureTests extends GreenCityTestRunnerWithLoginLogout {

    private EditProfilePage editProfilePage;

    @BeforeMethod
    public void openEditProfilePage() {
        logger.info("Starting signInWithValidCredentials");
        User user = UserRepository.get().temporary();
        editProfilePage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .clickEditButton();
    }

    @AfterMethod
    public void deleteImage(){
        new MyHabitPage(driver)
                .clickEditButton()
                .clickEditPictureButton()
                .clickDeletePhotoButton()
                .clickYesDeleteButtonInDeletePhotoPopUp();
    }

    @Test(testName = "GC-1566")
    @Description("User can add photo with valid parameters for the first time.")
    public void verifyAddPhotoWithValidParameters(){
        MyHabitPage myHabitPage = editProfilePage
//                .fillCredoField("credo")
                .clickEditPictureButton()
                .uploadPNGImage()
                .clickSavePhotoButton()
                .clickSaveButton();

        softAssert.assertFalse(myHabitPage.isUserImageDefault());
        softAssert.assertAll();
    }
}
