package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EcoNewsSingleViewTest extends GreenCityTestRunner {

    @Test
    @Description("GC-670")
    public void returningToNewsViaBackToNews() {
        logger.info("Starting returningToNewsViaBackToNews");

        // Steps
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0)
                .switchToEcoNewsPageBack();
        Assert.assertTrue(ecoNewsPage.isActiveGridView());

        ecoNewsPage = ecoNewsPage
                .switchToListView()
                .switchToSingleNewsPageByNumber(0)
                .switchToEcoNewsPageBack();
        Assert.assertTrue(ecoNewsPage.isActiveListView());
    }
}
