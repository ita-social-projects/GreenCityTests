package com.softserve.edu.greencity.api.models.econews;

public class EcoNewsAuthorModel {

    public Long id;
    public String name;

    public EcoNewsAuthorModel() {
        id = 0L;
        name = "";
    }

    public EcoNewsAuthorModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EcoNewsAuthorModel)) {
            return false;
        }
        EcoNewsAuthorModel a = (EcoNewsAuthorModel) obj;

        return id.equals(a.id) && name.equals(a.name);
    }
}
