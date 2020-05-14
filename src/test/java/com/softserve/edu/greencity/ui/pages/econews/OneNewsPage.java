package com.softserve.edu.greencity.ui.pages.econews;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.greencity.ui.pages.common.TopPart;

public class OneNewsPage extends TopPart {
	
	//there will be also functionality follow us & search - is not implemented
	//private WebElement editNewsButton;  // isn't working yet tags
	
	private WebElement  goToNews;
	private List<WebElement> tagsList;
	private WebElement title;
	private WebElement data;
	private WebElement author;
	private WebElement picture;
	private WebElement content;
	private ItemsContainer itemsContainer;

	public OneNewsPage(WebDriver driver) {
		super(driver);
		initElements();
	}

	private void initElements() {
		
		goToNews = driver.findElement(By.cssSelector("div.back-button"));
		tagsList = driver.findElements(By.cssSelector("div.tags > div"));
		title = driver.findElement(By.cssSelector("div.news-title"));
		data = driver.findElement(By.cssSelector("div.news-info > div.news-info-date"));
		author = driver.findElement(By.cssSelector("div.news-info > div.news-info-author"));
		picture = driver.findElement(By.cssSelector("div.news-image > img.news-image-img"));
		content = driver.findElement(By.cssSelector("div.news-text"));
		itemsContainer  = new ItemsContainer(driver);
	}
	
	//Page Object
	
	//goToNews 
	
	private WebElement getGoToNews () {
        return goToNews ;
    }

	private String getGoToNewsText() {
        return getGoToNews().getText();
    }
    
	private void clickGoToNewsButton() {
        getGoToNews().click();
    }
    
    //filtersList
    
	private List<WebElement> getTagsList() {
        return tagsList;
    }

   //title
    
	private WebElement getTitle() {
        return title;
    }

    public String getTitleText() {
        return getTitle().getText().trim();   //should we use trim?
    }
    
    //data
    
    private WebElement getData() {
        return data;
    }

    private String getDataText() {
        return getData().getText();
    }
    
    //author
    
    private WebElement getAuthor() {
        return author;
    }

    private String getAuthorText() {
        return getAuthor().getText();
    }
    
    //content
    
    private WebElement getContent() {
        return content;
    }

    private String getContentText() {
        return getContent().getText();
    }
    
    //Business logic
    
    /**
     * Go to next OneNewsPage
     * @param number
     * @return OneNewsPage
     */
    public OneNewsPage switchToNextOneNewsPagebyNumber(int number) {
		itemsContainer.chooseNewsByNumber(number).clickTitle();
		return new OneNewsPage(driver);
    }
    
    /**
     * Go to next OneNewsPage
     * @param number
     * @return OneNewsPage
     */
    public OneNewsPage switchToNextOneNewsPage() {
    	switchToNextOneNewsPagebyNumber(1);
		return new OneNewsPage(driver);
    }
    
    /**
     * Return to EconewsPage
     * @return EconewsPage
     */
    public EconewsPage switchToEconewsPageBack() {
    	clickGoToNewsButton();
		return new EconewsPage(driver);
    }
}
