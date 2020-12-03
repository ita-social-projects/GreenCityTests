package com.softserve.edu.greencity.data.econews;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

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

    CONTENT_LOREM_IPSUM_LONG("Lorem ipsum dolor sit amet, consectetuer adipiscing elit, " +
            "sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat." +
            " Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl" +
            " ut aliquip ex ea commodo consequat. Duis autem vel eum iriure d"),

    TITLE_LOREM_IPSUM("Lorem ipsum dolor sit amet, consectetuer adipiscing elit," +
            " sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. " +
            "Ut wisi enim ad minim ven"),



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
    IMAGE_ECO_LAVKA("src/test/resources/images/eco-shop.png"),

    TITLE_MINIMAL("1"),
    CONTENT_MINIMAL("12345678901234567890"),

    TITLE_MAXIMAL("This title contains one hundred and seventy characters! "
            + "This title contains one hundred and seventy characters!! "
            + "This title contains one hundred and seventy characters!!!"),

    CONTENT_MAXIMAL(readFromFile("src/test/resources/texts/maximalText.txt")),
    SOURCE_MAXIMAL("http://www.example.com/very-long-title/so-long-that-i-can-paste-here-some-text/for-example/"
            + "lorem-impsum-dolor-sit-amet/or-something-more-interesting/like/william-shakespeare-was-an"
            + "-english-playwright-poet-and-actor/he-was-born-in-stratford-upon-avon/konec"),

    TITLE_POEM("У академії IT"),
    CONTENT_POEM_VERSE1("Лагав комп'ютер під Віндою,\n" +
            "І захід за вікном горів.\n" +
            "Нас залишалось тільки троє\n" +
            "Із вісімнадцяти учнів.\n" +
            "\n" +
            "Чимало з них, студентів файних,\n" +
            "Сказало: «Нам не по путі»\n"),
    CONTENT_POEM_REFRAIN("На курсах автотестування\n" +
            "У академії IT.\n" +
            "На курсах автотестування\n" +
            "У академії IT.\n"),
    CONTENT_POEM_VERSE2("Світились тестів результати,\n" +
            "Як непрочитаний email —\n" +
            "Всі намагались з'ясувати,\n" +
            "Чому з'явився новий fail,\n" +
            "\n" +
            "Чому невдале порівняння,\n" +
            "Що ся вписало в логи ті\n"),
    CONTENT_POEM_VERSE3("Над нами ментори кружили,\n" +
            "Спостерігали кожен день,\n" +
            "Чи з головою ми дружили,\n" +
            "Чи не творили єрундєнь.\n" +
            "\n" +
            "До цілі працевлаштування\n" +
            "Ми вже стояли на мості\n"),
    CONTENT_POEM_VERSE4("Мені вже сняться баги вічні,\n" +
            "Stack trace ексепшинів до них\n" +
            "І класи на півтищі стрічок,\n" +
            "Що зрозуміти ледве зміг.\n" +
            "\n" +
            "Та час забути ці страждання:\n" +
            "Хто вижив, попадуть усі\n" +
            "Із курсів автотестування\n" +
            "Та й на посаду TAQC!\n" +
            "Із курсів автотестування\n" +
            "Та й на посаду TAQC!\n"),


    IMAGE_TOO_BIG_PNG("src/test/resources/images/tooLargePng.png"),
    IMAGE_TOO_BIG_JPEG("src/test/resources/images/tooLargeImage.jpg"),
    IMAGE_NOT_AN_IMAGE("src/test/resources/images/notAnImage.txt"),
    IMAGE_GIF("src/test/resources/images/gifImage.gif"),
    IMAGE_MAXIMAL("src/test/resources/images/maximalImage.png");

    private static String readFromFile(String filePath) {
        String str = null;
        try {
            str = FileUtils.readFileToString(
                    new File(filePath), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return str;
        }
    }


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
