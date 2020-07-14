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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TipsTricksPage extends TopPart {
    private WebElement startHabitTop;
    private WebElement startHabitCenter;
    private WebElement startHabitBelow;
    private WebElement subscribeOnTipsTricks;

    private WebElement enterEmailTipsTricks;
    private WebElement subscriptionErrorMessage;

    private WebElement amountPeople;
    private WebElement amountBags;
    private WebElement amountCups;

    private WebElement linkBags;
    private WebElement linkCups;

    private WebElement mainEcoNewsLink;
    private WebElement allNewsLink;

    private WebDriverWait wait;

    public TipsTricksPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        wait = new WebDriverWait(driver, 10);
        WebElement subscriptionError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='header-left']//button[@class='button primary-global-button']")));

        startHabitTop = driver.findElement(By.xpath("//div[@id='header-left']//button[@class='button primary-global-button']"));
    }

    public WebElement getStartHabitTop() {
        return startHabitTop = driver
                .findElement(By.xpath("//div[@id='header-left']//button[@class='button primary-global-button']"));
    }

    public MyCabinetPage clickStartHabitTop() {
        waiting(2);

        scrollToElementByAction(getStartHabitTop());

        getStartHabitTop().click();

        return new MyCabinetPage(driver);
    }

    public boolean isDisplayedStartHabitTop() {
        return getStartHabitTop().isDisplayed();
    }

    public WebElement getStartHabitCenter() {
        return startHabitCenter = driver
                .findElement(By.cssSelector(".stat-row-content:nth-child(2) .button"));
    }

    public void clickStartHabitCenter() {
        getStartHabitCenter().click();
    }

    public boolean isDisplayedStartHabitCenter() {
        return getStartHabitCenter().isDisplayed();
    }

    public WebElement getStartHabitBelow() {
        return startHabitBelow = driver
                .findElement(By.cssSelector(".stat-row-content:nth-child(1) .button"));
    }

    public void clickStartHabitBelow() {
        getStartHabitBelow().click();
    }

    public boolean isDisplayedStartHabitBelow() {
        return getStartHabitBelow().isDisplayed();
    }

    public WebElement getSubscribeOnTipsTricks() {
        return subscribeOnTipsTricks = driver
                .findElement(By.xpath("//div[@id='form-wrapper']/button[@class='primary-global-button']"));
    }

    public void clickSubscribeOnTipsTricks() {
        getSubscribeOnTipsTricks().click();

    }

    private void waiting(int x) {
        try {
            Thread.sleep(x * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isDisplayedSubscribeOnTipsTricks() {
        return getSubscribeOnTipsTricks().isDisplayed();
    }

    public WebElement getEmailTipsTricks() {
        return enterEmailTipsTricks = driver.findElement(By.xpath("//input[@type='email']"));
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

    public boolean isSubscriptionErrorDisplayed() {
        wait = new WebDriverWait(driver, 4);
        WebElement subscriptionError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("subscription-error")));

        return subscriptionError.isDisplayed();
    }

    public WebElement getAmountPeople() {
        return amountPeople = driver.findElement(By.cssSelector("#stats>h2"));
    }

    public String getAmountPeopleText() {
        return getAmountPeople().getText();
    }

    public boolean isDesplayedAmountPeople() {
        return getAmountPeople().isDisplayed();
    }

    public WebElement getAmountBags() {
        return amountBags = driver.findElement(By.cssSelector(".stat-row-content:nth-child(2) h3"));
    }

    public String getAmountBagsText() {
        return getAmountBags().getText();
    }

    public WebElement getAmountCups() {
        return amountCups = driver.findElement(By.cssSelector(".stat-row-content:nth-child(1) h3"));
    }

    public String getAmountCupsText() {
        return getAmountCups().getText();
    }

    public WebElement getBagsLink() {
        return linkBags = driver.findElement(
                By.xpath(".//div[@class='stat-row-image ng-star-inserted']//following-sibling::div/div/div/a"));
    }

    public void clickBagsLink() {
        getBagsLink().click();
    }

    public WebElement getCupsLink() {
        return linkCups = driver.findElement(By.xpath(
                ".//div[@class='stat-row-image ng-star-inserted']//preceding-sibling::div[@class='stat-row-content ng-star-inserted']/div/div/a"));
    }

    public void clickCupsLink() {
        getCupsLink().click();
    }

    public WebElement getMainEcoNewsLink() {
        return mainEcoNewsLink = driver.findElement(By.xpath(".//div[@class='navigation-menu-left'] / ul / li[1]"));
    }

    public void clickMainEcoNewsLink() {
        getMainEcoNewsLink().click();
    }

    public WebElement getAllNewsLink() {
        return allNewsLink = driver.findElement(By.id("eco-events"));
    }

    public void clickAllNewsLink() {
        getAllNewsLink().click();
    }

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
