package com.softserve.edu.greencity.ui.tools;

import com.softserve.edu.greencity.ui.pages.econews.ItemsContainer;

public class EcoNewsUtils {
    public static int getNumberOfNewsNotCreatedBy(String username, ItemsContainer news) {

        for (int i = 0; i < news.getItemComponentsCount(); i++) {
            if (!news.chooseNewsByNumber(i).getAuthorText().equals(username)) {
                return i;
            }
        }
        return -1;
    }
}
