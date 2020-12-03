package com.softserve.edu.greencity.api.tests.econews.negative;

import com.softserve.edu.greencity.api.assertions.BaseAssertion;
import com.softserve.edu.greencity.api.assertions.ErrorAssertions;
import com.softserve.edu.greencity.api.models.econews.EcoNewsPOSTdto;
import com.softserve.edu.greencity.api.models.errors.ErrorMessage;
import com.softserve.edu.greencity.api.tests.econews.EcoNewsApiTestRunner;
import com.softserve.edu.greencity.ui.tools.jdbc.entity.EcoNewsEntity;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.softserve.edu.greencity.api.builders.econews.EcoNewsDtoBuilder.ecoNewsDtoWith;
import static com.softserve.edu.greencity.data.econews.NewsDataStrings.*;

/**
 * It is called so because requests in this test return error in "short format"
 * See ErrorMessage class
 */
public class CreateNewsWithShortResponseTest extends EcoNewsApiTestRunner {

    @DataProvider(name = "improperNewsWithShortResponse")
    private Object[] getImproperNewsWithShortResponse() {
        return new Object[][]{
                {
                        "GC-596",
                        ecoNewsDtoWith().title(getShortTitle())
                                .text(getMediumText())
                                .image("image/png", getNormalImage())
                                .source(getSourceWithoutHttp())
                        ecoNewsDtoWith().title(TITLE_EKO_LAVKA.getString())
                                .text(CONTENT_EKO_LAVKA.getString())
                                .image(null)
                                .source(SOURCE_NO_HTTP_NEWS_COM.getString())
                                .tags(new String[]{"ads"}).build(),
                        new ErrorMessage("Malformed URL. The string could not be parsed.")
                },
                {
                        "GC-598",
                        ecoNewsDtoWith().title(getShortTitle())
                                .text(getMediumText())
                                .image("image/png", getNormalImage())
                                .source(getLinkWithImproperHttpPos())
                        ecoNewsDtoWith().title(TITLE_EKO_LAVKA.getString())
                                .text(CONTENT_EKO_LAVKA.getString())
                                .image(null)
                                .source(SOURCE_HTTP_INSIDE_EKO_LAVKA.getString())
                                .tags(new String[]{"ads"}).build(),
                        new ErrorMessage("Malformed URL. The string could not be parsed.")
                }
        };
    }

    /**
     * Use this command to run only this test from terminal:
     * mvn -Dtest=CreateNewsWithShortResponseTest#createNewsWithShortResponseTest test
     */
    @Test(dataProvider = "improperNewsWithShortResponse",
            testName = "GC-596, GC-598",
            description = "Tests which get response in short format")
    public void createNewsWithShortResponseTest(String testId, EcoNewsPOSTdto ecoNews, ErrorMessage expectedError){
        logger.info("Running createNewsWithShortResponseTest: {}", testId);
        Response created = ecoNewsClient.postNews(ecoNews);
        BaseAssertion assertCreated = new BaseAssertion(created);
        ErrorAssertions.errorEquals(created, expectedError);
        assertCreated.statusCode(400);

        List<EcoNewsEntity> list = ecoNewsService.getNewsByTitle(ecoNews.title);
        Assert.assertTrue(list.isEmpty());
    }
}
