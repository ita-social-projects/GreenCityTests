package com.softserve.edu.greencity.api.builders.ubscourier;

import com.softserve.edu.greencity.api.models.ubscourier.UBSPersonalDataModel;

public class UBSPersonalDataBuilder {
    private final UBSPersonalDataModel ubsCourier;

    public UBSPersonalDataBuilder() {
        ubsCourier = new UBSPersonalDataModel();
    }

    public static UBSPersonalDataBuilder ubsCourierWith() {
        return new UBSPersonalDataBuilder();
    }

    public UBSPersonalDataBuilder id(long id) {
        ubsCourier.id = id;
        return this;
    }
    public UBSPersonalDataBuilder email(String email){
        ubsCourier.email=email;
        return this;
    }

    public UBSPersonalDataBuilder firstName(String firstName){
        ubsCourier.firstName=firstName;
        return this;
    }

    public UBSPersonalDataBuilder lastName(String lastName){
        ubsCourier.lastName=lastName;
        return this;
    }

    public UBSPersonalDataBuilder phoneNumber(String phoneNumber){
        ubsCourier.phoneNumber=phoneNumber;
        return this;
    }

    public UBSPersonalDataModel build() {
        return this.ubsCourier;
    }



}
