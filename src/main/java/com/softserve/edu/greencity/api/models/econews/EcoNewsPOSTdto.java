package com.softserve.edu.greencity.api.models.econews;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;

public class EcoNewsPOSTdto {

    public String title;
    public String text;
    public String image;
    public String source;
    public String[] tags;

    public EcoNewsPOSTdto() {
        title = "";
        text = "";
        image = "";
        source = "";
        tags = new String[0];
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
