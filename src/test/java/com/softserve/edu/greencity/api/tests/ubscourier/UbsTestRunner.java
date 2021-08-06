package com.softserve.edu.greencity.api.tests.ubscourier;

import com.softserve.edu.greencity.api.clients.OwnSecurityClient;
import com.softserve.edu.greencity.api.clients.UBSCourierClient;
import com.softserve.edu.greencity.api.models.ownsecurity.OwnSecurityModel;
import com.softserve.edu.greencity.api.models.ownsecurity.SignInDto;
import com.softserve.edu.greencity.api.tests.GreenCityAPITestRunner;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

public class UbsTestRunner extends GreenCityAPITestRunner {
    UBSCourierClient ubsClient;
    Response response;

    @BeforeClass
    public void setup() {
        OwnSecurityClient authorizationClient = new OwnSecurityClient(ContentType.JSON);
        User user = UserRepository.get().temporary();
        response = authorizationClient
                .signIn(new SignInDto(user.getEmail(), user.getPassword()));

        OwnSecurityModel userData = response.as(OwnSecurityModel.class);
        ubsClient = new UBSCourierClient(ContentType.JSON, userData.accessToken);

    }

}
