package com.softserve.edu.greencity.api.models.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {

    public String message;

    @Override
    public boolean equals(Object msg) {
        if (msg == this) {
            return true;
        }
        if (!(msg instanceof ErrorMessage)) {
            return false;
        }
        ErrorMessage m = (ErrorMessage) msg;
        return message.equals(m.message);
    }

    public ErrorMessage(String message) {
        this.message = message;
    }

    public ErrorMessage() {}

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
