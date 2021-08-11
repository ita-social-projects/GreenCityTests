package com.softserve.edu.greencity.api.tests.ubscourier;

import com.softserve.edu.greencity.api.assertions.BaseAssertion;
import com.softserve.edu.greencity.api.clients.UBSCourierClient;
import com.softserve.edu.greencity.api.models.ubscourier.UBSCourierPOSTModel;
import com.softserve.edu.greencity.data.UBS.UBSDataStrings;
import com.softserve.edu.greencity.ui.tools.jdbc.dao.UBSPersonalDataDao;
import com.softserve.edu.greencity.ui.tools.jdbc.services.UBSPersonalDataService;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class UBSPersonalDataAPITests extends UbsTestRunner {


    private UBSPersonalDataDao ubsPersonalDataDao;
    private UBSPersonalDataService ubsPersonalDataService;

    @Test(testName = "GC-1855", description = "GC-1855")
    @Description("Verify that the updated data for the ubs_user in the database corresponds to the data in the server response.")
    public void dataInDatabaseCorrespondsToDataInServer() {
        UBSPersonalDataService ubsPersonalDataDao = new UBSPersonalDataService();
        ubsPersonalDataDao.updatePhoneNumber("060606060", 332);
        UBSCourierClient ubsClient = new UBSCourierClient(ContentType.JSON, userData.accessToken);
        Response response = ubsClient.getUserPersonalData();
        BaseAssertion correspondsData = new BaseAssertion(response);
        correspondsData.statusCode(200)
                .bodyValueEquals("email", "343@dfgdf.n")
                .bodyValueEquals("firstName", "dfgdfgdf")
                .bodyValueEquals("id", "332")
                .bodyValueEquals("lastName", "dfgdfgdfg")
                .bodyValueEquals("phoneNumber", "060606060")
                .bodyValueEquals("addressComment", "dfgf");
    }

    @Test(testName = "GC-1857", description = "GC-1857")
    @Description("Verify that the deleted data for the ubs_user in the database is null for the data in the server response.")
    public void deletedDataInDatabaseIsNull() {
        UBSPersonalDataService ubsPersonalDataDao = new UBSPersonalDataService();
        ubsPersonalDataDao.deleteUser(332);
        UBSCourierClient ubsClient = new UBSCourierClient(ContentType.JSON, userData.accessToken);
        Response response = ubsClient.getUserPersonalData();
        BaseAssertion deletedData = new BaseAssertion(response);
        deletedData.statusCode(200);
    }

    @Test(testName = "GC-1854", description = "GC-1854")
    @Description("Verify that the data for the ubs_user in the database corresponds to the data in the server response.")
    public void dataInDatabaseCorresponds() {
        UBSCourierClient ubsClient = new UBSCourierClient(ContentType.JSON, userData.accessToken);
        Response response = ubsClient.getUserPersonalData();
        BaseAssertion correspondsData = new BaseAssertion(response);
        correspondsData.statusCode(200)
                .bodyValueEquals("email", "343@dfgdf.n")
                .bodyValueEquals("firstName", "dfgdfgdf")
                .bodyValueEquals("id", "332")
                .bodyValueEquals("lastName", "dfgdfgdfg")
                .bodyValueEquals("phoneNumber", "060606060")
                .bodyValueEquals("addressComment", "dfgf");
    }

    @Test(testName = "GC-1909", description = "GC-1909")
    @Description("Verify that the system does not create an order if the 'first name' field contains special characters except .'-.")
    public void firstNameContainsSpecialCharacters() {
        UBSCourierClient ubsClient = new UBSCourierClient(ContentType.JSON, userData.accessToken);
        Response response = ubsClient.postProcessUserOrder(dto);}

        UBSCourierPOSTModel personalData = ubsCourierWith()
                .firstName(UBSDataStrings.PERSONAL_DATA_NAME.getMessage())
                .lastName(UBSDataStrings.PERSONAL_DATA_SURNAME.getMessage())
                .email(UBSDataStrings.PERSONAL_DATA_EMAIL.getMessage())
                .phoneNumber(UBSDataStrings.PERSONAL_DATA_PHONE.getMessage())
                .build();

}
