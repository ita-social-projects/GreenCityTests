package com.softserve.edu.greencity.api.clients;

import com.softserve.edu.greencity.api.models.editProfile.EditProfileDto;
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
        super(contentType, "editProfile",url);
        authToken = "";
    }

    public EditProfileClient(ContentType contentType, String authToken) {
        super(contentType, "econews",url);
        this.authToken = "Bearer " + authToken;
    }

    public EditProfileClient(String contentType) {
        super(contentType, "editProfile",url);
        authToken = "";
    }

    private MultiPartSpecification getMultiPart(EditProfileDto editProfileDto) {
        return new MultiPartSpecBuilder(editProfileDto.toString().getBytes(StandardCharsets.UTF_8))
                .controlName("tipsAndTricksDtoRequest")
                .fileName(null)
                .build();
    }

    public Response updateInformationAboutUser (EditProfileDto changeProfileInformation){
        return given()
                .baseUri(url)
                .accept(contentType)
                .pathParam("entity", entity)
                .config(RestAssured.config().encoderConfig(EncoderConfig.encoderConfig()
                        .encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .contentType("multipart/form-data; boundary=--MyBoundary")
                .multiPart(getMultiPart(changeProfileInformation))
                .put("{entity}/profile");
    }
}
