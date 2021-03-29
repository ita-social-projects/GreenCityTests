package com.softserve.edu.greencity.ui.pages.econews;

import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.greencity.ui.pages.common.CommentPart;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.greencity.ui.pages.common.TopPart;

import static com.softserve.edu.greencity.ui.locators.SingleNewsPageLocators.*;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * A detailed page with one specific news, e. g. https://ita-social-projects.github.io/GreenCityClient/#/news/179
 */
public class SingleNewsPage extends TopPart {

    protected WebDriverWait wait;


    private final String NEWS_DEFAULT_IMAGE = "https://ita-social-projects.github.io/GreenCityClient/assets/img/icon/econews/news-default-large.png";
    private List<WebElement> tagsList = driver.findElements(TAGS_LIST.getPath());
    private ItemsContainer itemsContainer;

    public SingleNewsPage(WebDriver driver) {
        super(driver);
        checkElements();
    }

    private void checkElements() {
        itemsContainer = new ItemsContainer(driver);
        waitsSwitcher.setExplicitWait(10, ExpectedConditions.visibilityOf(getTitle()));
    }

    private WebElement getGoToNews() {
        return searchElementByCss(GO_TO_NEWS.getPath());
    }

    private String getGoToNewsText() {
        return getGoToNews().getText();
    }

    private void clickGoToNewsButton() {
        getGoToNews().click();
    }

    private List<WebElement> getTagsList() {
        return tagsList;
    }

    public List<String> getTagsListText() {
        List<String> tags = new ArrayList<>();
        for (int i = 0; i < getTagsList().size(); i++) {
            tags.add(getTagsList().get(i).getText());
        }
        return tags;
    }


    private WebElement getTitle() {
        return searchElementByCss(TITLE.getPath());
    }

    private WebElement getData() {
        return searchElementByCss(DATA.getPath());
    }

    private String getDataText() {
        return getData().getText();
    }

    private WebElement getAuthor() {
        return searchElementByCss(AUTHOR.getPath());
    }

    public String getAuthorNameOnly() {
        return getAuthor().getText().split(" ")[1];
    }

    public String getAuthorText() {
        return getAuthor().getText();
    }

    private WebElement getContent() {
        return searchElementByCss(CONTENT.getPath());
    }

    private String getContentText() {
        return getContent().getText();
    }

    private WebElement getSourceTitle() {
        return searchElementByCss(SOURCE_TITLE.getPath());
    }

    public String getSourceTitleText() {
        return getSourceTitle().getText();
    }

    public WebElement getPicture() {
        return searchElementByCss(PICTURE.getPath());
    }

    public String getPictureSource() {
        return getPicture().getAttribute("src");
    }

    public boolean isDefaultPicture() {
        return getPictureSource().equals(NEWS_DEFAULT_IMAGE);
    }

    private WebElement getSourceLink() {
        return waitsSwitcher.setExplicitWait(
                ExpectedConditions.presenceOfElementLocated(SOURCE_LINK.getPath()));
    }

    public String getSourceLinkText() {
        String link = getSourceLink().getAttribute("href");
        try {
            if (link.equals("null") || link.equals("")) {
                return "";
            }
        } catch (NullPointerException e) {
            logger.warn("NullPointerException in getSourceLinkText() interpreted as not existing href attribute");
            return "";
        }
        return link;
    }

    public String getTitleText() {
        return getTitle().getText().trim();
    }

    /**
     * Go to next SingleNewsPage
     *
     * @param number
     * @return SingleNewsPage
     */
    public SingleNewsPage switchToNextSingleNewsPageByNumber(int number) {
        itemsContainer.chooseNewsByNumber(number).clickTitle();
        return new SingleNewsPage(driver);
    }

    /**
     * Go to next SingleNewsPage
     *
     * @return SingleNewsPage
     */
    public SingleNewsPage switchToNextSingleNewsPage() {
        switchToNextSingleNewsPageByNumber(1);
        return new SingleNewsPage(driver);
    }

    /**
     * Return to EcoNewsPage
     *
     * @return EcoNewsPage
     */
    public EcoNewsPage switchToEcoNewsPageBack() {
        clickGoToNewsButton();
        return new EcoNewsPage(driver);
    }

    /**
     * Gives a list of suggested news in the bottom of page
     *
     * @return ItemsContainer with suggested news
     */
    public ItemsContainer suggestedNews() {
        return new ItemsContainer(driver);
    }

    public boolean editNewsButtonExist() {
        if (driver.findElements(EDIT_NEWS_BUTTON.getPath()).size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Get comment part of SingleNewsPage
     *
     * @return comment part
     */
    public CommentPart getCommentPart() {
        return new CommentPart(driver);
    }

    public SingleNewsPage refreshPage() {
        driver.navigate().refresh();
        return this;
    }

    @Override
    public WebDriver setDriver() {
        return this.driver;
    }

}
