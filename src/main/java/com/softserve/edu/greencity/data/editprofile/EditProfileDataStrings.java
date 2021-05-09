package com.softserve.edu.greencity.data.editprofile;

/**
 * Constant string for tests with edit profile.
 * Mostly used by EditProfileDataRepository,
 * and it is recommended to call its method rather than use this enum directly
 */
public enum EditProfileDataStrings {
    NAME("taqcTestName"),
    CITY("Lviv"),
    CREDO("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "),
    SOCIAL_NETWORK_FACEBOOK_VALID("https://www.facebook.com/greencitysocialproject"),
    SOCIAL_NETWORK_FACEBOOK_VALID_GREENCITYTEST("https://facebook.com/greencitytest"),
    SOCIAL_NETWORK_INSTAGRAM_VALID("https://instagram.com/greencitysocialproject"),
    SOCIAL_NETWORK_TWITTER_VALID("https://twitter.com/?lang=uk"),
    SOCIAL_NETWORK_TIKTOK_VALID("https://www.tiktok.com/uk-UA/"),

    NAME_BY_DEFAULT("taqcUser"),
    CITY_BY_DEFAULT("Lviv"),
    CREDO_BY_DEFAULT("This is account created ny tagc command for testing")
    ;

    private final String str;

    EditProfileDataStrings(String str) {
        this.str = str;
    }

    public String getString() {
        return str;
    }

    @Override
    public String toString() {
        return str;
    }
}
