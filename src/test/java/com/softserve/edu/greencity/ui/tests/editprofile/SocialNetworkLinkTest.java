package com.softserve.edu.greencity.ui.tests.editprofile;

import com.softserve.edu.greencity.data.editprofile.EditProfileDataRepository;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.MyHabitPage;
import com.softserve.edu.greencity.ui.pages.cabinet.editprofile.EditProfilePage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.*;

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

    @AfterMethod
    public void signOut(){
        //TODO what obj to use?
        new MyHabitPage(driver)
                .goToEditProfile()
                .resetFields(EditProfileDataRepository.get().getRequiredDefaultFieldsEditProfile())
                .clickSaveButton()
                .signOut();
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

//    @Test(testName = "GC-1514")
//    @Description("After click 'Cancel' button last edits in 'Social network' module are not saved")
//    public void verifyNetworkNotSavedAfterClickCancel(){
//        MyHabitPage myHabitPage = editProfilePage
//                .clickAddSocialNetworksButton()
//                .fillSocialNetworkField(EditProfileDataRepository.get().getSocialNetworkFacebook())
//                .clickAddButton()
//                .clickCancelButton();
//
//        Assert.assertFalse(myHabitPage.isSocialIconPresent());
//    }

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

        Assert.assertTrue(myHabitPage.isSocialIconPresent());

    }
}
