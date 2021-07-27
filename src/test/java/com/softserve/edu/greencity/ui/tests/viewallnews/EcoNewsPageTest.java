package com.softserve.edu.greencity.ui.tests.viewallnews;

import java.util.List;

import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.SingleNewsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithLoginLogout;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import com.softserve.edu.greencity.data.econews.NewsData;
import com.softserve.edu.greencity.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.data.econews.Tag;
import com.softserve.edu.greencity.ui.pages.common.WelcomePage;

/**
 * Test cases to test EcoNewsPage
 *
 * @author lv-493
 */
public class EcoNewsPageTest extends GreenCityTestRunnerWithLoginLogout {

	@DataProvider
	public Object[][] newsTags() {
		return new Object[][]{
				{NewsDataRepository.get().getNewsByTags()}
		};
	}

	@DataProvider
	public Object[][] newsData() {
		return new Object[][]{
				{NewsDataRepository.get().getExistingNews()}
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

	//@Test
	public void selectListView() {
		logger.info("selectListView starts");

		EcoNewsPage page = loadApplication()
				.navigateMenuEcoNews()
				.switchToListView();

		Assert.assertTrue(page.isActiveListView(), "List view is not active:");
	}
}
