package com.softserve.edu.greencity.api.models.errors;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetailedErrorMessage extends ErrorMessage {

    public String timestamp;
    public Integer status;
    public String error;
    public String path;

    /**
     * Creates object without timestamp
     * @param status
     * @param error
     * @param message
     * @param path
     */
    public DetailedErrorMessage(Integer status, String error, String message, String path) {
        super(message);
        this.status = status;
        this.error = error;
        this.path = path;
    }

    public DetailedErrorMessage() {
        super();
    }

    @Override
    public boolean equals(Object msg) {
        if (msg == this) {
            return true;
        }
        if (!(msg instanceof DetailedErrorMessage)) {
            return false;
        }
        DetailedErrorMessage m = (DetailedErrorMessage) msg;
        return status.equals(m.status) && error.equals(m.error)
                && message.equals(m.message) && path.equals(m.path);
    }
}
