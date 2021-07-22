package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.UBS.AddressesListTexts;
import org.testng.annotations.DataProvider;

public class DataProviders_UBS {
    @DataProvider(name = "absenceOfAddressesMessages")
    public Object[][] absenceOfAddressesMessages()
    {
        return new Object[][]
                {
                        {"en", AddressesListTexts.ABSENCE_OF_ADDRESSES_MESSAGE_EN.getText()},
                        {"ua", AddressesListTexts.ABSENCE_OF_ADDRESSES_MESSAGE_UA.getText()},
                        {"ru", AddressesListTexts.ABSENCE_OF_ADDRESSES_MESSAGE_RU.getText()},
                };
    }
}
