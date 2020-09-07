package com.softserve.edu.greencity.ui.tests;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.SingleNewsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tests.runner.RetryAnalyzerImpl;
import com.softserve.edu.greencity.ui.tools.jdbc.entity.EcoNewsEntity;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import com.sun.net.httpserver.Authenticator;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.greencity.ui.data.econews.NewsData;
import com.softserve.edu.greencity.ui.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.ui.data.econews.Tag;
import com.softserve.edu.greencity.ui.pages.common.WelcomePage;
import org.testng.asserts.SoftAssert;

/**
 * Test cases to test EcoNewsPage
 *
 * @author lv-493
 */
public class EcoNewsPageTest extends GreenCityTestRunner {
	@BeforeTest
	private SoftAssert assertSoftly(){
		return new  SoftAssert();
	}

	@DataProvider
	public Object[][] newsTags() {
		return new Object[][]{
				{NewsDataRepository.getNewsByTags()}
		};
	}

	@DataProvider
	public Object[][] newsData() {
		return new Object[][]{
				{NewsDataRepository.getExistingNews()}
		};
	}

	//    @Test(dataProvider = "newsData")
	public void ecoNewsSmokeTest(NewsData news) {
		logger.info("ecoNewsSmokeTest starts with parameters: " + news.toString());

		WelcomePage page = loadApplication();
		page.navigateMenuEcoNews()
				.switchToSingleNewsPageByParameters(news)
				.switchToNextSingleNewsPage()
				.switchToEcoNewsPageBack();
	}

	//	@Test(dataProvider = "newsData")
	public void openNewsTest(NewsData news) {
		logger.info("openNewsTest starts with parameters: " + news.toString());

		SingleNewsPage findedeconewspage = loadApplication()
				.navigateMenuEcoNews()
				.switchToSingleNewsPageByParameters(news);

		Assert.assertEquals(news.getTitle(), findedeconewspage.getTitleText(),
				"titles of news does not match:");

	}

	//	@Test(dataProvider = "newsTags")
	public void chooseTags(List<Tag> tags) {
		logger.info("chooseTags starts with parameters: " + tags.toString());

		EcoNewsPage page = loadApplication().navigateMenuEcoNews().selectFilters(tags);

		Assert.assertEquals(page.getItemsContainer().getItemComponentsCount(),
				page.getNumberOfItemComponent(),
				"Number of news items does not match to required:");

	}

	//    @Test(dataProvider = "newsTags")
	public void deselectTags(List<Tag> tags) {
		logger.info("deselectTags starts with parameters: " + tags.toString());

		EcoNewsPage page = loadApplication()
				.navigateMenuEcoNews()
				.selectFilters(tags)
				.deselectFilters(tags);

		Assert.assertEquals(page.getItemsContainer().getItemComponentsCount(),
				page.getNumberOfItemComponent(),
				"Number of news items does not match to required:");
	}

	@Test
	public void selectListView() {
		logger.info("selectListView starts");

		EcoNewsPage page = loadApplication()
				.navigateMenuEcoNews()
				.switchToListView();

		Assert.assertTrue(page.isActiveListView(), "List view is not active:");
	}

	/*<======================================Grid View==========================================>*/

	@Test
	public void selectGridView() {
		logger.info("selectGridView starts");

		EcoNewsPage page = loadApplication()
				.navigateMenuEcoNews()
				.switchToListView()
				.switchToGridView();

		Assert.assertTrue(page.isActiveGridView(), "Grid view is  active:");
	}

	@Test(description = "GC-334")
	public void NavigateToEcoNews() {
		logger.info("NavigateToEcoNews starts");
		EcoNewsPage page = loadApplication()
				.navigateMenuEcoNews();
		assertSoftly().assertTrue(page.isActiveGridView()) ;
		assertSoftly().assertAll();
	}

	@Test(description = "GC-336")
	public void twelveNewsDisplayed(){
		logger.info("twelveNewsDisplayed starts");
		EcoNewsPage page = loadApplication()
				.navigateMenuEcoNews();
		assertSoftly()
				.assertTrue(
						page
								.getTopicsInPage()
								.size()>11);
		logger.info("elements found: "+
				page
				.getTopicsInPage()
				.size());
		assertSoftly().assertAll();
	}

	//Front bug
	//@Test
	@Description("Verify that all content in each article displayed GC-675")
	public void allContentDisplayedTest(){
		EcoNewsPage econewsPage = loadApplication().navigateMenuEcoNews();
		econewsPage.updateArticlesExistCount().scrollDown();
		econewsPage.waiting(econewsPage.getElements(econewsPage.getDisplayedArticles()));
		List<WebElement> elements = econewsPage.getElements(econewsPage.getDisplayedArticles());
		econewsPage.isArticleContentDisplayed(elements);
	}

	//Front bug
	//@Test
	@Description("Verify that at least text content displayed in each article displayed GC-675")
	public void allTextContentDisplayedTest(){
		EcoNewsPage econewsPage = loadApplication().navigateMenuEcoNews();
		econewsPage.updateArticlesExistCount().scrollDown();
		econewsPage.waiting(econewsPage.getElements(econewsPage.getDisplayedArticles()));
		List<WebElement> elements = econewsPage.getElements(econewsPage.getDisplayedArticles());
		econewsPage.isArticleTextContentDisplayed(elements);
	}
	//com.softserve.edu.greencity.ui.tools.jdbc.dao.ManagerDao.readProperties
	//@Test(retryAnalyzer= RetryAnalyzerImpl.class)
	@Description("Verify that at least text content displayed in each article displayed GC-337")
	public void ChronologicalNewsTest(){
		logger.info("ChronologicalNewsTest");
		logger.info("Get dates from Front in chronological order");
		EcoNewsPage econewsPage = loadApplication().navigateMenuEcoNews();
		econewsPage.updateArticlesExistCount().scrollDown();
		econewsPage.waiting(econewsPage.getElements(econewsPage.getDisplayedArticles()));
		List<WebElement> elements = econewsPage.getElements(econewsPage.getDisplayedArticles());
		List <String> dates = new ArrayList<>();
		for (WebElement element : elements)
			dates.add(econewsPage.getArticleCreationDate(element));
		logger.info("Get dates from DB in chronological order");
		List <String> datesDB = new ArrayList<>();
		EcoNewsService ecoNewsService = new EcoNewsService();
		ecoNewsService.getAllNewsOrderByDate().forEach(d -> datesDB.add(String.valueOf(d)));
		List <String> pureDateDB = new ArrayList<>();
		datesDB.stream().forEach(d -> pureDateDB.add(econewsPage.getChronologicalDateFromDB(d)));
		logger.info("compare  dates order in DB and front");
		assertSoftly().assertEquals(dates,pureDateDB,
				"assert dates order in DB and front");
		assertSoftly().assertAll();
	}

	/*<======================================Grid View==========================================>*/
}
