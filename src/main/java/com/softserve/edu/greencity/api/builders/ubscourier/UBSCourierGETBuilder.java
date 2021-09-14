package com.softserve.edu.greencity.api.builders.ubscourier;

import com.softserve.edu.greencity.api.models.ubscourier.UBSCourierGETModeldto;

public class UBSCourierGETBuilder {
    private final UBSCourierGETModeldto ubsCourier;

    public UBSCourierGETBuilder() {
        ubsCourier = new UBSCourierGETModeldto();
    }

    public static UBSCourierGETBuilder ubsCourierWith() {
        return new UBSCourierGETBuilder();
    }

    public UBSCourierGETBuilder id(long id) {
        ubsCourier.id = id;
        return this;
    }

    public UBSCourierGETBuilder email(String email) {
        ubsCourier.email = email;
        return this;
    }

    public UBSCourierGETBuilder firstName(String firstName) {
        ubsCourier.firstName = firstName;
        return this;
    }

    public UBSCourierGETBuilder lastName(String lastName) {
        ubsCourier.lastName = lastName;
        return this;
    }

    public UBSCourierGETBuilder phoneNumber(String phoneNumber) {
        ubsCourier.phoneNumber = phoneNumber;
        return this;
    }

    public UBSCourierGETBuilder addressComment(String addressComment) {
        ubsCourier.addressComment = addressComment;
        return this;
    }

    public UBSCourierGETModeldto build() {
        return this.ubsCourier;
    }


}
