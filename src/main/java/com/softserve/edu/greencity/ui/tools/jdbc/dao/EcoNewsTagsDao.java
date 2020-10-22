package com.softserve.edu.greencity.ui.tools.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.softserve.edu.greencity.ui.tools.jdbc.entity.EcoNewsTagsEntity;


public class EcoNewsTagsDao {

    public List<EcoNewsTagsEntity> selectByEcoNewsId(long ecoNewsId) {
        return selectByField("eco_news_id", String.valueOf(ecoNewsId));
    }

    public List<EcoNewsTagsEntity> selectByField(String field, String value) {
        Statement statement = ManagerDao.get().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(EcoNewsTagsEntity.SELECT_BY_FIELD, field, value));
            rows = ManagerDao.get().parseResultSet(resultSet);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        //
        ManagerDao.closeStatement(statement);
        return EcoNewsTagsEntity.getListEcoNewsTagsEntity(rows);
    }
    
    public void delete(EcoNewsTagsEntity ecoNewsTagsEntity) {
        deleteByEcoNewsId(ecoNewsTagsEntity.getEcoNewsId());
    }

    public void deleteByEcoNewsId(long ecoNewsId) {
        Statement statement = ManagerDao.get().getStatement();
        try {
            statement.execute(String.format(EcoNewsTagsEntity.DELETE_BY_ECO_NEWS_ID, String.valueOf(ecoNewsId)));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        //
        ManagerDao.closeStatement(statement);
    }
}
