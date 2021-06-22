package com.softserve.edu.greencity.api.clients;

import com.softserve.edu.greencity.api.models.editProfile.EditProfileDto;
import com.softserve.edu.greencity.api.models.ownsecurity.UpdatePasswordDto;
import io.restassured.RestAssured;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.MultiPartSpecification;

import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;

public class EditProfileClient extends BaseClient{

    private final String authToken;
    private static final String url = "https://greencity-user.azurewebsites.net";


    public EditProfileClient(ContentType contentType) {
        super(contentType, "user",url);
        authToken = "";
    }

    public EditProfileClient(ContentType contentType, String authToken) {
        super(contentType, "user", url);
        this.authToken = "Bearer " + authToken;
    }

    public EditProfileClient(String contentType) {
        super(contentType, "user",url);
        authToken = "";
    }

    private MultiPartSpecification getMultiPart(EditProfileDto editProfileDto) {
        return new MultiPartSpecBuilder(editProfileDto.toString().getBytes(StandardCharsets.UTF_8))
                .controlName("tipsAndTricksDtoRequest")
                .fileName(null)
                .build();
    }

    public Response updateInformationAboutUser (EditProfileDto changeProfileInformation){
        return prepareRequest()
                .header("Authorization", authToken)
                .body(changeProfileInformation)
                .put("{entity}/profile");
    }
}
