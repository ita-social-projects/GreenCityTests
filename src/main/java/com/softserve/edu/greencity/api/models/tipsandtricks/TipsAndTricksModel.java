package com.softserve.edu.greencity.api.models.tipsandtricks;

import java.util.Arrays;

public class TipsAndTricksModel {
    public TipsAndTricksAuthorModel author;
    public String creationDate;
    public Long id;
    public String imagePath;
    public String source;
    public String[] tags;
    public String text;
    public String title;

    public TipsAndTricksModel() {
        author = null;
        creationDate = null;
        id = 0L;
        imagePath = null;
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
        if (!(obj instanceof TipsAndTricksAuthorModel)) {
            return false;
        }
        TipsAndTricksModel e = (TipsAndTricksModel) obj;

        return author.equals(e.author) && creationDate.equals(e.creationDate)
                && id.equals(e.id) && imagePath.equals(e.imagePath)
                && source.equals(e.source) && text.equals(e.text) && title.equals(e.title)
                && Arrays.equals(tags, e.tags);
    }
}
