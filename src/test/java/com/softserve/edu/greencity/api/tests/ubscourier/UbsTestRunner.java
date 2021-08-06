package com.softserve.edu.greencity.api.tests.ubscourier;

import com.softserve.edu.greencity.api.clients.OwnSecurityClient;
import com.softserve.edu.greencity.api.clients.UBSCourierClient;
import com.softserve.edu.greencity.api.models.ownsecurity.OwnSecurityModel;
import com.softserve.edu.greencity.api.models.ownsecurity.SignInDto;
import com.softserve.edu.greencity.api.tests.GreenCityAPITestRunner;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.tools.jdbc.services.UBSPersonalDataService;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

public class UbsTestRunner extends GreenCityAPITestRunner {
    protected UBSCourierClient ubsClient;
    protected OwnSecurityModel userData;
    protected UBSPersonalDataService ubsPersonalDataService;

    @BeforeClass
    public void setup() {
        OwnSecurityClient authorizationClient = new OwnSecurityClient(ContentType.JSON);
        User user = UserRepository.get().temporary();
        Response signedIn = authorizationClient
                .signIn(new SignInDto(user.getEmail(), user.getPassword()));
        userData = signedIn.as(OwnSecurityModel.class);
        OwnSecurityModel userData = signedIn.as(OwnSecurityModel.class);
        ubsClient = new UBSCourierClient(ContentType.JSON, userData.accessToken);
        ubsPersonalDataService = new UBSPersonalDataService();
    }

}
