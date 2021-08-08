package com.softserve.edu.greencity.api.tests.ubscourier;

import com.softserve.edu.greencity.api.assertions.BaseAssertion;
import com.softserve.edu.greencity.api.clients.UBSCourierClient;
import com.softserve.edu.greencity.ui.tools.jdbc.dao.UBSPersonalDataDao;
import com.softserve.edu.greencity.ui.tools.jdbc.entity.UBSPersonalDataEntity;
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
        UBSPersonalDataEntity userId = ubsPersonalDataDao.getDataById(3);
//        ubsPersonalDataDao.getDataById(3);
        ubsPersonalDataDao.updatePhoneNumber("060606060");
        UBSCourierClient ubsClient = new UBSCourierClient(ContentType.JSON, userData.accessToken);
        Response response = ubsClient.getUserPersonalData();
        BaseAssertion correspondsData = new BaseAssertion(response);
        correspondsData.statusCode(200);
    }

//    @Test(testName = "GC-1855", description = "GC-1855")
//    @Description("Verify that the updated data for the ubs_user in the database corresponds to the data in the server response.")
//    public void dataInDatabaseCorrespondsToData() {
//        UBSCourierClient ubsClient = new UBSCourierClient(ContentType.JSON, userData.accessToken);
//        Response response = ubsClient.postProcessUserOrder(new UBSCourierPOSTDto());
//        BaseAssertion addComment = new BaseAssertion(response);
//        addComment.statusCode(400);
//
//    }

}
