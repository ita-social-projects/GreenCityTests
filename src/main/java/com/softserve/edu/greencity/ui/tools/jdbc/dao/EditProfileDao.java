package com.softserve.edu.greencity.ui.tools.jdbc.dao;

import com.softserve.edu.greencity.data.editprofile.EditProfileData;
import com.softserve.edu.greencity.ui.tools.jdbc.entity.EditProfileEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EditProfileDao {

    public EditProfileEntity selectByEmail(String email) {
        List<EditProfileEntity> result = selectByField("email", email);
        return result.size() > 0 ? result.get(0) : null;
    }

    public EditProfileEntity selectByName(String name) {
        List<EditProfileEntity> result = selectByField("name", name);
        return result.size() > 0 ? result.get(0) : null;
    }

    public EditProfileEntity selectById(long id) {
        List<EditProfileEntity> result = selectByField("id", String.valueOf(id));
        return result.size() > 0 ? result.get(0) : null;
    }

    public List<EditProfileEntity> selectByField(String field, String value) {
        Statement statement = ManagerDao.get().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(EditProfileEntity.SELECT_BY_FIELD, field, value));
            rows = ManagerDao.get().parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        ManagerDao.closeStatement(statement);
        return EditProfileEntity.getListEditProfileEntity(rows);
    }

    public List<EditProfileEntity> selectAll() {
        Statement statement = ManagerDao.get().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(EditProfileEntity.SELECT_ALL);
            rows = ManagerDao.get().parseResultSet(resultSet);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        ManagerDao.closeStatement(statement);
        return EditProfileEntity.getListEditProfileEntity(rows);
    }

    public void updateNameField(EditProfileData editProfileData, EditProfileEntity editProfileEntity){
        updateNameFieldById(editProfileData.getName(), editProfileEntity.getId());
    }

    public void updateCityField(EditProfileData editProfileData, EditProfileEntity editProfileEntity){
        updateCityFieldById(editProfileData.getCity(), editProfileEntity.getId());
    }

    public void updateUserCredoField(EditProfileData editProfileData, EditProfileEntity editProfileEntity){
        updateUserCredoFieldById(editProfileData.getCredo(), editProfileEntity.getId());
    }

    public void updateCityFieldById(String city, long id) {
        Statement statement = ManagerDao.get().getStatement();
        try {
            statement.execute(String.format(EditProfileEntity.UPDATE_FIELD_BY_ID, "city", city, String.valueOf(id)));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        ManagerDao.closeStatement(statement);
    }

    public void updateNameFieldById(String firstName, long id) {
        Statement statement = ManagerDao.get().getStatement();
        try {
            statement.execute(String.format(EditProfileEntity.UPDATE_FIELD_BY_ID, "first_name", firstName, String.valueOf(id)));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        ManagerDao.closeStatement(statement);
    }

    public void updateUserCredoFieldById(String userCredo, long id) {
        Statement statement = ManagerDao.get().getStatement();
        try {
            statement.execute(String.format(EditProfileEntity.UPDATE_FIELD_BY_ID, "user_credo", userCredo, String.valueOf(id)));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        ManagerDao.closeStatement(statement);
    }

    public void updateShowLocationFieldById(String showLocation, long id) {
        Statement statement = ManagerDao.get().getStatement();
        try {
            statement.execute(String.format(EditProfileEntity.UPDATE_FIELD_BY_ID, "show_location", showLocation, String.valueOf(id)));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        ManagerDao.closeStatement(statement);
    }

    public void updateShowEcoPlaceFieldById(String showEcoPlace, long id) {
        Statement statement = ManagerDao.get().getStatement();
        try {
            statement.execute(String.format(EditProfileEntity.UPDATE_FIELD_BY_ID, "show_eco_place", showEcoPlace, String.valueOf(id)));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        ManagerDao.closeStatement(statement);
    }

    public void updateShowShoppingListFieldById(String showShoppingList, long id) {
        Statement statement = ManagerDao.get().getStatement();
        try {
            statement.execute(String.format(EditProfileEntity.UPDATE_FIELD_BY_ID, "show_shopping_list", showShoppingList, String.valueOf(id)));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        ManagerDao.closeStatement(statement);
    }
}
