package com.softserve.edu.greencity.api.builders.econews;

import com.softserve.edu.greencity.api.models.econews.EcoNewsPOSTdto;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EcoNewsDtoBuilder {

    private EcoNewsPOSTdto ecoNews;

    private EcoNewsDtoBuilder() {
        ecoNews = new EcoNewsPOSTdto();
    }

    /**
     * Starts building
     * @return A builder with the possibility to set properties
     */
    public static EcoNewsDtoBuilder ecoNewsDtoWith() {
        return new EcoNewsDtoBuilder();
    }

    public EcoNewsDtoBuilder title(String title) {
        this.ecoNews.title = title;
        return this;
    }

    public EcoNewsDtoBuilder text(String text) {
        this.ecoNews.text = text;
        return this;
    }

    /**
     * MIME types reference: https://www.freeformatter.com/mime-types-list.html
     * @param mimeType manually assigned type of file like "text/plain", "image/png"
     * @param image filename of image to upload
     * @return possibility to set nex parameter
     */
    public EcoNewsDtoBuilder image(String mimeType, String image) {
        if(image == null || image.isEmpty()) {
            this.ecoNews.image = image;
        } else if(!mimeType.matches("\\w+/[-+.\\w]+")) {
            throw new IllegalArgumentException("mime type doesn't match format:" + mimeType);
        } else {
            StringBuilder str = new StringBuilder();
            str.append("data:").append(mimeType).append(";").append("base64,");

            byte[] fileContent = new byte[0];
            try {
                fileContent = FileUtils.readFileToByteArray(new File(image));
            } catch (IOException e) {
                e.printStackTrace();
            }
            str.append(Base64.getEncoder().encodeToString(fileContent));

            this.ecoNews.image = str.toString();//new String(str.toString().getBytes(), StandardCharsets.UTF_8);//
            //System.out.println(this.ecoNews.image);
        }
        return this;
    }

    public EcoNewsDtoBuilder source(String source) {
        this.ecoNews.source = source;
        return this;
    }

    public EcoNewsDtoBuilder tags(String[] tags) {
        this.ecoNews.tags = tags;
        return this;
    }

    public EcoNewsPOSTdto build() {
        return ecoNews;
    }
}
