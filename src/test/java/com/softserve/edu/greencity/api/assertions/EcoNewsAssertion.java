package com.softserve.edu.greencity.api.assertions;

import com.softserve.edu.greencity.api.models.econews.EcoNewsModel;
import com.softserve.edu.greencity.ui.tools.jdbc.entity.EcoNewsEntity;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import org.testng.asserts.SoftAssert;

public class EcoNewsAssertion {

    private static EcoNewsService ecoNewsService = new EcoNewsService();

    public static void assertExistence(EcoNewsModel expected) {
        SoftAssert softAssert = new SoftAssert();
        EcoNewsEntity actual = ecoNewsService.getNewsById(expected.id);
        softAssert.assertNotNull(actual);
    }
}
