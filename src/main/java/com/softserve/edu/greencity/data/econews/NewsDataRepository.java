package com.softserve.edu.greencity.data.econews;

import com.softserve.edu.greencity.data.users.UserRepository;
import static com.softserve.edu.greencity.data.econews.NewsDataStrings.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides templates for posting eco news, used in tests that create news.
 * Usage: NewsDataRepository.get().getNewsWithValidData() etc.
 */
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

    /**
     * This method allows you to pass a non-default title.
     * Could be very useful if other news may be created while a test run,
     * and you need to get the specific one by parameters.
     * Use in pair with EcoNewsPage.switchToSingleNewsPageByParameters()
     * @param title your title for news
     * @return NewsData with specified title and default content
     */
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
        tags.add(Tag.INITIATIVES);
        return new NewsData(tags,
                TITLE_EKO_LAVKA.getString(),
                CONTENT_EKO_LAVKA.getString(),
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

    public NewsData getNewsWithEmptySourceField2() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.INITIATIVES);
        return new NewsData(tags, TITLE_EKO_LAVKA.getString(),
                CONTENT_EKO_LAVKA.getString());
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
        return new NewsData(tags, TITLE_TOLOKA.getString(), CONTENT_TOLOKA.getString());
    }

    public NewsData getNewsWithInvalidTags(List<Tag> tags) {
        return new NewsData(tags,
                TITLE_BE_COOL.getString(),
                CONTENT_BE_COOL.getString()
        );
    }

    public NewsData getOneRowTitle() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        tags.add(Tag.EVENTS);
        return new NewsData(tags, TITLE_31W.getString(),
                CONTENT_LOREM_IPSUM.getString());
    }

    public NewsData getTwoRowsTitle() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        tags.add(Tag.EVENTS);
        return new NewsData(tags, TITLE_31W.getString() + TITLE_31W.getString(),
                CONTENT_LOREM_IPSUM.getString());
    }

    public NewsData getThreeRowsTitle() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        tags.add(Tag.EVENTS);
        return new NewsData(tags,
                TITLE_31W.getString() + TITLE_31W.getString() + TITLE_31W.getString(),
                CONTENT_LOREM_IPSUM.getString());
    }

    public NewsData getFourRowsTitle() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        tags.add(Tag.EVENTS);
        return new NewsData(tags,
                TITLE_31W.getString() + TITLE_31W.getString() +
                        TITLE_31W.getString() + TITLE_31W.getString(),
                CONTENT_LOREM_IPSUM.getString());
    }

    public NewsData getTitleForAutoResizeCheck() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        tags.add(Tag.EVENTS);
        return new NewsData(tags, CONTENT_EXHIBITION.getString(), //sic!
                CONTENT_LOREM_IPSUM.getString());
    }

    public NewsData getHeightCheckContent() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        tags.add(Tag.EVENTS);
        return new NewsData(tags, TITLE_LOREM_IPSUM.getString(), CONTENT_LOREM_IPSUM_LONG.getString());
    }

    /**
     * Congratulations! You've found an "Easter egg"!
     * This is a poem about TAQCs' hard studies
     * @author Meownjik
     */
    public NewsData getNewsWithPoem() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.EDUCATION);
        return new NewsData(tags,
                TITLE_POEM.getString(),
                CONTENT_POEM_VERSE1 + "\n"
                        + CONTENT_POEM_REFRAIN + "\n"
                        + CONTENT_POEM_VERSE2 + "\n"
                        + CONTENT_POEM_REFRAIN + "\n"
                        + CONTENT_POEM_VERSE3 + "\n"
                        + CONTENT_POEM_REFRAIN + "\n"
                        + CONTENT_POEM_VERSE4 + "\n");
    }

    public List<Tag> getNewsByTags() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        tags.add(Tag.ADS);
        tags.add(Tag.EVENTS);
        return tags;
    }

    public NewsData getWrappedNews() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.NEWS);
        return new NewsData(tags, TITLE_WO_SPACES.getString(), CONTENT_WO_SPACES.getString());
    }

    public  NewsData getWrappedNewsWithSpaces(){
        List<Tag> tags = new ArrayList<>();
        return new NewsData(tags, TITLE_WITH_SPACES.getString(), CONTENT_WITH_SPACES.getString());
    }
}
