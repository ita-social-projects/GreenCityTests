package com.softserve.edu.greencity.api.models.econews;

import java.util.Arrays;

public class EcoNewsPageModel {

    public EcoNewsModel[] page;
    public Long totalElements;
    public Long currentPage;
    public Long totalPages;

    public EcoNewsPageModel(EcoNewsModel[] page, long total, long current, long pages) {
        this.page = page;
        totalElements = total;
        currentPage = current;
        totalPages = pages;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EcoNewsAuthorModel)) {
            return false;
        }
        EcoNewsPageModel p = (EcoNewsPageModel) obj;

        return Arrays.equals(page, p.page) && totalPages.equals(p.totalElements)
                && currentPage.equals(p.currentPage) && totalPages.equals(p.totalPages);
    }
}
