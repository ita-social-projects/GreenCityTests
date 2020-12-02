package com.softserve.edu.greencity.api.clients;

import com.softserve.edu.greencity.api.models.ownsecurity.ChangePasswordDto;
import com.softserve.edu.greencity.api.models.ownsecurity.SignInDto;
import com.softserve.edu.greencity.api.models.ownsecurity.SignUpDto;
import com.softserve.edu.greencity.api.models.ownsecurity.UpdatePasswordDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class OwnSecurityClient extends BaseClient {

    private final String authToken;

    public OwnSecurityClient(ContentType contentType) {
        super(contentType, "ownSecurity");
        authToken = "";
    }

    public OwnSecurityClient(String contentType) {
        super(contentType, "ownSecurity");
        authToken = "";
    }

    /**
     * Use it to create authorized client.
     *
     * @param contentType content type of response and request
     * @param authToken   unique token. Use OwnSecurityClient to get it
     */
    public OwnSecurityClient(ContentType contentType, String authToken) {
        super(contentType, "econews");
        this.authToken = "Bearer " + authToken;
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
        return prepareRequest().given().log().all()
                .body(params)
                .post("/{entity}/changePassword");
    }

    public Response updatePassword(UpdatePasswordDto params) {
        return prepareRequest().given().log().all()
                .body(params)
                .header("Authorization", authToken)
                .put("/{entity}");
    }
}
