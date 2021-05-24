package com.softserve.edu.greencity.ui.tools.jdbc.dao;

import com.softserve.edu.greencity.ui.tools.jdbc.entity.EcoNewsEntity;
import com.softserve.edu.greencity.ui.tools.jdbc.entity.EditProfileEntity;
import com.softserve.edu.greencity.ui.tools.jdbc.entity.EditProfileSocialNetworksEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EditProfileSocialNetworksDao {

//    public EditProfileSocialNetworksEntity selectAllByUserId(long userId) {
//        List<EditProfileSocialNetworksEntity> result = selectByField("user_id", String.valueOf(userId));
//        return result.size() > 0 ? result.get(0) : null;
//    }

    public List<EditProfileSocialNetworksEntity> selectAllByUserId(long userId) {
        Statement statement = ManagerDao.get().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(EditProfileSocialNetworksEntity.SELECT_ALL_BY_USER_ID, userId));
            rows = ManagerDao.get().parseResultSet(resultSet);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        ManagerDao.closeStatement(statement);
        return EditProfileSocialNetworksEntity.getListEditProfileSocialNetworksEntity(rows);
    }

    public List<EditProfileSocialNetworksEntity> selectByField(String field, String value) {
        Statement statement = ManagerDao.get().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(EditProfileSocialNetworksEntity.SELECT_BY_FIELD, field, value));
            rows = ManagerDao.get().parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        ManagerDao.closeStatement(statement);
        return EditProfileSocialNetworksEntity.getListEditProfileSocialNetworksEntity(rows);
    }

    public List<EditProfileSocialNetworksEntity> selectAll() {
        Statement statement = ManagerDao.get().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(EditProfileSocialNetworksEntity.SELECT_ALL);
            rows = ManagerDao.get().parseResultSet(resultSet);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        ManagerDao.closeStatement(statement);
        return EditProfileSocialNetworksEntity.getListEditProfileSocialNetworksEntity(rows);
    }

    public void delete(EditProfileSocialNetworksEntity editProfileSocialNetworksEntity) {
        deleteByUserId(editProfileSocialNetworksEntity.getUserId());
    }

    public void deleteByUserId(long userId) {
        Statement statement = ManagerDao.get().getStatement();
        try {
            statement.execute(String.format(EditProfileSocialNetworksEntity.DELETE_SOCIAL_NETWORKS_BY_USER_ID, String.valueOf(userId)));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        //
        ManagerDao.closeStatement(statement);
    }
}
