package com.softserve.edu.greencity.api.tests.ubscourier;

import com.softserve.edu.greencity.api.clients.UBSCourierClient;
import com.softserve.edu.greencity.api.models.ubscourier.PersonalData;
import com.softserve.edu.greencity.api.models.ubscourier.UBSCourierPOSTModeldto;
import com.softserve.edu.greencity.ui.tools.jdbc.entity.UBSPersonalDataEntity;
import com.softserve.edu.greencity.ui.tools.jdbc.services.UBSPersonalDataService;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class UBSPersonalDataAPITests extends UbsTestRunner {

    @Test(testName = "GC-1855", description = "GC-1855")
    @Description("Verify that the updated data for the ubs_user in the database corresponds to the data in the server response.")
    public void dataInDatabaseCorrespondsToDataInServer() {
        UBSPersonalDataService ubsPersonalDataDao = new UBSPersonalDataService();
        ubsPersonalDataDao.updatePhoneNumber("060606060", 332);
        UBSCourierClient ubsClient = new UBSCourierClient(ContentType.JSON, userData.accessToken);
        Response response = ubsClient.getUserPersonalData();
        JsonPath jsonPathEvaluator = response.jsonPath();
        softAssert.assertEquals(jsonPathEvaluator.get("email[0]"), "343@dfgdf.n");
        softAssert.assertEquals(jsonPathEvaluator.get("firstName[0]"), "dfgdfgdf");
        softAssert.assertEquals((int)jsonPathEvaluator.get("id[0]"), 332);
        softAssert.assertEquals(jsonPathEvaluator.get("lastName[0]"), "dfgdfgdfg");
        softAssert.assertEquals(jsonPathEvaluator.get("phoneNumber[0]"), "060606060");
        softAssert.assertEquals(jsonPathEvaluator.get("addressComment[0]"), "dfgf");
        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertAll();
    }

    @Test(testName = "GC-1854", description = "GC-1854")
    @Description("Verify that the data for the ubs_user in the database corresponds to the data in the server response.")
    public void dataInDatabaseCorresponds() {
        UBSCourierClient ubsClient = new UBSCourierClient(ContentType.JSON, userData.accessToken);
        Response response = ubsClient.getUserPersonalData();
        JsonPath jsonPathEvaluator = response.jsonPath();
        softAssert.assertEquals(jsonPathEvaluator.get("email[0]"), "343@dfgdf.n");
        softAssert.assertEquals(jsonPathEvaluator.get("firstName[0]"), "dfgdfgdf");
        softAssert.assertEquals((int)jsonPathEvaluator.get("id[0]"), 332);
        softAssert.assertEquals(jsonPathEvaluator.get("lastName[0]"), "dfgdfgdfg");
        softAssert.assertEquals(jsonPathEvaluator.get("phoneNumber[0]"), "060606060");
        softAssert.assertEquals(jsonPathEvaluator.get("addressComment[0]"), "dfgf");
        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertAll();
    }

    @Test(testName = "GC-1909", description = "GC-1909")
    @Description("Verify that the system does not create an order if the 'first name' field contains special characters except .'-.")
    public void firstNameContainsSpecialCharacters() {
        UBSPersonalDataService ubsPersonalDataDao = new UBSPersonalDataService();
        UBSPersonalDataEntity beforePersonalData = ubsPersonalDataDao.getDataById(332);
        UBSCourierPOSTModeldto user = new UBSCourierPOSTModeldto();
        PersonalData personalData = new PersonalData();
        personalData.firstName = "@###$";
        personalData.lastName = "Serhova";
        personalData.email = "343@dfgdf.n";
        personalData.phoneNumber = "060606060";
        personalData.id = 332L;
        personalData.addressComment = "dfgf";
        user.personalData = personalData;
        UBSCourierClient ubsClient = new UBSCourierClient(ContentType.JSON, userData.accessToken);
        Response response = ubsClient.postProcessUserOrder(user);
        UBSPersonalDataEntity afterPersonalData = ubsPersonalDataDao.getDataById(332);
        JsonPath jsonPathEvaluator = response.jsonPath();
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertEquals(jsonPathEvaluator.get("name[0]"),"personalData.firstName");
        softAssert.assertEquals(jsonPathEvaluator.get("message[0]"), "must match \"[ЁёІіЇїҐґЄєА-Яа-яA-Za-z-'\\s.]{1,30}\"");
        softAssert.assertEquals(afterPersonalData.getFirstName(), beforePersonalData.getFirstName());
        softAssert.assertAll();
    }

    @Test(testName = "GC-1910", description = "GC-1910")
    @Description("Verify that the system does not create an order if the 'first name' field contains numeric characters")
    public void firstNameContainsNumericCharacters() {
        UBSPersonalDataService ubsPersonalDataDao = new UBSPersonalDataService();
        UBSPersonalDataEntity beforePersonalData = ubsPersonalDataDao.getDataById(332);
        UBSCourierPOSTModeldto user = new UBSCourierPOSTModeldto();
        PersonalData personalData = new PersonalData();
        personalData.firstName = "333456";
        personalData.lastName = "Serhova";
        personalData.email = "343@dfgdf.n";
        personalData.phoneNumber = "060606060";
        personalData.id = 332L;
        personalData.addressComment = "dfgf";
        user.personalData = personalData;
        UBSCourierClient ubsClient = new UBSCourierClient(ContentType.JSON, userData.accessToken);
        Response response = ubsClient.postProcessUserOrder(user);
        UBSPersonalDataEntity afterPersonalData = ubsPersonalDataDao.getDataById(332);
        JsonPath jsonPathEvaluator = response.jsonPath();
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertEquals(jsonPathEvaluator.get("name[0]"),"personalData.firstName");
        softAssert.assertEquals(jsonPathEvaluator.get("message[0]"), "must match \"[ЁёІіЇїҐґЄєА-Яа-яA-Za-z-'\\s.]{1,30}\"");
        softAssert.assertEquals(afterPersonalData.getFirstName(), beforePersonalData.getFirstName());
        softAssert.assertAll();
    }

    @Test(testName = "GC-1911", description = "GC-1911")
    @Description("Verify that the system does not create an order if the 'first name' field contains more than 30 characters")
    public void firstNameContainsTooManyCharacters() {
        UBSPersonalDataService ubsPersonalDataDao = new UBSPersonalDataService();
        UBSPersonalDataEntity beforePersonalData = ubsPersonalDataDao.getDataById(332);
        UBSCourierPOSTModeldto user = new UBSCourierPOSTModeldto();
        PersonalData personalData = new PersonalData();
        personalData.firstName = "WWWW.HHHHHHHTTTTdddddddddddddddd";
        personalData.lastName = "Serhova";
        personalData.email = "343@dfgdf.n";
        personalData.phoneNumber = "060606060";
        personalData.id = 332L;
        personalData.addressComment = "dfgf";
        user.personalData = personalData;
        UBSCourierClient ubsClient = new UBSCourierClient(ContentType.JSON, userData.accessToken);
        Response response = ubsClient.postProcessUserOrder(user);
        UBSPersonalDataEntity afterPersonalData = ubsPersonalDataDao.getDataById(332);
        JsonPath jsonPathEvaluator = response.jsonPath();
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertEquals(jsonPathEvaluator.get("name[0]"),"personalData.firstName");
        softAssert.assertEquals(jsonPathEvaluator.get("message[0]"), "must match \"[ЁёІіЇїҐґЄєА-Яа-яA-Za-z-'\\s.]{1,30}\"");
        softAssert.assertEquals(afterPersonalData.getFirstName(), beforePersonalData.getFirstName());
        softAssert.assertAll();
    }
}




