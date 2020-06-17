package com.softserve.edu.greencity.ui.pages.tipstricks;

import com.softserve.edu.greencity.ui.data.Languages;
import com.softserve.edu.greencity.ui.pages.cabinet.MyCabinetPage;
import com.softserve.edu.greencity.ui.pages.common.TopPart;
import com.softserve.edu.greencity.ui.pages.econews.EconewsPage;
import com.softserve.edu.greencity.ui.pages.map.MapPage;
import com.softserve.edu.greencity.ui.tools.QuantityItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TipsTricksPage extends TopPart {

    // Buttons on the TipsTricksPage
    private WebElement startHabitTop;
    private WebElement startHabitCenter;
    private WebElement startHabitBelow;
    private WebElement subscribeOnTipsTricks;

    // field for email for subscribe
    private WebElement enterEmailTipsTricks;
    private WebElement subscriptionErrorMessage;

    // Text about amountPeople, quantityBags, quantityCups
    private WebElement amountPeople;
    private WebElement amountBags;
    private WebElement amountCups;
    // private WebElement qrCode;

    // link to MapPage
    private WebElement linkBags;
    private WebElement linkCups;

    // link to EcoNews
    private WebElement mainEcoNewsLink;
    private WebElement other1EcoNewsLink;
    private WebElement other2EcoNewsLink;
    private WebElement allNewsLink;

    public TipsTricksPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        // init elements
        startHabitTop = driver
                .findElement(By.xpath("//div[@id='header-left']//button[@class='button primary-global-button']"));
        startHabitCenter = driver
                .findElement(By.cssSelector(".stat-row-content.ng-star-inserted:nth-child(2)> div> button"));
        startHabitBelow = driver
                .findElement(By.cssSelector(".stat-row-content.ng-star-inserted:nth-child(1) > div >button"));
        subscribeOnTipsTricks = driver
                .findElement(By.xpath("//div[@id='form-wrapper']/button[@class='primary-global-button']"));
        enterEmailTipsTricks = driver.findElement(By.xpath("//input[@type='email']"));
        subscriptionErrorMessage = driver.findElement(By.id("subscription-error"));
        amountPeople = driver.findElement(By.cssSelector("#stats>h2"));
        amountBags = driver.findElement(By.xpath("//app-stat-row/div/div[2]/div/h3"));
        amountCups = driver.findElement(By.cssSelector(".stat-row-content.ng-star-inserted:nth-child(1) > div >h3"));
        // qrCode =
        // driver.findElement(By.xpath("//div[@id='qr-code-wrapper']/img"));
        linkBags = driver.findElement(
                By.xpath(".//div[@class='stat-row-image ng-star-inserted']//following-sibling::div/div/div/a"));
        linkCups = driver.findElement(By.xpath(
                ".//div[@class='stat-row-image ng-star-inserted']//preceding-sibling::div[@class='stat-row-content ng-star-inserted']/div/div/a"));
        mainEcoNewsLink = driver.findElement(By.xpath(".//div[@class='navigation-menu-left'] / ul / li[1]"));
//        other1EcoNewsLink = driver.findElement(By.cssSelector("div#other-events > div:nth-child(1) > a"));
//        other2EcoNewsLink = driver.findElement(By.cssSelector("div#other-events > div:nth-child(3) > a"));
        allNewsLink = driver.findElement(By.id("eco-events"));
    }

    // Page Object

    // Button 'Start forming a habit'

    public WebElement getStartHabitTop() {
        return startHabitTop;
    }

    public MyCabinetPage clickStartHabitTop() {
        scrollToElementByAction(getStartHabitTop());

        getStartHabitTop().click();

        return new MyCabinetPage(driver);
    }

    public boolean isDisplayedStartHabitTop() {
        return getStartHabitTop().isDisplayed();
    }

    // ButtonCenter 'Start forming a habit'

    public WebElement getStartHabitCenter() {
        return startHabitCenter;
    }

    public void clickStartHabitCenter() {
        getStartHabitCenter().click();
    }

    public boolean isDisplayedStartHabitCenter() {
        return getStartHabitCenter().isDisplayed();
    }

    // ButtonBelow 'Start forming a habit'

    public WebElement getStartHabitBelow() {
        return startHabitBelow;
    }

    public void clickStartHabitBelow() {
        getStartHabitBelow().click();
    }

    public boolean isDisplayedStartHabitBelow() {
        return getStartHabitBelow().isDisplayed();
    }

    // Button 'Subscribe'

    public WebElement getSubscribeOnTipsTricks() {
        return subscribeOnTipsTricks;
    }

    public void clickSubscribeOnTipsTricks() {
        getSubscribeOnTipsTricks().click();

    }

    public boolean isDisplayedSubscribeOnTipsTricks() {
        return getSubscribeOnTipsTricks().isDisplayed();
    }

    // Enter email for Subscribe

    public WebElement getEmailTipsTricks() {
        return enterEmailTipsTricks;
    }

    public String getEmailTipsTricksText() {
        return getEmailTipsTricks().getText();
    }

    public void clickEmailTipsTricks() {
        getEmailTipsTricks().click();
    }

    public void setEmailTipsTricks(String email) {
        getEmailTipsTricks().sendKeys(email);
    }

    public void clearEmailTipsTricks() {
        getEmailTipsTricks().clear();
    }

    public boolean isDisplayedSubscriptionError() {
        return subscriptionErrorMessage.isDisplayed();
    }


    // amount People

    public WebElement getAmountPeople() {
        return amountPeople;
    }

    public String getAmountPeopleText() {
        return getAmountPeople().getText();
    }

    public boolean isDesplayedAmountPeople() {
        return getAmountPeople().isDisplayed();
    }

    // amount Bags

    public WebElement getAmountBags() {
        return amountBags;
    }

    public String getAmountBagsText() {
        return amountBags.getText();
    }

    // amount Cups

    public WebElement getAmountCups() {
        return amountCups;
    }

    public String getAmountCupsText() {
        return amountCups.getText();
    }

    // link bellow Bags to MapPage

    public WebElement getBagsLink() {
        return linkBags;
    }

    public void clickBagsLink() {
        getBagsLink().click();
    }

    // link bellow Cups to MapPage

    public WebElement getCupsLink() {
        return linkCups;
    }

    public void clickCupsLink() {
        getCupsLink().click();
    }

    // link mainEcoNewsLink

    public WebElement getMainEcoNewsLink() {
        return mainEcoNewsLink;
    }

    public void clickMainEcoNewsLink() {
        getMainEcoNewsLink().click();
    }

    // link other1EcoNewsLink
    public WebElement getOther1EcoNewsLink() {
        return other1EcoNewsLink;
    }

    public void clickOther1EcoNewsLink() {
        getOther1EcoNewsLink().click();
    }

    // link other2EcoNewsLink
    public WebElement getOther2EcoNewsLink() {
        return other2EcoNewsLink;
    }

    public void clickOther2EcoNewsLink() {
        getOther2EcoNewsLink().click();
    }

    // link to allNewsLink
    public WebElement getAllNewsLink() {
        return allNewsLink;
    }

    public void clickAllNewsLink() {
        getAllNewsLink().click();
    }
    // Functional

    /**
     * quantityPeople() - reads the text that contains about the number of
     * people
     *
     * @return number People, who signed to GreenCity
     */
    public int quantityPeople() {
        return new QuantityItems().quantityItems(getAmountPeopleText());
    }

    /**
     * @return quantity Bags, which we used
     */
    public int quantityBags() {
        return new QuantityItems().quantityItems(getAmountBagsText());
    }

    /**
     * @return quantity Cups, which we used
     */

    public int quantityCups() {
        return new QuantityItems().quantityItems(getAmountCupsText());
    }

    // Business Logic

    public TipsTricksPage switchLanguage(Languages language) {
        chooseLanguage(language);
        return new TipsTricksPage(driver);
    }

    /**
     * Create habit on page GreenCity using button on top "Start forming a
     * habit"
     *
     * @return open My cabinet
     */
    public MyCabinetPage navigateMyCabinet() {
        clickStartHabitTop();
        return new MyCabinetPage(driver);
    }

    // public MyCabinetPage navigateCenterMyCabinet() {
    // clickStartHabitCenter();
    // return new MyCabinetPage(driver);
    // }

    // public MyCabinetPage navigateHabitBellowMyCabinet() {
    // clickStartHabitBelow();
    // return new MyCabinetPage(driver);
    // }

    /**
     * @return on page Map using link bellow Bags
     */
    public MapPage moveBagsToMap() {
        clickBagsLink();
        return new MapPage(driver);
    }

    /**
     * @return on page Map using link bellow Cups
     */
    public MapPage moveCupsToMap() {
        clickCupsLink();
        return new MapPage(driver);

    }

    public EconewsPage moveMainEcoNewsLink() {
        clickMainEcoNewsLink();
        return new EconewsPage(driver);
    }


}
