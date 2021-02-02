package com.softserve.edu.greencity.api.models.tipsandtricks;


public class TipsAndTricksAuthorModel {

    public Long id;
    public String name;

    public TipsAndTricksAuthorModel() {
        id = 0L;
        name = "";
    }

    public TipsAndTricksAuthorModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TipsAndTricksAuthorModel)) {
            return false;
        }
        TipsAndTricksAuthorModel a = (TipsAndTricksAuthorModel) obj;

        return id.equals(a.id) && name.equals(a.name);
    }
}
