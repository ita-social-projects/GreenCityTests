package com.softserve.edu.greencity.data.econews;

/**
 * Constant string for tests with news creation.
 * Mostly used by NewsDataRepository,
 * and it is recommended to call its method rather than use this enum directly
 */
public enum NewsDataStrings {
    TITLE_GREEN_DAY("Green Day Test"),
    CONTENT_GREEN_DAY("Content = description"),

    TITLE_BE_COOL("Be eco! Be cool!"),
    CONTENT_BE_COOL("It's so healthy, fun and cool to bring eco habits in everyday life"),
    SOURCE_NEWS_COM("https://news.com"),

    TITLE_EXHIBITION("XVI International specialized exhibition of ecologic products for the daily life"),
    CONTENT_EXHIBITION("March 4 – 7, 2020, International Exhibition Center, Kyiv, 15 Brovarsky Ave.,"
            + "takes place the most important event for professionals and funs of natural food and healthy life"),
    SOURCE_NO_HTTP_GREEN_MATCH("www.greenmatch.co.uk/blog/how-to-be-more-eco-friendly"),

    CONTENT_SHORT_FOO("foo"),
    CONTENT_LOREM_IPSUM("Lorem ipsum dolor sit amet, consectetuer adipiscing elit, "
            + "sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. " +
            "Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit " +
            "lobortis nisl ut aliquip ex ea commodo consequat."),

    SOURCE_GOOD_NEWS("https://goodnews.com"),

    TITLE_LONG_LOSS_OF_SPECIES("The loss of any species is devastating. However, the decline or " +
            "extinction of one species can trigger an avalanche within an ecosystem, wiping out" +
            " many species in the process"),
    SOURCE_NO_HTTP_NEWS_COM("news.com"),

    TITLE_TOLOKA("Толока в Горіховому гаю"),
    CONTENT_TOLOKA("25 серпня запрошуємо на велику толоку в парку Горіховий гай. Початок о 9:00."),

    TITLE_31W("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"), //This is exactly 1 row of the title field

    TITLE_EKO_LAVKA("Еко лавка"),
    CONTENT_EKO_LAVKA("Мережа магазинів спеціалізованих на реалізації натуральної та екологічно чистої продукції, "
            + "яка вирощується і виробляється на фермерських господарствах."),
    CONTENT_LONG_EKO_LAVKA("Мережа магазинів спеціалізованих на реалізації натуральної та екологічно чистої продукції, " +
            "яка вирощується і виробляється на фермерських господарствах. В асортименті: молочна продукція, " +
            "постійні делікатеси, європейські сири, хлеб, бакалія, грузинські продукти, солодощі, " +
            "органічна косметика, безфосфатні миючі засоби та пральні порошки.\n" +
            "\n" +
            "Філософія «Еко-Лавки» - найкращі продукти з найкращим сервісом."),
    SOURCE_EKO_LAVKA("https://eco-lavca.ua/"),
    SOURCE_HTTP_INSIDE_EKO_LAVKA("eco-lavca.https://ua/"),

    TITLE_MINIMAL("1"),
    CONTENT_MINIMAL("12345678901234567890"),

    TITLE_MAXIMAL("This title contains one hundred and seventy characters! "
            + " This title contains one hundred and seventy characters!! "
            + "This title contains one hundred and seventy characters!!!");

    private final String str;

    NewsDataStrings(String str) {
        this.str = str;
    }

    public String getString() {
        return str;
    }

    @Override
    public String toString() {
        return str;
    }
}
