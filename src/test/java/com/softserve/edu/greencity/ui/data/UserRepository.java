package com.softserve.edu.greencity.ui.data;

import com.softserve.edu.greencity.ui.tools.Randomizer;

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

    public User temporary() {
        return new User(property.getProperty("temporaryLoginName"), property.getProperty("temporaryPass"));
    }

    public User defaultUserCredentials() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"),
                property.getProperty("defaultPass"),
                property.getProperty("defaultConfirmPass"));
    }

    public User googleUserCredentials() {
        return new User(property.getProperty("googleEmail"), property.getProperty("googlePass"));
    }

    public User emptyUserCredentials() {
        return new User("", "", "", "");
    }

    public User invalidUserCredentials() {
        return new User(
                property.getProperty("invalidName"),
                property.getProperty("invalidEmail"),
                property.getProperty("invalidPass"),
                property.getProperty("invalidConfirmPass"));
    }

    public User unregisterUser() {
        return new User(
                property.getProperty("validUnregisterEmail"),
                property.getProperty("temporaryPass"));
    }

    public User invalidEmailUserCredentials() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("invalidEmail"),
                property.getProperty("defaultPass"),
                property.getProperty("defaultPass"));
    }

    public User invalidConfirmPassCredentials() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"),
                property.getProperty("defaultPass") + "!",
                property.getProperty("defaultConfirmPass"));
    }

    public User userWithEmptyEmailField() {
        return new User("", property.getProperty("temporaryPass"));
    }

    public User userWithEmptyPasswordField() {
        return new User(property.getProperty("temporaryLoginName"), "");
    }

    public User userCredentialsWithInvalidPassword() {
        return new User(property.getProperty("temporaryLoginName"), property.getProperty("validIncorrectPassword"));
    }

    public User userCredentialsForRegistration() {
        return new User(Randomizer.getRamdomString20Letters(), property.getProperty("emailForRegistration"),
                property.getProperty("passwordForRegistration"), property.getProperty("passwordForRegistration"));
    }

    public User invalidPassUppercaseUserCreds() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"));
    }

    public User invalidPassDigitUserCreds() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"));
    }

    public User invalidPassLowercaseUserCreds() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"));
    }

    public User invalidPassSpecCharUserCreds() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"));
    }

    public User invalidPassLengthUserCreds() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"));
    }

    public User invalidPassSpaceUserCreds() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"));
    }
}
