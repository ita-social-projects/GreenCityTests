package com.softserve.edu.greencity.api.tests.ubscourier;

import com.softserve.edu.greencity.api.assertions.BaseAssertion;
import com.softserve.edu.greencity.data.UBS.Certificates;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class CertificatesAPITest extends  UbsTestRunner{

    //These tests are made without any testcases

    @DataProvider(name = "Certificates")
    public Object[][] certificates() {
        return new Object[][]{
                {Certificates.ACTIVE_500.getCertificate()},
                {Certificates.ACTIVE_300.getCertificate()},
                {Certificates.ACTIVE_100.getCertificate()},
                {Certificates.ACTIVE_1000.getCertificate()},
                {Certificates.EXPIRED_1000.getCertificate()},
                {Certificates.USED_500.getCertificate()},
        };
    }
    @Test
    @Description("Checks name of user")
    public void personalDataNameTest(){
        Response response1 = ubsClient.getUserPersonalData().then().extract().response();
        ArrayList<String> client = response1.path("firstName");
        Assert.assertEquals(client.get(0),"dfgdfgdf");
        BaseAssertion assertion = new BaseAssertion(response1);
        assertion.defaultAsserts();
    }
    @Test(dataProvider = "Certificates")
    @Description("Checks availability of certificates")
    public void certificateTest(String certificate){
        response = ubsClient.getCertificateAvailability(certificate);
        BaseAssertion assertion = new BaseAssertion(response);
        assertion.defaultAsserts();
    }
    @Test
    @Description("Checks wrong sertificate")
    public void wrongCertificatesTest(){
        response = ubsClient.getCertificateAvailability("4444-5555");
        BaseAssertion assertion = new BaseAssertion(response);
        assertion.statusCode(404);
    }
}
