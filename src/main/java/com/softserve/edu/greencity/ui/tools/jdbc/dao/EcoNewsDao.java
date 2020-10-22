package com.softserve.edu.greencity.ui.tools.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.softserve.edu.greencity.ui.tools.jdbc.entity.EcoNewsEntity;

public class EcoNewsDao {

    public void insert(EcoNewsEntity ecoNewsEntity) {
        Statement statement = ManagerDao.get().getStatement();
        // TODO
        ManagerDao.closeStatement(statement);
    }

    public EcoNewsEntity selectById(long id) {
        List<EcoNewsEntity> result = selectByField("id", String.valueOf(id));
        return result.size() > 0 ? result.get(0) : null;
    }

    public EcoNewsEntity selectByAuthorId(long id) {
        List<EcoNewsEntity> result = selectByField("author_id", String.valueOf(id));
        return result.size() > 0 ? result.get(0) : null;
    }

    public List<EcoNewsEntity> selectByField(String field, String value) {
        Statement statement = ManagerDao.get().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(EcoNewsEntity.SELECT_BY_FIELD, field, value));
            rows = ManagerDao.get().parseResultSet(resultSet);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        //
        ManagerDao.closeStatement(statement);
        return EcoNewsEntity.getListEcoNewsEntity(rows);
    }

    public List<EcoNewsEntity> selectByTitle(String title) {
        return selectByField("title", title);
    }

    ///***************************************************
    public List<EcoNewsEntity> selectByIdCorrect(long id) {
        return selectByField("id", String.valueOf(id));
    }

    public List<EcoNewsEntity> selectAll() {
        Statement statement = ManagerDao.get().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(EcoNewsEntity.SELECT_ALL);
            rows = ManagerDao.get().parseResultSet(resultSet);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        //
        ManagerDao.closeStatement(statement);
        return EcoNewsEntity.getListEcoNewsEntity(rows);
    }

    public List<EcoNewsEntity> selectAllOrderByDate() {
        Statement statement = ManagerDao.get().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(EcoNewsEntity.SELECT_ALL_ORDER_BY_DATE);
            rows = ManagerDao.get().parseResultSet(resultSet);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        //
        ManagerDao.closeStatement(statement);
        return EcoNewsEntity.getListEcoNewsEntity(rows);
    }

    public void update(EcoNewsEntity ecoNewsEntity) {
        Statement statement = ManagerDao.get().getStatement();
        // TODO
        ManagerDao.closeStatement(statement);
    }

    public void delete(EcoNewsEntity ecoNewsEntity) {
        deleteById(ecoNewsEntity.getId());
    }

    public void deleteById(long id) {
        Statement statement = ManagerDao.get().getStatement();
        try {
            statement.execute(String.format(EcoNewsEntity.DELETE_BY_ID, String.valueOf(id)));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        //
        ManagerDao.closeStatement(statement);
    }
}
