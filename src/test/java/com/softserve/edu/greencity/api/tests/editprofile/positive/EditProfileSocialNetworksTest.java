package com.softserve.edu.greencity.api.tests.editprofile.positive;

import com.softserve.edu.greencity.api.assertions.BaseAssertion;
import com.softserve.edu.greencity.api.clients.EditProfileClient;
import com.softserve.edu.greencity.api.models.editProfile.EditProfileDto;
import com.softserve.edu.greencity.api.tests.editprofile.EditProfileAPIRunner;
import com.softserve.edu.greencity.data.editprofile.EditProfileData;
import com.softserve.edu.greencity.data.editprofile.EditProfileDataRepository;
import com.softserve.edu.greencity.data.editprofile.EditProfileDataStrings;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.softserve.edu.greencity.api.builders.editprofile.EditProfileDtoBuilder.editProfileDtoWith;

public class EditProfileSocialNetworksTest extends EditProfileAPIRunner{

    private EditProfileDto name;

    @BeforeClass
    public void setUpFields(){
        String[] networks = {EditProfileDataRepository.get().getSocialNetworkFacebook().getSocialNetwork()};

        name = editProfileDtoWith()
                .city(EditProfileDataStrings.CITY.getString())
                .firstName(EditProfileDataStrings.NAME.getString())
                .showEcoPlace(true)
                .showLocation(true)
                .showShoppingList(true)
                .socialNetworks(networks)
                .userCredo(EditProfileDataStrings.CREDO.getString())
                .build();
    }

    @Test()
    public void changeName(){
        EditProfileClient editProfileClient = new EditProfileClient(ContentType.JSON, userData.accessToken);
        Response response = editProfileClient.updateInformationAboutUser(name);
        BaseAssertion editProfile = new BaseAssertion(response);
        editProfile.statusCode(200);
    }
}
