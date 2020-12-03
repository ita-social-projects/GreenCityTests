package com.softserve.edu.greencity.api.tests.econews.positive;

import com.softserve.edu.greencity.api.assertions.BaseAssertion;
import com.softserve.edu.greencity.api.models.econews.EcoNewsModel;
import com.softserve.edu.greencity.api.models.econews.EcoNewsPOSTdto;
import com.softserve.edu.greencity.api.models.errors.DetailedErrorMessage;
import com.softserve.edu.greencity.api.tests.econews.EcoNewsApiTestRunner;
import com.softserve.edu.greencity.ui.tools.jdbc.entity.EcoNewsEntity;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.softserve.edu.greencity.api.assertions.EcoNewsAssertion.assertExistence;
import static com.softserve.edu.greencity.api.builders.econews.EcoNewsDtoBuilder.ecoNewsDtoWith;
import static com.softserve.edu.greencity.api.data.econews.NewsRepository.*;

public class CreateNewsWithMaximalValuesTest extends EcoNewsApiTestRunner {

    @DataProvider(name = "maximalDataNews")
    private Object[] getIntermediateValues() {
        return new Object[]
                {
                        ecoNewsDtoWith().title(getMaximalTitle())
                                .text(getMaximalText())
                                .image("image/png", getNormalImage())
                                .source(getMaximalLink())
                                .tags(new String[]{"news", "ads", "events"}).build(),

                        ecoNewsDtoWith().title(getMinimalTitle())
                                .text(getMinimalText())
                                .image("image/png", getNormalImage())
                                .source(getProperSource())
                                .tags(new String[] {"news"}).build()
                };
    }

    /**
     * ALERT!
     * Run this test ONLY from TERMINAL, or you will crash your IDE
     * It happens because IDEA tries to print parameters of test into console
     * And ecoNews object contains the whole image as base64 string, so...
     * Use this command instead:
     * mvn -Dtest=CreateNewsWithMaximalValuesTest#createNewsWithMaximalValuesTest test
     */
    @Test(testName = "GC-626", description = "GC-626", dataProvider = "maximalDataNews")
    @Description("Verify that news will be created with max symbols entered in all fields")
    public void createNewsWithMaximalValuesTest(EcoNewsPOSTdto news) {
        logger.info("Running createNewsWithMaximalValuesTest");
        // Initial checking
        List<EcoNewsEntity> list = ecoNewsService.getNewsByTitle(news.title);
        Assert.assertTrue(list.isEmpty());

        Response created = ecoNewsClient.postNews(news);
        BaseAssertion assertCreation = new BaseAssertion(created);
        assertCreation.statusCode(201);

        // Verify the creation
        EcoNewsModel expected = created.as(EcoNewsModel.class);
        assertExistence(expected);

        // Clean-up
        ecoNewsService.deleteNewsById(expected.id);
    }
}
