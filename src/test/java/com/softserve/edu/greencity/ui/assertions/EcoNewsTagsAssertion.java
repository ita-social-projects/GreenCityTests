package com.softserve.edu.greencity.ui.assertions;

import com.softserve.edu.greencity.ui.data.econews.Tag;
import com.softserve.edu.greencity.ui.pages.econews.ItemsContainer;
import org.testng.Assert;

import java.util.List;

public class EcoNewsTagsAssertion {
    public static void assertNewsFilteredByTags(ItemsContainer news, List<Tag> tags) {
        boolean newFilteredCorrectly = true;
        for(int i = 0; i < news.getItemComponentsCount(); i++) {
            if(!news.chooseNewsByNumber(i).areTagsPresent(tags)) {
                newFilteredCorrectly = false;
            }
        }
        Assert.assertTrue(newFilteredCorrectly);
    }
}
