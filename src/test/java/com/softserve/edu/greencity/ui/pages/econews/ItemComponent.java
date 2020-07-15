package com.softserve.edu.greencity.ui.pages.econews;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public final class ItemComponent {

	private WebDriver driver;
	private WebElement newsItem;
	private List<WebElement> tags;
	private WebElement title;
	private WebElement content;
	private WebElement dateOfCreation;
	private WebElement author;

	public ItemComponent(WebDriver driver, WebElement newsItem) {
		this.driver = driver;
		this.newsItem = newsItem;
		initElements();
	}

	private void initElements() {
		makeElPresent();
	}

	private void makeElPresent() {
		Duration duration = Duration.ofMillis(20L);
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(duration).ignoring(TimeoutException.class);
		wait.until(ExpectedConditions
				.visibilityOfAllElements(
						getTitle(),
						getContent(),
						getDateOfCreation(),
						getAuthor()));
		System.out.println("makeElPresent()");
	}

	private List<WebElement> getTags() {
		tags = newsItem.findElements(By.cssSelector("div.filter-tag div"));
		return tags;
	}


	private WebElement getNewsItem() {
		return newsItem;
	}

	protected WebElement getTitle() {
		title = newsItem.findElement(By.cssSelector("div.title-list p"));
		return title;
	}

	protected String getTitleText() {
		return getTitle().getText();
	}

	protected void clickTitle() {
		getTitle().click();
	}

	private WebElement getContent() {
		content = newsItem.findElement(By.cssSelector("div.list-text p"));
		return content;
	}

	protected String getContentText() {
		return getContent().getText();
	}

	protected void clickContent() {
		getContent().click();
	}

	private WebElement getDateOfCreation() {
		dateOfCreation = newsItem.findElement(By.cssSelector("div.user-data-added-news > p:nth-child(1)"));
		return dateOfCreation;
	}

	private String getDateOfCreationText() {
		return getDateOfCreation().getText();
	}

	private WebElement getAuthor() {
		author = newsItem.findElement(By.cssSelector("div.user-data-added-news > p:nth-child(2)"));
		return author;
	}

	private String getAuthorText() {
		return getAuthor().getText();
	}

	/**
	 * List with names of Tags
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

}
