package com.softserve.edu.greencity.ui.tests.editprofile;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.MyHabitPage;
import com.softserve.edu.greencity.ui.pages.cabinet.editprofile.EditPicturePopUpComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.editprofile.EditProfilePage;
import com.softserve.edu.greencity.ui.pages.econews.CreateNewsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.softserve.edu.greencity.ui.tests.editprofile.EditProfileTexts.*;

public class EditProfileEditPhoto extends GreenCityTestRunner {
    private User getTemporaryUser() {
        return UserRepository.get().temporary();
    }

    private EditProfilePage editProfilePage;
    //    private EditPicturePopUpComponent editPicturePopUpComponent;
    private MyHabitPage editProfilePages;

    @BeforeMethod
    public void OpenCreateNewsPage() {
        editProfilePage = loadApplication()
                .loginIn(getTemporaryUser())
                .goToEditProfile();
    }

//    @AfterMethod
//    private void signOut(){
//
//    }

    @DataProvider(name = "editPhotoPopUpTexts")
    private Object[][] textsForEditPhotoPopUp() {
        return new Object[][]{
                {"en", EDIT_PICTURE_POP_UP_QUESTION_TEXT_EN.getText(),
                        CANCEL_EDITING_PROFILE_PHOTO_BUTTON_TEXT_EN.getText(),
                        DELETING_PROFILE_PHOTO_BUTTON_TEXT_EN.getText(),
                        UPLOAD_NEW_PROFILE_PHOTO_BUTTON_TEXT_EN.getText()},
                {"ua", EDIT_PICTURE_POP_UP_QUESTION_TEXT_UA.getText(),
                        CANCEL_EDITING_PROFILE_PHOTO_BUTTON_TEXT_UA.getText(),
                        DELETING_PROFILE_PHOTO_BUTTON_TEXT_UA.getText(),
                        UPLOAD_NEW_PROFILE_PHOTO_BUTTON_TEXT_UA.getText()},
                {"ru", EDIT_PICTURE_POP_UP_QUESTION_TEXT_RU.getText(),
                        CANCEL_EDITING_PROFILE_PHOTO_BUTTON_TEXT_RU.getText(),
                        DELETING_PROFILE_PHOTO_BUTTON_TEXT_RU.getText(),
                        UPLOAD_NEW_PROFILE_PHOTO_BUTTON_TEXT_RU.getText()}
        };
    }


    @Test(testName = "GC-1563", description = "GC-1563", dataProvider = "editPhotoPopUpTexts")
    @Description("Localization (En, Uk, Ru) verification on Edit photo pop up")
    public void localizationVerificationOnEditPicturePopUp(String languages, String question, String cancel,
                                                           String delete, String upload) {
        editProfilePage.createLanguageSwitchComponent()
                .changeLanguage(languages);

        EditPicturePopUpComponent editPicturePopUpComponent = editProfilePage.clickEditPhotoButton();
        softAssert.assertEquals(editPicturePopUpComponent.getTitleInPopUpEditPicture(), question);
        softAssert.assertEquals(editPicturePopUpComponent.getCancelButton(), cancel);
        softAssert.assertEquals(editPicturePopUpComponent.getDeletePhotoButton(), delete);
        softAssert.assertEquals(editPicturePopUpComponent.getUploadNewPhotoButton(), upload);

    }
}






