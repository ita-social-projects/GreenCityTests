package com.softserve.edu.greencity.data.UBS;

import com.softserve.edu.greencity.data.users.UserRepository;

import static com.softserve.edu.greencity.data.UBS.UBSDataStrings.*;

public class UBSPersonalDataRepository {

    private static volatile UBSPersonalDataRepository instance = null;

    private UBSPersonalDataRepository() {
    }

    public static UBSPersonalData getDefault() {
        return get().getRequiredDefaultFieldsPersonalData();
    }

    public static UBSPersonalDataRepository get() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UBSPersonalDataRepository();
                }
            }
        }
        return instance;
    }

    public UBSPersonalData getRequiredDefaultFieldsPersonalData() {
        return new UBSPersonalData(PERSONAL_DATA_NAME.getMessage(), PERSONAL_DATA_SURNAME.getMessage(), PERSONAL_DATA_EMAIL.getMessage(), PERSONAL_DATA_PHONE.getMessage());
    }
}
