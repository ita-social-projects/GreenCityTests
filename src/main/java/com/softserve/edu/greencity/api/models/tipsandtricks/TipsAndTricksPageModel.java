package com.softserve.edu.greencity.api.models.tipsandtricks;


import java.util.Arrays;

public class TipsAndTricksPageModel {
    public TipsAndTricksModel[] page;
    public Long totalElements;
    public Long currentPage;
    public Long totalPages;

    public TipsAndTricksPageModel(TipsAndTricksModel[] page, long total, long current, long pages) {
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
        if (!(obj instanceof TipsAndTricksAuthorModel)) {
            return false;
        }
        TipsAndTricksPageModel p = (TipsAndTricksPageModel) obj;

        return Arrays.equals(page, p.page) && totalPages.equals(p.totalElements)
                && currentPage.equals(p.currentPage) && totalPages.equals(p.totalPages);
    }
}
