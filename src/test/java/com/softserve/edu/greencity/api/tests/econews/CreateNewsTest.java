package com.softserve.edu.greencity.api.tests.econews;

import com.softserve.edu.greencity.api.assertions.BaseAssertion;
import com.softserve.edu.greencity.api.assertions.ErrorAssertions;
import com.softserve.edu.greencity.api.clients.EcoNewsClient;
import com.softserve.edu.greencity.api.clients.OwnSecurityClient;
import com.softserve.edu.greencity.api.models.econews.EcoNewsPOSTdto;
import com.softserve.edu.greencity.api.models.errors.*;
import com.softserve.edu.greencity.api.models.ownsecurity.OwnSecurityModel;
import com.softserve.edu.greencity.api.models.ownsecurity.SignInDto;
import com.softserve.edu.greencity.api.tests.GreenCityAPITestRunner;
import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.tools.jdbc.entity.EcoNewsEntity;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static com.softserve.edu.greencity.api.builders.econews.EcoNewsDtoBuilder.*;
import static com.softserve.edu.greencity.api.data.econews.NewsRepository.*;

public class CreateNewsTest extends GreenCityAPITestRunner {

    EcoNewsClient ecoNewsClient;
    EcoNewsService ecoNewsService;

    @DataProvider(name = "improperNewsWithShortResponse")
    private Object[] getImproperNewsWithShortResponse() {
        return new Object[][]{
                {
                        "GC-596",
                        ecoNewsDtoWith().title(getShortTitle())
                                .text(getMediumText())
                                .image(getImageUrl())
                                .source(getSourceWithoutHttp())
                                .tags(new String[]{"ads"}).build(),
                        new ErrorMessage("Malformed URL. The string could not be parsed.")
                },
                {
                        "GC-598",
                        ecoNewsDtoWith().title(getShortTitle())
                                .text(getMediumText())
                                .image(getImageUrl())
                                .source(getLinkWithImproperHttpPos())
                                .tags(new String[]{"ads"}).build(),
                        new ErrorMessage("Malformed URL. The string could not be parsed.")
                }
        };
    }

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

    @DataProvider(name = "improperNewsWithArrayResponse")
    private Object[] getImproperNewsWithArrayResponse() {
        return new Object[][]{
                {
                        "GC-571",
                        ecoNewsDtoWith().title(null)
                                .text(null)
                                .image(null)
                                .source(null).
                                tags(new String[0]).build(),
                        new PairErrorMessage[] {
                                new PairErrorMessage("text", "must not be empty"),
                                new PairErrorMessage("title", "must not be empty"),
                                new PairErrorMessage("tags", "must not be empty")
                        }
                },
                {
                        "GC-572",
                        ecoNewsDtoWith().title(null)
                                .text(getMediumText())
                                .image(getImageUrl())
                                .source(getProperSource())
                                .tags(new String[]{"ads"}).build(),
                        new PairErrorMessage[] {
                                new PairErrorMessage("title", "must not be empty")
                        }
                },
                {
                        "GC-581",
                        ecoNewsDtoWith().title(getTooLongTitle())
                                .text(getLongText())
                                .image(getImageUrl())
                                .source(getProperSource())
                                .tags(new String[]{"ads"}).build(),
                        new PairErrorMessage[] {
                                new PairErrorMessage("title", "size must be between 1 and 170")
                        }
                },
                {
                        "GC-585",
                        ecoNewsDtoWith().title(getShortTitle())
                                .text(getMediumText())
                                .image(getImageUrl())
                                .source(getProperSource())
                                .tags(new String[0]).build(),
                        new PairErrorMessage[] {
                                new PairErrorMessage("tags", "must not be empty")
                        }
                },
                {
                        "GC-600",
                        ecoNewsDtoWith().title(getShortTitle())
                                .text(null)
                                .image(getImageUrl())
                                .source(getProperSource())
                                .tags(new String[]{"ads"}).build(),
                        new PairErrorMessage[] {
                                new PairErrorMessage("text", "must not be empty")
                        }
                },
                {
                        "GC-601",
                        ecoNewsDtoWith().title(getShortTitle())
                                .text(getTooShortText())
                                .image(null)
                                .source(getProperSource())
                                .tags(new String[]{"ads"}).build(),
                        new PairErrorMessage[] {
                                new PairErrorMessage("text", "size must be between 20 and 63206")
                        }
                }
        };
    }

    @BeforeClass
    public void setUp() {
        OwnSecurityClient authorizationClient = new OwnSecurityClient(ContentType.JSON);
        User user = UserRepository.get().temporary();
        Response signedIn = authorizationClient
                .signIn(new SignInDto(user.getEmail(), user.getPassword()));

        OwnSecurityModel userData = signedIn.as(OwnSecurityModel.class);
        ecoNewsClient = new EcoNewsClient(ContentType.JSON, userData.accessToken);
        ecoNewsService = new EcoNewsService();
    }

    @Test(dataProvider = "improperNewsWithShortResponse", testName = "GC-596, GC-598")
    public void createNewsWithShortResponseTest(String testId, EcoNewsPOSTdto ecoNews, ErrorMessage expectedError){
        logger.info("Running createNewsWithShortResponseTest: {}", testId);
        Response created = ecoNewsClient.postNews(ecoNews);
        BaseAssertion assertCreated = new BaseAssertion(created);
        ErrorAssertions.errorEquals(created, expectedError);
        assertCreated.statusCode(400);

        List<EcoNewsEntity> list = ecoNewsService.getNewsByTitle(ecoNews.title);
        Assert.assertTrue(list.isEmpty());
    }

    @Test(dataProvider = "improperNewsWithDetailedResponse", testName = "GC-594, GC-631")
    public void createNewsWithDetailedResponseTest(String testId, EcoNewsPOSTdto ecoNews, DetailedErrorMessage expectedError){
        logger.info("Running createNewsWithDetailedResponseTest: {}", testId);
        Response created = ecoNewsClient.postNews(ecoNews);
        BaseAssertion assertCreated = new BaseAssertion(created);
        ErrorAssertions.errorEquals(created, expectedError);
        assertCreated.statusCode(500);

        List<EcoNewsEntity> list = ecoNewsService.getNewsByTitle(ecoNews.title);
        Assert.assertTrue(list.isEmpty());
    }

    @Test(dataProvider = "improperNewsWithArrayResponse", testName = "GC-571, GC-572, GC-581, " +
            "GC-585, GC-600, GC-601")
    public void createNewsWithArrayResponseTest(String testId, EcoNewsPOSTdto ecoNews, PairErrorMessage[] expectedError) {
        logger.info("Running createNewsWithArrayResponseTest: {}", testId);
        Response created = ecoNewsClient.postNews(ecoNews);
        BaseAssertion assertCreated = new BaseAssertion(created);
        ErrorAssertions.arrayEquals(
                created,
                Arrays.asList(expectedError));
        assertCreated.statusCode(400);

        List<EcoNewsEntity> list = ecoNewsService.getNewsByTitle(ecoNews.title);
        Assert.assertTrue(list.isEmpty());
    }
}
