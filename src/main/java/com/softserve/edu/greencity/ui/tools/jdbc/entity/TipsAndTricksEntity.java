package com.softserve.edu.greencity.ui.tools.jdbc.entity;

import java.util.ArrayList;
import java.util.List;

enum TipsAndTricksEntityFields {
    ID(0),
    CREATION_DATE(1),
    IMAGE_PATH(2),
    AUTHOR_ID(3),
    TEXT(4),
    TITLE(5);

    private int number;
    //
    private TipsAndTricksEntityFields(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}

public class TipsAndTricksEntity {
    public static final String SELECT_ALL = "SELECT * FROM tips_and_tricks;";
    public static final String SELECT_ALL_ORDER_BY_DATE = "SELECT * FROM tips_and_tricks ORDER BY creation_date DESC;";
    public static final String SELECT_BY_FIELD = "SELECT * FROM tips_and_tricks WHERE %s='%s';";
    public static final String DELETE_BY_ID = "DELETE FROM tips_and_tricks WHERE id=%s;";
    //Use the following query in a Data Base: "SELECT * FROM tips_and_tricks ORDER BY creation_date DESC;
    private long id;
    private String creationDate;
    private String imagePath;
    private long authorId;
    private String text;
    private String title;

    public TipsAndTricksEntity() {
        creationDate = "";
        imagePath = "";
        authorId = 0;
        text = "";
        title = "";
    }

    public TipsAndTricksEntity(String creationDate, String imagePath, long authorId, String text, String title) {
        this.creationDate = creationDate;
        this.imagePath = imagePath;
        this.authorId = authorId;
        this.text = text;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public TipsAndTricksEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public TipsAndTricksEntity setCreationDate(String creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getImagePath() {
        return imagePath;
    }

    public TipsAndTricksEntity setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public long getAuthorId() {
        return authorId;
    }

    public TipsAndTricksEntity setAuthorId(long authorId) {
        this.authorId = authorId;
        return this;
    }

    public String getText() {
        return text;
    }

    public TipsAndTricksEntity setText(String text) {
        this.text = text;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TipsAndTricksEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public String toString() {
        return "TipsAndTricksEntity ["
                + "id=" + id
                + ", creation_date=" + creationDate
                + ", image_path=" + imagePath
                + ", author_id=" + authorId
                + ", text=" + text
                + ", title=" + title
                + "]";
    }

    public static TipsAndTricksEntity getTipsAndTricksEntity(List<String> row) {
        return new TipsAndTricksEntity()
                .setId(Long.parseLong(row.get(TipsAndTricksEntityFields.ID.getNumber())))
                .setCreationDate(row.get(TipsAndTricksEntityFields.CREATION_DATE.getNumber()))
                .setImagePath(row.get(TipsAndTricksEntityFields.IMAGE_PATH.getNumber()))
                .setAuthorId(Long.parseLong(row.get(TipsAndTricksEntityFields.AUTHOR_ID.getNumber())))
                .setText(row.get(TipsAndTricksEntityFields.TEXT.getNumber()))
                .setTitle(row.get(TipsAndTricksEntityFields.TITLE.getNumber()));
    }

    public static List<TipsAndTricksEntity> getListTipsAndTricksEntity(List<List<String>> rows) {
        List<TipsAndTricksEntity> result = new ArrayList<>();
        for (List<String> currentRow : rows) {
            result.add(getTipsAndTricksEntity(currentRow));
        }
        return result;
    }
}
