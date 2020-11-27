package com.softserve.edu.greencity.ui.assertions;

import com.softserve.edu.greencity.data.econews.Tag;
import com.softserve.edu.greencity.ui.pages.econews.ItemsContainer;
import org.testng.Assert;

import java.util.List;

public class EcoNewsTagsAssertion {
    public static void assertNewsFilteredByTags(ItemsContainer news, List<Tag> tags) {
        boolean newFilteredCorrectly = true;
        int newsCount = news.getItemComponentsCount();
        for(int i = 0; i < newsCount; i++) {
            System.out.println("i = " + i);
            if(!news.chooseNewsByNumber(i).areTagsPresent(tags)) {
                newFilteredCorrectly = false;
                break;
            }
        }
        Assert.assertTrue(newFilteredCorrectly);
    }
}
