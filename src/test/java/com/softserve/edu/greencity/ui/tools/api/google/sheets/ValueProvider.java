package com.softserve.edu.greencity.ui.tools.api.google.sheets;

import lombok.SneakyThrows;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static com.softserve.edu.greencity.ui.tools.api.google.sheets.GoogleSheet.getRow;

public class ValueProvider {

    public Object getinvalidPassDigit() throws IOException, GeneralSecurityException {
        return getRow(1).get(1);
    }
    public Object getdefaultName() throws IOException, GeneralSecurityException {
        return getRow(2).get(1);
    }
    public Object getemailForRegistration() throws IOException, GeneralSecurityException {
        return getRow(3).get(1);
    }
    public Object getdefaultPass () throws IOException, GeneralSecurityException {
        return getRow(4).get(1);
    }
    public Object gettemporaryPass() throws IOException, GeneralSecurityException {
        return getRow(5).get(1);
    }
    public Object getinvalidName() throws IOException, GeneralSecurityException {
        return getRow(6).get(1);
    }
    public Object getinvalidPassLowercase() throws IOException, GeneralSecurityException {
        return getRow(7).get(1);
    }
    public Object getnameForRegistration() throws IOException, GeneralSecurityException {
        return getRow(8).get(1);
    }
    public Object getinvalidPass() throws IOException, GeneralSecurityException {
        return getRow(9).get(1);
    }
    public Object getinvalidPassSpecChar() throws IOException, GeneralSecurityException {
        return getRow(10).get(1);
    }
    public Object getinvalidPassSpace() throws IOException, GeneralSecurityException {
        return getRow(11).get(1);
    }
    public Object getinvalidPassLength() throws IOException, GeneralSecurityException {
        return getRow(12).get(1);
    }
    public Object getcomfTemporaryPass() throws IOException, GeneralSecurityException {
        return getRow(13).get(1);
    }
    public Object getgoogleEmail() throws IOException, GeneralSecurityException {
        return getRow(14).get(1);
    }
    public Object getgooglePass () throws IOException, GeneralSecurityException {
        return getRow(15).get(1);
    }
    public Object getinvalidPassUppercase() throws IOException, GeneralSecurityException {
        return getRow(16).get(1);
    }
    public Object getvalidIncorrectPassword() throws IOException, GeneralSecurityException {
        return getRow(17).get(1);
    }
    public Object gettemporaryLoginName() throws IOException, GeneralSecurityException {
        return getRow(18).get(1);
    }
    public Object getdefaultEmail() throws IOException, GeneralSecurityException {
        return getRow(19).get(1);
    }
    public Object getinvalidEmail() throws IOException, GeneralSecurityException {
        return getRow(20).get(1);
    }
    public Object getpasswordForRegistration() throws IOException, GeneralSecurityException {
        return getRow(21).get(1);
    }
    public Object getvalidUnregisterEmail() throws IOException, GeneralSecurityException {
        return getRow(22).get(1);
    }
    public Object getGridIp() throws IOException, GeneralSecurityException {
        return getRow(23).get(1);
    }
    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("<===============================================================>");
        System.out.println(new  ValueProvider().getinvalidPassDigit());
        System.out.println("<===============================================================>");
        System.out.println(new  ValueProvider().getdefaultName());
        System.out.println("<===============================================================>");
        System.out.println(new  ValueProvider().getemailForRegistration());
        System.out.println("<===============================================================>");
        System.out.println(new  ValueProvider().getdefaultPass());
        System.out.println("<===============================================================>");
        System.out.println(new  ValueProvider().gettemporaryPass());
        System.out.println("<===============================================================>");
        System.out.println(new  ValueProvider().getinvalidName());
        System.out.println("<===============================================================>");
        System.out.println(new  ValueProvider().getinvalidPassLowercase());
        System.out.println("<===============================================================>");
        System.out.println(new  ValueProvider().getnameForRegistration());
        System.out.println("<===============================================================>");
        System.out.println(new  ValueProvider().getinvalidPass());
        System.out.println("<===============================================================>");
        System.out.println(new  ValueProvider().getinvalidPassSpecChar());
        System.out.println("<===============================================================>");
        System.out.println(new  ValueProvider().getinvalidPassSpace());
        System.out.println("<===============================================================>");
        System.out.println(new  ValueProvider().getinvalidPassLength());
        System.out.println("<===============================================================>");
        System.out.println(new  ValueProvider().getcomfTemporaryPass());
        System.out.println("<===============================================================>");
        System.out.println(new  ValueProvider().getgoogleEmail());
        System.out.println("<===============================================================>");
        System.out.println(new  ValueProvider().getgooglePass());
        System.out.println("<===============================================================>");
        System.out.println(new  ValueProvider().getinvalidPassUppercase());
        System.out.println("<===============================================================>");
        System.out.println(new  ValueProvider().getvalidIncorrectPassword());
        System.out.println("<===============================================================>");
        System.out.println(new  ValueProvider().gettemporaryLoginName());
        System.out.println("<===============================================================>");
        System.out.println(new  ValueProvider().getdefaultEmail());
        System.out.println("<===============================================================>");
        System.out.println(new  ValueProvider().getinvalidEmail());
        System.out.println("<===============================================================>");
        System.out.println(new  ValueProvider().getpasswordForRegistration());
        System.out.println("<===============================================================>");
        System.out.println(new  ValueProvider().getvalidUnregisterEmail());
        System.out.println("<===============================================================>");
        System.out.println(new  ValueProvider().getGridIp());
        System.out.println("<===============================================================>");
    }
}
