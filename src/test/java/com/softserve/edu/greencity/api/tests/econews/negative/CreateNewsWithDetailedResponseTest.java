package com.softserve.edu.greencity.api.tests.econews.negative;

import com.softserve.edu.greencity.api.assertions.BaseAssertion;
import com.softserve.edu.greencity.api.assertions.ErrorAssertions;
import com.softserve.edu.greencity.api.models.econews.EcoNewsPOSTdto;
import com.softserve.edu.greencity.api.models.errors.DetailedErrorMessage;
import com.softserve.edu.greencity.api.tests.econews.EcoNewsApiTestRunner;
import com.softserve.edu.greencity.ui.tools.jdbc.entity.EcoNewsEntity;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.softserve.edu.greencity.api.builders.econews.EcoNewsDtoBuilder.ecoNewsDtoWith;
import static com.softserve.edu.greencity.data.econews.NewsRepository.*;
import static com.softserve.edu.greencity.data.econews.NewsRepository.getProperSource;

public class CreateNewsWithDetailedResponseTest extends EcoNewsApiTestRunner {

    @DataProvider(name = "improperNewsWithDetailedResponse")
    private Object[] getImproperNewsWithDetailedResponse() {
        return new Object[][]{
                {
                        "GC-594",
                        ecoNewsDtoWith().title(getShortTitle())
                                .text(getMediumText())
                                .image(null)
                                .source(getProperSource())
                                .tags(new String[]{"ads", "events", "education", "news"}).build(),
                        new DetailedErrorMessage(
                                500,
                                "Internal Server Error",
                                "Count of tags should be at least one but not more three",
                                "/econews")
                },
                {
                        "GC-631",
                        ecoNewsDtoWith().title(getShortTitle())
                                .text(getMediumText())
                                .image(null)
                                .source(getProperSource())
                                .tags(new String[]{"ads", "ads", "events"}).build(),
                        new DetailedErrorMessage(
                                500,
                                "Internal Server Error",
                                "Eco news haven't been saved because of constraint violation",
                                "/econews"),

                }
        };
    }

    @Test(dataProvider = "improperNewsWithDetailedResponse", testName = "GC-594, GC-631", description = "GC-594, GC-631")
    public void createNewsWithDetailedResponseTest(String testId, EcoNewsPOSTdto ecoNews, DetailedErrorMessage expectedError){
        logger.info("Running createNewsWithDetailedResponseTest: {}", testId);
        Response created = ecoNewsClient.postNews(ecoNews);
        BaseAssertion assertCreated = new BaseAssertion(created);
        ErrorAssertions.errorEquals(created, expectedError);
        assertCreated.statusCode(500);

        List<EcoNewsEntity> list = ecoNewsService.getNewsByTitle(ecoNews.title);
        Assert.assertTrue(list.isEmpty());
    }
}
