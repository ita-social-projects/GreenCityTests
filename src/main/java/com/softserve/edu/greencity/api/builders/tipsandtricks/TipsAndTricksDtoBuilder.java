package com.softserve.edu.greencity.api.builders.tipsandtricks;

import com.softserve.edu.greencity.api.models.tipsandtricks.TipsAndTricksPOSTdto;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class TipsAndTricksDtoBuilder {
    private TipsAndTricksPOSTdto tipsAndTricks;

    private TipsAndTricksDtoBuilder() {
        tipsAndTricks = new TipsAndTricksPOSTdto();
    }

    /**
     * Starts building
     * @return A builder with the possibility to set properties
     */
    public static TipsAndTricksDtoBuilder tipsAndTricksDtoWith() {
        return new TipsAndTricksDtoBuilder();
    }

    public TipsAndTricksDtoBuilder title(String title) {
        this.tipsAndTricks.title = title;
        return this;
    }

    public TipsAndTricksBuilder text(String text) {
        this.tipsAndTricks.text = text;
        return this;
    }

    /**
     * MIME types reference: https://www.freeformatter.com/mime-types-list.html
     * @param mimeType manually assigned type of file like "text/plain", "image/png"
     * @param image filename of image to upload
     * @return possibility to set nex parameter
     */
    public TipsAndTricksDtoBuilder image(String mimeType, String image) {
        if(image == null || image.isEmpty()) {
            this.tipsAndTricks.image = image;
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

            this.tipsAndTricks.image = str.toString();//new String(str.toString().getBytes(), StandardCharsets.UTF_8);//
            //System.out.println(this.tipsAndTricks.image);
        }
        return this;
    }

    public TipsAndTricksDtoBuilder source(String source) {
        this.tipsAndTricks.source = source;
        return this;
    }

    public TipsAndTricksBuilder tags(String[] tags) {
        this.tipsAndTricks.tags = tags;
        return this;
    }

    public TipsAndTricksPOSTdto build() {
        return tipsAndTricks;
    }
}
