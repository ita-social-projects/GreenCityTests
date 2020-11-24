package com.softserve.edu.greencity.data.econews;

import com.softserve.edu.greencity.data.users.UserRepository;
import static com.softserve.edu.greencity.data.econews.NewsDataStrings.*;

import java.util.ArrayList;
import java.util.List;

public final class NewsDataRepository {

    private static volatile NewsDataRepository instance = null;

    private NewsDataRepository() {
    }

    public static NewsData getDefault() {
        return get().getAllFieldsNews();
    }

    public static NewsDataRepository get() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new NewsDataRepository();
                }
            }
        }
        return instance;
    }

    public NewsData getRequiredFieldsNews() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        tags.add(Tag.EVENTS);
        return new NewsData(tags, TITLE_GREEN_DAY.getString(), CONTENT_GREEN_DAY.getString());
    }

    public NewsData getNewsWithValidData() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        tags.add(Tag.EVENTS);
        return new NewsData(tags,
                TITLE_BE_COOL.getString(),
                CONTENT_BE_COOL.getString()
        );
    }

    public NewsData getNewsWithValidData(String title) {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        return new NewsData(tags,
                title,
                CONTENT_BE_COOL.getString()
        );
    }

    public NewsData getNewsWithValidData(List<Tag> tags) {
        return new NewsData(tags,
                TITLE_BE_COOL.getString(),
                CONTENT_BE_COOL.getString()
        );
    }

    public NewsData getNewsWithValidSourceField() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        return new NewsData(tags,
                TITLE_BE_COOL.getString(),
                CONTENT_BE_COOL.getString(),
                SOURCE_NEWS_COM.getString()
        );
    }

    public NewsData getNewsWithInvalidSourceField() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        return new NewsData(tags,
                TITLE_EXHIBITION.getString(),
                CONTENT_EXHIBITION.getString(),
                SOURCE_NO_HTTP_GREEN_MATCH.getString()
        );
    }

    public NewsData getNewsWithEmptySourceField() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        tags.add(Tag.EDUCATION);
        return new NewsData(tags,
                TITLE_EXHIBITION.getString(),
                CONTENT_EXHIBITION.getString()
        );
    }

    public NewsData getNewsWithInvalidTitleField() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        tags.add(Tag.EDUCATION);
        return new NewsData(tags,
                " ",
                CONTENT_EXHIBITION.getString()
        );
    }

    public NewsData getNewsWithInvalidContentField() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        return new NewsData(tags, TITLE_GREEN_DAY.getString(), CONTENT_SHORT_FOO.getString());
    }

    public NewsData getNewsWithContentFieldForCheckAutoResize() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        return new NewsData(tags,
                TITLE_EXHIBITION.getString(),
                CONTENT_LOREM_IPSUM.getString() + CONTENT_LOREM_IPSUM.getString()
        );
    }

    public NewsData getNewsWithEmptyContentField() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        return new NewsData(tags,
                TITLE_EXHIBITION.getString(),
                " "
        );
    }

    public NewsData getAllFieldsNews() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        tags.add(Tag.EVENTS);
        return new NewsData(tags, TITLE_GREEN_DAY.getString(), SOURCE_GOOD_NEWS.getString(),
                SOURCE_GOOD_NEWS.getString());
    }

    public NewsData getNewsWithInvalidData() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.EVENTS);
        tags.add(Tag.NEWS);
        tags.add(Tag.EDUCATION);
        tags.add(Tag.ADS);
        return new NewsData(TITLE_LONG_LOSS_OF_SPECIES.getString(),
                tags,
                SOURCE_NO_HTTP_NEWS_COM.getString(),
                CONTENT_SHORT_FOO.getString(),
                "src/test/resources/images/gifImage.gif"
        );
    }

    public NewsData getExistingNews() { //Now doesn't exist. Used in a legacy EcoNewsPageTest
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.EVENTS);
        return new NewsData(tags, "Толока в Горіховому гаю", " Test Test Test Test Test Test Test  ");
    }

    public NewsData getNewsWithInvalidTags(List<Tag> tags) {
        return new NewsData(tags,
                "Be eco! Be cool!",
                "It's so healthy, fun and cool to bring eco habits in everyday life"
        );
    }

    public NewsData getOneRowTitle() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        tags.add(Tag.EVENTS);
        return new NewsData(tags, "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure d");
    }

    public NewsData getFourRowsTitle() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        tags.add(Tag.EVENTS);
        return new NewsData(tags, "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure d");
    }

    public NewsData getThreeRowsTitle() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        tags.add(Tag.EVENTS);
        return new NewsData(tags, "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure d");
    }

    public NewsData getTwoRowsTitle() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        tags.add(Tag.EVENTS);
        return new NewsData(tags, "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure d");
    }

    public NewsData getNewsWithoutSource() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.INITIATIVES);
        return new NewsData(tags, "Еко лавка",
                "Мережа магазинів спеціалізованих на реалізації натуральної та екологічно чистої продукції, яка вирощується і виробляється на фермерських господарствах.");
    }

    public NewsData getTitleForAutoResizeCheck() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        tags.add(Tag.EVENTS);
        return new NewsData(tags, "March 4 – 7, 2020, International Exhibition Center, Kyiv, 15 Brovarsky Ave., takes place the most important event for professionals and funs of natural food and healthy life.",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure d");
    }


    public NewsData getNewsWithSource() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.INITIATIVES);
        return new NewsData(tags, "Еко лавка",
                "Мережа магазинів спеціалізованих на реалізації натуральної та екологічно чистої продукції, яка вирощується і виробляється на фермерських господарствах.",
                "https://google.com.ua");
    }

    public List<Tag> getNewsByTags() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        tags.add(Tag.ADS);
        tags.add(Tag.EVENTS);
        return tags;
    }
}
