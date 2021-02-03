package com.softserve.edu.greencity.ui.tools.jdbc.entity;

import java.util.ArrayList;
import java.util.List;

    enum TipsAndTricksEntityTagsFields {
        TIPS_AND_TRICKS_ID(0),
        TAGS_ID(1);

        private int number;
        //
        private TipsAndTricksEntityTagsFields(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }
    }

    public class TipsAndTricksTagsEntity {
        public static final String SELECT_ALL = "SELECT * FROM tips_and_tricks_tags;";
        public static final String SELECT_BY_FIELD = "SELECT * FROM tips_and_tricks_tags WHERE %s='%s';";
        public static final String DELETE_BY_TIPS_AND_TRICKS_ID = "DELETE FROM tips_and_tricks_tags WHERE eco_news_id=%s;";
        //
        private long TipsAndTricksId;
        private long tagsId;

        public TipsAndTricksTagsEntity() {
            TipsAndTricksId = 0;
            tagsId = 0;
        }

        public TipsAndTricksTagsEntity(long TipsAndTricksId, long tagsId) {
            this.TipsAndTricksId = TipsAndTricksId;
            this.tagsId = tagsId;
        }

        public long getTipsAndTricksId() {
            return TipsAndTricksId;
        }

        public TipsAndTricksTagsEntity setTipsAndTricksId(long TipsAndTricksId) {
            this.TipsAndTricksId = TipsAndTricksId;
            return this;
        }

        public long getTagsId() {
            return tagsId;
        }

        public TipsAndTricksTagsEntity setTagsId(long tagsId) {
            this.tagsId = tagsId;
            return this;
        }

        @Override
        public String toString() {
            return "TipsAndTricksTagsEntity ["
                    + "TipsAndTricksId=" + TipsAndTricksId
                    + ", tagsId=" + tagsId
                    + "]";
        }

        public static TipsAndTricksTagsEntity getTipsAndTricksTagsEntity(List<String> row) {
            return new TipsAndTricksTagsEntity()
                    .setTipsAndTricksId(Long.parseLong(row.get(TipsAndTricksEntityTagsFields.TIPS_AND_TRICKS_ID.getNumber())))
                    .setTagsId(Long.parseLong(row.get(TipsAndTricksEntityTagsFields.TAGS_ID.getNumber())));
        }

        public static List<TipsAndTricksTagsEntity> getListTipsAndTricksTagsEntity(List<List<String>> rows) {
            List<TipsAndTricksTagsEntity> result = new ArrayList<>();
            for (List<String> currentRow : rows) {
                result.add(getTipsAndTricksTagsEntity(currentRow));
            }
            return result;
        }
}
