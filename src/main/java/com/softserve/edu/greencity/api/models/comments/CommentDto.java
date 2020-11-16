package com.softserve.edu.greencity.api.models.comments;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommentDto {
    public Integer parentCommentId;
    public String text;

    public CommentDto() {
        parentCommentId = 0;
        text = "string";
    }

    public CommentDto(Integer parentCommentId, String text) {
        this.parentCommentId = parentCommentId;
        this.text = text;
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
