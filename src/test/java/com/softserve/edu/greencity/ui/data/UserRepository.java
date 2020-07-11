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

    public User getDefault() {
        return temporary();
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
        return new User(property.getProperty("googleLogin"), property.getProperty("googlePass"));
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
                property.getProperty("defaultPass")+"!",
                property.getProperty("defaultConfirmPass"));
    }

    public User userCredentialsForRegistration() {
        return new User(Randomizer.getRamdomString20Letters(), property.getProperty("emailForRegistration"),
                property.getProperty("passwordForRegistration"), property.getProperty("passwordForRegistration"));
    }

    public User invalidPassUppercaseUserCreds() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"),
                "liubaliuba1*",
                "liubaliuba1*");
                //property.getProperty("defaultPass")+"!",
                //property.getProperty("defaultConfirmPass"));
    }

    public User invalidPassDigitUserCreds() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"),
                "liubaLiuba*",
                "liubaLiuba*");
                //property.getProperty("defaultPass")+"!",
               // property.getProperty("defaultConfirmPass"));
    }

    public User invalidPassLowercaseUserCreds() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"),
                "LIUBALIUBA1*",
                "LIUBALIUBA1*");
                //property.getProperty("defaultPass")+"!",
                //property.getProperty("defaultConfirmPass"));
    }

    public User invalidPassSpecCharUserCreds() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"),
                "liubaLiuba1",
                "liubaLiuba1");
                //property.getProperty("defaultPass")+"!",
                //property.getProperty("defaultConfirmPass"));
    }

    public User invalidPassLengthUserCreds() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"),
                "Liuba1*",
                "Liuba1*");
                //property.getProperty("defaultPass")+"!",
                //property.getProperty("defaultConfirmPass"));
    }

    public User invalidPassSpaceUserCreds() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultEmail"),
                "liubaLiuba 1*",
                "liubaLiuba 1*");
                //property.getProperty("defaultPass")+"!",
                //property.getProperty("defaultConfirmPass"));
    }
}
