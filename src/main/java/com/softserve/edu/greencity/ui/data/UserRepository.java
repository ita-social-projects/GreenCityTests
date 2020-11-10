package com.softserve.edu.greencity.ui.data;

import lombok.SneakyThrows;

import static com.softserve.edu.greencity.ui.api.google.sheets.ValueProvider.*;

import java.util.Properties;

public final class UserRepository {
    private static volatile UserRepository instance = null;
    private Properties property = new Properties();

    private UserRepository() {

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

    @SneakyThrows
    public User temporary() {
        return new User(
                (String) gettemporaryLoginName(),
                (String) gettemporaryPass());
    }

    @SneakyThrows
    public User defaultUserCredentials() {
        return new User(
                (String) getdefaultName(),
                (String) getdefaultEmail(),
                (String) getdefaultPass(),
                (String) getdefaultPass());
    }

    @SneakyThrows
    public User googleUserCredentials() {
        return new User((String) getgoogleEmail(), (String) getgooglePass());
    }

    public User emptyUserCredentials() {
        return new User("", "", "", "");
    }

    @SneakyThrows
    public User invalidUserCredentials() {
        return new User(
                getinvalidName(),
                getinvalidEmail(),
                getinvalidPass(),
                getinvalidPass());
    }

    @SneakyThrows
    public User invalidNameCredentials() {
        return new User(
                getinvalidName(),
                getdefaultEmail(),
                getdefaultPass(),
                getdefaultPass());
    }

    @SneakyThrows
    public User unregisterUser() {
        return new User(
                getdefaultName(),
                getvalidUnregisterEmail(),
                getUnregisterEmailPassword(),
                getComfUnregisterEmailPassword());
    }

    @SneakyThrows
    public User invalidEmailUserCredentials() {
        return new User(
                getdefaultName(),
                getinvalidEmail(),
                getdefaultPass(),
                getdefaultPass());
    }

    @SneakyThrows
    public User invalidConfirmPassCredentials() {
        return new User(
                getdefaultName(),
                getdefaultEmail(),
                getdefaultPass() + "!",
                getdefaultPass());
    }

    @SneakyThrows
    public User userWithEmptyEmailField() {
        return new User("", (String) gettemporaryPass());
    }

    @SneakyThrows
    public User userWithEmptyPasswordField() {
        return new User(gettemporaryLoginName(), "");
    }

    @SneakyThrows
    public User userCredentialsWithInvalidPassword() {
        return new User(
                gettemporaryLoginName(),
                getvalidIncorrectPassword());
    }

    @SneakyThrows
    public User userCredentialsForRegistration() {
        return new User(
                getnameForRegistration(),
                getemailForRegistration().replace("@", getRandom() + "@"),
                getpasswordForRegistration(),
                getpasswordForRegistration());
    }

    @SneakyThrows
    public User invalidPassUppercaseUserCreds() {
        return new User(
                getdefaultName(),
                getdefaultEmail(),
                getinvalidPassUppercase(),
                getinvalidPassUppercase());
    }

    @SneakyThrows
    public User invalidPassDigitUserCreds() {
        return new User(
                getdefaultName(),
                getdefaultEmail(),
                getinvalidPassDigit(),
                getinvalidPassDigit());
    }

    @SneakyThrows
    public User invalidPassLowercaseUserCreds() {
        return new User(
                getdefaultName(),
                getdefaultEmail(),
                getinvalidPassLowercase(),
                getinvalidPassLowercase());
    }

    @SneakyThrows
    public User invalidPassSpecCharUserCreds() {
        return new User(
                getdefaultName(),
                getdefaultEmail(),
                getinvalidPassSpecChar(),
                getinvalidPassSpecChar());
    }

    @SneakyThrows
    public User invalidPassLengthUserCreds() {
        return new User(
                getdefaultName(),
                getdefaultEmail(),
                getinvalidPassLength(),
                getinvalidPassLength());
    }

    @SneakyThrows
    public User invalidPassSpaceUserCreds() {
        return new User(
                getdefaultName(),
                getdefaultEmail(),
                getinvalidPassSpace(),
                getinvalidPassSpace());
    }

    private String getRandom() {
        return String.format("%s, %d", "+", (int) (Math.random() * ((Integer.MAX_VALUE - 1) - 10 + 1) + 1))
                .replaceAll("\\s+", "")
                .replace("-", "")
                .replace(",", "");
    }
}
