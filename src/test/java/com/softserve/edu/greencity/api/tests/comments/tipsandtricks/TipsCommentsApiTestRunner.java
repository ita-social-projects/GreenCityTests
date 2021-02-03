package com.softserve.edu.greencity.api.tests.comments.tipsandtricks;

import com.softserve.edu.greencity.api.clients.OwnSecurityClient;
import com.softserve.edu.greencity.api.clients.TipsAndTricksClient;
import com.softserve.edu.greencity.api.models.ownsecurity.OwnSecurityModel;
import com.softserve.edu.greencity.api.models.ownsecurity.SignInDto;
import com.softserve.edu.greencity.api.models.tipsandtricks.TipsAndTricksModel;
import com.softserve.edu.greencity.api.models.tipsandtricks.TipsAndTricksPOSTdto;
import com.softserve.edu.greencity.api.tests.GreenCityAPITestRunner;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static com.softserve.edu.greencity.api.builders.tipsandtricks.TipsAndTricksDtoBuilder.tipsAndTricksDtoWith;


public class TipsCommentsApiTestRunner extends GreenCityAPITestRunner {
    protected TipsAndTricksClient tipsAndTricksClient;
    //protected EcoNewsService ecoNewsService;
    protected OwnSecurityModel userData;
    protected String tipsAndTricksId;

    @BeforeClass
    public void setUp() {
        OwnSecurityClient authorizationClient = new OwnSecurityClient(ContentType.JSON);
        User user = UserRepository.get().temporary();
        Response signedIn = authorizationClient
                .signIn(new SignInDto(user.getEmail(), user.getPassword()));
        userData = signedIn.as(OwnSecurityModel.class);

        tipsAndTricksClient = new TipsAndTricksClient(ContentType.JSON, userData.accessToken);
      //  ecoNewsService = new EcoNewsService();
      //  ecoNewsService.getNewsByTitle(createTips().title);
        Response created = tipsAndTricksClient.postTipsAndTricks(createTips());
        TipsAndTricksModel expectedTipsAndTricks = created.as(TipsAndTricksModel.class);
        tipsAndTricksId = expectedTipsAndTricks.id.toString();
    }

    @AfterClass
 //   public void deleteNews() {
  //      ecoNewsService.deleteNewsById(Integer.parseInt(tipsAndTricksId));
  //  }

    private TipsAndTricksPOSTdto createTips() {
        return tipsAndTricksDtoWith().title("API Title")
                .text("text for api comment")
                .image(null, null)
                .source("")
                .tags(new String[]{"news", "ads"})
                .build();
    }
}
