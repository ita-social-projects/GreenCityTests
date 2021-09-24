package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.api.google.sheets.ValueProvider;
import com.softserve.edu.greencity.ui.elements.IconElement;
import com.softserve.edu.greencity.ui.elements.ImageElement;
import com.softserve.edu.greencity.ui.elements.LabelElement;
import com.softserve.edu.greencity.ui.pages.cabinet.editprofile.EditProfilePage;
import com.softserve.edu.greencity.ui.pages.common.TopPart;
import com.softserve.edu.greencity.ui.pages.common.WelcomePage;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.softserve.edu.greencity.ui.locators.MyHabitLocators.*;
import static com.softserve.edu.greencity.ui.locators.MyHabitLocators.SHOPPING_LIST_LABEL;

public class MyHabitPage extends TopPart  {
    protected WebDriverWait wait;
    private HabitsContainer habitsContainer;
    private MyGoalsContainer goalsContainer;
    private LanguageComponents languageSwitcher;

    private ImageElement userImage;
    private LabelElement usernameLabel;
    private LabelElement cityLabel;
    private LabelElement credoLabel;
    private LabelElement shoppingListLabel;
    private IconElement socialIcon;
    private ImageElement logo;

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
        waitsSwitcher.setExplicitWait(10,
                ExpectedConditions.visibilityOfElementLocated(EDIT_PROFILE_BUTTON.getPath()));
        return searchElementByCss(EDIT_PROFILE_BUTTON.getPath());
    }

    public ImageElement getUserImage(){
        userImage = new ImageElement(driver, USER_IMAGE);
        return userImage;
    }
    public ImageElement getLogo(){
        logo = new ImageElement(driver,LOGO);
        return logo;
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
    public LabelElement getShoppingListLabel(){
        if(shoppingListLabel == null){
            shoppingListLabel = new LabelElement(driver, SHOPPING_LIST_LABEL);
        }
        return  shoppingListLabel;
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

    public boolean isShoppingListPresent(){
        try {
            return getShoppingListLabel().isDisplayedLabel();
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
    public boolean isCityLabelPresent(){
        try {
            return getCityLabel().isDisplayedLabel();
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
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
    public EditProfilePage clickEditButton(){
        getEditButton().click();
        WaitsSwitcher.sleep(5000);
        return new EditProfilePage(driver);
    }

    public WelcomePage navigateToWelcomePage(){
        getLogo().click();
        return new WelcomePage(driver);
    }

    public SocialNetworkItemsContainer getSocialNetworkItemsContainer() {
        try {
            waitsSwitcher.setExplicitWait(60,
                    ExpectedConditions.presenceOfAllElementsLocatedBy(SOCIAL_NETWORK_CONTAINER.getPath()));
        }catch (TimeoutException e){
            logger.warn("icons not exists");
        }
        return new SocialNetworkItemsContainer(driver);
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
