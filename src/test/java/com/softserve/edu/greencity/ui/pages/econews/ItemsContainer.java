package com.softserve.edu.greencity.ui.pages.econews;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.edu.greencity.ui.data.econews.NewsData;

/**
 * Contains all itemsComponents that are present on page
 * Use for SingleNewsPage & EcoNewsPage.
 *
 * @author lv-493
 */
public class ItemsContainer {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private WebDriver driver;
	private List<ItemComponent> itemComponents;

	public ItemsContainer(WebDriver driver) {
		this.driver = driver;
		initElements();
	}

	private void initElements() {
		itemComponents = new ArrayList<>();
		for (WebElement current : driver
				.findElements(By.xpath("//div[@class=\"container\"]//ul[contains(@class, \"list\")]/li"))) {
			itemComponents.add(new ItemComponent(driver, current));
		}
	}

	private List<ItemComponent> getItemComponents() {
		return itemComponents;
	}

	/**
	 * In this method we get how many ItemComponent is displayed
	 *
	 * @return int
	 */
	public int getItemComponentsCount() {
		return getItemComponents().size();
	}

	/**
	 * We get list of all news headers
	 *
	 * @return list itemComponentsHeader
	 */
	private List<String> getItemComponentsHeader() {
		List<String> itemComponentsHeader = new ArrayList<>();
		for (ItemComponent cur : getItemComponents()) {
			itemComponentsHeader.add(cur.getTitleText());
		}
		return itemComponentsHeader;
	}

	/**
	 * Find news by header
	 *
	 * @param headerName headerName
	 * @return ItemComponent
	 */
	protected ItemComponent getItemComponentByHeader(String headerName) {
		ItemComponent result = null;
		for (ItemComponent cur : getItemComponents()) {
			if (cur.getTitleText().toLowerCase()
					.contains(headerName.toLowerCase())) {
				result = cur;
				break;
			}
		}
		if (result == null) {
			logger.warn("News with header " + headerName + "not exist");
			throw new RuntimeException("HeaderName " + headerName + " not found");
		}
		return result;
	}

	/**
	 * Open new page with news given by title, tags, content
	 *
	 * @param news one
	 */
	protected void clickItemComponentOpenPage(NewsData news) {
//   	getItemComponentByHeader(news.getTitle()).clickContent();
		findItemComponentByParameters(news).getTitle().click();

	}


	/**
	 * Find appropriate news by its parameters: title, list tags & content
	 *
	 * @param news
	 * @return ItemComponent
	 */
	protected ItemComponent findItemComponentByParameters(NewsData news) {
		ItemComponent result = null;
		for (ItemComponent cur : getItemComponents()) {
			if (cur.getTitleText().toLowerCase().equals(news.getTitle().toLowerCase())
					&& cur.getTagsText().equals(news.getTagsName())
					&& news.getContent().toLowerCase().contains(cur.getContentText().toLowerCase())) {
				result = cur;
			}
		}
		if (result == null) {
			logger.warn("News with parameters " + news.toString() + "not exist");
			throw new RuntimeException("ItemComponent with parameters " + news.toString() + " not found");
		}
		return result;
	}

	/**
	 * this method return ItemComponent by its number
	 *
	 * @param number
	 * @return ItemComponent
	 */
	protected ItemComponent chooseNewsByNumber(int number) {
		return getItemComponents().get(number);
	}
}
