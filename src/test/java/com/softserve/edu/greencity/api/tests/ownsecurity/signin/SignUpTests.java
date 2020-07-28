package com.softserve.edu.greencity.api.tests.ownsecurity.signin;

import com.softserve.edu.greencity.api.assertions.OwnSecurityAssertions;
import com.softserve.edu.greencity.api.builders.userbuilder.SignUpBuilder;
import com.softserve.edu.greencity.api.client.OwnSecurityClient;
import com.softserve.edu.greencity.api.model.APIResponseBody;
import com.softserve.edu.greencity.api.model.SignUpRequest;
import com.softserve.edu.greencity.api.model.SuccessfulSignUp;
import com.softserve.edu.greencity.ui.tools.DBQueries;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class SignUpTests {
    OwnSecurityClient client = new OwnSecurityClient();
    SignUpRequest model = new SignUpRequest();

    @BeforeMethod
    public void createModel() {
        SignUpBuilder builder = new SignUpBuilder();
        model = builder.setEmail("a@b")
                .setName("Liuba")
                .setPassword("!Error911")
                .build();
    }

    @AfterMethod
    public void deleteUserFromDB() {
        DBQueries queryObj = new DBQueries();
        queryObj.deleteUserByEmail(model.getEmail());
    }

    @Test
    public void successfulRegistration() {
        Response response = client.signUp(model);
        response.prettyPrint();
        SuccessfulSignUp apiResp = response.as(SuccessfulSignUp.class);
        OwnSecurityAssertions.checkValidSignUpResponse(apiResp, model);
    }

    @Test
    public void checkDuplicateUserIsNotCreated() {
        client.signUp(model);
        Response response = client.signUp(model);
        List<APIResponseBody> responseObjects = Arrays.asList(response.getBody().as(APIResponseBody[].class));
        OwnSecurityAssertions.checkDuplicateEmailResponse(responseObjects.get(0));
    }

    @Test
    public void checkInvalidPassRegistration() {
        model.setPassword("pass");
        Response response = client.signUp(model);
        List<APIResponseBody> responseObjects = Arrays.asList(response.getBody().as(APIResponseBody[].class));
        OwnSecurityAssertions.checkInvalidPassResponse(responseObjects.get(0));
    }

    @Test
    public void checkInvalidEmailRegistration() {
        model.setEmail("email");
        Response response = client.signUp(model);
        List<APIResponseBody> responseObjects = Arrays.asList(response.getBody().as(APIResponseBody[].class));
        OwnSecurityAssertions.checkInvalidEmailResponse(responseObjects.get(0));
    }


    @Test
    public void checkEmptyFieldsRegistration() {
        model.setEmail("");
        model.setName("");
        model.setPassword("");
        Response response = client.signUp(model);
        List<APIResponseBody> responseObjects = Arrays.asList(response.getBody().as(APIResponseBody[].class));
        OwnSecurityAssertions.checkEmptyFieldsRegistration(responseObjects);
    }

    @Test
    public void checkEmptyEmailRegistration() {
        model.setEmail("");
        Response response = client.signUp(model);
        List<APIResponseBody> responseObjects = Arrays.asList(response.getBody().as(APIResponseBody[].class));
        OwnSecurityAssertions.checkEmptyEmailResponse(responseObjects.get(0));
    }

    @Test
    public void checkEmptyPassRegistration() {
        model.setPassword("");
        Response response = client.signUp(model);
        List<APIResponseBody> responseObjects = Arrays.asList(response.getBody().as(APIResponseBody[].class));
        OwnSecurityAssertions.checkEmptyPassResponse(responseObjects.get(0));
    }


    @Test
    public void checkTooLongNameRegistration() {
        model.setName("21charstring21charstr");
        Response response = client.signUp(model);
        List<APIResponseBody> responseObjects = Arrays.asList(response.getBody().as(APIResponseBody[].class));
        OwnSecurityAssertions.checkTooLongNameResponse(responseObjects.get(0));
    }
}
