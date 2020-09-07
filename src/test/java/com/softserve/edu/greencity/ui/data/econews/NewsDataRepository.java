package com.softserve.edu.greencity.ui.data.econews;

import java.util.ArrayList;
import java.util.List;

public class NewsDataRepository {

    private NewsDataRepository() {
    }

    public static NewsData getDefault() {
        return getAllFieldsNews();
    }

    public static NewsData getRequiredFieldsNews() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        tags.add(Tag.EVENTS);
        return new NewsData( tags, "Green Day", "Content = description");
    }

    public static NewsData getAllFieldsNews() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        tags.add(Tag.EVENTS);
        return new NewsData("Green Day", tags, "https://news.com",
                "Content = description", "src/test/resources/valid.png");
    }

    public static NewsData getInvalidData() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.EVENTS);
        tags.add(Tag.NEWS);
        tags.add(Tag.EDUCATION);
        tags.add(Tag.ADS);
        return new NewsData("The loss of any species is devastating. However, the decline or " +
                "extinction of one species can trigger an avalanche within an ecosystem, wiping out" +
                " many species in the process",
                tags, "news.com", "Content", "src/test/resources/invalid.gif");
    }

    public static NewsData getExistingNews() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.EVENTS);
        return new NewsData(tags, " Test  ", " Test Test Test Test Test Test Test  ");
    }

    public static List<Tag> getNewsByTags() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        tags.add(Tag.ADS);
        tags.add(Tag.EVENTS);
        return tags;
    }
}
