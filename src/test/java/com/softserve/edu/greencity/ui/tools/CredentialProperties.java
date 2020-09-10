package com.softserve.edu.greencity.ui.tools;


import com.softserve.edu.greencity.ui.tools.api.google.sheets.ValueProvider;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Properties;

public class CredentialProperties {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void checkCredentialsExist(){
        if (!isCredentialsPropertiesExist()){
            setCredentialsProperties();
            logger.warn("resources\\credentials.properties was absent, please enter your credentials to the new generated file. All passwords set ass global variable");
        }
    }
    private Properties properties = new Properties();
    @SneakyThrows(IOException.class)
    private void setCredentialsProperties() {
        properties.setProperty("nameForRegistration", ValueProvider.getnameForRegistration());
        properties.setProperty("emailForRegistration",ValueProvider.getemailForRegistration());

        properties.setProperty("defaultName",ValueProvider.getdefaultName());
        properties.setProperty("defaultEmail",ValueProvider.getdefaultEmail());
        properties.setProperty("DEFAULT_PASS",ValueProvider.getdefaultPass());
        properties.setProperty("comfTemporaryPass",ValueProvider.getcomfTemporaryPass());

        properties.setProperty("invalidName",ValueProvider.getinvalidName());
        properties.setProperty("invalidEmail",ValueProvider.getinvalidEmail());
        properties.setProperty("invalidPassLowercase",ValueProvider.getinvalidPassLowercase());
        properties.setProperty("invalidPass",ValueProvider.getinvalidPass());
        properties.setProperty("invalidPassDigit",ValueProvider.getinvalidPassDigit());
        properties.setProperty("invalidPassSpecChar",ValueProvider.getinvalidPassSpecChar());
        properties.setProperty("invalidPassSpace",ValueProvider.getinvalidPassSpace());
        properties.setProperty("invalidPassLength",ValueProvider.getinvalidPassLength());
        properties.setProperty("invalidPassUppercase",ValueProvider.getinvalidPassUppercase());

        properties.setProperty("temporaryLoginName","xdknxusqvjeovowpfk@awdrt.com");
        properties.setProperty("temporaryPass","Temp#001");

        properties.setProperty("validUnregisterEmail",ValueProvider.getvalidUnregisterEmail());
        properties.setProperty("passwordForRegistration",ValueProvider.getpasswordForRegistration());

        properties.setProperty("googleEmail",ValueProvider.getgoogleEmail());
        properties.setProperty("passwordForRegistration",ValueProvider.getdefaultPass());

        FileOutputStream out = new FileOutputStream("src/test/resources/credentials.properties");
        properties.store(out,"Please setup credentials");   }

    private String getRandom() {
        return String.format("%s, %d", "+", (int) (Math.random() * ((Integer.MAX_VALUE - 1) - 10 + 1) + 1))
                .replaceAll("\\s+", "")
                .replace("-", "")
                .replace(",", "");
    }

    private boolean isCredentialsPropertiesExist()  {
        return new File("src/test/resources/credentials.properties").isFile();
    }

}
