package com.softserve.edu.greencity.ui.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
//TODO replace personal data getter to System.getenv
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

    public User temporary() {
        return new User(
                property.getProperty("temporaryLoginName"),
                System.getenv().get(property.getProperty("temporaryPass")));
    }

    public User defaultUserCredentials() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"),
                System.getenv().get(property.getProperty("defaultPass")),
                System.getenv().get(property.getProperty("defaultPass")));
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
                property.getProperty("invalidPass"));
    }

    public User invalidNameCredentials() {
        return new User(
                property.getProperty("invalidName"),
                property.getProperty("defaultEmail"),
                System.getenv().get(property.getProperty("defaultPass")),
                System.getenv().get(property.getProperty("defaultPass")));
    }

    public User unregisterUser() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("validUnregisterEmail").replace("@",getRandom()+"@"),
                System.getenv().get(property.getProperty("temporaryPass")),
                property.getProperty("comfTemporaryPass"));
    }

    public User invalidEmailUserCredentials() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("invalidEmail"),
                System.getenv().get(property.getProperty("defaultPass")),
                System.getenv().get(property.getProperty("defaultPass")));
    }

    public User invalidConfirmPassCredentials() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"),
                System.getenv().get(property.getProperty("defaultPass")) + "!",
                System.getenv().get(property.getProperty("defaultPass")));
    }

    public User userWithEmptyEmailField() {
        return new User("", System.getenv().get(property.getProperty("temporaryPass")));
    }

    public User userWithEmptyPasswordField() {
        return new User(property.getProperty("temporaryLoginName"), "");
    }

    public User userCredentialsWithInvalidPassword() {
        return new User(
                property.getProperty("temporaryLoginName"),
                property.getProperty("validIncorrectPassword"));
    }

    public User userCredentialsForRegistration() {
        return new User(
                property.getProperty("nameForRegistration"),
                property.getProperty("emailForRegistration").replace("@", getRandom() + "@"),
                System.getenv().get(property.getProperty("passwordForRegistration")),
                System.getenv().get(property.getProperty("passwordForRegistration")));
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

    private String getRandom() {
        return String.format("%s, %d", "+", (int) (Math.random() * ((Integer.MAX_VALUE - 1) - 10 + 1) + 1))
                .replaceAll("\\s+", "")
                .replace("-", "")
                .replace(",", "");
    }
}
