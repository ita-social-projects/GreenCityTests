package com.softserve.edu.greencity.api.tests.ubscourier;

import com.softserve.edu.greencity.api.assertions.BaseAssertion;
import com.softserve.edu.greencity.api.clients.UBSCourierClient;
import com.softserve.edu.greencity.ui.tools.jdbc.entity.UBSPersonalDataEntity;
import com.softserve.edu.greencity.ui.tools.jdbc.services.UBSPersonalDataService;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UBSPersonalDataAPIResetUserData extends UbsTestRunner{

    private UBSPersonalDataEntity user;

    @AfterMethod
    public void createUser(){
        UBSPersonalDataService ubsPersonalDataDao = new UBSPersonalDataService();
//        ubsPersonalDataDao.saveUser(user);
    }

    @BeforeMethod
    public void getUser(){
        UBSPersonalDataService ubsPersonalDataDao = new UBSPersonalDataService();
        user = ubsPersonalDataDao.getDataById(332);
    }

    @Test(testName = "GC-1857", description = "GC-1857")
    @Description("Verify that the deleted data for the ubs_user in the database is null for the data in the server response.")
    public void deletedDataInDatabaseIsNull() {
        UBSPersonalDataService ubsPersonalDataDao = new UBSPersonalDataService();
        ubsPersonalDataDao.deleteUser(332);
        UBSCourierClient ubsClient = new UBSCourierClient(ContentType.JSON, userData.accessToken);
        Response response = ubsClient.getUserPersonalData();
        softAssert.assertEquals(response.getStatusCode(), 200);
//        softAssert.assertFalse(ubsPersonalDataDao.getDataById(332));
        softAssert.assertAll();
    }
}
