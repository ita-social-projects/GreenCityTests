package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.econews.NewsData;
import com.softserve.edu.greencity.ui.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.ui.data.econews.Tag;
import com.softserve.edu.greencity.ui.pages.econews.EconewsPage;
import com.softserve.edu.greencity.ui.pages.econews.OneNewsPage;
import com.softserve.edu.greencity.ui.pages.tipstricks.TipsTricksPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Test cases to test EconewsPage
 * @author lv-493
 */
public class EconewsPageTest extends GreencityTestRunner {
	
	@DataProvider
	public Object[][] newsTags() {
		return new Object[][] {
				 {  NewsDataRepository.getNewsByTags() }
			 };
	}
	
	@DataProvider
	public Object[][] newsData() {
		return new Object[][] {
				 {  NewsDataRepository.getExistingNews() }
			 };
	}

	@Test(dataProvider = "newsData")
	public void econewsSmokeTest(NewsData news) {
		
		logger.info("econewsSmokeTest starts with parameters: " + news.toString());
		// go to EconewsPage -> OneNewsPage -> next OneNewsPage -> return to EconewsPage 
		
		TipsTricksPage page = loadApplication();
		page.navigateMenuEconews()
		.switchToOneNewsPagebyParameters(news)
		.switchToNextOneNewsPage()
		.switchToEconewsPageBack();
		
	}
	
	@Test(dataProvider = "newsData")
	public void openNewsTest(NewsData news) {
		
		logger.info("openNewsTest starts with parameters: " + news.toString());
		
		//open onenewspage
		
		OneNewsPage findedeconewspage = loadApplication()
				.navigateMenuEconews()
				.switchToOneNewsPagebyParameters(news);
		
		// check if is appropriate page
		
		Assert.assertEquals(news.getTitle(), findedeconewspage.getTitleText(), 
				"titles of news does not match:");	
		
	}
	
	@Test(dataProvider = "newsTags")
	public void chooseTags(List<Tag> tags) {
		
		logger.info("chooseTags starts with parameters: " + tags.toString());
		
		
		//open onenewspage

		EconewsPage page = loadApplication().navigateMenuEconews().selectFilters(tags);

		// check if is appropriate numbers of news items
		
		Assert.assertEquals(page.getItemsContainer().getItemComponentsCount(), 
				page.getNumberOfItemComponent(), 
				"Number of news items does not match to required:");
		
	}
	
	@Test(dataProvider = "newsTags")
	public void deselectTags(List<Tag> tags) {
		
		logger.info("deselectTags starts with parameters: " + tags.toString());
		
		//open onenewspage

		EconewsPage page = loadApplication()
				.navigateMenuEconews()
				.selectFilters(tags) 
				.deselectFilters(tags);

		// check if is appropriate numbers of news items
		
		Assert.assertEquals(page.getItemsContainer().getItemComponentsCount(), 
				page.getNumberOfItemComponent(), 
				"Number of news items does not match to required:");
		
	}
	
	@Test
	public void selectListView() {
		
		logger.info("selectListView starts");
		// open onenewspage

		EconewsPage page = loadApplication()
				.navigateMenuEconews()
				.switchToListViev();

		// check if it  is appropriate view

		Assert.assertTrue(page.isActiveListView(), "List view is not active:");

	}
	
	@Test
	public void selectGridView() {
		
		logger.info("selectListView starts");
		// open onenewspage

		EconewsPage page = loadApplication()
				.navigateMenuEconews()
				.switchToListViev()
				.switchToGridViev();

		// check if it  is appropriate view

		Assert.assertTrue(page.isActiveGridView(), "Grid view is not active:");

	}
		
}
