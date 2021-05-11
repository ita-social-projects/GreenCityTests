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
    INVALID_LINK_ERROR_COLOR("#f03127"),

    //Pop up element which appears when user click 'Edit Picture' icon under the profile photo
    EDIT_PICTURE_BUTTON_TEXT_EN("Edit Picture"),
    EDIT_PICTURE_BUTTON_TEXT_UA("Змінити Фотографію"),
    EDIT_PICTURE_BUTTON_TEXT_RU("Изменить Изображение"),

    EDIT_PICTURE_POP_UP_QUESTION_TEXT_EN("Do you want to change your profile picture?"),
    EDIT_PICTURE_POP_UP_QUESTION_TEXT_UA("Ви хочете змінити свою фотографію профілю?"),
    EDIT_PICTURE_POP_UP_QUESTION_TEXT_RU("Вы хотите изменить изображение своего профиля?"),

    CANCEL_EDITING_PROFILE_PHOTO_BUTTON_TEXT_EN("Cancel"),
    CANCEL_EDITING_PROFILE_PHOTO_BUTTON_TEXT_RU("Скасувати"),
    CANCEL_EDITING_PROFILE_PHOTO_BUTTON_TEXT_UA("Отменить"),

    DELETING_PROFILE_PHOTO_BUTTON_TEXT_EN("Delete photo"),
    DELETING_PROFILE_PHOTO_BUTTON_TEXT_UA("Видалити фото"),
    DELETING_PROFILE_PHOTO_BUTTON_TEXT_RU("Удалить фото"),

    UPLOAD_NEW_PROFILE_PHOTO_BUTTON_TEXT_EN("Upload new photo"),
    UPLOAD_NEW_PROFILE_PHOTO_BUTTON_TEXT_UA("Завантажити нове фото"),
    UPLOAD_NEW_PROFILE_PHOTO_BUTTON_TEXT_RU("Загрузить новое фото"),
    ;

    private final String text;

    EditProfileTexts(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
