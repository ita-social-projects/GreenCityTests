package com.softserve.edu.greencity.api.builders.ownsecurity;

import com.softserve.edu.greencity.api.models.ownsecurity.OwnSecurityModel;

/**
 * Gives a possibility to get a new OwnSecurityModel with desired properties
 * For example:
 *          OwnSecurityModel ownSecurity =
 *                  OwnSecurityBuilder.ownSecurityWith().name("Vasia").userId(1).build();
 */
public class OwnSecurityBuilder {
    private OwnSecurityModel ownSecurity;

    public OwnSecurityBuilder() {
        ownSecurity = new OwnSecurityModel();
    }

    /**
     * Starts building
     * @return A builder with the possibility to set properties
     */
    public static OwnSecurityBuilder ownSecurityWith() {
        return new OwnSecurityBuilder();
    }

    public OwnSecurityBuilder accessToken(String accessToken) {
        ownSecurity.accessToken = accessToken;
        return this;
    }

    public OwnSecurityBuilder name(String name) {
        ownSecurity.name = name;
        return this;
    }

    public OwnSecurityBuilder ownRegistrations(Boolean ownRegistrations) {
        ownSecurity.ownRegistrations = ownRegistrations;
        return this;
    }

    public OwnSecurityBuilder refreshToken(String refreshToken) {
        ownSecurity.refreshToken = refreshToken;
        return this;
    }

    public OwnSecurityBuilder userId(Integer userId) {
        ownSecurity.userId = userId;
        return this;
    }

    /**
     * Final method
     * @return The constructed OwnSecurityModel.
     * If it wasn't constructed, then returns default OwnSecurityModel.
     */
    public OwnSecurityModel build() {
        return ownSecurity;
    }

}
