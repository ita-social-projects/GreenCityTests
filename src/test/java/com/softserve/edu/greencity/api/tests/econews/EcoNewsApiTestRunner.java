package com.softserve.edu.greencity.api.tests.econews;

import com.softserve.edu.greencity.api.clients.EcoNewsClient;
import com.softserve.edu.greencity.api.clients.OwnSecurityClient;
import com.softserve.edu.greencity.api.models.ownsecurity.OwnSecurityModel;
import com.softserve.edu.greencity.api.models.ownsecurity.SignInDto;
import com.softserve.edu.greencity.api.tests.GreenCityAPITestRunner;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

public class EcoNewsApiTestRunner extends GreenCityAPITestRunner {

    protected EcoNewsClient ecoNewsClient;
    protected EcoNewsService ecoNewsService;

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
}
