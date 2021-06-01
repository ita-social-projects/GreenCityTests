package com.softserve.edu.greencity.api.tests.editprofile.positive;
import com.softserve.edu.greencity.api.assertions.BaseAssertion;
import com.softserve.edu.greencity.api.clients.EditProfileClient;
import com.softserve.edu.greencity.api.models.editProfile.EditProfileDto;
import com.softserve.edu.greencity.api.tests.editprofile.EditProfileAPIRunner;
import com.softserve.edu.greencity.data.editprofile.EditProfileDataStrings;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static com.softserve.edu.greencity.api.builders.editprofile.EditProfileDtoBuilder.editProfileDtoWith;


public class EditProfileTestNameField extends EditProfileAPIRunner {

    @DataProvider(name = "minimalName")
    private Object[] getMinimalValuesForName() {
        return new Object[]
                {
                        editProfileDtoWith().city(EditProfileDataStrings.CITY.getString())
                                .firstName(EditProfileDataStrings.NAME.getString())
                        .showEcoPlace(true)
                        .showLocation(true)
                        .showShoppingList(true)
                        .socialNetworks(null)
                        .userCredo(EditProfileDataStrings.CREDO.getString())
                };
    }

    @Test (dataProvider = "minimalName")
    public void changeName(EditProfileDto name){
        EditProfileClient editProfileClient = new EditProfileClient(ContentType.JSON, userData.accessToken);
        Response response = editProfileClient.updateInformationAboutUser(name);
        BaseAssertion editProfile = new BaseAssertion(response);
        editProfile.statusCode(200);
    }
}
