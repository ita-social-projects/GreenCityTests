package com.softserve.edu.greencity.api.tests.econews.negative;

import com.softserve.edu.greencity.api.assertions.BaseAssertion;
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
import static com.softserve.edu.greencity.api.data.econews.NewsRepository.*;

public class CreateNewsWithImproperImageTest extends EcoNewsApiTestRunner {

    @DataProvider(name = "images in PNG and JPEG formats")
    private Object[] getEconewsWithPngAndJpegFormats() {
        return new Object[]{
                        ecoNewsDtoWith().title(getShortTitle())
                                .text(getMediumText())
                                .image("image/png", getTooBigPngImage())
                                .source(getProperSource())
                                .tags(new String[]{"ads", "events"}).build(),

                        ecoNewsDtoWith().title(getShortTitle())
                                .text(getMediumText())
                                .image("image/jpeg", getTooBigJpegImage())
                                .source(getProperSource())
                                .tags(new String[]{"ads", "events"}).build()
        };
    }

    /**
     * ALERT!
     * Run this test ONLY from TERMINAL, or you will crash your IDE
     * It happens because IDEA tries to print parameters of test into console
     * And ecoNews object contains the whole image as base64 string, so...
     * Use this command instead:
     * mvn -Dtest=CreateNewsWithImproperImageTest#createNewsWithTooBigImage test
     */
    @Test(dataProvider = "images in PNG and JPEG formats",
            testName = "GC-619",
            description = "Verify that system doesn't allow to add picture JPEG, PNG format more than 10 MB")
    public void createNewsWithTooBigImage(EcoNewsPOSTdto ecoNews) {
        logger.info("running GC-619: createNewsWithTooBigImage");

        Response created = ecoNewsClient.postNews(ecoNews);
        DetailedErrorMessage err = created.as(DetailedErrorMessage.class);
        logger.info(err.message);
        BaseAssertion assertCreated = new BaseAssertion(created);
        assertCreated
                .statusCode(500)
                .bodyValueContains("message", "Maximum upload size exceeded;");

        List<EcoNewsEntity> list = ecoNewsService.getNewsByTitle(ecoNews.title);
        Assert.assertTrue(list.isEmpty());
    }

    @Test(testName = "GC-624",
            description = "Verify that system doesn’t allow to add file of another format in ‘Image’ field")
    public void createNewsWithImproperImageFile() {
        logger.info("running GC-624: createNewsWithImproperImageFile");

        EcoNewsPOSTdto ecoNews = ecoNewsDtoWith()
                .title(getShortTitle())
                .text(getMediumText())
                .image("text/plain", getNotAnImage())
                .source(getProperSource())
                .tags(new String[]{"ads", "events"}).build();

        Response created = ecoNewsClient.postNews(ecoNews);
        BaseAssertion assertCreated = new BaseAssertion(created);
        assertCreated
                .statusCode(500)
                .bodyValueContains("message", "ModelMapper mapping errors");

        List<EcoNewsEntity> list = ecoNewsService.getNewsByTitle(ecoNews.title);
        Assert.assertTrue(list.isEmpty());
    }

    /**
     * NOTICE!
     * This test fails, because server returns 201, but have to return 400 or 500(depends on devs)
     * And also news is being created, but requirements says, that only .jpeg and .png are acceptable
     * Probably, requirements have changed and now we are going to have possibility to load GIF images
     */
    @Test(testName = "GC-635",
            description = "Verify that system doesn’t allow to add image of inappropriate format")
    public void createNewsWithInappropriateImageFormat() {
        logger.info("running GC-635: createNewsWithInappropriateImageFormat");

        EcoNewsPOSTdto ecoNews = ecoNewsDtoWith()
                .title(getShortTitle())
                .text(getMediumText())
                .image("image/gif", getGifImage())
                .source(getProperSource())
                .tags(new String[]{"ads", "events"}).build();

        Response created = ecoNewsClient.postNews(ecoNews);
        BaseAssertion assertCreated = new BaseAssertion(created);
        assertCreated
                 .statusCode(500);

        List<EcoNewsEntity> list = ecoNewsService.getNewsByTitle(ecoNews.title);
        Assert.assertTrue(list.isEmpty());
    }
}
