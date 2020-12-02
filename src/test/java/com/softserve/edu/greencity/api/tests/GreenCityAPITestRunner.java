package com.softserve.edu.greencity.api.tests;

import com.softserve.edu.greencity.ui.tools.testng.TestNgListeners;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;

/**
 * A base class for API tests. All test classes should extend this one.
 */
@Listeners(TestNgListeners.class)
public class GreenCityAPITestRunner {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Set up REST-Assured filters (logging requests and responses)
     * @param method
     */
    @BeforeMethod
    public void setFilters(Method method) {
        File logFile = new File(System.getProperty("user.dir") + "\\target\\"
                + method.getName() + "_requests.log");
        try {
            //logFile.createNewFile();
            PrintStream stream = new PrintStream(logFile);
            RestAssured.filters(
                    new ResponseLoggingFilter(LogDetail.ALL, stream),
                    new RequestLoggingFilter(LogDetail.ALL, stream)
            );
        } catch (IOException er) {
            logger.warn("Can not set RestAssured filters' output to file, will log to console.");
            er.printStackTrace();
            RestAssured.filters(
                    new ResponseLoggingFilter(LogDetail.ALL),
                    new RequestLoggingFilter(LogDetail.ALL)
            );
        }
    }

    @AfterMethod
    public void switchOffFilters() {
        RestAssured.replaceFiltersWith(new io.restassured.filter.log.ErrorLoggingFilter());
    }
}
