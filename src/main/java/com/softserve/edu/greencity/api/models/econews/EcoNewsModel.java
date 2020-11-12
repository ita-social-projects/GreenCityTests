package com.softserve.edu.greencity.api.models.econews;

import java.util.Arrays;

public class EcoNewsModel {

    public EcoNewsAuthorModel author;
    public String creationDate;
    public Long id;
    public String imagePath;
    public String source;
    public String[] tags;
    public String text;
    public String title;

    public EcoNewsModel() {
        author = null;
        creationDate = null;
        id = 0L;
        imagePath = "https://ita-social-projects.github.io/GreenCityClient/assets/img/icon/econews/default-image-list-view.png";
        source = "";
        tags = new String[0];
        text = "";
        title = "";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EcoNewsAuthorModel)) {
            return false;
        }
        EcoNewsModel e = (EcoNewsModel) obj;

        return author.equals(e.id) && creationDate.equals(e.creationDate)
                && id.equals(e.id) && imagePath.equals(e.imagePath)
                && source.equals(e.source) && text.equals(e.text) && title.equals(e.title)
                && Arrays.equals(tags, e.tags);
    }

}
