package com.softserve.edu.greencity.ui.pages.common;

import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.pages.myhabits.MyCabinetPage;
import com.softserve.edu.greencity.ui.pages.places.MapPage;
import com.softserve.edu.greencity.ui.pages.tipstricks.TipsTricksPage;
import org.openqa.selenium.WebDriver;

public class SmallHeightPart extends TopPart {

    public SmallHeightPart(WebDriver driver) {
        super(driver);
    }

    @Override
    public EcoNewsPage navigateMenuEconews() {
        scrollToElementByAction(getCopyright());
        if (isMenuClickable()) {
            return super.navigateMenuEconews();
        }
        getMainMenuDropdown().closeNaviconButton();
        getMainMenuDropdown().clickFooterEcoNews();
        return new EcoNewsPage(driver);
    }

    @Override
    public TipsTricksPage navigateMenuTipsTricks() {
        scrollToElementByAction(getCopyright());
        if (isMenuClickable()) {
            return super.navigateMenuTipsTricks();
        }
        getMainMenuDropdown().closeNaviconButton();
        getMainMenuDropdown().clickFooterTipsTricks();
        return new TipsTricksPage(driver);
    }

    @Override
    public MapPage navigateMenuMap() {
        scrollToElementByAction(getCopyright());
        if (isMenuClickable()) {
            return super.navigateMenuMap();
        }
        getMainMenuDropdown().closeNaviconButton();
        getMainMenuDropdown().clickFooterPlaces();
        return new MapPage(driver);
    }

    @Override
    public MyCabinetPage navigateMenuMyCabinet() {
        scrollToElementByAction(getCopyright());
        if (isMenuClickable()) {
            return super.navigateMenuMyCabinet();
        }
        getMainMenuDropdown().closeNaviconButton();
        driver.get("https://ita-social-projects.github.io/GreenCityClient/#/auth");
        return new MyCabinetPage(driver);
    }

    @Override
    public AboutPage navigateMenuAbout() {
        scrollToElementByAction(getCopyright());
        if (isMenuClickable()) {
            return super.navigateMenuAbout();
        }
        getMainMenuDropdown().closeNaviconButton();
        getMainMenuDropdown().clickFooterAbout();
        return new AboutPage(driver);
    }
}
