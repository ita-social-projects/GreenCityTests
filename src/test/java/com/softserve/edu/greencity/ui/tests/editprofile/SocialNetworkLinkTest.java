package com.softserve.edu.greencity.ui.tests.editprofile;

import com.softserve.edu.greencity.data.editprofile.EditProfileDataRepository;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.MyHabitPage;
import com.softserve.edu.greencity.ui.pages.cabinet.editprofile.DeleteSocialNetworkPopUpComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.editprofile.EditProfilePage;
import com.softserve.edu.greencity.ui.pages.cabinet.editprofile.SocialNetworkComponent;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithLoginLogout;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EditProfileService;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.softserve.edu.greencity.ui.tests.editprofile.EditProfileTexts.*;

public class SocialNetworkLinkTest extends GreenCityTestRunnerWithLoginLogout {
    private EditProfilePage editProfilePage;
    private User user = UserRepository.get().temporary();

    @BeforeMethod
    public void openEditProfilePage() {
        logger.info("Starting signInWithValidCredentials");
        editProfilePage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .clickEditButton();
    }

    @AfterMethod
    public void clearUp() {
        EditProfileService editProfileService = new EditProfileService();
        editProfileService.updateUserEditProfileToDefaultByEmail(user.getEmail());
;
    }

    @DataProvider(name="popUpTexts")
    public Object[][] textsForDeleteLinkPopUp() {
        return new Object[][]{
                {"en", DELETE_SOCIAL_NETWORK_POP_UP_TEXT_EN.getText(),
                        CANCEL_DELETING_SOCIAL_NETWORK_BUTTON_TEXT_EN.getText(),
                        YES_DELETE_SOCIAL_NETWORK_BUTTON_TEXT_EN.getText()},
                {"ua", DELETE_SOCIAL_NETWORK_POP_UP_TEXT_UA.getText(),
                        CANCEL_DELETING_SOCIAL_NETWORK_BUTTON_TEXT_UA.getText(),
                        YES_DELETE_SOCIAL_NETWORK_BUTTON_TEXT_UA.getText()},
                {"ru", DELETE_SOCIAL_NETWORK_POP_UP_TEXT_RU.getText(),
                        CANCEL_DELETING_SOCIAL_NETWORK_BUTTON_TEXT_RU.getText(),
                        YES_DELETE_SOCIAL_NETWORK_BUTTON_TEXT_RU.getText()}
        };
    }

    @Test(testName = "GC-1509")
    @Description("User information without social networks can be saved and icons do not display on the 'My habits' page")
    public void infoWithoutNetworksSavedAndIconsNotDisplayed(){
        logger.info("Starting infoWithoutNetworksSavedAndIconsNotDisplayed");
        MyHabitPage myHabitPage = editProfilePage
                .fillAllRequiredFields(EditProfileDataRepository.get().getRequiredFieldsEditProfile())
                .clickSaveButton();

        softAssert.assertFalse(myHabitPage.isSocialIconPresent());
        softAssert.assertEquals(myHabitPage.getUsernameLabelText(), EditProfileDataRepository.get().getRequiredFieldsEditProfile().getName());
        softAssert.assertEquals(myHabitPage.getCityLabelText(), EditProfileDataRepository.get().getRequiredFieldsEditProfile().getCity());
        softAssert.assertEquals(myHabitPage.getCredoLabelText(), EditProfileDataRepository.get().getRequiredFieldsEditProfile().getCredo());
        softAssert.assertAll();
    }

    @Test(testName = "GC-1510")
    @Description("User can add to profile up to 5 any social network links")
//    @Ignore
    public void verifyPossibilityToAddUpToFiveNetworks(){
        logger.info("Starting verifyPossibilityToAddUpToFiveNetworks");
        MyHabitPage myHabitPage = editProfilePage
                .fillMaximumSocialNetworksFields(EditProfileDataRepository.get().getUpToFiveSocialNetworks())
                .clickSaveButton();

        Assert.assertEquals(myHabitPage.getSocialNetworkItemsContainer().getSocialNetworksSize(), 5);
    }

    @Test(testName = "GC-1512")
    @Description("User cannot add more than 5 social network links")
    public void verifyImpossibilityToMoreThanFiveNetworks(){
        logger.info("Starting verifyImpossibilityToMoreThanFiveNetworks");
        EditProfilePage newEditProfilePage = editProfilePage
                .fillMaximumSocialNetworksFields(EditProfileDataRepository.get().getUpToFiveSocialNetworks());

        Assert.assertFalse(newEditProfilePage.isAddSocialNetworkButtonActive(), "'Add social network' button should be disabled");
        editProfilePage.clickCancelButton().clickConfirmationButtonAfterCancelButtonPopup();
    }

    @Test(testName = "GC-1513")
    @Description("Error message appears when user adds two similar social network links")
    public void verifyErrorMessageAppearWhenUserAddTwoSimilarLinks(){
        logger.info("Starting verifyErrorMessageAppearWhenUserAddTwoSimilarLinks");
        SocialNetworkComponent socialNetworkComponent = editProfilePage
                .clickAddSocialNetworksButton()
                .fillSocialNetworkField(EditProfileDataRepository.get().getSocialNetworkFacebook())
                .clickAddButton()
                .clickAddSocialNetworksButton()
                .fillSocialNetworkField(EditProfileDataRepository.get().getSocialNetworkFacebook())
                .clickAddButtonWithTheInvalidLink();

        softAssert.assertEquals(socialNetworkComponent.getInvalidLinkTextError(), INVALID_LINK_ERROR.getText());
        softAssert.assertEquals(socialNetworkComponent.getColorOfInvalidLinkTextError(), INVALID_LINK_ERROR_COLOR.getText());
        softAssert.assertFalse(socialNetworkComponent.isAddButtonActive());
        softAssert.assertAll();

        socialNetworkComponent
                .clickCancelButton()
                .clickConfirmationButtonAfterCancelButtonPopup();
    }

    @Test(testName = "GC-1514")
    @Description("After click 'Cancel' button last edits in 'Social network' module are not saved")
    public void verifyNetworkNotSavedAfterClickCancel(){
        logger.info("Starting verifyNetworkNotSavedAfterClickCancel");
        MyHabitPage myHabitPage = editProfilePage
                .clickAddSocialNetworksButton()
                .fillSocialNetworkField(EditProfileDataRepository.get().getSocialNetworkFacebook())
                .clickAddButton()
                .clickCancelButton()
                .clickConfirmationButtonAfterCancelButtonPopup();

        Assert.assertFalse(myHabitPage.isSocialIconPresent());
    }

    @Test(testName = "GC-1515")
    @Description("User can edit social network links")
//    @Ignore
    public void verifyUserCanEditSocialNetworkLinks(){
        logger.info("Starting verifyUserCanEditSocialNetworkLinks");
        MyHabitPage myHabitPage = editProfilePage
                .clickAddSocialNetworksButton()
                .fillSocialNetworkField(EditProfileDataRepository.get().getSocialNetworkFacebook())
                .clickAddButton()
                .getSocialNetworksContainer()
                .chooseSocialNetworkByNumber(0)
                .clickEditLinkButton()
                .fillSocialNetworkField(EditProfileDataRepository.get().getSocialNetworkInstagram())
                .clickAddButton()
                .clickSaveButton();

        Assert.assertTrue(myHabitPage.isSocialIconPresent());
    }

    @Test(testName = "GC-1516", dataProvider = "popUpTexts")
    @Description("L10n deleting confirmation message ")
    @Ignore
    public void verifyPopUpAppearsWhenDeleteLink(String language, String warning, String cancel, String yes){
        logger.info("Starting verifyPopUpAppearsWhenDeleteLink");
        editProfilePage.createLanguageSwitchComponent()
                .changeLanguage(language);

        DeleteSocialNetworkPopUpComponent deleteSocialNetworkPopUpComponent =
                editProfilePage
                        .clickAddSocialNetworksButton()
                        .fillSocialNetworkField(EditProfileDataRepository.get().getSocialNetworkFacebook())
                        .clickAddButton()
                        .getSocialNetworksContainer()
                        .chooseSocialNetworkByNumber(0)
                        .clickDeleteButton();

        softAssert.assertEquals(deleteSocialNetworkPopUpComponent.getWarningText(), warning);
        softAssert.assertEquals(deleteSocialNetworkPopUpComponent.getCancelButtonText(), cancel);
        softAssert.assertEquals(deleteSocialNetworkPopUpComponent.getYesDeletingButtonText(),yes);
        softAssert.assertAll();
        editProfilePage.clickConfirmationButtonAfterDeleteButtonPopup();
    }

    @Test(testName = "GC-1517")
    @Description("User can remove social media links")
//    @Ignore
    public void verifyThatUserCanRemoveSocialNetworkLink(){
        logger.info("Starting verifyThatUserCanRemoveSocialNetworkLink");
        MyHabitPage myHabitPage = editProfilePage
                .clickAddSocialNetworksButton()
                .fillSocialNetworkField(EditProfileDataRepository.get().getSocialNetworkInstagram())
                .clickAddButton()
                .clickSaveButton()
                .clickEditButton()
                .getSocialNetworksContainer()
                .chooseSocialNetworkByNumber(0)
                .clickDeleteButton()
                .clickYesDeleteButton()
                .clickSaveButton();

        Assert.assertFalse(myHabitPage.isSocialIconPresent());
    }

    @Test(testName = "GC-1518")
    @Description("Social network link is saved when user does not confirm deleting of item")
    public void verifySocialNetworkIsSavedIfButtonCancelClicked(){
        logger.info("Starting verifySocialNetworkIsSavedIfButtonCancelClicked");
        EditProfilePage newEditProfilePage = editProfilePage
                .clickAddSocialNetworksButton()
                .fillSocialNetworkField(EditProfileDataRepository.get().getSocialNetworkFacebook())
                .clickAddButton()
                .getSocialNetworksContainer()
                .chooseSocialNetworkByNumber(0)
                .clickDeleteButton()
                .clickCancelDeletingButton();

        int networksSize = newEditProfilePage.getSocialNetworksContainer().getSocialNetworksSize();
        String socialNetworkLink = "https://" + newEditProfilePage
                .getSocialNetworksContainer()
                .chooseSocialNetworkByNumber(0)
                .getSocialNetworkLinkElementText();

        softAssert.assertEquals(networksSize, 1);
        softAssert.assertEquals(socialNetworkLink,
                EditProfileDataRepository.get().getSocialNetworkFacebook().getSocialNetwork());
        softAssert.assertTrue(newEditProfilePage.clickSaveButton().isSocialIconPresent());
        softAssert.assertAll();
    }


    @Test(testName = "GC-1519")
    @Description("The icons of social network on User Profile are clickable and redirect to corresponding link")
//    @Ignore
    public void verifyThatSocialNetworkIconIsClickable(){
        MyHabitPage myHabitPage = editProfilePage
                .clickAddSocialNetworksButton()
                .fillSocialNetworkField(EditProfileDataRepository.get().getSocialNetworkFacebook())
                .clickAddButton()
                .clickSaveButton();

        Assert.assertTrue(myHabitPage.getSocialNetworkItemsContainer().chooseSocialNetworkItemsByNumber(0).isSocialIconClickable());
    }

    @Test
    @Description("Error message appeared if added invalid social network link")
    public void verifyThatUserCanNotWriteInvalidLink(){
        SocialNetworkComponent socialNetworkComponent = editProfilePage
                .clickAddSocialNetworksButton()
                .fillSocialNetworkField(EditProfileDataRepository.get().getSocialNetworkWithInvalidLink())
                .clickSocialNetworkTitle();

        softAssert.assertFalse(socialNetworkComponent.isAddButtonActive());
        softAssert.assertEquals(socialNetworkComponent.getInvalidLinkTextError(), INCORRECT_URL_ERROR_MESSAGE.getText());
        softAssert.assertEquals(socialNetworkComponent.getColorOfInvalidLinkTextError(), INVALID_LINK_ERROR_COLOR.getText());
        softAssert.assertAll();
        editProfilePage.clickCancelButton().clickConfirmationButtonAfterDeleteButtonPopup();
    }

//    @DataProvider(name = "iconsAndLinks")
//    public Object[][] iconsWithLinks(){
//        return new Object[][]{
//                {"facebook", EditProfileDataRepository.get().getSocialNetworkFacebook()},
//                {"twitter", EditProfileDataRepository.get().getSocialNetworkTwitter()},
//                {"instagram", EditProfileDataRepository.get().getSocialNetworkInstagram()},
//                {"green-city", EditProfileDataRepository.get().getSocialNetworkTikTok()}
//        };
//    }

//    @Test(dataProvider = "iconsAndLinks")
//    public void verifyIconsOnEditProfile(String attribute, EditProfileData data){
//        MyHabitPage myHabitPage = editProfilePage
//                .clickAddSocialNetworksButton()
//                .fillSocialNetworkField(data)
//                .clickAddButton()
//                .clearCredoField()
//                .fillCredoField("credo")
//                .clickSaveButton();
//
//        boolean attributes = myHabitPage.getSocialNetworkItemsContainer()
//                .chooseSocialNetworkItemsByNumber(0)
//                .isIconContainsAttribute(attribute);
//
////        Assert.assertTrue(myHabitPage.getSocialNetworkItemsContainer()
////                .chooseSocialNetworkItemsByNumber(0)
////                .isIconContainsAttribute(attribute));
//    }
}
