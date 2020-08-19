package com.softserve.edu.greencity.ui.tools;


import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Properties;

public class CredentialProperties {
    public void checkCredentialsExist(){
        if (!isCredentialsPropertiesExist())
            setCredentialsProperties();
    }
    private Properties properties = new Properties();
    @SneakyThrows(IOException.class)
    private void setCredentialsProperties() {
        properties.setProperty("temporaryLoginName","greencitypavel@gmail.com");
        properties.setProperty("temporaryPass","bRDYBhAs3z48Y5H-");
        properties.setProperty("defaultName","Pavel");
        properties.setProperty("defaultEmail","greencitypavel@gmail.com");
        properties.setProperty("defaultPass","bRDYBhAs3z48Y5H-");
        properties.setProperty("invalidName","s");
        properties.setProperty("invalidEmail","asf");
        properties.setProperty("invalidPass","as2f");
        properties.setProperty("invalidPass","as2f");
        properties.setProperty("validUnregisterEmail","greencitypavel+"+getRandom()+"@gmail.com");
        properties.setProperty("emailForRegistration","greencitypavel+"+getRandom()+"@gmail.com");
        properties.setProperty("passwordForRegistration","bRDYBhAs3z48Y5H-");
        properties.setProperty("invalidEmail","asf");
        properties.setProperty("invalidPassUppercase","QWERTYASDFG-");
        properties.setProperty("invalidPassDigit","12345678-");
        properties.setProperty("invalidPassLowercase","qwertyasdfg-");
        properties.setProperty("invalidPassSpecChar","bRDYBhAs3 z48Y5H-");
        properties.setProperty("invalidPassLength","aA-");
        properties.setProperty("invalidPassSpace","          ");
        properties.setProperty("validIncorrectPassword","As3z48Y5H-bRDYBh");

        FileOutputStream out = new FileOutputStream("src/test/resources/credentials.properties");
        properties.store(out,"comment");   }



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
