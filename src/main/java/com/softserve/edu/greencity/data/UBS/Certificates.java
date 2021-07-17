package com.softserve.edu.greencity.data.UBS;

public enum Certificates {
    ACTIVE_1000("22222222"),
    ACTIVE_100("5554-3247"),
    ACTIVE_500("3999-0001"),
    ACTIVE_300("5554-3248"),
    EXPIRED_1000("11111111"),
    USED_1000("1111-9994"),
    USED_500("2222-5555"),
    USED_300("3000-3000");
    private final String number;

    Certificates(String numb) {
        this.number = numb;
    }

    public String getCertificate() {
        return number;
    }
}
