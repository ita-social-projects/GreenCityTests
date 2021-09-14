package com.softserve.edu.greencity.api.models.ubscourier;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bag {
    public Long amount;
    public Long id;

    @JsonProperty("amount")
    public long getAmount() { return amount; }
    @JsonProperty("amount")
    public void setAmount(long value) { this.amount = value; }

    @JsonProperty("id")
    public long getID() { return id; }
    @JsonProperty("id")
    public void setID(long value) { this.id = value; }
}
