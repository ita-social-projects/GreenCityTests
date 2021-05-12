package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.elements.IconElement;
import com.softserve.edu.greencity.ui.elements.ImageElement;
import com.softserve.edu.greencity.ui.elements.LabelElement;
import com.softserve.edu.greencity.ui.pages.cabinet.editprofile.EditProfilePage;
import com.softserve.edu.greencity.ui.pages.common.TopPart;
import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.softserve.edu.greencity.ui.locators.MyHabitLocators.*;

public class MyHabitPage extends TopPart  {
    protected WebDriverWait wait;
    private HabitsContainer habitsContainer;
    private MyGoalsContainer goalsContainer;
    private LanguageComponents languageSwitcher;

    private ImageElement userImage;
    private LabelElement usernameLabel;
    private LabelElement cityLabel;
    private LabelElement credoLabel;
    private IconElement socialIcon;

    private final String SRC_ATTRIBUTE = "src";

    public MyHabitPage(WebDriver driver) {
        super(driver);
        //TODO
        checkElements();
    }
    @Step
    private void checkElements() {
        waitsSwitcher.setExplicitWait(25, ExpectedConditions.visibilityOf(getEditButton()));
    }
    @Step
    public HabitsContainer getHabitsContainer() {
        return habitsContainer = new HabitsContainer(driver);
    }
    @Step
    public MyGoalsContainer getGoalsContainer() {
        return goalsContainer = new MyGoalsContainer(driver);
    }
    @Step
    public WebElement getAddNewHabitButton() {
        return searchElementByXpath(ADD_NEW_HABIT_BUTTON.getPath());
    }
    @Step
    public WebElement getEditButton() {
        return searchElementByCss(EDIT_PROFILE_BUTTON.getPath());
    }

    public ImageElement getUserImage(){
        userImage = new ImageElement(driver, USER_IMAGE);
        return userImage;
    }

    public LabelElement getUsernameLabel(){
        if(usernameLabel == null){
            usernameLabel = new LabelElement(driver, USERNAME_LABEL);
        }
        return usernameLabel;
    }

    public LabelElement getCityLabel(){
        if(cityLabel == null){
            cityLabel = new LabelElement(driver, CITY_LABEL);
        }
        return  cityLabel;
    }

    public LabelElement getCredoLabel(){
        if(credoLabel == null){
            credoLabel = new LabelElement(driver, CREDO_LABEL);
        }
        return credoLabel;
    }

    public IconElement getSocialIcon(){
        if(socialIcon == null){
            socialIcon = new IconElement(driver, SOCIAL_MEDIA_ICON);
        }
        return socialIcon;
    }

    public String getUsernameLabelText(){
        return getUsernameLabel().getText();
    }

    public String getCityLabelText(){
        return getCityLabel().getText();
    }

    public String getCredoLabelText(){
        return getCredoLabel().getText();
    }

    public boolean isSocialIconPresent(){
        try {
            return getSocialIcon().isDisplayed();
        }
        catch (TimeoutException er) {
            return false;
        }
    }

    public boolean isSocialIconClickable(){
        try {
            waitsSwitcher.setExplicitWait(ExpectedConditions.elementToBeClickable(SOCIAL_MEDIA_ICON.getPath()));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Step("Go to edit profile")
    public EditProfilePage goToEditProfile(){
        getEditButton().click();
        waitsSwitcher.setImplicitWait(20);
        return new EditProfilePage(driver);
    }

    @Step("Switch RU language")
    public MyHabitPage switchRuLanguage() {
        languageSwitcher = new LanguageComponents(driver);
        languageSwitcher.clickRuLanguage();
        return new MyHabitPage(driver);
    }

    @Step("Switch EN language")
    public MyHabitPage switchENLanguage() {
        languageSwitcher = new LanguageComponents(driver);
        languageSwitcher.clickEnLanguage();
        return new MyHabitPage(driver);
    }
    @Step("Switch UA language")
    public MyHabitPage switchUaLanguage() {
        languageSwitcher = new LanguageComponents(driver);
        languageSwitcher.clickUaLanguage();
        return new MyHabitPage(driver);
    }

    public SocialNetworkItemsContainer getSocialNetworkItemsContainer() {
        SocialNetworkItemsContainer socialNetworkItemsContainer
                = new SocialNetworkItemsContainer(driver);
        try {
            waitsSwitcher.setExplicitWait(60,
                    ExpectedConditions.presenceOfAllElementsLocatedBy(SOCIAL_NETWORK_CONTAINER.getPath()));
        }catch (TimeoutException e){
            socialNetworkItemsContainer.setSocialNetworkItemsSize(0);
        }
        return socialNetworkItemsContainer;
    }

    public boolean isUserImageDefault(){
        try{
            getUserImage().getAttribute(SRC_ATTRIBUTE);
            return false;
        } catch (Exception e){
            return true;
        }
    }
}
