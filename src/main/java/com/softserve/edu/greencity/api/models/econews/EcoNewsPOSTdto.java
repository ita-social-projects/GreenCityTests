package com.softserve.edu.greencity.api.models.econews;

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
}
