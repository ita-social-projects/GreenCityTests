package com.softserve.edu.greencity.api.tests.ownsecurity.signup;

import com.softserve.edu.greencity.api.assertions.ArrayAssertion;
import com.softserve.edu.greencity.api.builders.ownsecurity.SignInBuilder;
import com.softserve.edu.greencity.api.clients.OwnSecurityClient;
import com.softserve.edu.greencity.api.models.ownsecurity.SignInDto;
import com.softserve.edu.greencity.api.tests.ownsecurity.OwnSecurityTestRunner;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.api.mail.GoogleMailAPI;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SignUpApiTest {
    @DataProvider(name = "emptyCredentials")
    public Object[][] blankCredentials() { //GC-532, 534, 535
        return new Object[][]{
                {"", "", "", "must not be blank"},
                {"", "DefaultName", "Test1234_", "must not be blank"},
                {"TestEmail@gmail.com", "DefaultName", "", "must not be blank"},

        };
    }

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() { //GC-536, 537, 540, 541
        return new Object[][]{
                {"InvalidEmail", "DefaultName", "Test1234_", "The email is invalid"},
                {"TestEmail@gmail.com", "DefaultName", "Te1_", "Password has contain at least one character of Uppercase letter (A-Z)," +
                        " Lowercase letter (a-z), Digit (0-9)," +
                        " Special character (~`!@#$%^&*()+=_-{}[]|:;"},
                {"TestEmail@gmail.com", "InvalidVeryLongNameForSignUpTest", "Test1234_", "length must be between 6 and 30"},
                {"TestEmail@gmail.com", "DefaultName", "InvalidPassword", "Password has contain at least one character of Uppercase letter (A-Z)," +
                        " Lowercase letter (a-z), Digit (0-9)," +
                        " Special character (~`!@#$%^&*()+=_-{}[]|:;"},
        };
    }

    @DataProvider(name = "existingEmail")
    public Object[][] existingEmail() { //GC-528
        return new Object[][]{
                {"lelekatestacc@gmail.com", "DefaultName", "Test1234_", "User with this email is already registered"},
        };
    }

    @DataProvider(name = "validCredentials")
    public Object[][] validCredentials() { //GC-533
        return new Object[][]{
                {
                        UserRepository.get().userCredentialsForRegistration().getEmail(),
                        UserRepository.get().userCredentialsForRegistration().getUserName(),
                        UserRepository.get().userCredentialsForRegistration().getPassword(),
                        "You should verify the email first, check your email box!"},
        };
    }

    @DataProvider(name = "SuccessfulRegistration")
    public Object[][] successfulRegistration() { //
        return new Object[][]{
                {
                        UserRepository.get().userCredentialsForRegistration().getEmail(),
                        UserRepository.get().userCredentialsForRegistration().getUserName(),
                        UserRepository.get().userCredentialsForRegistration().getPassword(),
                },
        };
    }



    @Test(dataProvider = "emptyCredentials", testName = "GC-532, GC-534, GC-535", description = "GC-532, GC-534, GC-535")
    @Description("Verify that user is not registered, when values in request are empty")
    public void blankCredentialsTest(String email, String name, String password, String message) {
        ArrayAssertion assertion = OwnSecurityTestRunner.getSignUpAssertion(email, name, password);
        assertion.statusCode(400)
                .bodyArrayContains("message", message);
    }

    @Test(dataProvider = "invalidCredentials", testName = "GC-536, GC-537, GC-540, GC-541", description = "GC-536, GC-537, GC-540, GC-541")
    @Description("Verify that user is not registered, when credentials are invalid")
    public void invalidCredentialsTest(String email, String name, String password, String message) {
        ArrayAssertion assertion = OwnSecurityTestRunner.getSignUpAssertion(email, name, password);
        assertion.statusCode(400)
                .bodyArrayStartWith("message", message);
    }

    @Test(dataProvider = "existingEmail", testName = "GC-528", description = "GC-528")
    @Description("Verify that the user cannot register with the already registered email address")
    public void existingEmailTest(String email, String name, String password, String message) {
        ArrayAssertion assertion = OwnSecurityTestRunner.getSignUpAssertion(email, name, password);
        assertion.statusCode(400)
                .bodyArrayContains("message", message);
    }

    @Test(dataProvider = "validCredentials", testName = "GC-533", description = "GC-533")
    @Description("Verify that user can not Sign in if he didn’t verify email address in email box ")
    public void signInWithoutVerifyingEmailTest(String email, String name, String password, String message) {
        ArrayAssertion assertion = OwnSecurityTestRunner.getSignUpAssertion(email, name, password);
        assertion.statusCode(201);
        assertion = OwnSecurityTestRunner.getSignInAssertion(email, password);
        assertion.statusCode(403)
                .bodyValueContains("message", message);
    }



    @Test(dataProvider = "SuccessfulRegistration", testName = "GC-531", description = "GC-531")
    @Description("Verify that user is registered, after he enters valid values ")
    public void signUpWithValidCredentialsTest(String email, String name, String password) {
        new GoogleMailAPI().clearMail(email, password);
        ArrayAssertion assertion = OwnSecurityTestRunner.getSignUpAssertion(email, name, password);
        assertion.statusCode(201);

        assertion = OwnSecurityTestRunner.getVerifyEmailAssertion(email, password);
        assertion.statusCode(200);
    }
}

