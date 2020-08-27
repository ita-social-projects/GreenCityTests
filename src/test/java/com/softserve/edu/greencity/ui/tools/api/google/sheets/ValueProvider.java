package com.softserve.edu.greencity.ui.tools.api.google.sheets;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static com.softserve.edu.greencity.ui.tools.api.google.sheets.GoogleSheet.getRow;

public class ValueProvider {

    public Object getGridIp() throws IOException, GeneralSecurityException {
        return getRow(24).get(1);
    }
}
