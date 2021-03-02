package com.softserve.edu.greencity.ui.tools.jdbc.dao;

import com.softserve.edu.greencity.ui.tools.jdbc.entity.TipsAndTricksEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TipsAndTricksDao {
    public void insert(TipsAndTricksEntity tipsAndTricksEntity) {
        Statement statement = ManagerDao.get().getStatement();
        // TODO
        ManagerDao.closeStatement(statement);
    }

    public TipsAndTricksEntity selectById(long id) {
        List<TipsAndTricksEntity> result = selectByField("id", String.valueOf(id));
        return result.size() > 0 ? result.get(0) : null;
    }

    public TipsAndTricksEntity selectByAuthorId(long id) {
        List<TipsAndTricksEntity> result = selectByField("author_id", String.valueOf(id));
        return result.size() > 0 ? result.get(0) : null;
    }

    public List<TipsAndTricksEntity> selectByField(String field, String value) {
        Statement statement = ManagerDao.get().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(TipsAndTricksEntity.SELECT_BY_FIELD, field, value));
            rows = ManagerDao.get().parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        //
        ManagerDao.closeStatement(statement);
        return TipsAndTricksEntity.getListTipsAndTricksEntity(rows);
    }

    public List<TipsAndTricksEntity> selectByTitle(String title) {
        return selectByField("title", title);
    }

    ///***************************************************
    public List<TipsAndTricksEntity> selectByIdCorrect(long id) {
        return selectByField("id", String.valueOf(id));
    }

    public List<TipsAndTricksEntity> selectAll() {
        Statement statement = ManagerDao.get().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(TipsAndTricksEntity.SELECT_ALL);
            rows = ManagerDao.get().parseResultSet(resultSet);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        //
        ManagerDao.closeStatement(statement);
        return TipsAndTricksEntity.getListTipsAndTricksEntity(rows);
    }

    public List<TipsAndTricksEntity> selectAllOrderByDate() {
        Statement statement = ManagerDao.get().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(TipsAndTricksEntity.SELECT_ALL_ORDER_BY_DATE);
            //TODO use proper time zone
            rows = ManagerDao.get().parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        //
        ManagerDao.closeStatement(statement);
        return TipsAndTricksEntity.getListTipsAndTricksEntity(rows);
    }

    public void update(TipsAndTricksEntity tipsAndTricksEntity) {
        Statement statement = ManagerDao.get().getStatement();
        // TODO
        ManagerDao.closeStatement(statement);
    }

    public void delete(TipsAndTricksEntity tipsAndTricksEntity) {
        deleteById(tipsAndTricksEntity.getId());
    }

    public void deleteById(long id) {
        Statement statement = ManagerDao.get().getStatement();
        try {
            statement.execute(String.format(TipsAndTricksEntity.DELETE_BY_ID, String.valueOf(id)));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        //
        ManagerDao.closeStatement(statement);
    }
}
