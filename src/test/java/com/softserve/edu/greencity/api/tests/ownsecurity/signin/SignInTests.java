package com.softserve.edu.greencity.api.tests.ownsecurity.signin;

import com.softserve.edu.greencity.api.assertions.BaseAssertions;
import com.softserve.edu.greencity.api.client.OwnSecurityClient;
import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

public class SignInTests {
    OwnSecurityClient ownSecurityClient;
    User user;

    @BeforeClass
    public void beforeClass() {
        ownSecurityClient = new OwnSecurityClient();
        user = UserRepository.get().temporary();
    }

    @Test
    public void signInValidationTest() {
        Response response = ownSecurityClient.signIn(user);

        BaseAssertions.checkResponse(response, HttpURLConnection.HTTP_OK);
    }
}
