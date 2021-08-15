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
        return new UBSPersonalData(API_USER_NAME.getMessage(), API_USER_SURNAME.getMessage(), API_USER_EMAIL.getMessage(), API_USER_PHONE.getMessage());
    }
}
