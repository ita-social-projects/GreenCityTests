package com.softserve.edu.greencity.data.UBS;

public enum AddressesListTexts {
    ABSENCE_OF_ADDRESSES_MESSAGE_EN("You have no addresses added. Please add an address."),
    ABSENCE_OF_ADDRESSES_MESSAGE_UA("У Вас немає доданих адрес. Додайте, будь ласка, адресу."),
    ABSENCE_OF_ADDRESSES_MESSAGE_RU("У Вас нет дополнительных адресов. Добавьте, пожалуйста, адрес.");

    String text;

    AddressesListTexts(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
