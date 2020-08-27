package com.softserve.edu.greencity.ui.tools.api.google.sheets;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Arrays;
import java.util.logging.Level;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

public class GoogleSheet {
    private static Sheets sheetsService;
    private static String APPLICATION_NAME = "OAuth client";
    private static String SPREADSHEET_ID = "165DiBh-2TKxIHPtfBTDJ_GBq8kgal4Ac5vRlbaUC6O4";

    private static Credential authorize() throws IOException, GeneralSecurityException{
        java.util.logging.Logger.getLogger("com.google.api.**").setLevel(Level.OFF);
        InputStream in = GoogleSheet.class.getResourceAsStream("/client_secret.json");
        GoogleClientSecrets clientSecrets =  GoogleClientSecrets.load (
                JacksonFactory.getDefaultInstance(), new InputStreamReader(in)
        );

        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(),
                clientSecrets, scopes)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens") ))
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver())
                .authorize("user");
        return credential;
    }
 public static Sheets  getSheetsService() throws IOException, GeneralSecurityException {
        Credential credential = authorize();
        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(),credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
 }

    public static List<List<Object>> values() throws IOException, GeneralSecurityException {
        sheetsService  = getSheetsService();
        String range = "A2:B10";
        ValueRange response = sheetsService.spreadsheets().values()
                .get(SPREADSHEET_ID,range)
                .execute();
        List< List <Object> > values = response.getValues();

        if (values == null || values.isEmpty()){

            System.out.println("empty value");
        } else {
            return values;
        }

        return values;
    }

    public static List<Object> getRow(int row) throws IOException, GeneralSecurityException {
        sheetsService  = getSheetsService();
        String range = "A1:B25";
        ValueRange response = sheetsService.spreadsheets().values()
                .get(SPREADSHEET_ID,range)
                .execute();
        List< List <Object> > values = response.getValues();

        if (values == null || values.isEmpty()){

            System.out.println("empty value");
        } else {
                return values.get(row) ;
        }
        return null;
    }
}
/*
Це в класі GreencityTestRunner  в методі beforeSuite()
System.setProperty("webdriver.chrome.silentOutput", "true");
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        // System.setProperty("webdriver.chrome.verboseLogging", "false");
         System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");

        java.util.logging.Logger.getLogger("org.openqa.selenium.remote").setLevel(Level.OFF);

але все одно, ще один запис висвітлюється
* */