package com.softserve.edu.greencity.ui.tests.editprofile;

import com.softserve.edu.greencity.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.data.editprofile.EditProfileDataRepository;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.MyHabitPage;
import com.softserve.edu.greencity.ui.pages.cabinet.editprofile.AddedSocialNetworkContainer;
import com.softserve.edu.greencity.ui.pages.cabinet.editprofile.DeleteSocialNetworkPopUpComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.editprofile.EditProfilePage;
import com.softserve.edu.greencity.ui.pages.cabinet.editprofile.SocialNetworkComponent;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.softserve.edu.greencity.ui.tests.editprofile.EditProfileTexts.*;

public class SocialNetworkLinkTest extends GreenCityTestRunner {
    private User user = UserRepository.get().temporary();
    private EditProfilePage editProfilePage;

    @BeforeMethod
    public void openEditProfilePage() {
        logger.info("Starting signInWithValidCredentials");
        User user = UserRepository.get().temporary();
        editProfilePage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .goToEditProfile();
    }

//    @AfterMethod
//    public void signOut(){
//        //TODO what obj to use?
//        new MyHabitPage(driver)
//                .goToEditProfile()
//                .resetFields(EditProfileDataRepository.get().getRequiredDefaultFieldsEditProfile())
//                .clickSaveButton()
//                .signOut();
//    }

    @DataProvider(name="popUpTexts")
    public Object[][] textsForDeleteLinkPopUp() {
        return new Object[][]{
                {"en", DELETE_SOCIAL_NETWORK_POP_UP_TEXT_EN.getText(),
                        CANCEL_DELETING_SOCIAL_NETWORK_BUTTON_TEXT_EN.getText(),
                        YES_DELETE_SOCIAL_NETWORK_BUTTON_TEXT_EN.getText()
                },
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
        MyHabitPage myHabitPage = editProfilePage
                .fillAllRequiredFields(EditProfileDataRepository.get().getRequiredFieldsEditProfile())
                .clickSaveButton();

        //add db testing
        Assert.assertFalse(myHabitPage.isSocialIconPresent());
    }

    @Test(testName = "GC-1510")
    @Description("User can add to profile up to 5 any social network links")
    public void verifyPossibilityToAddUpToFiveNetworks(){
        MyHabitPage myHabitPage = editProfilePage
                .clickAddSocialNetworksButton()
                .fillUpToFiveSocialNetworksFields(EditProfileDataRepository.get().getUpToFiveSocialNetworks())
                .clickSaveButton();


    }

    @Test(testName = "GC-1512")
    @Description("User cannot add more than 5 social network links")
    public void verifyImpossibilityToMoreThanFiveNetworks(){
        EditProfilePage newEditProfilePage = editProfilePage
                .clickAddSocialNetworksButton()
                .fillUpToFiveSocialNetworksFields(EditProfileDataRepository.get().getUpToFiveSocialNetworks());

        Assert.assertFalse(newEditProfilePage.isAddSocialNetworkButtonActive(), "'Add social network' button should be disabled");
    }

    @Test(testName = "GC-1513")
    @Description("Error message appears when user adds two similar social network links")
    public void verifyErrorMessageAppearWhenUserAddTwoSimilarLinks(){
        SocialNetworkComponent socialNetworkComponent = editProfilePage
                .clickAddSocialNetworksButton()
                .fillSocialNetworkField(EditProfileDataRepository.get().getSocialNetworkFacebook())
                .clickAddButton()
                .clickAddSocialNetworksButton()
                .fillSocialNetworkField(EditProfileDataRepository.get().getSocialNetworkFacebook())
                .clickAddButtonWithTheSameLink();

        Assert.assertEquals(socialNetworkComponent.getInvalidLinkTextError(), INVALID_LINK_ERROR.getText());
        Assert.assertEquals(socialNetworkComponent.getColorOfInvalidLinkTextError(), INVALID_LINK_ERROR_COLOR.getText());
    }

    @Test(testName = "GC-1514")
    @Description("After click 'Cancel' button last edits in 'Social network' module are not saved")
    public void verifyNetworkNotSavedAfterClickCancel(){
        MyHabitPage myHabitPage = editProfilePage
                .clickAddSocialNetworksButton()
                .fillSocialNetworkField(EditProfileDataRepository.get().getSocialNetworkFacebook())
                .clickAddButton()
                .clickCancelButton();

        Assert.assertFalse(myHabitPage.isSocialIconPresent());
    }

    @Test(testName = "GC-1515")
    @Description("User can edit social network links")
    public void verifyUserCanEditSocialNetworkLinks(){
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
        //bug
        Assert.assertTrue(myHabitPage.isSocialIconPresent());
    }

    @Test(testName = "GC-1516", dataProvider = "popUpTexts")
    @Description("L10n deleting confirmation message ")
    public void verifyPopUpAppearsWhenDeleteLink(String language, String warning, String cancel, String yes){
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

//        deleteSocialNetworkPopUpComponent.clickCancelDeletingButton().clickCancelButton();
    }

    @Test(testName = "GC-1517")
    @Description("User can remove social media links")
    public void verifyThatUserCanRemoveSocialNetworkLink(){
        MyHabitPage myHabitPage = editProfilePage
                .clickAddSocialNetworksButton()
                .fillSocialNetworkField(EditProfileDataRepository.get().getSocialNetworkInstagram())
                .clickAddButton()
                .clickSaveButton()
                .goToEditProfile()
                .getSocialNetworksContainer()
                .chooseSocialNetworkByNumber(0)
                .clickDeleteButton()
                .clickYesDeleteButton()
                .clickSaveButton();

        Assert.assertFalse(myHabitPage.isSocialIconPresent());
    }

    @Test(testName = "GC-1518")
    @Description(" Social network link is saved when user does not confirm deleting of item")
    public void verifySocialNetworkIsSavedIfButtonCancelClicked(){
        AddedSocialNetworkContainer socialNetworkContainer = editProfilePage
                .clickAddSocialNetworksButton()
                .fillSocialNetworkField(EditProfileDataRepository.get().getSocialNetworkFacebook())
                .clickAddButton()
                .getSocialNetworksContainer()
                .chooseSocialNetworkByNumber(0)
                .clickDeleteButton()
                .clickCancelDeletingButton()
                .getSocialNetworksContainer();

        softAssert.assertEquals(socialNetworkContainer.getSocialNetworksSize(), 1);
        softAssert.assertEquals("https://" + socialNetworkContainer
                        .chooseSocialNetworkByNumber(0)
                        .getSocialNetworkLinkElementText(),
                EditProfileDataRepository.get().getSocialNetworkFacebook().getSocialNetwork()
                );
        softAssert.assertAll();
    }

    @Test(testName = "GC-1519")
    @Description("The icons of social network on User Profile are clickable and redirect to corresponding link")
    public void verifyThatSocialNetworkIconIsClickable(){
        MyHabitPage myHabitPage = editProfilePage
                .clickAddSocialNetworksButton()
                .fillSocialNetworkField(EditProfileDataRepository.get().getSocialNetworkFacebook())
                .clickAddButton()
                .clickSaveButton();

        Assert.assertTrue(myHabitPage.isSocialIconClickable());
    }
}
