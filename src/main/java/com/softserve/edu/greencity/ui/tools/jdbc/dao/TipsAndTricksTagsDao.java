package com.softserve.edu.greencity.ui.tools.jdbc.dao;

import com.softserve.edu.greencity.ui.tools.jdbc.entity.TipsAndTricksTagsEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TipsAndTricksTagsDao {
    public List<TipsAndTricksTagsEntity> selectByTipsAndTricksId(long tipsAndTricksId) {
        return selectByField("tips_and_tricks_id", String.valueOf(tipsAndTricksId));
    }

    public List<TipsAndTricksTagsEntity> selectByField(String field, String value) {
        Statement statement = ManagerDao.get().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(TipsAndTricksTagsEntity.SELECT_BY_FIELD, field, value));
            rows = ManagerDao.get().parseResultSet(resultSet);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        //
        ManagerDao.closeStatement(statement);
        return TipsAndTricksTagsEntity.getListTipsAndTricksTagsEntity(rows);
    }

    public void delete(TipsAndTricksTagsEntity tipsAndTricksTagsEntity) {
        deleteByTipsAndTricksId(tipsAndTricksTagsEntity.getTipsAndTricksId());
    }

    public void deleteByTipsAndTricksId(long tipsAndTricksId) {
        Statement statement = ManagerDao.get().getStatement();
        try {
            statement.execute(String.format(TipsAndTricksTagsEntity.DELETE_BY_TIPS_AND_TRICKS_ID, String.valueOf(tipsAndTricksId)));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        //
        ManagerDao.closeStatement(statement);
    }


}


