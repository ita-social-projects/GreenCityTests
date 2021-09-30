package com.softserve.edu.greencity.ui.pages.ubsadmin.UbsAdminPage;

import com.softserve.edu.greencity.ui.locators.UbsAdminLocators.UbsAdminCommonLocator;
import com.softserve.edu.greencity.ui.pages.common.TopPart;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UbsAdminCommon extends TopPart {
    private WaitsSwitcher waitsSwitcher;;
//    private UBSAdminTableComponent ubsAdminTableComponent;
//    private UBSAdminViewTableComponent ubsAdminViewTableComponent;
//    private UBSAdminRowTableComponentLocator ubsAdminRowTableComponentLocator;

    private WebElement searchField;
    private WebElement searchIcon;
    private WebElement viewTable;
    private WebElement displayCounterDropdown;
    private WebElement displayCounter10;
    private WebElement displayCounter15;
    private WebElement displayCounter20;
    public UbsAdminCommon(WebDriver driver) {
        super(driver);
    }
    public WebElement getSearchField() {
        searchField = driver.findElement(UbsAdminCommonLocator.SEARCH_FIELD.getPath());
        return searchField;
    }
    public WebElement getSearchIcon() {
        searchIcon = driver.findElement(UbsAdminCommonLocator.SEARCH_ICON.getPath());
        return searchIcon;
    }
    public WebElement getViewTable() {
        viewTable = driver.findElement(UbsAdminCommonLocator.VIEW_TABLE_BUTTON.getPath());
        return viewTable;
    }
    public WebElement getDisplayCounterDropdown() {
        displayCounterDropdown = driver.findElement(UbsAdminCommonLocator.DISPLAY_COUNTER_DROPDOWN.getPath());
        return displayCounterDropdown;
    }
    public WebElement getDisplayCounter10() {
        displayCounter10 = driver.findElement(UbsAdminCommonLocator.DISPLAY_COUNTER_10.getPath());
        return displayCounterDropdown;
    }
    public WebElement getDisplayCounter15() {
        displayCounter15 = driver.findElement(UbsAdminCommonLocator.DISPLAY_COUNTER_15.getPath());
        return displayCounterDropdown;
    }
    public WebElement getDisplayCounter20() {
        displayCounter20 = driver.findElement(UbsAdminCommonLocator.DISPLAY_COUNTER_20.getPath());
        return displayCounterDropdown;
    }
    public WebElement getSortOrderNumberButton(){
        return driver.findElement(UbsAdminCommonLocator.SORT_ORDER_NUMBER_BUTTON.getPath());
    }

    public WebElement getSortDistrictButton(){
        return driver.findElement(UbsAdminCommonLocator.SORT_DISTRICT_BUTTON.getPath());
    }
    public WebElement getSortEmailButton(){
        return driver.findElement(UbsAdminCommonLocator.SORT_EMAIL_BUTTON.getPath());
    }
    public UbsAdminCommon clearSearchField() {
        getSearchField().clear();
        return this;
    }
    public UbsAdminTableComponent getUbsAdminTableComponent() {
        return new UbsAdminTableComponent(driver);
    }

//    public UbsAdminRowTableComponent getTableRow() {
//        tableIsLoaded();
//        return new UBSAdminRowTableComponent(driver, driver.findElement(UBSAdminTableComponentLocator.ROW.getPath()));
//    }

//    public UbsAdminMenuComponent getAdminMenu() {
//        return new UBSAdminMenuComponent(driver);
//    }

    public UbsAdminCommon setSearchField(String s) {
        tableIsLoaded();
        getSearchField().sendKeys(s);
        return this;
    }

//    public UBSAdminViewTableComponent clickViewTable() {
//        tableIsLoaded();
//        getViewTable().click();
//        return new UBSAdminViewTableComponent(driver);
//    }

    public void tableIsLoaded() {
        waitsSwitcher = new WaitsSwitcher(driver);
        waitsSwitcher.setImplicitWait(500);
    }

    public UbsAdminCommon clickDisplayCounterDropdown() {
        getDisplayCounterDropdown().click();
        return this;
    }


    public UbsAdminCommon clickDisplayCounter10() {
        getDisplayCounter10().click();
        tableIsLoaded();
        return this;
    }

    public UbsAdminCommon clickDisplayCounter15() {
        getDisplayCounter15().click();
        tableIsLoaded();
        return this;
    }

    public UbsAdminCommon clickDisplayCounter20() {
        getDisplayCounter20().click();
        tableIsLoaded();
        return this;
    }

    public UbsAdminCommon clickSortDistrictButton(){
        getSortDistrictButton().click();
        tableIsLoaded();
        return this;
    }


    public UbsAdminCommon clickSortOrderNumberButton(){
        getSortOrderNumberButton().click();
        tableIsLoaded();
        return this;
    }
    public UbsAdminCommon clickSortOrderEmailButton(){
        getSortEmailButton().click();
        tableIsLoaded();
        return this;
    }
}
