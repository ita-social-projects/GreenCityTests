package com.softserve.edu.greencity.data.UBS;

public enum UBSDataStrings {
    ORDER_COMMENT("Над нами ментори кружили. Спостерігали кожен день, чи з головою ми дружили,чи не творили єрундєнь."),
    ADDRES_COMMENT("Ремонтується дорога. Під'їзд до будинку доступний зі сторони будинку номер 15"),
    CANCEL_UA("Відмінити"),
    CANCEL_RU("Отменить"),
    CANCEL_ENG("Cancel"),
    BONUS_500_CERTIFICATE_MESSAGE_ENG("A certificate for 1000 UAH is activated in excess of the amount of your order. The balance of 500 UAH will be credited to your " +
            "bonus account for the next order. The certificate is valid until 2022-09-17"),
    CORRECT_500_CERTIFICATE_MESSAGE_ENG("Certificate for 500 UAH activated. Certificate validity period is up to 2021-11-27"),
    FOUR_DIGITS("2222-"),
    CORRECT_CERTIFICATE_THREE_ACTIVE("Certificate for 1800 UAH activated. Certificate validity period is up to 2021-11-27"),
    ORDER_NUMBER_ONE("1111111111"),
    ORDER_NUMBER_TWO("2222222222"),
    ADDRESS_STREET_INFO_MESSAGE("This field is required"),
    ADDRESS_CITY_INFO_MESSAGE("At the moment we serve only the city of Kiev"),
    PERSONAL_DATA_NAME("Lina"),
    PERSONAL_DATA_SURNAME("Serhova"),
    PERSONAL_DATA_PHONE("0961111111"),
    PERSONAL_DATA_EMAIL("dcghkv@gmail.com"),
    PERSONAL_DATA_NAME_SURNAME("Lina Serhova");

    private final String string;

    UBSDataStrings(String str) {
        this.string = str;
    }

    public String getMessage() {
        return string;
    }
}
