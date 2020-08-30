package com.softserve.edu.greencity.ui.tools.api.google.sheets;

import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static com.softserve.edu.greencity.ui.tools.api.google.sheets.GoogleSheet.getRow;

public class ValueProvider {

    public static String getinvalidPassDigit() throws IOException, GeneralSecurityException {
        return (String) getRow(1).get(1);
    }
    public static String getdefaultName() throws IOException, GeneralSecurityException {
        return (String) getRow(2).get(1);
    }
    public static String getemailForRegistration() throws IOException, GeneralSecurityException {
        return (String) getRow(3).get(1);
    }
    public static String getdefaultPass () throws IOException, GeneralSecurityException {
        return (String) getRow(4).get(1);
    }
    public static String gettemporaryPass() throws IOException, GeneralSecurityException {
        return (String) getRow(5).get(1);
    }
    public static String getinvalidName() throws IOException, GeneralSecurityException {
        return (String) getRow(6).get(1);
    }
    public static String getinvalidPassLowercase() throws IOException, GeneralSecurityException {
        return (String) getRow(7).get(1);
    }
    public static String getnameForRegistration() throws IOException, GeneralSecurityException {
        return (String) getRow(8).get(1);
    }
    public static String getinvalidPass() throws IOException, GeneralSecurityException {
        return (String) getRow(9).get(1);
    }
    public static String getinvalidPassSpecChar() throws IOException, GeneralSecurityException {
        return (String) getRow(10).get(1);
    }
    public static String getinvalidPassSpace() throws IOException, GeneralSecurityException {
        return (String) getRow(11).get(1);
    }
    public static String getinvalidPassLength() throws IOException, GeneralSecurityException {
        return (String) getRow(12).get(1);
    }
    public static String getcomfTemporaryPass() throws IOException, GeneralSecurityException {
        return (String) getRow(13).get(1);
    }
    public static String getgoogleEmail() throws IOException, GeneralSecurityException {
        return (String) getRow(14).get(1);
    }
   @NonNull
   public static String getgooglePass () throws IOException, GeneralSecurityException {
        return (String) getRow(15).get(1);
    }
    public static String getinvalidPassUppercase() throws IOException, GeneralSecurityException {
        return (String) getRow(16).get(1);
    }
    public static String getvalidIncorrectPassword() throws IOException, GeneralSecurityException {
        return (String) getRow(17).get(1);
    }
    public static String gettemporaryLoginName() throws IOException, GeneralSecurityException {
        return (String) getRow(18).get(1);
    }
    public static String getdefaultEmail() throws IOException, GeneralSecurityException {
        return (String) getRow(19).get(1);
    }
    public static String getinvalidEmail() throws IOException, GeneralSecurityException {
        return (String) getRow(20).get(1);
    }
    public static String getpasswordForRegistration() throws IOException, GeneralSecurityException {
        return (String) getRow(21).get(1);
    }
    public static String getvalidUnregisterEmail() throws IOException, GeneralSecurityException {
        return (String) getRow(22).get(1);
    }
    public static String getGridIp() throws IOException, GeneralSecurityException {
        return (String) getRow(23).get(1);
    }
    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("<===============================================================>");
        System.out.println(getinvalidPassDigit());
        System.out.println("<===============================================================>");
        System.out.println(getdefaultName());
        System.out.println("<===============================================================>");
        System.out.println(getemailForRegistration());
        System.out.println("<===============================================================>");
        System.out.println(getdefaultPass());
        System.out.println("<===============================================================>");
        System.out.println(gettemporaryPass());
        System.out.println("<===============================================================>");
        System.out.println(getinvalidName());
        System.out.println("<===============================================================>");
        System.out.println(getinvalidPassLowercase());
        System.out.println("<===============================================================>");
        System.out.println(getnameForRegistration());
        System.out.println("<===============================================================>");
        System.out.println(getinvalidPass());
        System.out.println("<===============================================================>");
        System.out.println(getinvalidPassSpecChar());
        System.out.println("<===============================================================>");
        System.out.println(getinvalidPassSpace());
        System.out.println("<===============================================================>");
        System.out.println(getinvalidPassLength());
        System.out.println("<===============================================================>");
        System.out.println(getcomfTemporaryPass());
        System.out.println("<===============================================================>");
        System.out.println(getgoogleEmail());
        System.out.println("<===============================================================>");
        System.out.println(getgooglePass());
        System.out.println("<===============================================================>");
        System.out.println(getinvalidPassUppercase());
        System.out.println("<===============================================================>");
        System.out.println(getvalidIncorrectPassword());
        System.out.println("<===============================================================>");
        System.out.println(gettemporaryLoginName());
        System.out.println("<===============================================================>");
        System.out.println(getdefaultEmail());
        System.out.println("<===============================================================>");
        System.out.println(getinvalidEmail());
        System.out.println("<===============================================================>");
        System.out.println(getpasswordForRegistration());
        System.out.println("<===============================================================>");
        System.out.println(getvalidUnregisterEmail());
        System.out.println("<===============================================================>");
        System.out.println(getGridIp());
        System.out.println("<===============================================================>");
    }
}
