package com.softserve.edu.greencity.ui.tests.editprofile;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.editprofile.EditPicturePopUpComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.editprofile.EditProfilePage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.softserve.edu.greencity.ui.tests.editprofile.EditProfileTexts.*;

public class EditProfileEditPhoto extends GreenCityTestRunner {
    private EditProfilePage editProfilePage;
    private EditPicturePopUpComponent editPicturePopUpComponent;

    private User getTemporaryUser() {
        return UserRepository.get().temporary();
    }

    @BeforeMethod
    public void OpenCreateNewsPage() {
        editProfilePage = loadApplication()
                .loginIn(getTemporaryUser())
                .clickEditButton();
    }

    @AfterMethod
    private void goOutFromEditProfilePage() {
        editProfilePage = editPicturePopUpComponent.clickClosePopUpButton();
        editProfilePage.clickCancelButtonWithPopUp().clickCancelButton();
    }

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

    @DataProvider(name = "deletePhotoPopUpTexts")
    private Object[][] textsForDeletePhotoPopUp() {
        return new Object[][]{
                {"en", DELETE_PHOTO_POP_UP_QUESTION_TEXT_EN.getText(),
                        CONTINUE_EDITING_IN_DELETE_PHOTO_TEXT_EN.getText(),
                        YES_DELETE_IN_DELETE_PHOTO_TEXT_EN.getText()},
                {"ua", DELETE_PHOTO_POP_UP_QUESTION_TEXT_UA.getText(),
                        CONTINUE_EDITING_IN_DELETE_PHOTO_TEXT_UA.getText(),
                        YES_DELETE_IN_DELETE_PHOTO_TEXT_UA.getText()},
                {"ru", DELETE_PHOTO_POP_UP_QUESTION_TEXT_RU.getText(),
                        CONTINUE_EDITING_IN_DELETE_PHOTO_TEXT_RU.getText(),
                        YES_DELETE_IN_DELETE_PHOTO_TEXT_RU.getText()}
        };
    }

    @Test(testName = "GC-1563", description = "GC-1563", dataProvider = "editPhotoPopUpTexts")
    @Description("Localization (En, Uk, Ru) verification on Edit photo pop up")
    public void localizationVerificationOnEditPicturePopUp(String languages, String question, String cancel,
                                                           String delete, String upload) {
        logger.info("Starting localizationVerificationOnEditPicturePopUp");

        editProfilePage.createLanguageSwitchComponent()
                .changeLanguage(languages);

//        editProfilePage.clickEditPhotoButton().uploadPNGImage().clickSavePhotoButton();

        editPicturePopUpComponent = editProfilePage.clickEditPhotoButton();

        softAssert.assertEquals(editPicturePopUpComponent.getTitleInPopUpEditPicture(), question);
        softAssert.assertEquals(editPicturePopUpComponent.getCancelButton(), cancel);
        softAssert.assertEquals(editPicturePopUpComponent.getDeletePhotoButton(), delete);
        softAssert.assertEquals(editPicturePopUpComponent.getUploadNewPhotoButton(), upload);
    }

    @Test(testName = "GC-1597", dataProvider = "deletePhotoPopUpTexts")
    @Description("Localization (En, Uk, Ru) verification on Delete photo pop up")
    public void localizationVerificationOnDeletePhotoPopUp(String languages, String question,
                                                           String continueEditing, String yes_delete) {
        logger.info("Starting verifyImpossibleToAddPNGImageMore10Mb");


        editProfilePage.createLanguageSwitchComponent()
                .changeLanguage(languages);

        editPicturePopUpComponent =
                editProfilePage.clickEditPhotoButton()
                        .uploadJPGImage()
                        .clickSavePhotoButton()
                        .clickEditPhotoButton()
                        .clickDeletePhotoButton();

        softAssert.assertEquals(editPicturePopUpComponent.getTitleInPopUpInDeletePhoto(), question);
        softAssert.assertEquals(editPicturePopUpComponent.getContinueEditingButtonInDeletePhotoPopUp(), continueEditing);
        softAssert.assertEquals(editPicturePopUpComponent.getYesDeleteButtonInDeletePhotoPopUp().getText(), yes_delete);

    }

    @Test
    @Description("Verify that system doesn't allow to add PNG image more than 10 MB")
    public void verifyImpossibleToAddPNGImageMore10Mb() {
        logger.info("Starting verifyImpossibleToAddPNGImageMore10Mb");
//        editPicturePopUpComponent = editProfilePage.clickEditPhotoButton()
//                .clickUploadNewPhotoButton()
//                .uploadTooLargePNGImage();
//        Assert.assertFalse(editPicturePopUpComponent.isSaveButtonClickable());
//        Assert.assertEquals(editPicturePopUpComponent.getErrorTextWhenInvalidImage(), UPLOAD_IMAGE_TEXT_ERROR.getText());
//        editPicturePopUpComponent.clickClosePopUpButton();
//
    }

    @Test
    @Description("Verify that system doesn't allow to add GIF image")
    public void verifyImpossibleToAddGIFImage() {
        logger.info("Starting verifyImpossibleToAddGIFImage");
//        editPicturePopUpComponent = editProfilePage.clickEditPhotoButton()
//                .clickUploadNewPhotoButton()
//                .uploadGIFImage();
//        Assert.assertFalse(editPicturePopUpComponent.isSaveButtonClickable());
//        Assert.assertEquals(editPicturePopUpComponent.getErrorTextWhenInvalidImage(), UPLOAD_IMAGE_TEXT_ERROR.getText());
//        editPicturePopUpComponent.clickClosePopUpButton();
//
    }

    @Test
    @Description("Verify that system doesn't allow to add JPEG image more than 10 MB")
    public void verifyImpossibleToAddJPEGImageMore10Mb() {
        logger.info("Starting verifyImpossibleToAddJPEGImageMore10Mb");
//        editPicturePopUpComponent = editProfilePage.clickEditPhotoButton()
//                .clickUploadNewPhotoButton()
//                .uploadTooLargeJPEGImage();
//        Assert.assertFalse(editPicturePopUpComponent.isSaveButtonClickable());
//        Assert.assertEquals(editPicturePopUpComponent.getErrorTextWhenInvalidImage(), UPLOAD_IMAGE_TEXT_ERROR.getText());
//        editPicturePopUpComponent.clickClosePopUpButton();
//
    }

    @Test(testName = "GC-1594")
    @Description("User can delete uploaded photo but can not delete default photo")
    public void userCanNotDeleteDefaultPhoto() {
        logger.info("Starting userCanNotDeleteDefaultPhoto");

//        editProfilePage.clickEditPhotoButton()
//                .clickUploadNewPhotoButton()
//                .uploadJPGImage()
//                .clickSavePhotoButton();
//        editProfilePage.clickEditPhotoButton()
//                .clickDeletePhotoButton()
//                .clickYesDeleteButtonInDeletePhotoPopUp();
//        editProfilePage.clickEditPhotoButton();
//
//        Assert.assertFalse(editPicturePopUpComponent.isDeleteButtonClickable());
//        editPicturePopUpComponent.clickClosePopUpButton();

    }

}






