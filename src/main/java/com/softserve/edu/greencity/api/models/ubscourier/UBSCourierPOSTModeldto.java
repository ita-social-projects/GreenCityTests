package com.softserve.edu.greencity.api.models.ubscourier;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UBSCourierPOSTModeldto {

    public String[] additionalOrders;
    public Long addressId;
    public Bag[] bags;
    public String[] certificates;
    public String orderComment;
    public PersonalData personalData;
    public Long pointsToUse;

    public UBSCourierPOSTModeldto() {
        additionalOrders = new String[0];
        addressId = 0L;
        bags = new Bag[0];
        certificates = new String[0];
        orderComment = "";
        personalData = null;
        pointsToUse = 0L;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        String objAsJson = null;
        try {
            objAsJson = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return objAsJson;
    }
}
