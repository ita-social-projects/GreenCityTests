package com.softserve.edu.greencity.ui.pages.econews;

import com.softserve.edu.greencity.ui.data.econews.Tag;
import  static com.softserve.edu.greencity.ui.locators.ItemComponentLocators.*;

import com.softserve.edu.greencity.ui.locators.ItemComponentLocators;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import static com.softserve.edu.greencity.ui.locators.ItemComponentLocators.*;

public final class ItemComponent {

    protected WebDriverWait wait;
    private final WebDriver driver;
    private final WebElement newsItem;
    private WaitsSwitcher waitsSwitcher;
    private Logger logger;

    public ItemComponent(WebDriver driver, WebElement newsItem) {
        this.driver = driver;
        this.newsItem = newsItem;
        this.waitsSwitcher = new WaitsSwitcher(driver);
        logger = LoggerFactory.getLogger("ItemComponent");
    }

//	private void makeElPresent() {
//		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
//		Duration duration = Duration.ofMillis(20L);
//		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(duration).ignoring(TimeoutException.class);
//		WebDriverWait wait = new WebDriverWait(myDriver, 15);
//		wait.until(webDriver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete"));
//		wait.until(ExpectedConditions.visibilityOfAllElements(title, content, dateOfCreation, author));
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	}

    public List<WebElement> getTags() {
        return newsItem.findElements(TAGS.getPath());
    }

    public WebElement getTagsContainer() {
        waitsSwitcher.setExplicitWait(5,
                ExpectedConditions.visibilityOfElementLocated(TAGS_CONTAINER.getPath()));
        return findFromItemWithStaleReferenceWrap(TAGS_CONTAINER);
    }

    public boolean isDisplayedTags() {
        boolean isDisplayedCurrent = false;
        int retriesLeft = 5;
        do {
            try {
                for (WebElement current : getTags()) {
                    isDisplayedCurrent = current.isDisplayed();
                }
                return isDisplayedCurrent;
            } catch (StaleElementReferenceException error) {
                logger.warn("StaleElementReferenceException caught, retrying...");
                WaitsSwitcher.sleep(100);
            }
            retriesLeft--;
        } while (retriesLeft > 0);

        return isDisplayedCurrent;
    }

    //Image
    public WebElement getImage() {
        return findFromItemWithStaleReferenceWrap(IMAGE);
    }

    public boolean isDisplayedImage() {
        return getImage().isDisplayed();
    }

    //Title
    public WebElement getTitle() {
        waitsSwitcher.setExplicitWait(5,
                ExpectedConditions.visibilityOfElementLocated(TITLE.getPath()));
        return findFromItemWithStaleReferenceWrap(TITLE);
    }

    public String getTitleText() {
        return getTitle().getText();
    }

    public int getTitleLineHeight() {
        return Integer.parseInt(getTitle().getCssValue("line-height").split("px")[0]);
    }

    public int getTitleHeight() {
        return getTitle().getSize().getHeight();
    }

    public int getTitleNumberRow() {
        return getTitleHeight() / getTitleLineHeight();
    }

    protected void clickTitle() {
        getTitle().click();
    }

    public void click() {
        newsItem.click();
    }

    public boolean isDisplayedTitle() {
        return getTitle().isDisplayed();
    }

    //Content
    public WebElement getContent() {
        return findFromItemWithStaleReferenceWrap(CONTENT);
    }

    public String getContentText() {
        return getContent().getText();
    }

    public int getContentLineHeight() {
        return Integer.parseInt(getContent().getCssValue("line-height").split("px")[0]);
    }

    public int getContentHeight() {
        return getContent().getSize().getHeight();
    }

    public int getContentNumberVisibleRow() {
        if (getContentWrapHeight() > getContentHeight())
            return getContentHeight() / getContentLineHeight();
        else return getContentWrapHeight() / getContentLineHeight();
    }

    public int getContentWrapHeight() {
        return driver.findElement(CONTENT_WRAP.getPath()).getSize().getHeight();
    }

    protected void clickContent() {
        getContent().click();
    }

    public boolean isDisplayedContent() {
        return getContent().isDisplayed();
    }


    //DateOfCreation
    public WebElement getDateOfCreation() {
        waitsSwitcher.setExplicitWait(5,
                ExpectedConditions.visibilityOfElementLocated(DATE_OF_CREATION.getPath()));
        return findFromItemWithStaleReferenceWrap(DATE_OF_CREATION);
    }

    public WebElement getDateAndAuthorContainer() {
        waitsSwitcher.setExplicitWait(5,
                ExpectedConditions.visibilityOfElementLocated(DATE_AND_AUTHOR_CONTAINER.getPath()));
        return findFromItemWithStaleReferenceWrap(DATE_AND_AUTHOR_CONTAINER);
    }

    public Date getCreationDate() {
        String date = getDateOfCreationText().replace(",", "").toUpperCase();
        DateFormat format = new SimpleDateFormat("MMM d yyyy", Locale.ENGLISH);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public String getDateOfCreationText() {
        return getDateOfCreation().getText();
    }

    public Date getDateOfCreationDateFormat() {
        Date date = null;
        try {
            date = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH).parse(getDateOfCreationText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public boolean isDisplayedDateOfCreation() {
        return getDateOfCreation().isDisplayed();
    }

    public boolean isCorrectDateFormat(String date) {
        Pattern DATE_PATTERN = Pattern.compile("[a-zA-Z]{3}\\s+\\d{1,2},?\\s+\\d{4}");
        return DATE_PATTERN.matcher(date).matches();
    }

    //Author
    private WebElement getAuthor() {
        return findFromItemWithStaleReferenceWrap(AUTHOR);
    }

    public String getAuthorText() {
        return getAuthor().getText();
    }

    public boolean isDisplayedAuthor() {
        return getAuthor().isDisplayed();
    }

    /**
     * List with names of Tags
     *
     * @return List<String>
     */
    protected List<String> getTagsText() {
        List<String> str = new ArrayList<String>();
        for (WebElement elem : getTags()) {
            str.add(elem.getText().toLowerCase());
        }
        Collections.sort(str);
        return str;
    }

    /**
     * Checks if at least one of tags provided is present in news
     * @param tags tags to check
     * @return
     */
    public boolean areTagsPresent(List<Tag> tags) {
        int trialsLeft = 5;
        do {
            try {
                for (WebElement actualTag : getTags()) {
                    for (Tag tagToCheck : tags) {
                        if (actualTag.getText().equalsIgnoreCase(tagToCheck.toString())) {
                            return true;
                        }
                    }
                }
                return false;
            } catch (StaleElementReferenceException error) {
                logger.warn("StaleElementReferenceException caught, retrying...");
                WaitsSwitcher.sleep(100);
            }
            trialsLeft--;
        } while (trialsLeft > 0);
        return false;
    }

    private WebElement findFromItemWithStaleReferenceWrap(ItemComponentLocators locator) {
        int retriesLeft = 5;
        do {
            try {
                return newsItem.findElement(locator.getPath());
            } catch (StaleElementReferenceException error) {
                logger.warn("StaleElementReferenceException caught, retrying...");
                WaitsSwitcher.sleep(100);
                //TODO need somehow to refresh the newsItem...
            }
            retriesLeft--;
        } while (retriesLeft > 0);

        return newsItem.findElement(locator.getPath());
    }
}
