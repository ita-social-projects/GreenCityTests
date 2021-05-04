package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.data.Languages;
import static com.softserve.edu.greencity.ui.locators.MyCabinetLocators.*;
import com.softserve.edu.greencity.ui.pages.common.TopPart;
import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyCabinetPage extends TopPart  {
    protected WebDriverWait wait;
    private HabitsContainer habitsContainer;
    private MyGoalsContainer goalsContainer;
    private LanguageComponents languageSwitcher;

    public MyCabinetPage(WebDriver driver) {
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

    @Step("Switch RU language")
    public MyCabinetPage switchRuLanguage() {
        languageSwitcher = new LanguageComponents(driver);
        languageSwitcher.clickRuLanguage();
        return new MyCabinetPage(driver);
    }
}
