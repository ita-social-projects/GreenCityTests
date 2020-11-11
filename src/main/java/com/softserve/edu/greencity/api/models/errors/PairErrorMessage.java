package com.softserve.edu.greencity.api.models.errors;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PairErrorMessage extends ErrorMessage {

    public String name;

    @Override
    public boolean equals(Object msg) {
        if (msg == this) {
            return true;
        }
        if (!(msg instanceof PairErrorMessage)) {
            return false;
        }
        PairErrorMessage m = (PairErrorMessage) msg;
        return  name.equals(m.name) && message.equals(m.message);
    }

    public PairErrorMessage(String name, String message) {
        super(message);
        this.name = name;
    }

    public PairErrorMessage() {
        super();
    }


}
