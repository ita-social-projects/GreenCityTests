package com.softserve.edu.greencity.api.data.econews;

public class NewsRepository {

    private NewsRepository(){}

    /**
     * @return string with length more than 170 symbols
     */
    public static String getTooLongTitle() {
        return "Еко-лавка. Еко-лавка - це Мережа магазинів спеціалізованих на реалізації натуральної " +
                "та екологічно чистої продукції, яка вирощується і виробляється на фермерських господарствах.  " +
                "В асортименті: молочна продукція, постійні делікатеси, європейські сири, хлеб, бакалія, " +
                "грузинські продукти, солодощі, , органічна косметика, безфосфатні миючі засоби та пральні порошки.\n" +
                "Філософія «Еко-Лавки» - найкращі продукти з найкращим сервісом.";
    }

    public static String getShortTitle() {
        return "Еко-лавка";
    }

    public static String getLongText() {
        return "Мережа магазинів спеціалізованих на реалізації натуральної та екологічно чистої продукції, " +
                "яка вирощується і виробляється на фермерських господарствах. В асортименті: молочна продукція, " +
                "постійні делікатеси, європейські сири, хлеб, бакалія, грузинські продукти, солодощі, " +
                "органічна косметика, безфосфатні миючі засоби та пральні порошки.\n" +
                "\n" +
                "Філософія «Еко-Лавки» - найкращі продукти з найкращим сервісом.";
    }

    public static String getMediumText() {
        return "Мережа магазинів спеціалізованих на реалізації натуральної та екологічно чистої продукції, " +
                "яка вирощується і виробляється на фермерських господарствах.";
    }

    public static String getTooShortText() {
        return "Магазин";
    }

    public static String getProperSource() {
        return "https://eco-lavca.ua/";
    }

    public static String getSourceWithoutHttp() {
        return "eco-lavca.ua";
    }

    public static String getLinkWithImproperHttpPos() {
        return "eco-lavca.https://ua/";
    }

    //TODO
    public static String getNormalImageData() {
        return null;
    }

    /**
     * @return title with length 1
     */
    public static String getMinimalTitle() {
        return "1";
    }

    /**
     * @return text with length 20
     */
    public static String getMinimalText() {
        return "12345678901234567890";
    }
}

