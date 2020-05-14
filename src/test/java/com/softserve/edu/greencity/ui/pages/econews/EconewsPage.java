package com.softserve.edu.greencity.ui.pages.econews;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.softserve.edu.greencity.ui.data.Languages;
import com.softserve.edu.greencity.ui.data.econews.NewsData;
import com.softserve.edu.greencity.ui.data.econews.Tag;
import com.softserve.edu.greencity.ui.pages.common.TopPart;
import com.softserve.edu.greencity.ui.tools.QuantityItems;

/**
 * 
 * @author lv-493 Taqc/Java
 *
 */
public class EconewsPage extends TopPart {

	private TagsComponent tagsComponent;
	private WebElement createNewsButton;
	private WebElement gridView;
	private WebElement listView;
	private ItemsContainer itemsContainer;
	private WebElement foundItems;

	public EconewsPage(WebDriver driver) {
		super(driver);
		visualiseElements();
		initElements();

	}

	private void visualiseElements() {

		int i = 0; 
		waiting(2);
		scrollToElement(getCopyright()); //  open all news
		waiting(2);
		List <WebElement> listElements = driver.findElements(By.cssSelector("div[id='list-gallery-content']"));
		while (i < listElements.size()) {
			waiting(2);
			scrollToElement(listElements.get(i));
			i++;
			listElements = driver.findElements(By.cssSelector("div[id='list-gallery-content']"));	
			}	
	}

	private void initElements() {
		tagsComponent = new TagsComponent(driver);
		createNewsButton = driver.findElement(By.id("create-button"));
		itemsContainer = new ItemsContainer(driver);
		gridView = driver.findElement(By.cssSelector("div[class*='gallery-view']"));
		listView = driver.findElement(By.cssSelector("div[class*='list-view']"));
		foundItems = driver.findElement(By.xpath("//p[@class=\"ng-star-inserted\"]"));
	}

	// Page Object

	// tagsComponent

	private TagsComponent getTagsComponent() {
		return tagsComponent;
	}

	// foundItems
	
	private WebElement getFoundItems() {
		return foundItems;
	}

	private String getFoundItemsText() {
		return getFoundItems().getText();
	}

	// gridViev

	private WebElement getGridView() {
		return gridView;
	}

	public boolean isActiveGridView() {
		return getGridView().getAttribute("class").contains("active");
	}

	private void clickGridView() {
		if (!isActiveGridView()) {
			scrollToElement(getGridView());
			getGridView().click();
		}
	}

	// listViev

	private WebElement getListView() {
		return listView;
	}

	public boolean isActiveListView() {
		return getListView().getAttribute("class").contains("active");
	}

	private void clickListView() {
		if (!isActiveListView()) {
			scrollToElement(getListView());
			getListView().click();
		}
	}

	// createNewsButton

	private WebElement getCreateNewsButton() {
		return createNewsButton;
	}

	private String getCreateNewsButtonText() {
		return getCreateNewsButton().getText();
	}

	private void clickCreateNewsButton() {
		getCreateNewsButton().click();
	}

	// itemsContainer

	public ItemsContainer getItemsContainer() {
		return itemsContainer;
	}
	
	/**
	 * Scroll to WebElement, in case when need to click on it or without scrolling are invisible
	 * @param WebElement
	 */
	private void scrollToElement(WebElement el) {
		
		Actions action = new Actions(driver);
		action.moveToElement(el).perform();
	}
	
	/**
	 * Waiting for elements became visible
	 * @param int
	 */
	private void waiting(int i) {
		try {
			Thread.sleep(i * 1000);
			} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get number of ItemComponent, what are present on EconewsPage
	 * @return int
	 */
	public int getNumberOfItemComponent() {

		return new QuantityItems().quantityItems(getFoundItemsText());
	}

	// Business Logic

	/**
	 * Method allows to choose type of news, which will be displayed on the EcoNewsPage 
	 * @param List<Tag> tags
	 * @return EconewsPage
	 */
	public EconewsPage selectFilters(List<Tag> tags) {

		scrollToElement(getTagsComponent().getTags().get(1));
		getTagsComponent().selectTags(tags);
		return new EconewsPage(driver);
	}
	
	/**
	 * Method allows to choose type of news, which will be displayed on the EcoNewsPage 
	 * @param List<Tag> tags
	 * @return EconewsPage
	 */
	public EconewsPage deselectFilters(List<Tag> tags) {

		scrollToElement(getTagsComponent().getTags().get(1));
		getTagsComponent().deselectTags(tags);
		return new EconewsPage(driver);
	}

	/**
	 * Choose language
	 * @param Languages
	 * @return EconewsPage
	 */
	public EconewsPage switchLanguage(Languages language) {
		chooseLanguage(language);
		return new EconewsPage(driver);
	}

	/**
	 * News are displayed as grid 
	 * @return EconewsPage
	 */
	public EconewsPage switchToGridViev() {
		clickGridView();
		return new EconewsPage(driver);
	}

	/**
	 * News are displaeyd as list 
	 * @return EconewsPage
	 */
	public EconewsPage switchToListViev() {
		clickListView();
		return new EconewsPage(driver);
	}

	/**
	 * Open OneNewsPage
	 * @param int
	 * @return OneNewsPage
	 */
	public OneNewsPage switchToOneNewsPagebyNumber(int number) {
		scrollToElement(itemsContainer.chooseNewsByNumber(number).getIitle());
		itemsContainer.chooseNewsByNumber(number).clickTitle();
		return new OneNewsPage(driver);
	}

	/**
	 * Open OneNewsPage
	 * @param OneNewsData
	 * @return OneNewsPage
	 */
	public OneNewsPage switchToOneNewsPagebyParameters(NewsData news) {

		scrollToElement(itemsContainer.findItemComponentByParameters(news).getIitle());
		itemsContainer.clickItemComponentOpenPage(news);
		return new OneNewsPage(driver);
	}

	/**
	 * Open CreateNewsPage
	 * @return CreateNewsPage
	 */
	public CreateNewsPage gotoCreateNewsPage() {
		scrollToElement(getCreateNewsButton());
		clickCreateNewsButton();
		return new CreateNewsPage(driver);
	}
}
