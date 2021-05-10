package com.softserve.edu.greencity.ui.tests.editprofile;

public enum EditProfileTexts {
    //Pop up element which appears when user click 'Delete' icon near the social network link
    DELETE_SOCIAL_NETWORK_POP_UP_TEXT_EN("Are you sure that you want to remove this social network link from your profile?"),
    DELETE_SOCIAL_NETWORK_POP_UP_TEXT_RU("Вы уверены, что хотите удалить эту ссылку на соцсеть со своего профиля?"),
    DELETE_SOCIAL_NETWORK_POP_UP_TEXT_UA("Ви впевнені, що хочете видалити це посилання на соцмережу зі свого профілю?"),

    CANCEL_DELETING_SOCIAL_NETWORK_BUTTON_TEXT_EN("Cancel"),
    CANCEL_DELETING_SOCIAL_NETWORK_BUTTON_TEXT_RU("Отменить"),
    CANCEL_DELETING_SOCIAL_NETWORK_BUTTON_TEXT_UA("Скасувати"),

    YES_DELETE_SOCIAL_NETWORK_BUTTON_TEXT_EN("Yes"),
    YES_DELETE_SOCIAL_NETWORK_BUTTON_TEXT_RU("Да"),
    YES_DELETE_SOCIAL_NETWORK_BUTTON_TEXT_UA("Так"),

    INVALID_LINK_ERROR("This link has been already added"),
    INVALID_LINK_ERROR_COLOR("#f03127")
    ;

    private final String text;

    EditProfileTexts(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
