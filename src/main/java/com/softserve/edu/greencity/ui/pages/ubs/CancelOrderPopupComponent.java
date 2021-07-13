package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.elements.LabelElement;
import com.softserve.edu.greencity.ui.pages.cabinet.editprofile.EditProfilePage;
import com.softserve.edu.greencity.ui.pages.common.WelcomePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import static com.softserve.edu.greencity.ui.locators.EditProfileLocators.*;

public class CancelOrderPopupComponent<T, R> {

    private WebDriver driver;
    private ButtonElement continueMakingOrderButton;
    private ButtonElement cancelOrderButton;
    private ButtonElement closeButton;
    private String titleOfCancelComponent;
    private String subTitleOfCancelComponent;
    private T currentClass;
    private R redirectClass;

    public CancelOrderPopupComponent(WebDriver driver, T currentClass, R redirectClass) {
        this.driver = driver;
        this.currentClass = currentClass;
        this.redirectClass = redirectClass;
    }

    private ButtonElement getContinueMakingOrderButton() {
        continueMakingOrderButton = new ButtonElement(driver, CONTINUE_EDITING_BUTTON);
        return continueMakingOrderButton;
    }

    private ButtonElement getCancelOrderButton() {
        cancelOrderButton = new ButtonElement(driver, CANCEL_EDITING);
        return cancelOrderButton;
    }

    private ButtonElement getCloseButton() {
        closeButton = new ButtonElement(driver, CLOSE_EDITING_BUTTON);
        return closeButton;
    }

    private boolean isCancelEditPageButtonIsExists() {
        try {
            getCancelOrderButton();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public String getTitleOfCancelPopUpComponent() {
        titleOfCancelComponent = new LabelElement(driver, TITLE_CANCEL_POP_UP).getText();
        return titleOfCancelComponent;
    }

    public String getSubTitleOfCancelComponent() {
        subTitleOfCancelComponent = new LabelElement(driver, SUB_TITLE_CANCEL_POP_UP).getText();
        return subTitleOfCancelComponent;
    }

    public T clickCloseButton() {
        getCloseButton().click();
        return currentClass;
    }

    public T clickContinueMakingOrderButton() {
        getContinueMakingOrderButton().click();
        return currentClass;
    }

    public T clickEsc() {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).build().perform();
        return currentClass;
    }

    public R clickEnter() {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).build().perform();
        return redirectClass;
    }

    public R clickCancelOrderButton() {
        if (isCancelEditPageButtonIsExists()) {
            getCancelOrderButton().click();
        }
        return redirectClass;
    }
}
