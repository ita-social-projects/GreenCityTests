package com.softserve.edu.greencity.ui.tools.jdbc.services;

import com.softserve.edu.greencity.data.UBS.UBSPersonalData;
import com.softserve.edu.greencity.data.editprofile.EditProfileDataRepository;
import com.softserve.edu.greencity.ui.tools.jdbc.dao.UBSPersonalDataDao;
import com.softserve.edu.greencity.ui.tools.jdbc.entity.UBSPersonalDataEntity;

import java.util.List;

public class UBSPersonalDataService {

    private UBSPersonalDataDao ubsPersonalDataDao;

    public UBSPersonalDataService() {
        ubsPersonalDataDao = new UBSPersonalDataDao();
    }

    public UBSPersonalDataEntity getDataByEmail(String email) {
        return ubsPersonalDataDao.selectByEmail(email);
    }

    public UBSPersonalDataEntity getDataByFirstName(String firstName) {
        return ubsPersonalDataDao.selectByFirstName(firstName);
    }

    public UBSPersonalDataEntity getDataByLastName(String lastName) {
        return ubsPersonalDataDao.selectByLastName(lastName);
    }

    public UBSPersonalDataEntity getDataByPhoneNumber(String phoneNumber) {
        return ubsPersonalDataDao.selectByPhoneNumber(phoneNumber);
    }

    public UBSPersonalDataEntity getDataById(long id) {
        return ubsPersonalDataDao.selectById(id);
    }

    public List<UBSPersonalDataEntity> getAllData() {
        return ubsPersonalDataDao.selectAll();
    }





}
