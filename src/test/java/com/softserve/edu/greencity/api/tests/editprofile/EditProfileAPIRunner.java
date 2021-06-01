package com.softserve.edu.greencity.api.tests.editprofile;

import com.softserve.edu.greencity.api.clients.EditProfileClient;
import com.softserve.edu.greencity.api.clients.OwnSecurityClient;
import com.softserve.edu.greencity.api.models.ownsecurity.OwnSecurityModel;
import com.softserve.edu.greencity.api.models.ownsecurity.SignInDto;
import com.softserve.edu.greencity.api.tests.GreenCityAPITestRunner;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EditProfileService;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;


public class EditProfileAPIRunner extends GreenCityAPITestRunner {
    protected OwnSecurityModel userData;
    protected EditProfileClient editProfileClient;
    protected EditProfileService editProfileService;

    @BeforeClass
    public void setUp (){
        OwnSecurityClient authorizationClient = new OwnSecurityClient(ContentType.JSON);
        User user = UserRepository.get().temporary();
        Response signedIn = authorizationClient
                .signIn(new SignInDto(user.getEmail(), user.getPassword()));
        userData = signedIn.as(OwnSecurityModel.class);

        OwnSecurityModel userData = signedIn.as(OwnSecurityModel.class);
        editProfileClient = new EditProfileClient(ContentType.JSON, userData.accessToken);
        editProfileService = new EditProfileService();
    }

//    TODO create AfterClass with put default fields

}
