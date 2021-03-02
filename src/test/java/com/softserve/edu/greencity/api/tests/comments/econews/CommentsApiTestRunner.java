package com.softserve.edu.greencity.api.tests.comments.econews;

import com.softserve.edu.greencity.api.clients.EcoNewsClient;
import com.softserve.edu.greencity.api.clients.OwnSecurityClient;
import com.softserve.edu.greencity.api.models.econews.EcoNewsModel;
import com.softserve.edu.greencity.api.models.econews.EcoNewsPOSTdto;
import com.softserve.edu.greencity.api.models.ownsecurity.OwnSecurityModel;
import com.softserve.edu.greencity.api.models.ownsecurity.SignInDto;
import com.softserve.edu.greencity.api.tests.GreenCityAPITestRunner;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static com.softserve.edu.greencity.api.builders.econews.EcoNewsDtoBuilder.ecoNewsDtoWith;


public class CommentsApiTestRunner extends GreenCityAPITestRunner {
    protected EcoNewsClient ecoNewsClient;
    protected EcoNewsService ecoNewsService;
    protected OwnSecurityModel userData;
    protected String ecoNewsId;

    @BeforeClass
    public void setUp() {
        OwnSecurityClient authorizationClient = new OwnSecurityClient(ContentType.JSON);
        User user = UserRepository.get().temporary();
        Response signedIn = authorizationClient
                .signIn(new SignInDto(user.getEmail(), user.getPassword()));
        userData = signedIn.as(OwnSecurityModel.class);

        ecoNewsClient = new EcoNewsClient(ContentType.JSON, userData.accessToken);
        ecoNewsService = new EcoNewsService();
        ecoNewsService.getNewsByTitle(createNews().title);
        Response created = ecoNewsClient.postNews(createNews());
        EcoNewsModel expectedEcoNews = created.as(EcoNewsModel.class);
        ecoNewsId = expectedEcoNews.id.toString();
    }

    @AfterClass
    public void deleteNews() {
        ecoNewsService.deleteNewsById(Integer.parseInt(ecoNewsId));
    }

    private EcoNewsPOSTdto createNews() {
        return ecoNewsDtoWith().title("API Title")
                .text("text for api comment")
                .image(null, null)
                .source("")
                .tags(new String[]{"news", "ads"})
                .build();
    }
}