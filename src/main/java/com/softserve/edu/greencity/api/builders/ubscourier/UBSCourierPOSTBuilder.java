package com.softserve.edu.greencity.api.builders.ubscourier;

import com.softserve.edu.greencity.api.models.ubscourier.Bag;
import com.softserve.edu.greencity.api.models.ubscourier.PersonalData;
import com.softserve.edu.greencity.api.models.ubscourier.UBSCourierPOSTModeldto;

public class UBSCourierPOSTBuilder {
    private final UBSCourierPOSTModeldto ubsCourier;

    private UBSCourierPOSTBuilder() {
        ubsCourier = new UBSCourierPOSTModeldto();
    }

    public static UBSCourierPOSTBuilder ubsCourierWith() {
        return new UBSCourierPOSTBuilder();
    }

    public UBSCourierPOSTBuilder pointsToUse(long pointsToUse) {
        ubsCourier.pointsToUse = pointsToUse;
        return this;
    }

    public UBSCourierPOSTBuilder orderComment(String orderComment) {
        ubsCourier.orderComment = orderComment;
        return this;
    }

    public UBSCourierPOSTBuilder addressID(Long addressID) {
        ubsCourier.addressID = addressID;
        return this;
    }

    public UBSCourierPOSTBuilder additionalOrders(String[] additionalOrders) {
        ubsCourier.additionalOrders = additionalOrders;
        return this;
    }

    public UBSCourierPOSTBuilder certificates(String[] certificates) {
        ubsCourier.certificates = certificates;
        return this;
    }

    public UBSCourierPOSTBuilder bags(Bag[] bags) {
        ubsCourier.bags = bags;
        return this;
    }

    public UBSCourierPOSTBuilder personalData(PersonalData personalData) {
        ubsCourier.personalData = personalData;
        return this;
    }

    public UBSCourierPOSTModeldto build() {
        return this.ubsCourier;
    }


}


