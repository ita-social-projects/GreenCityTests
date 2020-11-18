package com.softserve.edu.greencity.api.tests.econews.positive;

import com.softserve.edu.greencity.api.assertions.BaseAssertion;
import com.softserve.edu.greencity.api.models.econews.EcoNewsModel;
import com.softserve.edu.greencity.api.models.econews.EcoNewsPOSTdto;
import com.softserve.edu.greencity.api.tests.econews.EcoNewsApiTestRunner;
import com.softserve.edu.greencity.ui.tools.jdbc.entity.EcoNewsEntity;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.softserve.edu.greencity.api.builders.econews.EcoNewsDtoBuilder.ecoNewsDtoWith;
import static com.softserve.edu.greencity.api.data.econews.NewsRepository.*;
import static com.softserve.edu.greencity.api.assertions.EcoNewsAssertion.*;

public class CreateNewsWithMinimalValuesTest extends EcoNewsApiTestRunner {

    @DataProvider(name = "minimalDataNews")
    private Object[] getMinimalValues() {
        return new Object[]
                {
                        ecoNewsDtoWith().title(getMinimalTitle())
                                .text(getMinimalText())
                                .image(null)
                                .source("")
                                .tags(new String[]{"news"}).build()
                };
    }

    @Test(testName = "GC-605", dataProvider = "minimalDataNews")
    @Description("Verify that news will be created with min symbols entered in all editable fields")
    public void createNewsWithMinimalValuesTest(EcoNewsPOSTdto news) {
        logger.info("Running createNewsWithMinimalValuesTest");
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
