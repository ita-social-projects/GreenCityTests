package com.softserve.edu.greencity.data.UBS;

public enum UBSDataStrings {
    ORDER_COMMENT("Над нами ментори кружили. Спостерігали кожен день, чи з головою ми дружили,чи не творили єрундєнь."),
    CORRECT_CERTIFICATE_MESSAGE_ENG("Certificate for 1000 UAH activated. Certificate validity period is up to 2021-09-17"),
    FOUR_DIGITS("2222-");
    private final String string;

    UBSDataStrings(String str) {
        this.string = str;
    }
    public String getMessage(){return string;}
}
