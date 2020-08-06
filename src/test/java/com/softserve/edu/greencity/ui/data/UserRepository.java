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
        return new User(property.getProperty("temporaryEmail"), property.getProperty("temporaryPass"));
    }

    public User defaultUserCredentials() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"),
                property.getProperty("defaultPass"),
                property.getProperty("defaultPass"));
    }

    public User gMailUserCredentialsSignIn() {
        return new User(property.getProperty("gMailAddressSignIn"), property.getProperty("gMailPassSignIn"));
    }


    public User emptyUserCredentials() {
        return new User("", "", "", "");
    }

    public User invalidUserCredentials() {
        return new User(
                property.getProperty("invalidName"),
                property.getProperty("invalidEmail"),
                property.getProperty("invalidPass"),
                property.getProperty("invalidPass"));
    }

    public User invalidNameCredentials() {
        return new User(
                property.getProperty("invalidName"),
                property.getProperty("defaultEmail"),
                property.getProperty("defaultPass"),
                property.getProperty("defaultPass"));
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
                property.getProperty("defaultPass"));
    }

    public User userWithEmptyEmailField() {
        return new User("", property.getProperty("temporaryPass"));
    }

    public User userWithEmptyPasswordField() {
        return new User(property.getProperty("temporaryEmail"), "");
    }

    public User userCredentialsWithInvalidPassword() {
        return new User(property.getProperty("temporaryEmail"), property.getProperty("validUnregisterPassword"));
    }

    public User userCredentialsForRegistration() {
        return new User(Randomizer.getRamdomString20Letters(), property.getProperty("gMailAddressSignUP"),
                property.getProperty("passwordForRegistration"), property.getProperty("passwordForRegistration"));
    }

    public User invalidPassUppercaseUserCreds() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"),
                property.getProperty("invalidPassUppercase"),
                property.getProperty("invalidPassUppercase"));
    }

    public User invalidPassDigitUserCreds() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"),
                property.getProperty("invalidPassDigit"),
                property.getProperty("invalidPassDigit"));
    }

    public User invalidPassLowercaseUserCreds() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"),
                property.getProperty("invalidPassLowercase"),
                property.getProperty("invalidPassLowercase"));
    }

    public User invalidPassSpecCharUserCreds() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"),
                property.getProperty("invalidPassSpecChar"),
                property.getProperty("invalidPassSpecChar"));
    }

    public User invalidPassLengthUserCreds() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"),
                property.getProperty("invalidPassLength"),
                property.getProperty("invalidPassLength"));
    }

    public User invalidPassSpaceUserCreds() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"),
                property.getProperty("invalidPassSpace"),
                property.getProperty("invalidPassSpace"));
    }
}
