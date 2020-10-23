package com.softserve.edu.greencity.ui.pages.econews;

import com.softserve.edu.greencity.ui.data.econews.Tag;
import  static com.softserve.edu.greencity.ui.locators.ItemComponentLocators.*;

import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public final class ItemComponent {

    protected WebDriverWait wait;
    private final WebDriver driver;
    private final WebElement newsItem;
    private WaitsSwitcher waitsSwitcher;

    public ItemComponent(WebDriver driver, WebElement newsItem) {
        this.driver = driver;
        this.newsItem = newsItem;
        this.waitsSwitcher = new WaitsSwitcher(driver);
        //initElements();
    }

	/*private void initElements() {
		tags = newsItem.findElements(By.cssSelector("div.filter-tag div"));
		title = newsItem.findElement(By.cssSelector("div.title-list p"));
		content = newsItem.findElement(By.cssSelector("div.list-text p"));
		dateOfCreation = newsItem.findElement(By.cssSelector("div.user-data-added-news > p:nth-child(1)"));
		author = newsItem.findElement(By.cssSelector("div.user-data-added-news > p:nth-child(2)"));
//		makeElPresent();
	}

	private void makeElPresent() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		Duration duration = Duration.ofMillis(20L);
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(duration).ignoring(TimeoutException.class);
//		WebDriverWait wait = new WebDriverWait(myDriver, 15);
//		wait.until(webDriver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete"));
		wait.until(ExpectedConditions.visibilityOfAllElements(title, content, dateOfCreation, author));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}*/

    public List<WebElement> getTags() {
        return newsItem.findElements(TAGS.getPath());
    }

    public boolean isDisplayedTags() {
        boolean isDisplayedCurrent = false;
        for (WebElement current : getTags()) {
            isDisplayedCurrent = current.isDisplayed();
        }
        return isDisplayedCurrent;
    }

    //Image
    public WebElement getImage() {
        return newsItem.findElement(IMAGE.getPath());
    }

    public boolean isDisplayedImage() {
        return getImage().isDisplayed();
    }

    //Title
    public WebElement getTitle() {
        waitsSwitcher.setExplicitWait(5, ExpectedConditions.visibilityOfElementLocated(TITLE.getPath()));
        return newsItem.findElement(TITLE.getPath());
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

    public boolean isDisplayedTitle() {
        return getTitle().isDisplayed();
    }

    //Content
    public WebElement getContent() {
        return newsItem.findElement(CONTENT.getPath());
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
        return newsItem.findElement(DATE_OF_CREATION.getPath());
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
        return newsItem.findElement(AUTHOR.getPath());
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
        for(WebElement actualTag : getTags()) {
            for(Tag tagToCheck : tags) {
                if(actualTag.getText().equalsIgnoreCase(tagToCheck.toString())) {
                    return true;
                }
            }
        }
        return false;
    }
}
