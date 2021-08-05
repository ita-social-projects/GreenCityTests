package com.softserve.edu.greencity.api.clients;

import com.softserve.edu.greencity.api.models.ubscourier.UBSCourierPOSTDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UBSCourierClient extends BaseClient {

    private static final String url = "https://greencity-ubs.azurewebsites.net";
    private final String authToken;

    public UBSCourierClient(ContentType contentType) {
        super(contentType, "ubs", url);
        authToken = "";
    }

    public UBSCourierClient(String contentType) {
        super(contentType, "ubs", url);
        authToken = "";
    }

    public UBSCourierClient(ContentType contentType, String authToken) {
        super(contentType, "ubs", url);
        this.authToken = "Bearer " + authToken;
    }

    public Response postProcessUserOrder(UBSCourierPOSTDto dto) {
        return prepareRequest()
                .body(dto)
                .header("Authorization", authToken)
                .post("/{entity}/processOrder");
    }

    public Response getUserPersonalData() {
        return prepareRequest()
                .header("Authorization", authToken)
                .get("/{entity}/personal-data");
    }

    public Response putUpdateRecipientInformationInOrder(UBSCourierPOSTDto dto) {
        return prepareRequest()
                .body(dto)
                .header("Authorization", authToken)//Ask about Authorization
                .put("/{entity}/update-recipient-data");
    }
}
