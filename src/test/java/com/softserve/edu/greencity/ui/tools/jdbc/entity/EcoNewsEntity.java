package com.softserve.edu.greencity.ui.tools.jdbc.entity;

import java.util.ArrayList;
import java.util.List;

enum EcoNewsEntityFields {
    ID(0),
    CREATION_DATE(1),
    IMAGE_PATH(2),
    AUTHOR_ID(3),
    TEXT(4),
    TITLE(5);
    
    private int number;
    //
    private EcoNewsEntityFields(int number) {
        this.number = number;
    }
    
    public int getNumber() {
        return number;
    }
}

public class EcoNewsEntity {
    public static final String SELECT_ALL = "SELECT * FROM eco_news;";
    public static final String SELECT_ALL_ORDER_BY_DATE = "SELECT * FROM eco_news ORDER BY creation_date DESC;";
    public static final String SELECT_BY_FIELD = "SELECT * FROM eco_news WHERE %s='%s';";
    public static final String DELETE_BY_ID = "DELETE FROM eco_news WHERE id=%s;";
    //Use the following query in a Data Base: "SELECT * FROM eco_news ORDER BY creation_date DESC;
    private long id;
    private String creationDate;
    private String imagePath;
    private long authorId;
    private String text;
    private String title;

    public EcoNewsEntity() {
        creationDate = "";
        imagePath = "";
        authorId = 0;
        text = "";
        title = "";
    }

    public EcoNewsEntity(String creationDate, String imagePath, long authorId, String text, String title) {
        this.creationDate = creationDate;
        this.imagePath = imagePath;
        this.authorId = authorId;
        this.text = text;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public EcoNewsEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public EcoNewsEntity setCreationDate(String creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getImagePath() {
        return imagePath;
    }

    public EcoNewsEntity setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public long getAuthorId() {
        return authorId;
    }

    public EcoNewsEntity setAuthorId(long authorId) {
        this.authorId = authorId;
        return this;
    }

    public String getText() {
        return text;
    }

    public EcoNewsEntity setText(String text) {
        this.text = text;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public EcoNewsEntity setTitle(String title) {
        this.title = title;
        return this;
   }

    @Override
    public String toString() {
        return "EcoNewsEntity ["
                + "id=" + id
                + ", creation_date=" + creationDate
                + ", image_path=" + imagePath
                + ", author_id=" + authorId
                + ", text=" + text
                + ", title=" + title
                + "]";
    }
    
    public static EcoNewsEntity getEcoNewsEntity(List<String> row) {
        return new EcoNewsEntity()
                .setId(Long.valueOf(row.get(EcoNewsEntityFields.ID.getNumber())))
                .setCreationDate(row.get(EcoNewsEntityFields.CREATION_DATE.getNumber()))
                .setImagePath(row.get(EcoNewsEntityFields.IMAGE_PATH.getNumber()))
                .setAuthorId(Long.valueOf(row.get(EcoNewsEntityFields.AUTHOR_ID.getNumber())))
                .setText(row.get(EcoNewsEntityFields.TEXT.getNumber()))
                .setTitle(row.get(EcoNewsEntityFields.TITLE.getNumber()));
    }
    
    public static List<EcoNewsEntity> getListEcoNewsEntity(List<List<String>> rows) {
        List<EcoNewsEntity> result = new ArrayList<>();
        for (List<String> currentRow : rows) {
            result.add(getEcoNewsEntity(currentRow));
        }
        return result;
    }
}
