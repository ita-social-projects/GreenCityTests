package com.softserve.edu.greencity.ui.tests.editprofile;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.MyHabitPage;
import com.softserve.edu.greencity.ui.pages.cabinet.editprofile.EditProfilePage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithLoginLogout;
import com.softserve.edu.greencity.ui.tools.jdbc.entity.EditProfileEntity;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EditProfileService;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
    public void deleteImage() {
        new MyHabitPage(driver)
                .clickEditButton()
                .clickEditPictureButton()
                .clickDeletePhotoButton()
                .clickYesDeleteButtonInDeletePhotoPopUp();
    }

    @Test(testName = "GC-1566")
    @Description("User can add photo with valid parameters for the first time.")
    public void verifyAddPhotoWithValidParameters() {
        EditProfileService editProfileService = new EditProfileService();
        EditProfileEntity editProfile = editProfileService.getById(312);

        softAssert.assertNull(editProfile.getProfilePicture(), "ProfilePicture is Null");

        MyHabitPage myHabitPage = editProfilePage
                .clickEditPictureButton()
                .clickUploadNewPhotoButton()
                .uploadPNGImage()
                .clickSavePhotoButton()
                .clickCancelButton()
                .clickConfirmationButtonAfterCancelButtonPopup();

        editProfile = editProfileService.getById(312);

        softAssert.assertNotNull(editProfile.getProfilePicture(), "ProfilePicture NotNull");
        softAssert.assertFalse(myHabitPage.isUserImageDefault(), "Is user picture not default");
        softAssert.assertAll();
    }
}
