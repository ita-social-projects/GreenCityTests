package com.softserve.edu.greencity.ui.tools.jdbc.dao;

import com.softserve.edu.greencity.data.UBS.UBSPersonalData;
import com.softserve.edu.greencity.ui.tools.jdbc.entity.UBSPersonalDataEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UBSPersonalDataDao {

    public UBSPersonalDataEntity selectByEmail(String email) {
        List<UBSPersonalDataEntity> result = selectByField("email", email);
        return result.size() > 0 ? result.get(0) : null;
    }

    public UBSPersonalDataEntity selectByFirstName(String firstName) {
        List<UBSPersonalDataEntity> result = selectByField("first_name", firstName);
        return result.size() > 0 ? result.get(0) : null;
    }

    public UBSPersonalDataEntity selectByLastName(String lastName) {
        List<UBSPersonalDataEntity> result = selectByField("last_name", lastName);
        return result.size() > 0 ? result.get(0) : null;
    }

    public UBSPersonalDataEntity selectByPhoneNumber(String phoneNumber) {
        List<UBSPersonalDataEntity> result = selectByField("phone_number", phoneNumber);
        return result.size() > 0 ? result.get(0) : null;
    }

    public UBSPersonalDataEntity selectById(long id) {
        List<UBSPersonalDataEntity> result = selectByField("id", String.valueOf(id));
        return result.size() > 0 ? result.get(0) : null;
    }

    public List<UBSPersonalDataEntity> selectByField(String field, String value) {
        Statement statement = ManagerDao.get().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(UBSPersonalDataEntity.SELECT_BY_FIELD, field, value));
            rows = ManagerDao.get().parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        ManagerDao.closeStatement(statement);
        return UBSPersonalDataEntity.getListUBSPersonalDataEntity(rows);
    }

    public List<UBSPersonalDataEntity> selectAll() {
        Statement statement = ManagerDao.get().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(UBSPersonalDataEntity.SELECT_ALL);
            rows = ManagerDao.get().parseResultSet(resultSet);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        ManagerDao.closeStatement(statement);
        return UBSPersonalDataEntity.getListUBSPersonalDataEntity(rows);
    }

    public void updateFirstNameField(UBSPersonalData ubsPersonalData, UBSPersonalDataEntity ubsPersonalDataEntity) {
        updateFirstNameFieldById(ubsPersonalData.getFirstName(), ubsPersonalDataEntity.getId());
    }

    public void updateLastNameField(UBSPersonalData ubsPersonalData, UBSPersonalDataEntity ubsPersonalDataEntity) {
        updateLastNameFieldById(ubsPersonalData.getLastName(), ubsPersonalDataEntity.getId());
    }

    public void updateEmailField(UBSPersonalData ubsPersonalData, UBSPersonalDataEntity ubsPersonalDataEntity) {
        updateEmailFieldById(ubsPersonalData.getEmail(), ubsPersonalDataEntity.getId());
    }

    public void updatePhoneNumberField(UBSPersonalData ubsPersonalData, UBSPersonalDataEntity ubsPersonalDataEntity) {
        updatePhoneNumberFieldById(ubsPersonalData.getPhoneNumber(), ubsPersonalDataEntity.getId());
    }

    public void updateFirstNameFieldById(String firstName, long id) {
        Statement statement = ManagerDao.get().getStatement();
        try {
            statement.execute(String.format(UBSPersonalDataEntity.UPDATE_FIELD_BY_ID, "first_name", firstName, String.valueOf(id)));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        ManagerDao.closeStatement(statement);
    }

    public void updateLastNameFieldById(String lastName, long id) {
        Statement statement = ManagerDao.get().getStatement();
        try {
            statement.execute(String.format(UBSPersonalDataEntity.UPDATE_FIELD_BY_ID, "last_name", lastName, String.valueOf(id)));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        ManagerDao.closeStatement(statement);
    }

    public void updateEmailFieldById(String email, long id) {
        Statement statement = ManagerDao.get().getStatement();
        try {
            statement.execute(String.format(UBSPersonalDataEntity.UPDATE_FIELD_BY_ID, "email", email, String.valueOf(id)));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        ManagerDao.closeStatement(statement);
    }

    public void updatePhoneNumberFieldById(String phoneNumber, long id) {
        Statement statement = ManagerDao.get().getStatement();
        try {
            statement.execute(String.format(UBSPersonalDataEntity.UPDATE_FIELD_BY_ID, "phone_number", phoneNumber, String.valueOf(id)));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        ManagerDao.closeStatement(statement);
    }

    public void delete(UBSPersonalDataEntity ubsPersonalDataEntity) {
        deleteById(ubsPersonalDataEntity.getId());
    }

    public void deleteById(long id) {
        Statement statement = ManagerDao.get().getStatement();
        try {
            statement.execute(String.format(UBSPersonalDataEntity.DELETE_BY_ID, String.valueOf(id)));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        //
        ManagerDao.closeStatement(statement);
    }
}
