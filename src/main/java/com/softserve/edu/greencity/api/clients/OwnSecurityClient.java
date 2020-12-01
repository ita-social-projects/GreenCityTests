package com.softserve.edu.greencity.api.clients;

import com.softserve.edu.greencity.api.models.ownsecurity.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class OwnSecurityClient extends BaseClient {

    public OwnSecurityClient(ContentType contentType) {
        super(contentType, "ownSecurity");
    }

    public OwnSecurityClient(String contentType) {
        super(contentType, "ownSecurity");
    }

    public Response signIn(SignInDto credentials) {
        return prepareRequest()
                .body(credentials)
                .post("/{entity}/signIn");
    }

    public Response signUp(SignUpDto credentials) {
        return prepareRequest()
                .body(credentials)
                .post("/{entity}/signUp");
    }

    public Response verifyEmail(String emailToken, String user_id) {
        return prepareRequest().given().log().all()
                .queryParam("token", emailToken)
                .queryParam("user_id", Integer.parseInt(user_id))
                .get("/{entity}/verifyEmail");
    }

    public Response restorePassword(String email) {
        return prepareRequest().given().log().all()
                .queryParam("email", email)
                .get("/{entity}/restorePassword");
    }

    public Response changePassword(ChangePasswordDto params) {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("confirmPassword", params.confirmPassword);
        formParams.put("password", params.password);
        formParams.put("token", params.token);

        return prepareRequest()
                .formParams(formParams)
                .contentType(ContentType.URLENC)
                .post("/{entity}/changePassword");
    }

    public Response updatePassword(UpdatePasswordDto params) {
        return prepareRequest().given().log().all()
                .body(params)
                .post("/{entity}");
    }
}
