package com.softserve.edu.greencity.api.clients;

import com.softserve.edu.greencity.api.models.EditProfile.EditProfileDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class EditProfileClient extends BaseClient{

    private final String authToken;
    private static final String url = "https://greencity-user.azurewebsites.net";


    public EditProfileClient(ContentType contentType) {
        super(contentType, "editProfile",url);
        authToken = "";
    }

    public EditProfileClient(String contentType) {
        super(contentType, "editProfile",url);
        authToken = "";
    }

    public Response updateInformationAboutUser (EditProfileDto changeProfileInformationDto){
        return prepareRequest().given().log().all()
                .header("Authorization", authToken)
                .put("/user/profile");
    }
}
