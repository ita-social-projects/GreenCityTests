package com.softserve.edu.greencity.api.data.econews;

import org.apache.commons.io.FileUtils;

import java.io.*;

public class NewsRepository {

    private NewsRepository(){}

    /*TITLE*/

    /**
     * @return title with length 1
     */
    public static String getMinimalTitle() {
        return "1";
    }

    public static String getShortTitle() {
        return "Еко-лавка";
    }

    public static String getMediumTitle() {
        return "Earth Day 2020";
    }

    /**
     * @return title with length 170
     */
    public static String getMaximalTitle() {
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt "
                + "ut labore eto dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercit";
    }

    /**
     * @return title with length more than 170
     */
    public static String getTooLongTitle() {
        return "Еко-лавка. Еко-лавка - це Мережа магазинів спеціалізованих на реалізації натуральної " +
                "та екологічно чистої продукції, яка вирощується і виробляється на фермерських господарствах.  " +
                "В асортименті: молочна продукція, постійні делікатеси, європейські сири, хлеб, бакалія, " +
                "грузинські продукти, солодощі, , органічна косметика, безфосфатні миючі засоби та пральні порошки.\n" +
                "Філософія «Еко-Лавки» - найкращі продукти з найкращим сервісом.";
    }

    /*TEXT*/

    /**
     * @return text shorter than 20
     */
    public static String getTooShortText() {
        return "Магазин";
    }

    /**
     * @return text with length 20
     */
    public static String getMinimalText() {
        return "12345678901234567890";
    }

    public static String getMediumText() {
        return "Мережа магазинів спеціалізованих на реалізації натуральної та екологічно чистої продукції, " +
                "яка вирощується і виробляється на фермерських господарствах.";
    }

    public static String getLongText() {
        return "Мережа магазинів спеціалізованих на реалізації натуральної та екологічно чистої продукції, " +
                "яка вирощується і виробляється на фермерських господарствах. В асортименті: молочна продукція, " +
                "постійні делікатеси, європейські сири, хлеб, бакалія, грузинські продукти, солодощі, " +
                "органічна косметика, безфосфатні миючі засоби та пральні порошки.\n" +
                "\n" +
                "Філософія «Еко-Лавки» - найкращі продукти з найкращим сервісом.";
    }

    /**
     * @return text with length 63206
     */
    public static String getMaximalText() {
        String str = "";
        try {
            str = FileUtils.readFileToString(
                    new File("src/test/resources/texts/maximalText.txt"),
                    "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return str;
        }
    }

    /*SOURCE*/

    public static String getProperSource() {
        return "https://eco-lavca.ua/";
    }

    public static String getSourceWithoutHttp() {
        return "eco-lavca.ua";
    }

    public static String getLinkWithImproperHttpPos() {
        return "eco-lavca.https://ua/";
    }

    /**
     * @return url with length of 255 symbols
     */
    public static String getMaximalLink() {
        return "http://www.example.com/very-long-title/so-long-that-i-can-paste-here-some-text/for-example/" +
                "lorem-impsum-dolor-sit-amet/or-something-more-interesting/like/william-shakespeare-was-an" +
                "-english-playwright-poet-and-actor/he-was-born-in-stratford-upon-avon/konec";
    }

    /*IMAGE*/

    /**
     * @return image in jpeg format with length more than 10MB
     */
    public static String getTooBigJpegImage() {
        return "src/test/resources/images/tooLargeImage.jpg";
    }

    /**
     * @return image in png format with length more than 10MB
     */
    public static String getTooBigPngImage() {
        return "src/test/resources/images/tooLargePng.png";
    }

    public static String getNormalImage() {
        return "src/test/resources/images/eco-shop.png";
    }

    /**
     * @return text file instead o image
     */
    public static String getNotAnImage() {
        return "src/test/resources/images/notAnImage.txt";
    }

    public static String getGifImage() {
        return "src/test/resources/images/gifImage.gif";
    }

    //TODO
    public static String getMaximalImage() {
        return "src/test/resources/images/maximalImage.png";
    }
}

