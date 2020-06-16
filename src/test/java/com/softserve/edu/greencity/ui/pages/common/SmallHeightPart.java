package com.softserve.edu.greencity.ui.pages.common;

import org.openqa.selenium.WebDriver;

import com.softserve.edu.greencity.ui.pages.cabinet.MyCabinetPage;
import com.softserve.edu.greencity.ui.pages.econews.EconewsPage;
import com.softserve.edu.greencity.ui.pages.map.MapPage;
import com.softserve.edu.greencity.ui.pages.tipstricks.TipsTricksPage;

public class SmallHeightPart extends TopPart {

	public SmallHeightPart(WebDriver driver) {
		super(driver);
		initElements();
	}

	private void initElements() {
		// init elements
	}

	// Page Object

	// Functional

	// Business Logic

	@Override
	public EconewsPage navigateMenuEconews() {
		scrollToElementByCoordinates(getCopyright());
		if (isMenuClickable()) {
			return super.navigateMenuEconews();
		}
		getMainMenuDropdown().closeNaviconButton();
		getMainMenuDropdown().clickFooterEcoNews();
		return new EconewsPage(driver);
	}

	@Override
	public TipsTricksPage navigateMenuTipsTricks() {
		scrollToElementByCoordinates(getCopyright());
		if (isMenuClickable()) {
			return super.navigateMenuTipsTricks();
		}
		getMainMenuDropdown().closeNaviconButton();
		getMainMenuDropdown().clickFooterTipsTricks();
		return new TipsTricksPage(driver);
	}

	@Override
	public MapPage navigateMenuMap() {
		scrollToElementByCoordinates(getCopyright());
		if (isMenuClickable()) {
			return super.navigateMenuMap();
		}
		getMainMenuDropdown().closeNaviconButton();
		getMainMenuDropdown().clickFooterPlaces();
		return new MapPage(driver);
	}

	@Override
	public MyCabinetPage navigateMenuMyCabinet() {
		scrollToElementByCoordinates(getCopyright());
		if (isMenuClickable()) {
			return super.navigateMenuMyCabinet();
		}
		getMainMenuDropdown().closeNaviconButton();
		// getMainMenuDropdown().clickFooterMyCabinet();
		// TODO delete
		driver.get("https://ita-social-projects.github.io/GreenCityClient/#/auth");
		return new MyCabinetPage(driver);
	}

	@Override
	public AboutPage navigateMenuAbout() {
		scrollToElementByCoordinates(getCopyright());
		if (isMenuClickable()) {
			return super.navigateMenuAbout();
		}
		getMainMenuDropdown().closeNaviconButton();
		getMainMenuDropdown().clickFooterAbout();
		return new AboutPage(driver);
	}
}
