package com.softserve.edu.greencity.api.util;

import com.softserve.edu.greencity.api.model.EcoNewsModel;
import io.restassured.response.Response;

import java.util.List;

public class EcoNewsUtils {

    public static List<EcoNewsModel> responseToList(Response response) {
        return response.jsonPath().getList("", EcoNewsModel.class);
    }
}
