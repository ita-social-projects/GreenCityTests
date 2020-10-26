package com.softserve.edu.greencity.ui.tools;

import com.softserve.edu.greencity.ui.data.econews.Tag;
import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;

import java.util.function.Function;

public class TagsUtill {

    public static Tag getSuitableTag(EcoNewsPage ecoNewsPage, Function<EcoNewsPage, Boolean> condition) {
        Tag[] tags = new Tag[] {Tag.ADS, Tag.EDUCATION, Tag.EVENTS, Tag.NEWS, Tag.INITIATIVES};

        Tag suitableTag = null;
        for(Tag tag : tags) {
            ecoNewsPage = ecoNewsPage.selectFilter(tag);
            if(condition.apply(ecoNewsPage).booleanValue()) {
                suitableTag = tag;
                break;
            }
            else {
                ecoNewsPage = ecoNewsPage.deselectFilter(tag);
            }
        }

        return suitableTag;
    }
}
