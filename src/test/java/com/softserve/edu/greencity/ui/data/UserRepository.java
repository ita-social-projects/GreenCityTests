package com.softserve.edu.greencity.ui.data;

import com.softserve.edu.greencity.ui.tools.Randomizer;
import io.qameta.allure.Step;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class UserRepository {
    private static volatile UserRepository instance = null;

    private Properties property = new Properties();

    private UserRepository() {
        try {
            final FileInputStream fis = new FileInputStream("src/test/resources/credentials.properties");
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static UserRepository get() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }

    //replace System.getenv().get(property.getProperty("temporaryPass"))); to property.getProperty("temporaryPass"));
    @Step("get temporary credentials properties")
    public User temporary() {
        return new User(
                property.getProperty("temporaryLoginName"),
                property.getProperty("temporaryPass"));
    }
    @Step("get default User credentials properties")
    public User defaultUserCredentials() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"),
                property.getProperty("defaultPass"),
                property.getProperty("defaultPass"));
    }
    @Step("get google User  credentials properties")
    public User googleUserCredentials() {
        return new User(property.getProperty("googleEmail"), property.getProperty("googlePass"));
    }
    @Step("get empty User credentials properties")
    public User emptyUserCredentials() {
        return new User("", "", "", "");
    }
    @Step("get invalid Usercredentials properties")
    public User invalidUserCredentials() {
        return new User(
                property.getProperty("invalidName"),
                property.getProperty("invalidEmail"),
                property.getProperty("invalidPass"),
                property.getProperty("invalidPass"));
    }
    @Step("get invalid Name credentials properties")
    public User invalidNameCredentials() {
        return new User(
                property.getProperty("invalidName"),
                property.getProperty("defaultEmail"),
                property.getProperty("defaultPass"),
                property.getProperty("defaultPass"));
    }
    @Step("get unregister User credentials properties")
    public User unregisterUser() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("validUnregisterEmail").replace("@",getRandom()+"@"),
                property.getProperty("temporaryPass"),
                property.getProperty("comfTemporaryPass"));
    }
    @Step("get   invalid Email User Credentials")
    public User invalidEmailUserCredentials() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("invalidEmail"),
                property.getProperty("defaultPass"),
                property.getProperty("defaultPass"));
    }
    @Step("get invalid Confirm Pass Credentials")
    public User invalidConfirmPassCredentials() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"),
                property.getProperty("defaultPass") + "!",
                property.getProperty("defaultPass"));
    }
    @Step("get user With Empty Email Field credentials properties")
    public User userWithEmptyEmailField() {
        return new User("", property.getProperty("temporaryPass"));
    }
    @Step("get   user With Empty Password Field credentials properties")
    public User userWithEmptyPasswordField() {
        return new User(property.getProperty("temporaryLoginName"), "");
    }
    @Step("get user Credentials With Invalid Password ")
    public User userCredentialsWithInvalidPassword() {
        return new User(
                property.getProperty("temporaryLoginName"),
                property.getProperty("validIncorrectPassword"));
    }
    @Step("get user Credentials For Registration")
    public User userCredentialsForRegistration() {
        return new User(
                property.getProperty("nameForRegistration"),
                property.getProperty("emailForRegistration").replace("@", getRandom() + "@"),
                property.getProperty("passwordForRegistration"),
                property.getProperty("passwordForRegistration"));
    }
    @Step("get invalid Pass Uppercase User Creds credentials properties")
    public User invalidPassUppercaseUserCreds() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"),
                property.getProperty("invalidPassUppercase"),
                property.getProperty("invalidPassUppercase"));
    }
    @Step("get invalid Pass Digit User credentials properties")
    public User invalidPassDigitUserCreds() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"),
                property.getProperty("invalidPassDigit"),
                property.getProperty("invalidPassDigit"));
    }
    @Step("get invalid Pass Lowercase User credentials properties")
    public User invalidPassLowercaseUserCreds() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"),
                property.getProperty("invalidPassLowercase"),
                property.getProperty("invalidPassLowercase"));
    }
    @Step("get invalid Pass Spec Char User credentials properties")
    public User invalidPassSpecCharUserCreds() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"),
                property.getProperty("invalidPassSpecChar"),
                property.getProperty("invalidPassSpecChar"));
    }
    @Step("get invalid Pass Length User credentials properties")
    public User invalidPassLengthUserCreds() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"),
                property.getProperty("invalidPassLength"),
                property.getProperty("invalidPassLength"));
    }
    @Step("get invalid Pass Space User credentials properties")
    public User invalidPassSpaceUserCreds() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"),
                property.getProperty("invalidPassSpace"),
                property.getProperty("invalidPassSpace"));
    }
    @Step("get   random number")
    private String getRandom() {
        return String.format("%s, %d", "+", (int) (Math.random() * ((Integer.MAX_VALUE - 1) - 10 + 1) + 1))
                .replaceAll("\\s+", "")
                .replace("-", "")
                .replace(",", "");
    }
}
