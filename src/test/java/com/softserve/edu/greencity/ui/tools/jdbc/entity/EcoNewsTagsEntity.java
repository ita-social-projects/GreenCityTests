package com.softserve.edu.greencity.ui.tools.jdbc.entity;

import java.util.ArrayList;
import java.util.List;

enum EcoNewsEntityTagsFields {
    ECO_NEWS_ID(0),
    TAGS_ID(1);
    
    private int number;
    //
    private EcoNewsEntityTagsFields(int number) {
        this.number = number;
    }
    
    public int getNumber() {
        return number;
    }
}

public class EcoNewsTagsEntity {
    public static final String SELECT_ALL = "SELECT * FROM eco_news_tags;";
    public static final String SELECT_BY_FIELD = "SELECT * FROM eco_news_tags WHERE %s='%s';";
    public static final String DELETE_BY_ECO_NEWS_ID = "DELETE FROM eco_news_tags WHERE eco_news_id=%s;";
    //
    private long ecoNewsId;
    private long tagsId;

    public EcoNewsTagsEntity() {
        ecoNewsId = 0;
        tagsId = 0;
    }

    public EcoNewsTagsEntity(long ecoNewsId, long tagsId) {
        this.ecoNewsId = ecoNewsId;
        this.tagsId = tagsId;
    }

    public long getEcoNewsId() {
        return ecoNewsId;
    }

    public EcoNewsTagsEntity setEcoNewsId(long ecoNewsId) {
        this.ecoNewsId = ecoNewsId;
        return this;
    }

    public long getTagsId() {
        return tagsId;
    }

    public EcoNewsTagsEntity setTagsId(long tagsId) {
        this.tagsId = tagsId;
        return this;
    }

    @Override
    public String toString() {
        return "EcoNewsTagsEntity ["
                + "ecoNewsId=" + ecoNewsId 
                + ", tagsId=" + tagsId
                + "]";
    }

    public static EcoNewsTagsEntity getEcoNewsTagsEntity(List<String> row) {
        return new EcoNewsTagsEntity()
                .setEcoNewsId(Long.valueOf(row.get(EcoNewsEntityTagsFields.ECO_NEWS_ID.getNumber())))
                .setTagsId(Long.valueOf(row.get(EcoNewsEntityTagsFields.TAGS_ID.getNumber())));
    }
    
    public static List<EcoNewsTagsEntity> getListEcoNewsTagsEntity(List<List<String>> rows) {
        List<EcoNewsTagsEntity> result = new ArrayList<>();
        for (List<String> currentRow : rows) {
            result.add(getEcoNewsTagsEntity(currentRow));
        }
        return result;
    }
}
