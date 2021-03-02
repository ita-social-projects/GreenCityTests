package com.softserve.edu.greencity.ui.pages.common;

import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Header for signed in user
 */
public class TopUserComponent {
	protected final String PROFILE_DROPDOWN_NULL_MESSAGE = "ProfileDropdown is null";
	//
	private final String TAG_ATTRIBUTE_CLASS = "class";
	private final String USER_NAME_CSS = "#header_user-wrp > li.tertiary-global-button > a"; //"div#user-avatar-wrapper li.tertiary-global-button > a";
	//
	private WebDriver driver;
	//
	private WebElement profileButton;
	private WebElement userNameButton;
	//
	private ProfileDropdown profileDropdown;

	public TopUserComponent(WebDriver driver) {
		this.driver = driver;
		initElements();
	}

	private void initElements() {

	}

	// Page Object

	// profileButton

	public WebElement getProfileButton() {

		profileButton = driver.findElement(By.cssSelector("#header_user-wrp > li.tertiary-global-button > a"));
		return profileButton;
	}

	// userNameButton

	public WebElement getUserNameButton() {
		WaitsSwitcher waitsSwitcher = new WaitsSwitcher(driver);
		waitsSwitcher.setExplicitWait(7,
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector(USER_NAME_CSS)));

		return userNameButton = driver.findElement(By.cssSelector(USER_NAME_CSS));
	}

	public String getUserNameButtonText() {
		return getUserNameButton().getText();
	}

	public void clickUserNameButton() {
		getUserNameButton().click();
	}

	public boolean isDisplayedUserNameButton() {
		return getUserNameButton().isDisplayed();
	}

	// Functional

	public boolean isExpanded() {
		return getProfileButton()
				.getAttribute(TAG_ATTRIBUTE_CLASS)
				.equals("add-shadow");
	}

	// profileDropdown

	protected ProfileDropdown getProfileDropdown() {
		if (profileDropdown == null)
		{
			// TODO Develop Custom Exception
			throw new RuntimeException(PROFILE_DROPDOWN_NULL_MESSAGE);
		}
		return profileDropdown;
	}

	protected void openProfileDropdown() {
		if (!isExpanded()) {
			clickUserNameButton();
		}
	}

	protected ProfileDropdown createProfileDropdown() {
		openProfileDropdown();
		profileDropdown = new ProfileDropdown(driver);
		return getProfileDropdown();
	}
	/*Removed from UI
	protected void clickProfileDropdownFavoritePlaces() {
		createProfileDropdown().clickFavoritePlaces();
		closeProfileDropdown();
	}
	*/

	protected void clickProfileDropdownUserSettings() {
		createProfileDropdown().clickUserSettings();
		closeProfileDropdown();
	}

	protected void clickProfileDropdownSignout() {
		createProfileDropdown().clickSignout();
		closeProfileDropdown();
	}

	protected void closeProfileDropdown() {
		profileDropdown = null;
	}

	// Business Logic

}
